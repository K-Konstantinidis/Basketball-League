package com.example.esake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentGameManagementStatsManager extends Fragment {

	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;

	public FragmentGameManagementStatsManager() {
		// Required empty public constructor
	}

	public static FragmentGameManagementStatsManager newInstance(String param1, String param2) {
		FragmentGameManagementStatsManager fragment = new FragmentGameManagementStatsManager();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Get the view
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_game_management_stats_manager, null);
		//Get the radio group with the 10 players (5 home + 5 away)
		ToggleButtonGroupTableLayout rdg1 = root.findViewById(R.id.radGroup_player);
		//Get the radio group with the 5 opponents
		ToggleButtonGroupTableLayout radioOpp = root.findViewById(R.id.radGroup_choose_opponent);
		ArrayList<Button> buttonsList = new ArrayList<>();

		buttonsList.add(root.findViewById(R.id.button_2points));
		buttonsList.add(root.findViewById(R.id.button_2pointsMissed));
		buttonsList.add(root.findViewById(R.id.button_3points));
		buttonsList.add(root.findViewById(R.id.button_3pointsMissed));
		buttonsList.add(root.findViewById(R.id.button_freeThrow));
		buttonsList.add(root.findViewById(R.id.button_freeThrowMissed));
		buttonsList.add(root.findViewById(R.id.button_assist));
		buttonsList.add(root.findViewById(R.id.button_foul));
		buttonsList.add(root.findViewById(R.id.button_turnover));
		buttonsList.add(root.findViewById(R.id.button_rebound));
		buttonsList.add(root.findViewById(R.id.button_reboundDefensive));
		buttonsList.add(root.findViewById(R.id.button_block));
		buttonsList.add(root.findViewById(R.id.button_steal));

		for(Button b : buttonsList) {
			b.setOnClickListener(view -> {
				String text = (String) b.getText();
				RadioButton player = root.findViewById(rdg1.getCheckedRadioButtonId());
				setVisibility(radioOpp, text.equals("STL"));

				if(player.isChecked()) {
					switch (text) {
						case "+2 \nPTS":
							Toast.makeText(getContext(), "Ο " + player.getText() + " ευστοχεί σε σουτ 2 πόντων", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "2 PTS Miss":
							Toast.makeText(getContext(), "Ο " + player.getText() + " αστοχεί σε σουτ 2 πόντων", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "+3 \nPTS":
							Toast.makeText(getContext(), "Ο " + player.getText() + " ευστοχεί σε σουτ 3 πόντων", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "3 PTS Miss":
							Toast.makeText(getContext(), "Ο " + player.getText() + " αστοχεί σε σουτ 3 πόντων", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "Free Throw":
							Toast.makeText(getContext(), "Ο " + player.getText() + " βάζει μία ελεύθερη βολή", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "FT Miss":
							Toast.makeText(getContext(), "Ο " + player.getText() + " χάνει μία ελεύθερη βολή", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "AST":
							Toast.makeText(getContext(), "Η ασσίστ ανήκει στον " + player.getText(), Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "Foul":
							Toast.makeText(getContext(), "Υπάρχει σφύριγμα για φάουλ που χρεώνεται στον " + player.getText(), Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "TO":
							Toast.makeText(getContext(), "Ο " + player.getText() + " υποπίπτει σε λάθος και χάνει την κατοχή της μπάλας", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "OFFENSIVE REB":
							Toast.makeText(getContext(), "Ο " + player.getText() + " μαζεύει το επιθετικό ριμπάουντ", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "DEFENSIVE REB":
							Toast.makeText(getContext(), "Ο " + player.getText() + " μαζεύει το αμυντικό ριμπάουντ", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "BLK":
							Toast.makeText(getContext(), "Ο " + player.getText() + " κόβει τον αντίπαλό του", Toast.LENGTH_SHORT).show();
							player.setChecked(false);
							break;
						case "STL":
							RadioButton opponent = root.findViewById(radioOpp.getCheckedRadioButtonId());
							if (opponent != null) {
								Toast.makeText(getContext(), "Ο " + player.getText() + " κλέβει την μπάλα από τον " + opponent.getText(), Toast.LENGTH_SHORT).show();
								player.setChecked(false);
								opponent.setChecked(false);
							} else
								Toast.makeText(getContext(), "You must select an opponent before the action", Toast.LENGTH_SHORT).show();
							break;
					}
				} else
					selectPlayer();
			});
		}
		return root;
	}

	private void setVisibility(ToggleButtonGroupTableLayout radioOpp, boolean flag) {
		if(flag)
			radioOpp.setVisibility(View.VISIBLE);
		else
			radioOpp.setVisibility(View.GONE);
	}

	private void selectPlayer() {
		Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
	}
}