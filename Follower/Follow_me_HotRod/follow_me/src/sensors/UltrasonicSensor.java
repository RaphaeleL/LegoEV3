package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;


public class UltrasonicSensor {

	private static EV3UltrasonicSensor us1;
	private static SampleProvider sp;
	private static float[] sample;

	public UltrasonicSensor(Port port) {
		this.us1 = new EV3UltrasonicSensor(port);
		this.sp = us1.getDistanceMode();
		this.sample = new float[sp.sampleSize()];
	}
	
	public static int getDirection() {
		int distance = 1;
		return distance;
	}

	public static float getDistance() {
		sp.fetchSample(sample, 0);
		float result = sample[0];
		return result * 100;
	}
	

}
