package nl.rrp.renvoorgroen;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Model {

	private final String CONSUMER = "8a2971d28d0f42eb85772988cf2d9796";
	

	private final String CONSUMER_SECRET = "758cf173fac54467832191a2aed23ff2";
	
	private String accesToken;
	private String accesTokenS;
	private String UserId;
	
	private static Model model;
	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
	
	public void init(Context c){
		SharedPreferences p = c.getSharedPreferences("Groen", Context.MODE_PRIVATE);
		accesToken = p.getString("accesToken","");
		accesTokenS = p.getString("accesTokenS","");
		UserId = p.getString("UserID","");
		
		Log.d("P", accesTokenS);
		Log.d("a", accesToken);
	}
	
	public String getCONSUMER() {
		return CONSUMER;
	}

	public String getCONSUMER_SECRET() {
		return CONSUMER_SECRET;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public String getAccesTokenS() {
		return accesTokenS;
	}

	public String getUserId() {
		return UserId;
	}

	
	
}
