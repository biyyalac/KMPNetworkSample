package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a6.daysPhrase
import com.example.a6.platform
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box (Modifier.padding(innerPadding)){
                        App(

                        )
                    }
                }
            }
        }
    }
}
@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    MaterialTheme {
        val greetings by mainViewModel.greetingList.collectAsStateWithLifecycle()

        Column {
            Text("Rocket Launch Details", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(10.dp))
            Divider()
            Box(modifier = Modifier.fillMaxWidth().padding(12.dp)){
                Button(onClick = {
                    mainViewModel. callAPI()
                }) {
                    Text("Get Data")

                }
            }

            LazyColumn(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(greetings){
                    var color=  Color.Black
                    if(it.contains("true"))
                     color=  Color.Blue
                    else
                     color=  Color.Red

                    Text(it,style = TextStyle(fontSize = 20.sp, color = color), modifier = Modifier.padding(10.dp))
                    Divider()
                }

            }
        }
    }
}


@Composable
fun Greetin(name: String, modifier: Modifier = Modifier) {
     val s= platform()

    Text(
        text = "Hello See the List of Rocket Launches Names",
        modifier = modifier
    )
}

@Preview(showBackground = true,
    device = "spec:width=1080px,height=2340px,dpi=440,cutout=tall",
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greetin("Android")
    }
}