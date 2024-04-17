package com.example.chart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class MainActivity : AppCompatActivity() {
    lateinit var chart: LineChart
    val values = ArrayList<Entry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chart = findViewById(R.id.chart)

        //chart.setBackgroundColor()
        chart.description.isEnabled = false //hiện chú thích
        chart.legend.isEnabled = false
        chart.setNoDataText("No data available")
        chart.setTouchEnabled(true)
        //chart.setOnChartValueSelectedListener(this)

        chart.setDrawGridBackground(false)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)


        // chỉnh thông số cho trục dọc
        chart.axisRight.isEnabled=false
        chart.axisLeft.apply {
            axisMinimum = 0f //giá trị thấp nhất
            axisMaximum = 200f// giá trị cao nhất
            setDrawGridLines(true)
        }
        // chỉnh thông số cho trục kẻ ngang
        chart.xAxis.apply {
            axisMinimum = 0f
            axisMaximum = 30f
            isGranularityEnabled = true
            setDrawGridLines(true)
            position = XAxis.XAxisPosition.BOTTOM
        }
        setData()
        chart.animateX(1500)

    }


    private fun setData()
    {   // thêm điểm A(x,y) vào đường kẻ
        values.add(Entry(0f, 80f))
        values.add(Entry(1f, 85f))
        values.add(Entry(2f, 75f))
        values.add(Entry(3f, 80f))
        values.add(Entry(4f, 81f))
        values.add(Entry(5f, 82f))
        values.add(Entry(6f, 79f))
        values.add(Entry(7f, 75f))
        values.add(Entry(8f, 80f))
        values.add(Entry(9f, 81f))


        val set1: LineDataSet
        if (chart.data != null && chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.setDrawIcons(false)

            // Chỉnh màu cho line
            set1.color = Color.WHITE
            //set1.setDrawCircleHole(true)
            set1.setDrawValues(false)// hiển thị giá trị lên biểu đồ
            set1.setCircleColor(Color.GRAY)// có dấu chấm tại giá trị
            set1.setDrawCircles(true)
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.isHighlightEnabled =true
            set1.setDrawHighlightIndicators(false)

            // chỉnh kích thước đường kẻ và kích thước dấu chấm
            set1.lineWidth = 2f
            set1.circleRadius = 3f


            // kích thước và màu của chữ
            set1.valueTextSize = 15f
            set1.valueTextColor = R.color.black


            // Tạo vùng phủ màu dưới đường kẻ
            set1.setDrawFilled(true)
            set1.fillDrawable = getDrawable(R.drawable.bg_line)

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart.data = data
        }
    }

}
