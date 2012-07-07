package test.xahir;

import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final int LENGHT;

	private int startPos;
	int endPos = 0;
	int k = 0;
	int j = 0;

	int year;
	int month;

	int day;
	int monthMax;
	int prevMonthMax;

	int nakaiMax[] = { 13, 14, 13, 13, 13, 13, 14, 14, 13, 14, 14, 14, 14, 14,
			14, 14, 14, 13, 13, 14, 14, 13, 14, 14, 13, 13, 13 };

	// if<<notleapyear>> nakaiMax[6] = nakaiMax[6]-1;

	int nakaiDay;
	int nakaiLocator;

	// Calendar calendar = Calendar.getInstance(Locale.getDefault());
	// int today = calendar.get(Calendar.DAY_OF_MONTH);

	public ImageAdapter(Context context, int lenght, int monthMax,
			int startDay, int prevMonthMax, int nakaiDayMonthStart,
			int nakaiLocator) {
		this.context = context;
		this.LENGHT = lenght;
		this.startPos = startDay;
		this.monthMax = monthMax;
		this.prevMonthMax = prevMonthMax;

		this.nakaiDay = nakaiDayMonthStart;
		this.nakaiLocator = nakaiLocator;

	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.cell, null);

			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.textView1);
			TextView text2 = (TextView) gridView.findViewById(R.id.textView2);
			TextView text3 = (TextView) gridView.findViewById(R.id.textView3);
			// textView.setText(mobileValues[position]);

			if (position + 1 < startPos) {
				Log.d("POS", "pos: " + String.valueOf(position)
						+ " ..startPos: " + String.valueOf(startPos));
				textView.setText("no");

				// adds remaining day to the front
				if (monthMax + startPos > 35) {
					int addedDays = (36 - startPos) + (++k);
					if (addedDays <= monthMax)
						textView.setText(String.valueOf(addedDays));

				} else {

					textView.setText(String.valueOf(prevMonthMax
							- (startPos - 1) + (++j)));

				}

			} else {
				// normal day updates here
				textView.setText(String.valueOf(++day));
			}

			if (day > monthMax)
				textView.setText(String.valueOf(day % monthMax));

			
			
			
			
			
			
		if (position + 1 >= startPos  ) {

				text2.setText(String.valueOf(nakaiDay));
				
							
				

				if (nakaiDay == nakaiMax[nakaiLocator]) {
					
					Log.d("Nakamax", String.valueOf(nakaiLocator));
					nakaiDay = 0;
					nakaiLocator ++;
					if(nakaiLocator>26)
						nakaiLocator = 0;
					

				}

				nakaiDay++;

		}
//				else{
//			text2.setText("add");
//		}

			text3.setText("");

			// set image based on selected text
			// ImageView imageView = (ImageView) gridView
			// .findViewById(R.id.grid_item_image);

			// String mobile = mobileValues[position];

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	public int getCount() {
		return LENGHT;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

}