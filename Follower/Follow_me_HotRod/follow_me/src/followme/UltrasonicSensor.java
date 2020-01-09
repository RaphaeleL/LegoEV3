package followme;


import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class UltrasonicSensor {

	private static EV3UltrasonicSensor us1;
	private static SampleProvider sp;
	private static float[] sample;

	// Konstruktor (mache den Sensor ready)
	public UltrasonicSensor(Port port) {
		this.us1 = new EV3UltrasonicSensor(port);
		this.sp = us1.getDistanceMode();
		this.sample = new float[sp.sampleSize()];

	}
	
	// Nicht genutzt
	public static int getDirection() {
		int distance = 1;
		return distance;
	}

	// Bekomme den Abstand nach vorne
	public static float getDistance() {
		sp.fetchSample(sample, 0);
		float result = sample[0];
		return result * 100;
	}
	

}
