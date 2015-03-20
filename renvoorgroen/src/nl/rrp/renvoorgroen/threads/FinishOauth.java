package nl.rrp.renvoorgroen.threads;

import com.temboo.Library.Fitbit.OAuth.FinalizeOAuth;
import com.temboo.Library.Fitbit.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Fitbit.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class FinishOauth extends AsyncTask<InitializeOAuthResultSet, Void, Void> {

	private TembooSession session = null;
	private Context c;
	public FinishOauth (TembooSession sess,Context c){
		this.session = sess;
		this.c = c;
	}
	@Override
	protected Void doInBackground(InitializeOAuthResultSet... params) {
    	if(params.length != 1){
    		Log.d("Url", "NOPE");
    		return null;
    	}
    	InitializeOAuthResultSet initializeOAuthResults = params[0];

    	
		try {
		
		FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(session);
		
		// Get an InputSet object for the choreo
		FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
		
		// Set inputs
		finalizeOAuthInputs.set_CallbackID(initializeOAuthResults.get_CallbackID());
		finalizeOAuthInputs.set_OAuthTokenSecret(initializeOAuthResults.get_OAuthTokenSecret());
		finalizeOAuthInputs.set_ConsumerSecret("758cf173fac54467832191a2aed23ff2");
		finalizeOAuthInputs.set_ConsumerKey("8a2971d28d0f42eb85772988cf2d9796");
	
		
		// Execute Choreo
		FinalizeOAuthResultSet finalizeOAuthResults;
		try {
			finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
			Log.d("acces 2222", finalizeOAuthResults.get_AccessToken());
			Log.d("acces Sec", finalizeOAuthResults.get_AccessTokenSecret());
		
			SharedPreferences p = c.getSharedPreferences("Groen", Context.MODE_PRIVATE);
			Editor e = p.edit();
			e.putString("accesToken", finalizeOAuthResults.get_AccessToken());
			e.putString("accesTokenS", finalizeOAuthResults.get_AccessTokenSecret());
			e.putString("UserID", finalizeOAuthResults.get_UserID());
			e.apply();
			e.commit();
			
		} catch (TembooException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return null;
	} catch(Exception e) {
		e.printStackTrace();
	}
		return null;
	}
}
