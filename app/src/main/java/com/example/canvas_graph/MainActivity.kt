package com.example.canvas_graph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvas_graph.ui.theme.Canvas_GraphTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Canvas_GraphTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

//    Column(modifier = modifier.background(vm.backgroundColor)) {
//        Row(modifier = modifier.height(50.dp)) {
//
//            Button(onClick = { vm.startPushInt() }) {
//                Text(text = "")
//            }
//
//            Text(
//                text = "Hello $name!",
//                modifier = modifier
//            )
//            if (vm.lineChart.isNotEmpty()){
//                vm.lineChart.forEach {
//                    Text(text = it.toString())
//                }
//            }
//        }
//    }
     Box(
         modifier = modifier.fillMaxSize()
     ){
        Column {
            StatusPanel(str = "00:00")
            StatusPanel(str = "238")
        }
        CanvasChart()
     }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Canvas_GraphTheme {
        Greeting("Android")
    }
}