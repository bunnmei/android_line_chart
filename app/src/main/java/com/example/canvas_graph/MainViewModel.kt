package com.example.canvas_graph

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var lineChart = mutableStateListOf<Line>()
    var list = mutableStateOf(0)

    fun startPushInt() {
        val range=(10..150)

        Thread{

            while (true) {
                val num = range.random()
                Thread.sleep(1000)
                addPath(num)
//                println("--- ${list.value}")
                list.value = num
//                println("------- ${list.value}")

            }
        }.start()

    }

    private fun addPath(num: Int) {
        if(list.value != 0) {
            val h = 2242f / 160
            if (lineChart.isEmpty()){
                val old_x = 0f
                val old_y = 2242f - (num * h)
                val new_x = 0f
                val new_y = 2242f - (num * h)

                val line = Line(
                    start = Offset(old_x, old_y),
                    end = Offset(new_x, new_y)
                )
                lineChart.add(line)
            } else {
                val old_x = (lineChart.size - 1) * 5f
                val old_y = 2242f - (list.value * h)
                val new_x = (lineChart.size) * 5f
                val new_y = 2242f - (num * h)

                val line = Line(
                    start = Offset(old_x, old_y),
                    end = Offset(new_x, new_y)
                )

                println(line)
                lineChart.add(line)
            }
        }

//            if (lineChart.size != 1){
//                val old_x = (lineChart.size - 1) * 5f
//                val old_y = 2242f - (list.value * h)
//                val new_x = (lineChart.size) * 5f
//                val new_y = 2242f - (num * h)
//
//                val line = Line(
//                    start = Offset(old_x, old_y),
//                    end = Offset(new_x, new_y)
//                )
//
//                println(line)
//                lineChart.add(line)
//            }


    }


}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth:Dp = 1.dp
)