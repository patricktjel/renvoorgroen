package nl.rrp.renvoorgroen.threads;

import java.io.UnsupportedEncodingException;

import nl.rrp.renvoorgroen.Model;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class SendDataToServer extends AsyncTask<JSONObject, Void, Void>
{
	
	private String url = "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/data/put";
	@Override
	protected Void doInBackground(JSONObject... params) {
		JSONObject j = params[0];
		HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPut put = new HttpPut(url);
        StringEntity se;
		try {
			
			
			se = new StringEntity(getElements(j));
			put.addHeader("Content-type", "application/json");
	        put.setEntity(se);
	        try{

	             HttpResponse response = httpClient.execute(put, localContext);
	             Log.d("server", response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
	             
	        }
	         catch (Exception e) {
	            e.printStackTrace();
	        }
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        

        return null;
        

		
	}
	private String getElements(JSONObject j) throws JSONException {
		String res = "{";

		Model model = Model.getInstance();
		j = j.getJSONObject("summary");
		res+= "\"fitbitid\" : \"" + model.getUserId() + "\",";
		res+=  "\"floors\" : " + j.getInt("floors") + ",";
		res+=	"\"steps\": " + j.getInt("steps") +  ",";
		JSONArray ar = j.getJSONArray("distances");
		for(int i = 0; i<ar.length();i++){
			JSONObject o = ar.getJSONObject(i);
			if(o.getString("activity").equals("total")){
				res+=	"\"distance\": "+ o.getString("distance")+  "}";

				model.setDistance(o.getDouble("distance"));
				break;
			}
		}
		Log.d("JSONvoorPat", res);
		model.setFloor(j.getInt("floors"));
		model.setSteps(j.getInt("steps"));
		return res;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		Model.getInstance().change();
	}

}
