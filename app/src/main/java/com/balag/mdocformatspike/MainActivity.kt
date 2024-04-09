package com.balag.mdocformatspike

import android.os.Bundle
import android.util.Base64
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.decodeFromByteArray
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.identity.mdoc.mso.StaticAuthDataParser
import com.balag.mdocformatspike.ui.theme.MdocFormatSpikeTheme
import com.balag.mdocmocklibrary.api.RetrofitHelper
import com.balag.mdocmocklibrary.mock.MockApi
import com.balag.mdocmocklibrary.mock.MockVcResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {

    var mockResponse = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdocFormatSpikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

}

@Composable
fun MainScreen( modifier: Modifier = Modifier) {

    var responseText by remember { mutableStateOf("Empty Response") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column {
        Text(
            text = "Spike for mDL format Credential",
            modifier = modifier
        )

//        Button(onClick = {
//            val mockapi = RetrofitHelper().getInstance(context).create(MockApi::class.java)
//
//            scope.launch {
//                val result = mockapi.getMockFormatVc()
//                if (result != null){
//                    Toast.makeText(context, "Hi ${result.body()}", Toast.LENGTH_LONG).show()
//                    responseText = result.body().toString()
//                    Log.d("balaggg--->: ", result.body().toString())
//                }
//
//            }
//        }) {
//            Text(text = "Get Mock Data", fontSize = 24.sp)
//        }
//        Button(onClick = {
//            val mockapi = RetrofitHelper().getInstance(context).create(MockApi::class.java)
//
//            val call = mockapi.getMockMDocFormatVC()
//            call.enqueue(object : Callback<MockVcResponse> {
//                override fun onResponse(call: Call<MockVcResponse>, response: Response<MockVcResponse>) {
//                    if (response.isSuccessful) {
//
////                    responseText = result.body().toString()
//                        val gson = Gson()
//                        val jsonString = gson.toJson(response.body())
//                        Toast.makeText(context, "Hello ${response.body()}", Toast.LENGTH_LONG).show()
//                    Log.d("balaggg--->: ", response.body().toString())
//                    } else {
//                        Log.d("balaggg--->Oops: ", response.body().toString())
//                        Toast.makeText(context, "Oooops ${response.body()}", Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<MockVcResponse>, t: Throwable) {
//                    Log.d("balaggg--->Error: "," response.body().toString()")
//                    Toast.makeText(context, "Failure", Toast.LENGTH_LONG).show()
//                }
//            })
//        }) {
//            Text(text = "Get Mock Data without suspend", fontSize = 24.sp)
//        }
//
//        Text(
//            text = responseText,
//            modifier = modifier
//        )
//    }

        Button(onClick = {


            val base64Url = "omdkb2NUeXBldW9yZy5pc28uMTgwMTMuNS4xLm1ETGxpc3N1ZXJTaWduZWSiam5hbWVTcGFjZXOhcW9yZy5pc28uMTgwMTMuNS4xiNgYWFukaGRpZ2VzdElEAWZyYW5kb21QcbnmTIHt0_17t-AcHkKZbHFlbGVtZW50SWRlbnRpZmllcmppc3N1ZV9kYXRlbGVsZW1lbnRWYWx1ZdkD7GoyMDI0LTAxLTEy2BhYXKRoZGlnZXN0SUQCZnJhbmRvbVBRwvzBVJYBc2plhd7vXZwTcWVsZW1lbnRJZGVudGlmaWVya2V4cGlyeV9kYXRlbGVsZW1lbnRWYWx1ZdkD7GoyMDI1LTAxLTEy2BhYWqRoZGlnZXN0SUQDZnJhbmRvbVDcuBh2xE6SqxDDECOY9H3CcWVsZW1lbnRJZGVudGlmaWVya2ZhbWlseV9uYW1lbGVsZW1lbnRWYWx1ZWtTaWx2ZXJzdG9uZdgYWFKkaGRpZ2VzdElEBGZyYW5kb21QHu5Fe96gJQH-NeOAvSuJdHFlbGVtZW50SWRlbnRpZmllcmpnaXZlbl9uYW1lbGVsZW1lbnRWYWx1ZWRJbmdh2BhYW6RoZGlnZXN0SUQFZnJhbmRvbVDI-4b03R-29ljFhUoZMHP0cWVsZW1lbnRJZGVudGlmaWVyamJpcnRoX2RhdGVsZWxlbWVudFZhbHVl2QPsajE5OTEtMTEtMDbYGFhVpGhkaWdlc3RJRAZmcmFuZG9tUCJlXpl0UAxhiiN9BwSnLeBxZWxlbWVudElkZW50aWZpZXJvaXNzdWluZ19jb3VudHJ5bGVsZW1lbnRWYWx1ZWJVU9gYWFukaGRpZ2VzdElEB2ZyYW5kb21QbWz_ggUxytSax7_FqCzoEHFlbGVtZW50SWRlbnRpZmllcm9kb2N1bWVudF9udW1iZXJsZWxlbWVudFZhbHVlaDEyMzQ1Njc42BhYoqRoZGlnZXN0SUQIZnJhbmRvbVBbSwOg91lMspu_ctBa2uqgcWVsZW1lbnRJZGVudGlmaWVycmRyaXZpbmdfcHJpdmlsZWdlc2xlbGVtZW50VmFsdWWBo3V2ZWhpY2xlX2NhdGVnb3J5X2NvZGVhQWppc3N1ZV9kYXRl2QPsajIwMjMtMDEtMDFrZXhwaXJ5X2RhdGXZA-xqMjA0My0wMS0wMWppc3N1ZXJBdXRohEOhASahGCFZAWEwggFdMIIBBKADAgECAgYBjJHZwhkwCgYIKoZIzj0EAwIwNjE0MDIGA1UEAwwrSjFGd0pQODdDNi1RTl9XU0lPbUpBUWM2bjVDUV9iWmRhRko1R0RuVzFSazAeFw0yMzEyMjIxNDA2NTZaFw0yNDEwMTcxNDA2NTZaMDYxNDAyBgNVBAMMK0oxRndKUDg3QzYtUU5fV1NJT21KQVFjNm41Q1FfYlpkYUZKNUdEblcxUmswWTATBgcqhkjOPQIBBggqhkjOPQMBBwNCAAQCilV5ugmlhHJzDVgqSRE5d8KkoQqX1jVg8WE4aPjFODZQ66fFPFIhWRP3ioVUi67WGQSgTY3F6Vmjf7JMVQ4MMAoGCCqGSM49BAMCA0cAMEQCIGcWNJwFy8RGV4uMwK7k1vEkqQ2xr-BCGRdN8OZur5PeAiBVrNuxV1C9mCW5z2clhDFaXNdP2Lp_7CBQrHQoJhuPcNgYWQHopWd2ZXJzaW9uYzEuMG9kaWdlc3RBbGdvcml0aG1nU0hBLTI1Nmx2YWx1ZURpZ2VzdHOhcW9yZy5pc28uMTgwMTMuNS4xqAFYIKuS8FCeCcvDMwZgEezuuVv-DYsUpdypJp9abJrqHAmXAlggu7D-3vr-NrLg3zigunUzEKFqYAyG5sA-ffvmDjRxZ24DWCC2OBnhoZFhqE7s8PRfdej8t5frp-HgF_2X4qMtzvEY6ARYIBF_rl93VR21umkIdSMiWqFmT5Jxs0n3H5SWonWrJoDrBVggKDvVyMU358Le0n6TkVb2c0BbhbSMJwpswtPLNiZrTR8GWCAFZzJwAmnC7QcMQwq72FDQlmPxk0434cZbh6_rt1VagQdYIHwBHQ3-sVPtco-RcUhuYYq6iivujjYyJmQBbQ_OdhFDCFggcjT2HYgkoxnwWP-9jqO_6-D-d69H9UW2xjpDWrknlvBnZG9jVHlwZXVvcmcuaXNvLjE4MDEzLjUuMS5tRExsdmFsaWRpdHlJbmZvo2ZzaWduZWTAdDIwMjQtMDEtMTJUMDA6MTA6MDVaaXZhbGlkRnJvbcB0MjAyNC0wMS0xMlQwMDoxMDowNVpqdmFsaWRVbnRpbMB0MjAyNS0wMS0xMlQwMDoxMDowNVpYQHFzEb09NFyFlj533FE_1B9I2rku90K52ar64Id1CyOUXWXzhINeVfoJU1cfxgCT2CX1369cGd_TQxSjhVx8bpY";

            val encodedByteArray = Base64.decode(base64Url, Base64.NO_WRAP or Base64.URL_SAFE)
            System.out.println("balaggggg-decodedeData->${encodedByteArray.contentToString()}")

            val parsedData = StaticAuthDataParser(encodedByteArray).parse()
            System.out.println("balaggggg-parsedData->$parsedData")

            // Replace "yourBase64urlString" with your actual data
//            val base64urlString = "omdkb2NUeXBldW9yZy5pc28uMTgwMTMuNS4xLm1ETGxpc3N1ZXJTaWduZWSiam5hbWVTcGFjZXOhcW9yZy5pc28uMTgwMTMuNS4xiNgYWFukaGRpZ2VzdElEAWZyYW5kb21QcbnmTIHt0_17t-AcHkKZbHFlbGVtZW50SWRlbnRpZmllcmppc3N1ZV9kYXRlbGVsZW1lbnRWYWx1ZdkD7GoyMDI0LTAxLTEy2BhYXKRoZGlnZXN0SUQCZnJhbmRvbVBRwvzBVJYBc2plhd7vXZwTcWVsZW1lbnRJZGVudGlmaWVya2V4cGlyeV9kYXRlbGVsZW1lbnRWYWx1ZdkD7GoyMDI1LTAxLTEy2BhYWqRoZGlnZXN0SUQDZnJhbmRvbVDcuBh2xE6SqxDDECOY9H3CcWVsZW1lbnRJZGVudGlmaWVya2ZhbWlseV9uYW1lbGVsZW1lbnRWYWx1ZWtTaWx2ZXJzdG9uZdgYWFKkaGRpZ2VzdElEBGZyYW5kb21QHu5Fe96gJQH-NeOAvSuJdHFlbGVtZW50SWRlbnRpZmllcmpnaXZlbl9uYW1lbGVsZW1lbnRWYWx1ZWRJbmdh2BhYW6RoZGlnZXN0SUQFZnJhbmRvbVDI-4b03R-29ljFhUoZMHP0cWVsZW1lbnRJZGVudGlmaWVyamJpcnRoX2RhdGVsZWxlbWVudFZhbHVl2QPsajE5OTEtMTEtMDbYGFhVpGhkaWdlc3RJRAZmcmFuZG9tUCJlXpl0UAxhiiN9BwSnLeBxZWxlbWVudElkZW50aWZpZXJvaXNzdWluZ19jb3VudHJ5bGVsZW1lbnRWYWx1ZWJVU9gYWFukaGRpZ2VzdElEB2ZyYW5kb21QbWz_ggUxytSax7_FqCzoEHFlbGVtZW50SWRlbnRpZmllcm9kb2N1bWVudF9udW1iZXJsZWxlbWVudFZhbHVlaDEyMzQ1Njc42BhYoqRoZGlnZXN0SUQIZnJhbmRvbVBbSwOg91lMspu_ctBa2uqgcWVsZW1lbnRJZGVudGlmaWVycmRyaXZpbmdfcHJpdmlsZWdlc2xlbGVtZW50VmFsdWWBo3V2ZWhpY2xlX2NhdGVnb3J5X2NvZGVhQWppc3N1ZV9kYXRl2QPsajIwMjMtMDEtMDFrZXhwaXJ5X2RhdGXZA-xqMjA0My0wMS0wMWppc3N1ZXJBdXRohEOhASahGCFZAWEwggFdMIIBBKADAgECAgYBjJHZwhkwCgYIKoZIzj0EAwIwNjE0MDIGA1UEAwwrSjFGd0pQODdDNi1RTl9XU0lPbUpBUWM2bjVDUV9iWmRhRko1R0RuVzFSazAeFw0yMzEyMjIxNDA2NTZaFw0yNDEwMTcxNDA2NTZaMDYxNDAyBgNVBAMMK0oxRndKUDg3QzYtUU5fV1NJT21KQVFjNm41Q1FfYlpkYUZKNUdEblcxUmswWTATBgcqhkjOPQIBBggqhkjOPQMBBwNCAAQCilV5ugmlhHJzDVgqSRE5d8KkoQqX1jVg8WE4aPjFODZQ66fFPFIhWRP3ioVUi67WGQSgTY3F6Vmjf7JMVQ4MMAoGCCqGSM49BAMCA0cAMEQCIGcWNJwFy8RGV4uMwK7k1vEkqQ2xr-BCGRdN8OZur5PeAiBVrNuxV1C9mCW5z2clhDFaXNdP2Lp_7CBQrHQoJhuPcNgYWQHopWd2ZXJzaW9uYzEuMG9kaWdlc3RBbGdvcml0aG1nU0hBLTI1Nmx2YWx1ZURpZ2VzdHOhcW9yZy5pc28uMTgwMTMuNS4xqAFYIKuS8FCeCcvDMwZgEezuuVv-DYsUpdypJp9abJrqHAmXAlggu7D-3vr-NrLg3zigunUzEKFqYAyG5sA-ffvmDjRxZ24DWCC2OBnhoZFhqE7s8PRfdej8t5frp-HgF_2X4qMtzvEY6ARYIBF_rl93VR21umkIdSMiWqFmT5Jxs0n3H5SWonWrJoDrBVggKDvVyMU358Le0n6TkVb2c0BbhbSMJwpswtPLNiZrTR8GWCAFZzJwAmnC7QcMQwq72FDQlmPxk0434cZbh6_rt1VagQdYIHwBHQ3-sVPtco-RcUhuYYq6iivujjYyJmQBbQ_OdhFDCFggcjT2HYgkoxnwWP-9jqO_6-D-d69H9UW2xjpDWrknlvBnZG9jVHlwZXVvcmcuaXNvLjE4MDEzLjUuMS5tRExsdmFsaWRpdHlJbmZvo2ZzaWduZWTAdDIwMjQtMDEtMTJUMDA6MTA6MDVaaXZhbGlkRnJvbcB0MjAyNC0wMS0xMlQwMDoxMDowNVpqdmFsaWRVbnRpbMB0MjAyNS0wMS0xMlQwMDoxMDowNVpYQHFzEb09NFyFlj533FE_1B9I2rku90K52ar64Id1CyOUXWXzhINeVfoJU1cfxgCT2CX1369cGd_TQxSjhVx8bpY";
//          //  val base64urlString = "yourBase64urlString"
//
//// Decode Base64url (resulting in encoded CBOR)
//            val encodedCborByteArray: ByteArray = Base64.decode(base64urlString, Base64.URL_SAFE)
//
//// Decode CBOR (assuming it represents a map)
//            val decodedMap: Map<String, String> = decodeFromByteArray<Map<String, String>>(encodedCborByteArray)
//
//// Access decoded CBOR data
//            println(decodedMap)


        }) {
            Text(text = "Test Identity Credential", fontSize = 24.sp)
        }

        Text(
            text = responseText,
            modifier = modifier
        )
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MdocFormatSpikeTheme {
        MainScreen()
    }
}

