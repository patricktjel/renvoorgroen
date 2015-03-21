package nl.rrp.renvoorgroen;

import java.util.Observable;
import java.util.Observer;

import nl.rrp.renvoorgroen.threads.Sender;
import nl.rrp.renvoorgroen.view.ProgressWheel;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Overzicht extends Activity implements Observer {
	boolean running;
	private ProgressWheel pw_one;
	private ProgressWheel pw_two;
	private ProgressWheel pw_three;
	
	private TextView tv_steps,tv_distance,tv_floors;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overzicht);
		//getActionBar().setBackgroundDrawable(android.R.color.holo_green_light);
		startService(new Intent(this, Sender.class));
		pw_one = (ProgressWheel) findViewById(R.id.progressBarOne);
		pw_two = (ProgressWheel) findViewById(R.id.progressBarTwo);
		pw_three = (ProgressWheel) findViewById(R.id.progressBarThree);
		tv_distance = (TextView) findViewById(R.id.tv_Distance);
		tv_floors = (TextView) findViewById(R.id.tv_Floor);
		tv_steps = (TextView) findViewById(R.id.tv_Steps);
		
		pw_one.setProgress(300);
		pw_two.setProgress(50);
		pw_three.setProgress(123);
		Model.getInstance().addObserver(this);
	
		setView();

     
	}
	
	public void open1(View v){
		Intent i = new Intent(this, ArchivementActivty.class);
		i.putExtra("key", 1);
		startActivity(i);
		
	}
	
	public void open2(View v){
		Intent i = new Intent(this, ArchivementActivty.class);
		i.putExtra("key", 2);
		startActivity(i);
	}
	
	public void open3(View v){
		Intent i = new Intent(this, ArchivementActivty.class);
		i.putExtra("key", 3);
		startActivity(i);
	}
	
	private void setView(){
		// 1 floor
		// 2 steps
		// 3 distance
		Model m = Model.getInstance();
		tv_distance.setText(m.getDistance()+" km");
		tv_floors.setText(((int)m.getFloor()) + "");
		tv_steps.setText(((int)m.getSteps())+"");
		
		pw_one.setProgress(m.getArchivementFloor().getRadian());
		pw_two.setProgress(m.getArchivementSteps().getRadian());
		pw_three.setProgress(m.getArchivementDistance().getRadian());
      
	}
	
	
	
	public void ttt(View v){
		stopService(new Intent(this, Sender.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overzicht, menu);
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
	public void update(Observable observable, Object data) {
		setView();
		
	}
}
