package com.example.smartcare;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    View view;
    Date dayShow[] = new Date[4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);

        TextView statusText = (TextView) view.findViewById(R.id.tvBMIResult);
        statusText.setTextColor(Color.GREEN);

        // Set graph view android
        Calendar calendar = Calendar.getInstance();
        for(int i=0; i<4; i++){
            dayShow[i] = calendar.getTime();
            calendar.add(Calendar.DATE, -1);
        }

        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> seriesData = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(dayShow[3], 1650),
                new DataPoint(dayShow[2], 1650),
                new DataPoint(dayShow[1], 1400),
                new DataPoint(dayShow[0], 1540)
        });
        seriesData.setDrawDataPoints(true);

        LineGraphSeries<DataPoint> seriesLine = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(dayShow[3], 1500),
                new DataPoint(dayShow[2], 1500),
                new DataPoint(dayShow[1], 1500),
                new DataPoint(dayShow[0], 1500),
        });
        seriesLine.setColor(Color.RED);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getGridLabelRenderer().setHumanRounding(false);

        graph.getViewport().setMinY(0);
        graph.getViewport().setMinX(dayShow[dayShow.length-1].getTime());
        graph.getViewport().setMaxX(dayShow[0].getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.addSeries(seriesLine);
        graph.addSeries(seriesData);

        return view;
    }
}
