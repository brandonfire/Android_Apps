package ca.uwaterloo.lab3_206_13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class AccelerometerEventListener implements SensorEventListener {

	TextView display;
	LineGraphView graph;
	TextView counter;
    
	float[] record = new float[1];
	float delta;
	//float max;
	
	AccelerationState state = AccelerationState.WAITING;
	
	public AccelerometerEventListener (TextView outputView, LineGraphView graph, TextView counter){
		display = outputView;
		this.graph = graph;
		this.counter = counter;
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
			//get z-axis value
			delta = event.values[2] - record[0];
			record[0] += delta / 16;
			
			display.setText(String.format("Linear acceleration on z axis:\n%,5.2f in m/s^2 \n ", 
						record[0]));
			graph.addPoint(record);
			
			state = state.process(delta, record[0]);
			
			counter.setText("Steps: " + DisplacementCounter.steps() + "\nState: " + state.toString());
			
		}
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

    
}
