package com.balag.mdocformatspike

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.balag.mdocformatspike.ui.theme.MdocFormatSpikeTheme
import com.balag.mdocmocklibrary.api.RetrofitHelper
import com.balag.mdocmocklibrary.mock.MockApi
import com.balag.mdocmocklibrary.parse.CborUtils
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

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

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var responseText by remember { mutableStateOf("") }
    var base64URL by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Spike for mdl/mDL Format",
            modifier = modifier.padding(16.dp)
        )

        Button(modifier = Modifier.padding(16.dp),onClick = {
            val mockapi = RetrofitHelper().getInstance(context).create(MockApi::class.java)

            scope.launch {
                val result = mockapi.getMockFormatVc()
                if (result != null){
                    Toast.makeText(context, "API call is success ${result.body()}", Toast.LENGTH_LONG).show()
                    responseText = "Base64 url received from API------->"+result.body()?.credential.toString()
                    base64URL = result.body()?.credential.toString()
                } else {
                    Toast.makeText(context, "API call failed}", Toast.LENGTH_LONG).show()
                }

            }
        }) {
            Text(text = "API call to Issuer", fontSize = 24.sp)
        }

        Button(modifier = Modifier.padding(16.dp),onClick = {
            val result = parseMockData(context,base64URL)
            responseText = "Parsed Data---->$result"
        }) {
            Text(text = "Decode and Parse", fontSize = 24.sp)
        }

        Text(
            text = responseText,
            modifier = modifier
        )
    }

}


fun parseMockData(context: Context, base64EncodedUrl: String): String {

    val decodedData: ByteArray = Base64.decode(base64EncodedUrl, Base64.URL_SAFE)

    val parsedData = CborUtils.extractIssuerNamespacedElements(decodedData)
    System.out.println("balaggggg-cbrData in Map------->$parsedData")


    val parsedJson = CborUtils.parseCborWithJsonResponse(decodedData)
    System.out.println("balaggggg-cbrData in JSON------->$parsedJson")
    Toast.makeText(context, "Parsing is Done", Toast.LENGTH_LONG).show()
    return  parsedJson.toString()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MdocFormatSpikeTheme {
        MainScreen()
    }
}

