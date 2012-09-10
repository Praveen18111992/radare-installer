/*
radare2 installer for Android
(c) 2012 Pau Oliva Fora <pof[at]eslack[dot]org>
*/
package org.radare.installer;

import org.radare.installer.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.EditText;

import android.widget.Toast;
import java.io.File;

public class LaunchActivity extends Activity {

	private Utils mUtils;

	private RadioGroup radiogroup;
	private Button btnDisplay;
	private EditText file_to_open;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mUtils = new Utils(getApplicationContext());

		File radarebin = new File("/data/data/org.radare.installer/radare2/bin/radare2");
		if (radarebin.exists()) {
			setContentView(R.layout.launch);
			addListenerOnButton();
		} else {
			mUtils.myToast("Please install radare2 first!", Toast.LENGTH_SHORT);
			finish();
		}
	}

	public void addListenerOnButton() {

		radiogroup = (RadioGroup) findViewById(R.id.radiogroup1);
		btnDisplay = (Button) findViewById(R.id.button_open);

		btnDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int selectedId = radiogroup.getCheckedRadioButtonId();

				file_to_open = (EditText) findViewById(R.id.file_to_open);
				Bundle b = new Bundle();
				b.putString("filename", file_to_open.getText().toString());

				switch (selectedId) {
					case R.id.radiobutton_web :
						Intent intent1 = new Intent(LaunchActivity.this, WebActivity.class);
						intent1.putExtras(b);
						startActivity(intent1);
					break;
					case R.id.radiobutton_console :
						Intent intent2 = new Intent(LaunchActivity.this, LauncherActivity.class);
						intent2.putExtras(b);
						startActivity(intent2);
					break;
				}
			}
		});
	}
}