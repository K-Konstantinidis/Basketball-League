package com.example.esake;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connector {

	public static HttpURLConnection connect(String urlAddress)
	{
		try
		{
			URL url=new URL(urlAddress);
			HttpURLConnection con= (HttpURLConnection) url.openConnection();

			//PROPERTIES
			con.setRequestMethod("POST");
			con.setConnectTimeout(20000);
			con.setReadTimeout(20000);
			con.setDoInput(true);
			con.setDoOutput(true);

			//RETURN
			return con;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
