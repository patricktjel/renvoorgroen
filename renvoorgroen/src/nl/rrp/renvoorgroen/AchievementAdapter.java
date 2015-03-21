package nl.rrp.renvoorgroen;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AchievementAdapter extends ArrayAdapter<Achievement> {

	public AchievementAdapter(Context context, int resource, List<Achievement> objects) {
		super(context, resource, objects);
	
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.achievement_item, null);
		}
		TextView tv_Name,tv_Value;
		ProgressBar pb_progress = (ProgressBar) convertView.findViewById(R.id.progressBar1);
		tv_Name = (TextView) convertView.findViewById(R.id.tv_name);
		tv_Value = (TextView) convertView.findViewById(R.id.tv_value);
		
		Achievement a = getItem(position);
		tv_Name.setText(a.getName());
		tv_Value.setText(a.getShow());
		pb_progress.setProgress((int) a.progresProcent());
		
		if(!a.gotIt()){
			ImageView v = (ImageView)convertView.findViewById(R.id.imageView1);
			v.setVisibility(View.INVISIBLE);
		} else {
			ImageView v = (ImageView)convertView.findViewById(R.id.imageView1);
			v.setVisibility(View.VISIBLE);
		}
		// TODO Auto-generated method stub
		return convertView;
	}
	
}
