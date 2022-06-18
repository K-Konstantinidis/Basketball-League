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

public class FragmentGameManagementStatsManager extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;
    private boolean flag;

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
        //Get the buttons
        Button btn2pts = root.findViewById(R.id.button_2points);
        Button btn2ptsM = root.findViewById(R.id.button_2pointsMissed);
        Button btn3pts = root.findViewById(R.id.button_3points);
        Button btn3ptsM = root.findViewById(R.id.button_3pointsMissed);
        Button btnFT = root.findViewById(R.id.button_freeThrow);
        Button btnFTM = root.findViewById(R.id.button_freeThrowMissed);
        Button btnAssist = root.findViewById(R.id.button_assist);
        Button btnFoul = root.findViewById(R.id.button_foul);
		Button btnTurnOver = root.findViewById(R.id.button_turnover);
        Button btnReboundAtt = root.findViewById(R.id.button_rebound);
		Button btnReboundDef = root.findViewById(R.id.button_reboundDefensive);
        Button btnBlock = root.findViewById(R.id.button_block);
        Button btnSteal = root.findViewById(R.id.button_steal);
        //Get the radio group with the 5 opponents
        ToggleButtonGroupTableLayout radioOpp = root.findViewById(R.id.radGroup_choose_opponent);
        flag = false;

        btn2pts.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " ευστοχεί σε σουτ 2 πόντων", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btn2ptsM.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " αστοχεί σε σουτ 2 πόντων", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btn3pts.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " ευστοχεί σε σουτ 3 πόντων", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
		});

        btn3ptsM.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " αστοχεί σε σουτ 3 πόντων", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btnFT.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " βάζει μία ελεύθερη βολή", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btnFTM.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " χάνει μία ελεύθερη βολή", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btnAssist.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Η ασσίστ ανήκει στον " + player.getText(), Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btnFoul.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Υπάρχει σφύριγμα για φάουλ που χρεώνεται στον " + player.getText(), Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

		btnTurnOver.setOnClickListener(view -> {
			if(flag)
				radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " υποπίπτει σε λάθος και χάνει την κατοχή της μπάλας", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
		});

		btnReboundAtt.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " μαζεύει το επιθετικό ριμπάουντ", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

		btnReboundDef.setOnClickListener(view -> {
			if(flag)
				radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " μαζεύει το αμυντικό ριμπάουντ", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
		});

        btnBlock.setOnClickListener(view -> {
            if(flag)
                radioOpp.setVisibility(View.GONE);

			RadioButton player = getPlayer(rdg1, root);
			if(player != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " κόβει τον αντίπαλό του", Toast.LENGTH_SHORT).show();
				player.setChecked(false);
			}
			else
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
        });

        btnSteal.setOnClickListener(view -> {
            radioOpp.setVisibility(View.VISIBLE);
            flag = true;

			RadioButton player = getPlayer(rdg1, root);
			RadioButton opponent = getPlayer(radioOpp, root);

			if(player != null && opponent != null) {
				Toast.makeText(getContext(), "Ο " + player.getText() + " κλέβει την μπάλα από τον " + opponent.getText(), Toast.LENGTH_SHORT).show();
				player.setChecked(false);
				opponent.setChecked(false);
			}
			else if(player == null)
				Toast.makeText(getContext(), "You must select a player before the action", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getContext(), "You must select an opponent before the action", Toast.LENGTH_SHORT).show();
		});

        return root;
    }

    public RadioButton getPlayer(ToggleButtonGroupTableLayout rdg, ViewGroup root){
		return root.findViewById(rdg.getCheckedRadioButtonId());
	}
}