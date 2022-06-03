package com.example.esake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentTop5 extends Fragment implements AdapterView.OnItemSelectedListener{

    private final String myIP = "192.168.1.8";

    public FragmentTop5() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get the view
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_top5, null);
        //Get the spinner from the xml.
        Spinner dropdown = (Spinner) root.findViewById(R.id.spinner);
        //Create a list of items for the spinner.
        String[] items = new String[]{"Week 1", "Week 2", "Week 3", "etc"};
        //Create an adapter to describe how the items are displayed.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(root.getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Show selected spinner item
        Toast.makeText(parent.getContext(), "You selected: ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}