package nl.rrp.renvoorgroen.threads;

import java.io.InputStream;

import nl.rrp.renvoorgroen.Achievement;
import nl.rrp.renvoorgroen.Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.backup.RestoreObserver;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadAchievements extends AsyncTask<Void, Void, Void> {

	String url = "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/achievement/get";
	@Override
	protected Void doInBackground(Void... params) {
		  HttpClient httpclient = new DefaultHttpClient();

	        // Prepare a request object
	        HttpGet httpget = new HttpGet(url); 

	        // Execute the request
	        HttpResponse response;
	        try {
	            response = httpclient.execute(httpget);
	            // Examine the response status
	           
	            // Get hold of the response entity
	            HttpEntity entity = response.getEntity();
	            // If the response does not enclose an entity, there is no need
	            // to worry about connection release
	        	Log.d("AchivementJSON", response.getStatusLine().getReasonPhrase() + " " + response.getStatusLine().getStatusCode());
	            if(response.getStatusLine().getStatusCode() == 200){
	            	ResponseHandler<String> handler = new BasicResponseHandler();
	            	String result = handler.handleResponse(response);
	            	Log.d("AchivementJSON", result);
	            	Model model = Model.getInstance();
	            	JSONArray ar = new JSONArray(result);
	            	for(int i = 0; i<ar.length();i++){
	            		try {
	            			model.addArchivement(new Achievement(ar.getJSONObject(i)));
						} catch (Exception e) {
							e.printStackTrace();
						}
	            	}
	            	
	            }
	             
          
	        } catch (Exception e){
	        	
	        }
	            
	        return null;
	}

	
	
}
