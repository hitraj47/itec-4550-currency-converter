package com.bewareofraj.itec4550currencyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText txtAmount;
	private Spinner spnFrom, spnTo;
	private Button btnConvert;
	private TextView lblResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeGUI();
	}

	private void initializeGUI() {
		txtAmount = (EditText) findViewById(R.id.txtAmount);
		spnFrom = (Spinner) findViewById(R.id.spnFrom);
		spnTo = (Spinner) findViewById(R.id.spnTo);
		
		btnConvert = (Button) findViewById(R.id.btnConvert);
		btnConvert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (txtAmount.getText().toString().equals("")) {
					Toast toast = Toast.makeText(MainActivity.this, "Enter an amount to convert!", Toast.LENGTH_LONG);
					toast.show();
				} else {
					calculateResult();
				}
			}
		});
		
		lblResult = (TextView) findViewById(R.id.lblResult);		
	}
	
	private void calculateResult() {
		
	}
}
