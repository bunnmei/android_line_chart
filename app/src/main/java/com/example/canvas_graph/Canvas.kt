package com.example.canvas_graph

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativeCanvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.snapshots.SnapshotStateList as SnapshotStateList1

@OptIn(ExperimentalTextApi::class)
@Composable
fun CanvasChart(modifier : Modifier = Modifier) {
    val minu = 30
    val textMeasure = rememberTextMeasurer()
    val memoryPx = minu * 300f + 100f
    val oneMinutesRange = 60f * 5f
    val canvasDp = with(LocalDensity.current) { memoryPx.toDp()}

    val vm:MainViewModel = viewModel()

    SideEffect {
        Log.d("Canvas", "Reconpose Canvas")
    }

    BoxWithConstraints(modifier = modifier.horizontalScroll(rememberScrollState())) {
        val screenHeight = with(LocalDensity.current) { constraints.maxHeight.toDp() }
        Button(onClick = { vm.startPushInt() }) {
            Text("ボタン")
        }
        SideEffect {
            Log.d("Canvas", "Reconpose Canvas")
        }

        Canvas(
            modifier = modifier
                .height(screenHeight)
                .width(canvasDp)
                .zIndex(2f)
        ) {
            val height = size.height
            for (minutes in 1..minu) {
                val start = Offset(x = oneMinutesRange * minutes, y = height)
                val end = Offset(x = oneMinutesRange * minutes, y = height - 20f)
                val minutesToString = (minutes).toString().padStart(2, '0')
//          時間メモリ線描画
                drawLine(
                    color = Color.Black,
                    start = start,
                    end = end,
                    strokeWidth = 2.5f
                )
//          時間テキスト描画
                drawText(
                    textMeasurer = textMeasure,
                    text = minutesToString,
                    style = TextStyle(
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    topLeft = Offset(x = oneMinutesRange * minutes - 17f, y = height - 60f)
                )
//          線グラフの描画
                lineChart(vm.lineChart)
            }
        }
    }
}

fun DrawScope.lineChart(chartList: SnapshotStateList1<Line>){
    println("chat - recompose")
    if (chartList.isNotEmpty()){

        chartList.forEach{ line ->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWidth.toPx()
            )
        }

    }
}


//                    val oneMemTemp = height / 160
//
//                    currentState.value.forEachIndexed { index, temp ->
//                        val x = (index + 1) * 5f
//                        val y = height - ((temp) * oneMemTemp)
//    //          最初の点の位置を移動するため
//                        if (index == 0) {
//                            path.moveTo(x = x, y = y)
//                        }
//                        path.lineTo(x = x, y = y)
//                    }
//
//drawPath(
//path = vm.path.value,
//color = Color.Black,
//style = Stroke(width = 3f),
//)