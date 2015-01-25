package org.oc;

import java.util.Calendar;
import java.util.Date;

public class BMICalculator {
	private int length;
	private int weight;
	private Calendar birth;

	public BMICalculator(int length, int weight, Calendar birth){
		this.length = length;
		this.weight = weight;
		this.birth = birth;
	}
	
	public float getAge(){
		 int age = 0;
		 Calendar now = Calendar.getInstance();
		 
		 if(this.birth!= null) {
			 now.setTime(new Date());
			 
			 if(this.birth.after(now)) {
				 throw new IllegalArgumentException("Can't be born in the future");
			 }
			 
			 age = now.get(Calendar.YEAR) - this.birth.get(Calendar.YEAR);
			 
			 if(now.get(Calendar.DAY_OF_YEAR) < this.birth.get(Calendar.DAY_OF_YEAR)) {
				 age-=1;
			 }
		 }
		 
		 return age;
	}
	
	public float getBMI(){
		if (this.length > 0){
			return this.weight / (float)((this.length/(float)100 * this.length/(float)100));
		}
		else{
			return  (float) 0.0;
		}
	}
}
