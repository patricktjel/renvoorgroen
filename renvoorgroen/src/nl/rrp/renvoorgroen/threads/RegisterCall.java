package nl.rrp.renvoorgroen.threads;

import java.io.UnsupportedEncodingException;

import nl.rrp.renvoorgroen.Model;
import nl.rrp.renvoorgroen.RegisterActivity;
import nl.rrp.renvoorgroen.TabActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class RegisterCall extends AsyncTask<String, Void, Integer> {

	private Context c;
	public RegisterCall(Context c){
		this.c = c;
	}
	
	private ProgressDialog d;
	
	@Override
	protected void onPreExecute() {
		d = new ProgressDialog(c);
		d.setMessage("Wait for a moment");
		
		d.show();
	}
	
	private String url = "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/user/add";
	@Override
	protected Integer doInBackground(String... arg0) {
		String json = "";
		String id = Model.getInstance().getUserId();
		
		HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost put = new HttpPost(url);
        StringEntity se;
        
        json += "{ \"fitbitid\" : \"" + id + "\",";
        json += "\"wachtwoord\" : \"JeMoeder\",";
        json += "\"inlognaam\" : \"" + arg0[0] + "\",";
        json += "\"naam\" : \""+ arg0[0] + "\"}";
        
		try {
		
			se = new StringEntity(json);
			put.addHeader("Content-type", "application/json");
	        put.setEntity(se);
	        try{

	             HttpResponse response = httpClient.execute(put, localContext);
	             Log.d("server", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
	            return response.getStatusLine().getStatusCode();
	             
	        }
	         catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        

		
		
		
		return 0;
	}
	
	
	@Override
	protected void onPostExecute(Integer result) {
		d.cancel();
		if(result == 201){
	    	c.startActivity(new Intent(c, TabActivity.class));
	    	
		} else {
			SharedPreferences p = c.getSharedPreferences("Groen", Context.MODE_PRIVATE);
			Editor e = p.edit();
			e.clear().apply();
		}
		if (c instanceof RegisterActivity){
    		((RegisterActivity)c).finish();
    	}
	}

}
