package nl.rrp.renvoorgroen.threads;

import java.util.Calendar;

import nl.rrp.renvoorgroen.Model;

import org.json.JSONException;
import org.json.JSONObject;

import com.temboo.Library.Fitbit.Activities.GetActivities;
import com.temboo.Library.Fitbit.Activities.GetActivities.GetActivitiesInputSet;
import com.temboo.Library.Fitbit.Activities.GetActivities.GetActivitiesResultSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadActivity extends AsyncTask<Void, Void, JSONObject> {

	@Override
	protected JSONObject doInBackground(Void... arg0) {
		Model model = Model.getInstance();
		TembooSession session;
		try {
			session = new TembooSession("renvoorgroen", "myFirstApp", "3fd23aa5415148a79494d2c057029535");
		
	
			GetActivities getActivitiesChoreo = new GetActivities(session);
	
			// Get an InputSet object for the choreo
			GetActivitiesInputSet getActivitiesInputs = getActivitiesChoreo.newInputSet();
	
			// Set inputs
			Calendar cal = Calendar.getInstance();
			String s = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
			Log.d("Datum", s);
			getActivitiesInputs.set_Date(s);
			
			getActivitiesInputs.set_AccessToken(model.getAccesToken());
			getActivitiesInputs.set_AccessTokenSecret(model.getAccesTokenS());
			getActivitiesInputs.set_ConsumerKey(model.getCONSUMER());
			getActivitiesInputs.set_ConsumerSecret(model.getCONSUMER_SECRET());
			// Execute Choreo
			GetActivitiesResultSet getActivitiesResults = getActivitiesChoreo.execute(getActivitiesInputs);
			Log.d("json", getActivitiesResults.get_Response());
	
			return new JSONObject(getActivitiesResults.get_Response());
		} catch (TembooException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
