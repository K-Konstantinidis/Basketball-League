package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTop5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTop5 extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTop5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTop5.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTop5 newInstance(String param1, String param2) {
        FragmentTop5 fragment = new FragmentTop5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    ImageView myImage;
//    private String imageUri;
	private Connector top5;
	private ImageView imageTop5;
	private TextView nameTop5, posTop5, ratingTop5, pointsTop5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get the view
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_top5, null);
        //Get the spinner from the xml.
        Spinner dropdown = (Spinner) root.findViewById(R.id.spinner);

        super.onCreate(savedInstanceState);

//
//        imageUri = cbl.lookup("Nissan", "Sunny").getImage();
//        myImage= (ImageView) root.findViewById(R.id.imgTop5);
//        Picasso.with(root.getContext()).load(Uri.parse(imageUri)).resize(300, 0).into(myImage);

        //Picasso.with(getApplicationContext()).load(Uri.parse(imageUri)).resize(300, 0).into(myImage);
        // Picasso resizing will be useful somewhere

		top5 = new Connector(myIP.getIp(), "top5");

		imageTop5 = root.findViewById(R.id.imgTop5);
		nameTop5 = root.findViewById(R.id.nameTop5);
		posTop5 = root.findViewById(R.id.positionTop5);
		ratingTop5 = root.findViewById(R.id.ratingTop5);

//		imageTop5.setImageResource();
		nameTop5.setText(top5.getTop5Name(0));
		posTop5.setText(top5.getTop5Position(0));
		ratingTop5.setText(top5.getTop5Rating(0));

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