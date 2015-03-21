package nl.rrp.renvoorgroen;

import java.util.Observable;
import java.util.Observer;

import nl.rrp.renvoorgroen.view.ProgressWheel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Overview extends Fragment implements Observer {
	boolean running;
	private ProgressWheel pw_one;
	private ProgressWheel pw_two;
	private ProgressWheel pw_three;
	
	private TextView tv_steps,tv_distance,tv_floors;
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_overview, container, false);
		
		// 1 floor
		pw_one = (ProgressWheel) v.findViewById(R.id.progressBarOne);
		pw_two = (ProgressWheel) v.findViewById(R.id.progressBarTwo);
		pw_three = (ProgressWheel) v.findViewById(R.id.progressBarThree);
		tv_distance = (TextView) v.findViewById(R.id.tv_Distance);
		tv_floors = (TextView) v.findViewById(R.id.tv_Floor);
		tv_steps = (TextView) v.findViewById(R.id.tv_Steps);

		Model.getInstance().addObserver(this);
	
		setView();

		return v;
	}
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_overzicht);
//		//getActionBar().setBackgroundDrawable(android.R.color.holo_green_light);
//		
//		pw_one = (ProgressWheel) findViewById(R.id.progressBarOne);
//		pw_two = (ProgressWheel) findViewById(R.id.progressBarTwo);
//		pw_three = (ProgressWheel) findViewById(R.id.progressBarThree);
//		tv_distance = (TextView) findViewById(R.id.tv_Distance);
//		tv_floors = (TextView) findViewById(R.id.tv_Floor);
//		tv_steps = (TextView) findViewById(R.id.tv_Steps);
//
//		Model.getInstance().addObserver(this);
//	
//		setView();
//
//     
//	}
//	
//	public void open1(View v){
//		Intent i = new Intent(this, ArchivementActivty.class);
//		i.putExtra("key", 1);
//		startActivity(i);
//		
//	}
//	
//	public void open2(View v){
//		Intent i = new Intent(this, ArchivementActivty.class);
//		i.putExtra("key", 2);
//		startActivity(i);
//	}
//	
//	public void open3(View v){
//		Intent i = new Intent(this, ArchivementActivty.class);
//		i.putExtra("key", 3);
//		startActivity(i);
//	}
//	
	private void setView(){
		// 1 floor
		// 2 steps
		// 3 distance
		Model m = Model.getInstance();
		tv_distance.setText(m.getDistance()+" km");
		tv_floors.setText(((int)m.getFloor()) + "");
		tv_steps.setText(((int)m.getSteps())+"");
		
		pw_one.setProgress(m.getArchivementFloor().getRadian());
		pw_two.setProgress(m.getAchievementSteps().getRadian());
		pw_three.setProgress(m.getArchivementDistance().getRadian());
      
	}
	
	
	
//	public void ttt(View v){
//		stopService(new Intent(this, Sender.class));
//	}



	@Override
	public void update(Observable observable, Object data) {
		setView();
		
	}
}
