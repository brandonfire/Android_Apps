package ca.uwaterloo.lab1_206_13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class LightSensorEventListener implements SensorEventListener {

	TextView output;
	
	public LightSensorEventListener (TextView outputView){
		output = outputView;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
				output.setText("Light level: " + event.values[0] + " in lux\n\n");
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
	
	

}
