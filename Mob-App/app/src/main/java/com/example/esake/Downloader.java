package com.example.esake;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,String> {

	Context c;
	String urlAddress;
	Spinner spinner;
	ProgressDialog pd;

	public Downloader(Context c, String urlAddress, Spinner spinner) {
		this.c = c;
		this.urlAddress = urlAddress;
		this.spinner = spinner;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		pd = new ProgressDialog(c);
		pd.setTitle("Downloading Gameweek Info..");
		pd.setMessage("Retrieving data...");
		pd.show();
	}

	@Override
	protected String doInBackground(Void... params) {
		return this.retrieveData();
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);

		pd.dismiss();

		spinner.setAdapter(null);

		if(s != null)
		{
			Parser parser = new Parser(c,s,spinner);
			parser.execute();
			//Toast.makeText(c,"Data retrieved",Toast.LENGTH_SHORT).show();
			//System.out.println(s);

		}else {
			Toast.makeText(c,"No data retrieved",Toast.LENGTH_SHORT).show();
		}
	}

	private String retrieveData()
	{
		HttpURLConnection con = Connector.connect(urlAddress);
		if(con==null)
		{
			return null;
		}
		try
		{
			InputStream is = con.getInputStream();

			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer receivedData = new StringBuffer();

			while ((line=br.readLine()) != null)
			{
				receivedData.append(line+"n");
			}

			br.close();
			is.close();

			return receivedData.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
