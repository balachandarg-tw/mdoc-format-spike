/*
 *  Copyright 2023 Google LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.balag.mdocformatspike

import MDLVcResponse
import co.nstant.`in`.cbor.CborDecoder
import co.nstant.`in`.cbor.model.DataItem
import co.nstant.`in`.cbor.model.MajorType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import java.io.ByteArrayInputStream
import kotlin.collections.Map
import kotlin.collections.set
import co.nstant.`in`.cbor.model.Array as CborArray
import co.nstant.`in`.cbor.model.ByteString as CborByteString
import co.nstant.`in`.cbor.model.Map as CborMap
import co.nstant.`in`.cbor.model.UnicodeString as CborUnicodeString

class CborUtils {

    companion object {

        fun extractIssuerNamespacedElements(cborBytes: ByteArray): Map<String, String> {
            val result = mutableMapOf<String, String>()

            val cbors = CborDecoder(ByteArrayInputStream(cborBytes)).decode()

            val elements =
                cbors[0]["issuerSigned"]["nameSpaces"]["org.iso.18013.5.1"] as CborArray

            for (item in elements.dataItems) {
                val decoded =
                    CborDecoder(ByteArrayInputStream((item as CborByteString).bytes)).decode()

                val identifier = decoded[0]["elementIdentifier"].toString()
                val value = decoded[0]["elementValue"]

                if (value.majorType == MajorType.BYTE_STRING) {
                    result[identifier] = "<bytes>";
                } else {
                    result[identifier] = value.toString()
                }
            }

            return result
        }

        fun parseCborWithJsonResponse(cborBytes: ByteArray): JsonObject {
            val jsonObjectResult = buildJsonObject {
                //val result = mutableMapOf<String, String>()

                val cbors = CborDecoder(ByteArrayInputStream(cborBytes)).decode()

                val elements =
                    cbors[0]["issuerSigned"]["nameSpaces"]["org.iso.18013.5.1"] as CborArray

                for (item in elements.dataItems) {
                    val decoded =
                        CborDecoder(ByteArrayInputStream((item as CborByteString).bytes)).decode()

                    val identifier = decoded[0]["elementIdentifier"].toString()
                    val value = decoded[0]["elementValue"]

                    when (value.majorType) {
                        MajorType.UNICODE_STRING -> put(identifier, value.toString())
                        MajorType.ARRAY -> {
                            val dpJsonObject = buildJsonObject {
                                val drivingPrivileges = value.get(0)
                                System.out.println("balaggg-issue_date->: ${drivingPrivileges["issue_date"]}")
                                put("issue_date", drivingPrivileges["issue_date"].toString())
                                put("expiry_date", drivingPrivileges["expiry_date"].toString())
                                put("vehicle_category_code", drivingPrivileges["vehicle_category_code"].toString())

                            }
                            putJsonArray(identifier) {
                                add(dpJsonObject)
                            }
                        }
                        else -> {
                            put(identifier, value.toString())
                        }
                    }
                }
            }

            val vcJsonObject = Json.decodeFromString<MDLVcResponse>(jsonObjectResult.toString())


            System.out.println("Json Object-family_name-->: ${vcJsonObject.familyName}")
            System.out.println("Json Object-driving-Vehicle category code->: ${vcJsonObject.drivingPrivileges.get(0).vehicleCategoryCode}")
            System.out.println("Json Object-expiry-->: ${vcJsonObject.drivingPrivileges.get(0).expiryDateDP}")

            return jsonObjectResult
        }
    }



}

operator fun DataItem.get(name: String): DataItem {
    check(this.majorType == MajorType.MAP)
    this as CborMap
    return this.get(CborUnicodeString(name))
}

operator fun DataItem.get(index: Int): DataItem {
    check(this.majorType == MajorType.ARRAY)
    this as CborArray
    return this.dataItems[index]
}