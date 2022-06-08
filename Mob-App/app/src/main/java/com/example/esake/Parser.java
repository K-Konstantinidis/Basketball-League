package com.example.esake;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Void,Integer> {

	Context c;
	String dataString;
	ArrayList roundList = new ArrayList();
	Spinner spinner;

	public Parser(Context c, String data,Spinner spinner) {
		this.c = c;
		this.dataString = data;
		this.spinner = spinner;
	}

	@Override
	protected Integer doInBackground(Void... params) {
		return this.parse();
	}

	@Override
	protected void onPostExecute(Integer integer) {
		super.onPostExecute(integer);

		if(integer==1)
		{
			ArrayAdapter adapter=new ArrayAdapter(c,android.R.layout.simple_list_item_1, roundList);
			spinner.setAdapter(adapter);
			//Toast.makeText(c,"Parsed Successfully",Toast.LENGTH_SHORT).show();

		}else {
			Toast.makeText(c,"Unable To Parse Data",Toast.LENGTH_SHORT).show();
		}
	}

	private int parse()
	{
		try
		{
			JSONObject jObj = new JSONObject(dataString);
			JSONArray jsonArray = jObj.getJSONArray("rounds");
			for (int i=0; i<jsonArray.length(); i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				String roundId = obj.getString("round_id");
				System.out.println(roundId);
				roundList.add("Gameweek "+ roundId);
			}

			return 1;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
