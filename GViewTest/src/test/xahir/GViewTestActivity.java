package test.xahir;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class GViewTestActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	GridView gridView;

	static final int GRIDLENGH = 35;

	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec" };
	int[] monthMax = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	String[] nakai = {"Mula","Furahalha","Uthurahalha","Huvan","Dhinasha","Hiyavihaa","Furabadhuruva","Fasbadhuruva","Reyva",
			"Assidha","Burunu","Kethi","Roanu","Miyaheli","Adha","Funos","Fus","Ahuliha","Maa","Fura","Uthura",
			"Atha","Hitha","Hei","Vihaa","Nora","Dhosha"
	};

	
	int nakaiDayMonthStart[] = {10,1,4,7,10,13,1,4,9,11,1,5}; //jan 1 = 1 Furahalha ...
	int monthStartNakai [] =  {1,4,6,8,10,12,15,17,19,21,24,26}; //jan = Furahalha, feb = dhinasha......
	
	/*
	 * if <<notleapyear>>
	 * for(int i =3; i<nakaiMontStart.lengh -2;i++) 
	 * nakaiMonthStart[i] = nakaiMonthStart[i] -1;
	 */
	
	Button btnLeft;
	Button btnRight;
	Button btnMiddle;

	int startWeekDay = 0;

	Calendar calendar = Calendar.getInstance(Locale.getDefault());
	// int today = calendar.get(Calendar.DAY_OF_MONTH);
	int year;
	int month = calendar.get(Calendar.MONTH);;
	int startDay = 0;
	int prevMonthMax;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gridView = (GridView) findViewById(R.id.gridView1);

		ImageAdapter adapter = new ImageAdapter(this, GRIDLENGH,
				monthMax[month], startDay,prevMonthMax,nakaiDayMonthStart[month],monthStartNakai[month]);

		adapter.notifyDataSetChanged();
		gridView.setAdapter(adapter);

		/*
		 * gridView.setOnItemClickListener(new OnItemClickListener() { public
		 * void onItemClick(AdapterView<?> parent, View v, int position, long
		 * id) { Toast.makeText( getApplicationContext(), ((TextView)
		 * v.findViewById(R.id.textView1)) .getText(),
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * } });
		 */
		btnLeft = (Button) findViewById(R.id.btnLeft);
		btnMiddle = (Button) findViewById(R.id.btnMiddle);
		btnRight = (Button) findViewById(R.id.btnRight);

		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnMiddle.setOnClickListener(this);

	}

	public void onClick(View view) {
			 
				
		if (view == btnMiddle) {

			// Log.d("Today",String.valueOf(today));
			// Log.d("weekday",
			// String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));

			// Log.d("weekday", String.valueOf(getStartWeekDay(20,5)));

			this.month = getMonth();
			this.startDay = getStartWeekDay(
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.DAY_OF_WEEK));
			
			

			Log.d("weekday", String.valueOf(startDay));
			Log.d("Month", months[getMonth()]);
			Log.d("MonthMax", String.valueOf(monthMax[month]));
			
			onCreate(null);

		}

		if (view == btnRight) {
			if (month == 11){
				month = 0;
				prevMonthMax = 31;
			}
				
			else{
				prevMonthMax = monthMax[month];
				month++;
			}
				

			calendar.add(Calendar.MONTH, 1);
			this.startDay = getStartWeekDay(
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.DAY_OF_WEEK));
			onCreate(null);
		}
		if (view == btnLeft) {
			calendar.add(Calendar.MONTH, -1);
			if (month == 0){
				month = 11;
				prevMonthMax = 30;
			}
				
			else{
				prevMonthMax = monthMax[month];
				month--;
			}
				
			this.startDay = getStartWeekDay(
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.DAY_OF_WEEK));
			onCreate(null);
		}

		btnMiddle.setText(months[month]);
		
		
	}

	public int getMonth() {

		return calendar.get(Calendar.MONTH);

	}

	public int getStartWeekDay(int day, int weekday) {

		int i;
		// int j = calendar.get(Calendar.DAY_OF_WEEK);
		int j = weekday;

		for (i = day; i > 1; i--) {

			j--;
			if (j < 1)
				j = 7;
		}
		return j;

	}

}