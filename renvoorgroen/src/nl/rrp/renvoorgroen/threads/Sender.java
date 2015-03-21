package nl.rrp.renvoorgroen.threads;

import java.util.concurrent.ExecutionException;

import nl.rrp.renvoorgroen.Model;

import org.json.JSONObject;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Sender extends IntentService {



	public Sender() {
		super("Service1");
	}

	private boolean run = true;
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
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "service stopping", Toast.LENGTH_SHORT).show();
		run = false;
		super.onDestroy();
	}
	
	
	
	private void run() throws InterruptedException, ExecutionException{
		JSONObject j =  new DownloadActivity().execute().get();
		new SendDataToServer().execute(j).get();
		Thread.sleep(15000);

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			while(run){
				run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
