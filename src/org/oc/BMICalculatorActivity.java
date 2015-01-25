package org.oc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.oc.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class BMICalculatorActivity extends Activity {
	EditText edtLength = null;	
	EditText edtWeight = null;
	EditText edtBirth = null;
	TextView tvBMI = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initComponents();
    }
    
    private void initComponents() {
    	edtLength = (EditText) findViewById(R.id.edtLength);
    	edtWeight = (EditText) findViewById(R.id.edtWeight);
//    	edtBirth = (EditText) findViewById(R.id.edtBirth);
     	tvBMI = (TextView) findViewById(R.id.tvBMI);
     	
     	TextWatcher tw = new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean notEmpty(String s) {
				 return (s != null && s.length() > 0);
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				String length = (notEmpty(edtLength.getText().toString())) ? edtLength.getText().toString():"0";
				String weight = (notEmpty(edtWeight.getText().toString())) ? edtWeight.getText().toString():"0";
//				String birth = (notEmpty(edtBirth.getText().toString())) ? edtBirth.getText().toString():"01-01-1900";
				String birth = "01-01-1970";
				DateFormat formatter ; 
				Date date ; 
				formatter = new SimpleDateFormat("dd-mm-yyyy");
				Calendar cal=Calendar.getInstance();
				try {
					date = (Date)formatter.parse(birth);
					cal.setTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				BMICalculator BMICalc = new BMICalculator(Integer.parseInt(length),
						Integer.parseInt(weight),
						cal);
				float bmi = Round(BMICalc.getBMI(), 2);				
//				tvBMI.setText(Float.toString(bmi));
				
				if (bmi >= 18.5 && bmi <= 24.9){
					tvBMI.setTextColor(0xF000CF00);
					tvBMI.setText("Normal " + Float.toString(bmi));
				}
				else if (bmi >= 24.9 && bmi <= 29.9){
					tvBMI.setTextColor(0xFFF06D2F);
					tvBMI.setText("Overweight " + Float.toString(bmi));
				}
				else if(bmi < 18.5){
					tvBMI.setTextColor(0xffff0000);
					tvBMI.setText("Underweight " + Float.toString(bmi));
				}
				else if(bmi > 30.0){
					tvBMI.setTextColor(0xF0EE0001);
					tvBMI.setText("Obesity " + Float.toString(bmi));
				}
			}
     		
     	};
     	
     	edtLength.addTextChangedListener(tw);
     	edtWeight.addTextChangedListener(tw);
//     	edtBirth.addTextChangedListener(tw);
    }
    
    public static float Round(float Rval, int Rpl) {
    	  float p = (float)Math.pow(10,Rpl);
    	  Rval = Rval * p;
    	  float tmp = Math.round(Rval);
    	  return (float)tmp/p;
    }
}