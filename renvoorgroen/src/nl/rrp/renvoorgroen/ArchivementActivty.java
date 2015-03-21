package nl.rrp.renvoorgroen;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ArchivementActivty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archivement_activty);
		// 1 floor
		// 2 steps
		// 3 distance
		Intent i = getIntent();
		int key = i.getIntExtra("key", 1);
		List<Archivement> list = null;
		switch (key) {
			case 1: list = Model.getInstance().getArchivementsFloor();  break;
			case 2: list = Model.getInstance().getArchivementsSteps();  break;
			case 3: list = Model.getInstance().getArchivementsDistance();  break;
		}
		if(list != null){
			ListView l = (ListView) findViewById(R.id.listView1);
			ArchivementAdapter ad = new ArchivementAdapter(this, 0, list);
			l.setAdapter(ad);
		}
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archivement_activty, menu);
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
}
