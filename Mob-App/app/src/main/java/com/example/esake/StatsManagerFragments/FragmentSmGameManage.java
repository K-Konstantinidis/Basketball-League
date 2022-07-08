package com.example.esake.StatsManagerFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.esake.R;
import com.example.esake.ToggleButtonGroupTableLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentSmGameManage extends Fragment {

	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

	public FragmentSmGameManage() {
		// Required empty public constructor
	}

	public static FragmentSmGameManage newInstance(String param1, String param2) {
		FragmentSmGameManage fragment = new FragmentSmGameManage();
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

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Get the view
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_game_management_stats_manager, null);
		//Get the radio group with the 10 players (5 home + 5 away)
		ToggleButtonGroupTableLayout rdg1 = root.findViewById(R.id.radGroup_player);
		//Get the radio group with the 5 opponents
		ToggleButtonGroupTableLayout radioOpp = root.findViewById(R.id.radGroup_choose_opponent);
		//Show team images
		ImageView homeTeamImg = root.findViewById(R.id.home_team_game_statsmanager);
		ImageView awayTeamImg = root.findViewById(R.id.away_team_game_statsmanager);
		Picasso.with(getContext()).load(mParam1).fit().into(homeTeamImg);
		Picasso.with(getContext()).load(mParam2).fit().into(awayTeamImg);

		String[] homePlayers = new String[]{"Bohoridis", "Avdalas", "Meikon", "Nedovic", "Sant-Ros"};

		String[] awayPlayers = new String[]{"Cowan", "Netzipogloy", "Poyliantis", "Hanlan", "Sxizas"};

		RadioButton rd1 = root.findViewById(R.id.playerHome1);
		RadioButton rd2 = root.findViewById(R.id.playerHome2);
		RadioButton rd3 = root.findViewById(R.id.playerHome3);
		RadioButton rd4 = root.findViewById(R.id.playerHome4);
		RadioButton rd5 = root.findViewById(R.id.playerHome5);
		RadioButton rd6 = root.findViewById(R.id.playerAway1);
		RadioButton rd7 = root.findViewById(R.id.playerAway2);
		RadioButton rd8 = root.findViewById(R.id.playerAway3);
		RadioButton rd9 = root.findViewById(R.id.playerAway4);
		RadioButton rd10 = root.findViewById(R.id.playerAway5);

		ArrayList<RadioButton> radioHomeTeam = new ArrayList<>();

		radioHomeTeam.add(rd1);
		radioHomeTeam.add(rd2);
		radioHomeTeam.add(rd3);
		radioHomeTeam.add(rd4);
		radioHomeTeam.add(rd5);

		RadioButton rdo1 = root.findViewById(R.id.opponent1);
		RadioButton rdo2 = root.findViewById(R.id.opponent2);
		RadioButton rdo3 = root.findViewById(R.id.opponent3);
		RadioButton rdo4 = root.findViewById(R.id.opponent4);
		RadioButton rdo5 = root.findViewById(R.id.opponent5);

		rd1.setText(homePlayers[0]);rd2.setText(homePlayers[1]);
		rd3.setText(homePlayers[2]);rd4.setText(homePlayers[3]);
		rd5.setText(homePlayers[4]);rd6.setText(awayPlayers[0]);
		rd7.setText(awayPlayers[1]);rd8.setText(awayPlayers[2]);
		rd9.setText(awayPlayers[3]);rd10.setText(awayPlayers[4]);

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

				boolean flag = false;

				if(player != null && player.isChecked()) {
					setVisibility(radioOpp, text.equals("STL"));
					for (RadioButton rd : radioHomeTeam) {
						if(player.getText().equals(rd.getText())) {
							flag = true;
							break;
						}
					}
					if(flag){
						rdo1.setText(awayPlayers[0]);
						rdo2.setText(awayPlayers[1]);
						rdo3.setText(awayPlayers[2]);
						rdo4.setText(awayPlayers[3]);
						rdo5.setText(awayPlayers[4]);
					}
					else{
						rdo1.setText(homePlayers[0]);
						rdo2.setText(homePlayers[1]);
						rdo3.setText(homePlayers[2]);
						rdo4.setText(homePlayers[3]);
						rdo5.setText(homePlayers[4]);
					}
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
							if (opponent != null && opponent.isChecked()) {
								Toast.makeText(getContext(), "Ο " + player.getText() + " κλέβει την μπάλα από τον " + opponent.getText(), Toast.LENGTH_SHORT).show();
								player.setChecked(false);
								opponent.setChecked(false);
								radioOpp.setVisibility(View.GONE);
							}
							else
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