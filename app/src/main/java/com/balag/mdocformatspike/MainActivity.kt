package com.balag.mdocformatspike

import android.os.Bundle
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.balag.mdocformatspike.ui.theme.MdocFormatSpikeTheme
import com.balag.mdocmocklibrary.api.RetrofitHelper
import com.balag.mdocmocklibrary.mock.MockApi
import kotlinx.coroutines.launch
import androidx.compose.runtime.*


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

        Button(onClick = {
            val mockapi = RetrofitHelper().getInstance(context).create(MockApi::class.java)

            scope.launch {
                val result = mockapi.getMockFormatVc()
                if (result != null){
                    Toast.makeText(context, "Hi ${result.body()}", Toast.LENGTH_LONG).show()
                    responseText = result.body().toString()
                    Log.d("balaggg--->: ", result.body().toString())
                }

            }
        }) {
            Text(text = "Get Mock Data", fontSize = 24.sp)
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

