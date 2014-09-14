package com.bewareofraj.itec4550currencyconverter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class RetrieveRateTask extends AsyncTask<String, Void, Float>{

	@Override
	protected Float doInBackground(String... currencies) {
		Float rate = 0f;
		String from = currencies[0];
		String to = currencies[1];
		StringBuilder urlStringBuilder = new StringBuilder("http://rate-exchange.appspot.com/currency?from=");
		urlStringBuilder.append(from);
		urlStringBuilder.append("&to=");
		urlStringBuilder.append(to);
		try {
			URL url = new URL(urlStringBuilder.toString());
			
			InputStream is = url.openStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		
    		StringBuilder jsonStringBuilder = new StringBuilder();
    		String line;
    		while ((line = br.readLine()) != null)
    			jsonStringBuilder.append(line);

    		br.close();
    		is.close();
    		
    		rate = getRateFromJSON(jsonStringBuilder.toString());
    		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rate;
	}

	private Float getRateFromJSON(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		return Float.parseFloat(obj.getString("rate"));
	}

}
