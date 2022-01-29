package com.map.scannerhhhh.ui.occupation.sallesParBloc;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.map.scannerhhhh.LoginActivity;
import com.map.scannerhhhh.R;
import com.map.scannerhhhh.adapter.SalleBlocRecyclerViewAdapter;
import com.map.scannerhhhh.model.Bloc;
import com.map.scannerhhhh.model.Salle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SallesParBlocFragment extends Fragment {

    RequestQueue requestQueue;
    private RecyclerView recyclerViewSalles;
    private ArrayList<Salle> salles = new ArrayList<Salle>();
    String urlAfficheSalles = "http://" + LoginActivity.getIp() + "/occupation/occupe/";
    String bloc = "";

    public SallesParBlocFragment (String bloc){
        this.bloc = bloc;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salles_par_bloc, container, false);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        getSallesParBlocs();

        recyclerViewSalles = view.findViewById(R.id.recyclerViewSalles);



        return view;
    }

    private void getSallesParBlocs () {

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, urlAfficheSalles + bloc , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    salles = new ArrayList<Salle>();
                    JSONArray array = response.getJSONArray("array");
                    for ( int i=0 ; i < array.length() ; i++ ) {
                        Salle salle = new Salle(array.getJSONObject(i).getString("name"),
                                array.getJSONObject(i).getString("type"),
                                new Bloc(array.getJSONObject(i).getString("bloc")),
                                array.getJSONObject(i).getInt("occupied")
                        );
                        salles.add(salle);
                    }

                    SalleBlocRecyclerViewAdapter adapter = new SalleBlocRecyclerViewAdapter(getContext(), salles);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerViewSalles.setLayoutManager(layoutManager);
                    recyclerViewSalles.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewSalles.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                for ( int i=0; )

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.networkResponse);

            }
        });
        requestQueue.add(jsonArrayRequest);
        Log.d("HIBAHIBA", "run: 5");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("gggg", "onActivityResult: ");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}