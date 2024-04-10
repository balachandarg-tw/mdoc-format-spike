package com.balag.mdocmocklibrary.mock

import com.google.gson.annotations.SerializedName


data class MdlCredentialResponse(

    @SerializedName("credential")
    val credential: String,
)


data class MockVcResponse(

    @SerializedName("format")
    val format: String,

    @SerializedName("doctype")
    val docType: String,

    @SerializedName("claims")
    val claims: Claims,

    @SerializedName("proof")
    val proof: Proof
)

data class Proof(
    @SerializedName("proof_type")
    val proofType: String,

    @SerializedName("jwt")
    val jwt: String
)


data class Claim(
    @SerializedName("given_name")
    val givenName: DisplayList,

    @SerializedName("family_name")
    val familyName: DisplayList,

    @SerializedName("birth_date")
    val birthDate: BirthDate,
)

data class DisplayList(
    @SerializedName("display")
    val display: List<Display>
)

data class Display(
    @SerializedName("name")
    val name: String,

    @SerializedName("locale")
    val locale: String)

data class BirthDate(
    @SerializedName("mandatory")
    val mandatory: Boolean
)


data class Claims(
    @SerializedName("org.iso.18013.5.1")
    val idStandard: Claim,
)