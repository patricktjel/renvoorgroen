package nl.rrp.renvoorgroen;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AchievementsFragment extends Fragment {

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		 ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_achievement_list, container, false);
		// 1 floor
		// 2 steps
		// 3 distance
		Bundle i = getArguments();
		int key = i.getInt("key", 1);
		List<Achievement> list = null;
		switch (key) {
			case 1: list = Model.getInstance().getAchievementsFloor();  break;
			case 2: list = Model.getInstance().getAchievementsSteps();  break;
			case 3: list = Model.getInstance().getAchievementsDistance();  break;
		}
		if(list != null){
			ListView l = (ListView) v.findViewById(R.id.listView1);
			AchievementAdapter ad = new AchievementAdapter(v.getContext(), 0, list);
			l.setAdapter(ad);
		}
		
		return v;
}
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_archivement_activty);
//		// 1 floor
//		// 2 steps
//		// 3 distance
//		Intent i = getIntent();
//		int key = i.getIntExtra("key", 1);
//		List<Archivement> list = null;
//		switch (key) {
//			case 1: list = Model.getInstance().getArchivementsFloor();  break;
//			case 2: list = Model.getInstance().getArchivementsSteps();  break;
//			case 3: list = Model.getInstance().getArchivementsDistance();  break;
//		}
//		if(list != null){
//			ListView l = (ListView) findViewById(R.id.listView1);
//			ArchivementAdapter ad = new ArchivementAdapter(this, 0, list);
//			l.setAdapter(ad);
//		}
//		
//
//		
//	}

	
}
