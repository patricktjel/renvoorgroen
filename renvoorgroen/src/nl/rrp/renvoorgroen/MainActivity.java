package nl.rrp.renvoorgroen;

import java.util.concurrent.ExecutionException;

import nl.rrp.renvoorgroen.threads.DownloadAchievements;
import nl.rrp.renvoorgroen.threads.InitOAuth;

import com.temboo.Library.Fitbit.OAuth.FinalizeOAuth.FinalizeOAuthInputSet;
import com.temboo.Library.Fitbit.OAuth.FinalizeOAuth.FinalizeOAuthResultSet;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Fitbit.OAuth.*;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {
	BroadcastReceiver mReceiver;
	private WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        Model m = Model.getInstance();
        try {
        	new DownloadAchievements().execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        m.init(this);
        Log.d("url", m.getAccesToken());
        if(m.getAccesToken().equals("")){  
        	
	        w = (WebView) findViewById(R.id.webView);
	        InitOAuth init = new InitOAuth(w,this);
	        init.execute();
        } else {
        	startActivity(new Intent(this, TabActivity.class));
        	finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d("Resultc", resultCode+"");
       	Log.d("Requestc", requestCode+"");
       	
    }
    
   

}
