package ca.uwaterloo.lab4_206_13;

import mapper.PositionListener;

public class DisplacementCounter {
	private static int steps;
	private static float south = 0f;	//y-axis
	private static float east = 0f;	//x-axis
	private static PositionListener pl;
	public final static float DECLINATION = 19f;
	
	public static int steps(){
		return steps;
	}
	
	public static void stepIncrement(){
		steps++;
		pl.userPointChanged(east, south);
	}
	
	public static void reset(){
		steps = 0;
		south = 0f;
		east = 0f;
		
	}	
	
	public static void getFinalDisplacement(){
		double finalOrient = NonLinearAccelerometerListener.getOrientation() + DECLINATION;
		float pX = east;
		float pY = south;
		//Calculate the displacement of each step based on the previous position
		//the corresponding changes with respect to the previous coordinates would be 
		//cos(angle) and sin(angle) for North and East respectively
		float cX = east + ((float) Math.sin((double)(finalOrient / 180 * 3.14))) * PositionHandler.STEPLENGTH;
		float cY = south - ((float) Math.cos((double)(finalOrient / 180 * 3.14))) * PositionHandler.STEPLENGTH;
			
		if(PositionHandler.checkUserPoint(pX, pY, cX, cY)){
			east = cX;
			south = cY;
		}
		
	}
	
	public static float getSouth(){
		return south;
	}
	
	public static float getEast(){
		return east;
	}
	
	public static void setSouth(float s){
		south = s;
	}
	
	public static void setEast(float e){
		east = e;
	}
	
	public static void addPositionListener(PositionListener pl){
		DisplacementCounter.pl = pl;
	}
}