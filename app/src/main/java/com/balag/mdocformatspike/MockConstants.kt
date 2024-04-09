package com.balag.mdocformatspike

object MockConstants {

    const val mockDecodedMdoc = "{\n" +
            "  \"docType\": \"org.iso.18013.5.1.mDL\",\n" +
            "  \"issuerSigned\": {\n" +
            "    \"nameSpaces\": {\n" +
            "      \"org.iso.18013.5.1\": [\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 1,\n" +
            "            \"random\": h'71b9e64c81edd3fd7bb7e01c1e42996c',\n" +
            "            \"elementIdentifier\": \"issue_date\",\n" +
            "            \"elementValue\": 1004(\"2024-01-12\")\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 2,\n" +
            "            \"random\": h'51c2fcc1549601736a6585deef5d9c13',\n" +
            "            \"elementIdentifier\": \"expiry_date\",\n" +
            "            \"elementValue\": 1004(\"2025-01-12\")\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 3,\n" +
            "            \"random\": h'dcb81876c44e92ab10c3102398f47dc2',\n" +
            "            \"elementIdentifier\": \"family_name\",\n" +
            "            \"elementValue\": \"Silverstone\"\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 4,\n" +
            "            \"random\": h'1eee457bdea02501fe35e380bd2b8974',\n" +
            "            \"elementIdentifier\": \"given_name\",\n" +
            "            \"elementValue\": \"Inga\"\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 5,\n" +
            "            \"random\": h'c8fb86f4dd1fb6f658c5854a193073f4',\n" +
            "            \"elementIdentifier\": \"birth_date\",\n" +
            "            \"elementValue\": 1004(\"1991-11-06\")\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 6,\n" +
            "            \"random\": h'22655e9974500c618a237d0704a72de0',\n" +
            "            \"elementIdentifier\": \"issuing_country\",\n" +
            "            \"elementValue\": \"US\"\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 7,\n" +
            "            \"random\": h'6d6cff820531cad49ac7bfc5a82ce810',\n" +
            "            \"elementIdentifier\": \"document_number\",\n" +
            "            \"elementValue\": \"12345678\"\n" +
            "          }\n" +
            "        >>),\n" +
            "        24(<<\n" +
            "          {\n" +
            "            \"digestID\": 8,\n" +
            "            \"random\": h'5b4b03a0f7594cb29bbf72d05adaeaa0',\n" +
            "            \"elementIdentifier\": \"driving_privileges\",\n" +
            "            \"elementValue\": [\n" +
            "              {\n" +
            "                \"vehicle_category_code\": \"A\",\n" +
            "                \"issue_date\": 1004(\"2023-01-01\"),\n" +
            "                \"expiry_date\": 1004(\"2043-01-01\")\n" +
            "              }\n" +
            "            ]\n" +
            "          }\n" +
            "        >>)\n" +
            "      ]\n" +
            "    },\n" +
            "    \"issuerAuth\": [\n" +
            "      h'a10126',\n" +
            "      {\n" +
            "        33: h'3082015d30820104a0030201020206018c91d9c219300a06082a8648ce3d04030230363134303206035504030c2b4a3146774a50383743362d514e5f5753494f6d4a415163366e3543515f625a6461464a3547446e5731526b301e170d3233313232323134303635365a170d3234313031373134303635365a30363134303206035504030c2b4a3146774a50383743362d514e5f5753494f6d4a415163366e3543515f625a6461464a3547446e5731526b3059301306072a8648ce3d020106082a8648ce3d03010703420004028a5579ba09a58472730d582a49113977c2a4a10a97d63560f1613868f8c5383650eba7c53c52215913f78a85548baed61904a04d8dc5e959a37fb24c550e0c300a06082a8648ce3d040302034700304402206716349c05cbc446578b8cc0aee4d6f124a90db1afe04219174df0e66eaf93de022055acdbb15750bd9825b9cf672584315a5cd74fd8ba7fec2050ac7428261b8f70'\n" +
            "      },\n" +
            "      24(<<\n" +
            "        {\n" +
            "          \"version\": \"1.0\",\n" +
            "          \"digestAlgorithm\": \"SHA-256\",\n" +
            "          \"valueDigests\": {\n" +
            "            \"org.iso.18013.5.1\": {\n" +
            "              1: h'ab92f0509e09cbc333066011eceeb95bfe0d8b14a5dca9269f5a6c9aea1c0997',\n" +
            "              2: h'bbb0fedefafe36b2e0df38a0ba753310a16a600c86e6c03e7dfbe60e3471676e',\n" +
            "              3: h'b63819e1a19161a84eecf0f45f75e8fcb797eba7e1e017fd97e2a32dcef118e8',\n" +
            "              4: h'117fae5f77551db5ba69087523225aa1664f9271b349f71f9496a275ab2680eb',\n" +
            "              5: h'283bd5c8c537e7c2ded27e939156f673405b85b48c270a6cc2d3cb36266b4d1f',\n" +
            "              6: h'056732700269c2ed070c430abbd850d09663f1934e37e1c65b87afebb7555a81',\n" +
            "              7: h'7c011d0dfeb153ed728f9171486e618aba8a2bee8e36322664016d0fce761143',\n" +
            "              8: h'7234f61d8824a319f058ffbd8ea3bfebe0fe77af47f545b6c63a435ab92796f0'\n" +
            "            }\n" +
            "          },\n" +
            "          \"docType\": \"org.iso.18013.5.1.mDL\",\n" +
            "          \"validityInfo\": {\n" +
            "            \"signed\": 0(\"2024-01-12T00:10:05Z\"),\n" +
            "            \"validFrom\": 0(\"2024-01-12T00:10:05Z\"),\n" +
            "            \"validUntil\": 0(\"2025-01-12T00:10:05Z\")\n" +
            "          }\n" +
            "        }\n" +
            "      >>),\n" +
            "      h'717311bd3d345c85963e77dc513fd41f48dab92ef742b9d9aafae087750b23945d65f384835e55fa0953571fc60093d825f5dfaf5c19dfd34314a3855c7c6e96'\n" +
            "    ]\n" +
            "  }\n" +
            "}"
}