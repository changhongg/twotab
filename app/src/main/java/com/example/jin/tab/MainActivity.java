package com.example.jin.tab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    private CustomGauge gauge1;

    int i;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTabs();

        //#2 Bind java and xml

        listView = (ListView) findViewById(R.id.list);

        //#3 Defined Array values to show in ListView

        ArrayList<String> listItems = new ArrayList<String>();

        //#4 Adding items to arrayList

        listItems.add("Item 1");
        listItems.add("Item 2");
        listItems.add("Item 3");


        //#5 create ArrayAdapter with listItems

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItems);

        //#6 Assign adapter to ListView, show the items in the listView
        listView.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        gauge1 = findViewById(R.id.gauge1);

        text1  = findViewById(R.id.textView1);
        text1.setText(String.valueOf(gauge1.getValue()));

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        for (i=0;i<301;i++) {
                            if (i == 50) {

                            }
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        gauge1.setValue(i*1);
                                        text1.setText(String.valueOf(gauge1.getValue()));

                                    }
                                });
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });


// super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_data_storage);
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        ArrayList value = new ArrayList();

        value.add(new Entry(45f, 0));
        value.add(new Entry(10f, 1));
        value.add(new Entry(33f, 2));
        value.add(new Entry(24f, 3));
        value.add(new Entry(69f, 4));
        value.add(new Entry(14f, 5));
        value.add(new Entry(50f, 6));

        PieDataSet dataSet = new PieDataSet(value, "Earn");


        ArrayList month = new ArrayList();

        month.add("Jan");
        month.add("Feb");
        month.add("Mar");
        month.add("Apr");
        month.add("May");
        month.add("Jun");
        month.add("Jul");

        PieData data1 = new PieData(month, dataSet);
        dataSet.setValueTextSize(15);
        pieChart.setData(data1);
        pieChart.setDescription("Pie Chart");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(2000, 2000);


    }





    private void setupTabs() {

        TabHost tabs = (TabHost) this.findViewById(R.id.DStabhost);
        tabs.setup();


        //Tab #1 SharedPreferences Example
        TabHost.TabSpec ts1 = tabs.newTabSpec("SharedPreferences");
        ts1.setIndicator("SharedPref");
        ts1.setContent(R.id.content1);
        tabs.addTab(ts1);

        //Tab #2 SQLiteDatabase Example
        TabHost.TabSpec ts2 = tabs.newTabSpec("Chart");
        ts2.setIndicator("Chart");
        ts2.setContent(R.id.content2);
        tabs.addTab(ts2);
    }


}