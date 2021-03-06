package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Refer to the https://blog.csdn.net/weixin_44233889/article/details/90722027 to making the result as Pie Chart
 */
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    private LinearLayout lLayoutOut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        lLayoutOut2 = this.findViewById(R.id.ll_two);
        initChartPie();
    }
    /**
     * initialize the chart pie
     */
    private void initChartPie(){
        for (int i = 0; i<getIntent().getIntExtra("NumOfQ",0);i++){
            PieChart pieChart = new PieChart(Result.this);
            PieChart.LayoutParams PLayoutParams = new PieChart.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    800);
            pieChart.setId(View.generateViewId());
            pieChart.setLayoutParams(PLayoutParams);
            lLayoutOut2.addView(pieChart);
            showPieChart(pieChart,getPieChartData(getIntent().getIntExtra(i+"A",0),
                                                  getIntent().getIntExtra(i+"B",0),
                                                  getIntent().getIntExtra(i+"C",0)),"Question "+(i+1));
        }

    }

    /**
     * Figure out the parameters and the percentage of each choices
     * @param A_num the number of option A
     * @param B_num the number of option B
     * @param C_num the number of option C
     * @return a list of pie Entry
     */
    private List<PieEntry> getPieChartData(int A_num,int B_num,int C_num) {
        List<PieEntry> mPie = new ArrayList<>();
        int totalNum = A_num  +  B_num + C_num;
        PieEntry pieEntry1 = new PieEntry(((float) A_num/totalNum) * 100,"A");
        PieEntry pieEntry2 = new PieEntry(((float) B_num/totalNum) * 100,"B");
        PieEntry pieEntry3 = new PieEntry(((float) C_num/totalNum) * 100,"C");
        mPie.add(pieEntry1);
        mPie.add(pieEntry2);
        mPie.add(pieEntry3);
        return mPie;
    }

    /**
     * create the pie chart and configure all the setting of the chart
     * @param pieChart A pie chart
     * @param pieList a list of chart entry
     * @param question the title of the chart
     */
    private void showPieChart(PieChart pieChart, List<PieEntry> pieList, String question) {

        PieDataSet dataSet = new PieDataSet(pieList,"");

        /**
         * The list of the pie colors
         */

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        /**
         *  Description of the pieChart
         */

        Description description = new Description();
        description.setEnabled(true);
        description.setTextSize(15);
        description.setTextColor(Color.RED);
        description.setText(question);
        pieChart.setDescription(description);
        /**
         * Set Transparent
         */

        pieChart.setTransparentCircleRadius(0f);

        /**
         * Set initial Rotation Angle
          */

        pieChart.setRotationAngle(-90);

        /**
         * The line which connected to the pie and the parameter (80f = 0.8)
          */

        dataSet.setValueLinePart1OffsetPercentage(80f);

        /**
         * set the color of the line which connected to the pie and the parameter
          */

        dataSet.setValueLineColor(Color.RED);
        /**
         * set the line which connected to the pie and the parameter outside the pie
          */

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        /**
         *  Set the spacing between the slices
          */

        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        /**
         * Show Legend & spacing of the pie and the parent
         */
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);

        pieChart.setExtraOffsets(26, 5, 26, 5);


        /**
         * Set whether the pieChart chart can be manually rotated
         * Set whether click Item highlighting is available on pieChart chart
         * Set the pieChart chart to display the animation effect, and the animation runs for 2 seconds
         * Set whether pieChart displays only the percentages on the pieChart without the text
         * Whether to draw PieChart internal center text
         * Draw the content value and set the font color size
          */

        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);
        pieChart.setDrawEntryLabels(true);
        pieChart.setDrawCenterText(false);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // Update pieChart view
        pieChart.postInvalidate();
    }

    /**
     * finish all the view and clear it and go back to main activity
     * @param view view
     */
    public void finish_and_back(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
