package nl.rrp.renvoorgroen;

import nl.rrp.renvoorgroen.threads.RegisterCall;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	public void summitToServer(View v){
		EditText t = (EditText)findViewById(R.id.editText1);
		String text = t.getText().toString();
		Log.d("username", text);
		
		new RegisterCall(this).execute(text);
	}
}
