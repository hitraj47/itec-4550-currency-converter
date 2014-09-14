package com.bewareofraj.itec4550currencyconverter;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

import com.bewareofraj.itec4550currencyconverter.util.RetrieveRateTask;

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
					try {
						calculateResult();
					} catch (InterruptedException | ExecutionException e) {
						Toast toast = Toast.makeText(MainActivity.this, "An error occurred while trying to get rates...", Toast.LENGTH_LONG);
						toast.show();
					}
				}
			}
		});
		
		lblResult = (TextView) findViewById(R.id.lblResult);		
	}
	
	private void calculateResult() throws InterruptedException, ExecutionException {
		String[] fromToArray = new String[2];
		fromToArray[0] = spnFrom.getSelectedItem().toString();
		fromToArray[1] = spnTo.getSelectedItem().toString();
		Float rate = new RetrieveRateTask().execute(fromToArray).get();
		Float amount = Float.parseFloat(txtAmount.getText().toString());
		Float result = amount * rate;
		DecimalFormat money = new DecimalFormat("#.00");
		lblResult.setText(money.format(result));
	}
}
