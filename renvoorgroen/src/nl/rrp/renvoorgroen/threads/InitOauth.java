package nl.rrp.renvoorgroen.threads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.temboo.Library.Fitbit.OAuth.InitializeOAuth;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

public class InitOAuth extends AsyncTask<Void, Void, InitializeOAuthResultSet> {
	private TembooSession session;
	private WebView w;
	private Context c;
	public InitOAuth (WebView web, Context c){
		this.w = web;
		this.c = c;
	}
	
	@Override
	protected InitializeOAuthResultSet doInBackground(Void... params) {
//String link = "https://www.fitbit.com/oauth/authorize?oauth_token=2a156572260617b9d94980a31898e439&oauth_token_secret=de0d2eeaf1d444a9c09e020b0afff421&oauth_callback_confirmed=true";
    	
    	try {
    		session = new TembooSession("renvoorgroen", "myFirstApp", "3fd23aa5415148a79494d2c057029535");

			//session = new TembooSession("renvoorgroen", "myFirstApp", "3fd23aa5415148a79494d2c057029535");
			InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);

			// Get an InputSet object for the choreo
			InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();

			// Set inputs
			initializeOAuthInputs.set_ConsumerSecret("758cf173fac54467832191a2aed23ff2");
			initializeOAuthInputs.set_ConsumerKey("8a2971d28d0f42eb85772988cf2d9796");
			// Execute Choreo
			InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
			
			
			return initializeOAuthResults;
    	} catch (TembooException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    

    	return null;
	}
	
	@Override
	protected void onPostExecute(final InitializeOAuthResultSet result) {

		if(result == null){
			Log.d("Result", "is null");
		}
		w.loadUrl(result.get_AuthorizationURL()+"&display=touch");
		w.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				Log.d("Url", url);
				if(url.contains("renvoorgroen.temboolive.com")){
					
					FinishOAuth a = new FinishOAuth(session,c);
					a.execute(result);
				}
				
				
			}
			

		});
	}

}
