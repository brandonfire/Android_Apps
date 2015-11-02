package ca.uwaterloo.lab2_206_13;

import java.util.Arrays;

import ca.uwaterloo.lab2_206_13.AccelerometerEventListener;
import ca.uwaterloo.lab2_206_13.LineGraphView;
import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*@Override 
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			LinearLayout l = (LinearLayout) rootView.findViewById(R.id.label2);
			l.setOrientation(LinearLayout.VERTICAL);
			
			createLabel(rootView, l, "Lab 2: Counting Steps\n");
			
			TextView accelarationValue = createLabel(rootView, l, "");
			
			//create line graph for accelerometer
			LineGraphView graph = new LineGraphView(rootView.getContext(), 100, Arrays.asList("x", "y", "z"));
			l.addView(graph);
			graph.setVisibility(View.VISIBLE);
			
			//display the steps
			final TextView counter = createLabel(rootView, l, "");
			
			//add a reset button for steps
			Button resetButton = new Button(rootView.getContext());
			resetButton.setText("Reset Steps!");
			l.addView(resetButton);
			resetButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 StepCounter.reset();
	                 //counter.setText("Steps: 0");
	             }
	         });
			
			
			//setup acceleration sensor
			SensorManager sensorManager = (SensorManager)
					rootView.getContext().getSystemService(SENSOR_SERVICE);
			Sensor accelerometer = 
					sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
			SensorEventListener accelaration = new AccelerometerEventListener(accelarationValue, graph, counter);
			sensorManager.registerListener(accelaration, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
			
			return rootView;
		}
		private TextView createLabel(View rootView, LinearLayout l, String labelName){
			TextView tv = new TextView(rootView.getContext());
			tv.setText(labelName);
			l.addView(tv);
			
			return tv;
		}
		
	}

}
