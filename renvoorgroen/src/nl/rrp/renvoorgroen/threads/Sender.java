package nl.rrp.renvoorgroen.threads;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import nl.rrp.renvoorgroen.Model;

import org.json.JSONObject;

import com.temboo.Library.Fitbit.Activities.GetActivities;
import com.temboo.Library.Fitbit.Activities.GetActivities.GetActivitiesInputSet;
import com.temboo.Library.Fitbit.Activities.GetActivities.GetActivitiesResultSet;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthInputSet;
import com.temboo.Library.Fitbit.OAuth.InitializeOAuth.InitializeOAuthResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Sender extends Service {

	private Model model;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		model = Model.getInstance();
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "service starting" + flags + " " + startId, Toast.LENGTH_LONG).show();
		try {
			run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "service stopping", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	
	
	private void run() throws InterruptedException, ExecutionException{
		//while(true){
			JSONObject j =  new DownloadActivity().execute().get();
			Log.d("summ", j.toString());
			
		//}
	}
	
	
	
	

}
