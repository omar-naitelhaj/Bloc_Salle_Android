package com.map.scannerhhhh.ui.dashboard;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.map.scannerhhhh.LoginActivity;
import com.map.scannerhhhh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    RequestQueue requestQueue;
    List<DataEntry> data = new ArrayList<>();
    Pie pie;
    AnyChartView anyChartView;

    String urlChart = "http://" + LoginActivity.getIp() + "/dashboard/chart/mobile" ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.activity_graph_activity, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        pie = AnyChart.pie();
        pie.title("Liste des Salles par bloc");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getNames(pie);
            }
        });

        anyChartView = (AnyChartView) root.findViewById(R.id.any_chart_view);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void getNames(Pie pie) {

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlChart , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("array");
                    for ( int i=0 ; i < array.length() ; i++ ) {
                        data.add(new ValueDataEntry(array.getJSONObject(i).getString("bloc"), array.getJSONObject(i).getInt("nbrSalle")));

                    }
                    pie.data(data);
                    anyChartView.setChart(pie);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                for ( int i=0; )

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}