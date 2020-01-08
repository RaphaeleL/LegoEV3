package followme;


import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class UltrasonicSensor {

	private static EV3UltrasonicSensor us1;
	private static SampleProvider sp;
	private static float[] sample;

	/**
	 * Initialiseren des Ultraschallsensors
	 * @param port Port in welchem der Sensor im EV3-Baustein eingesteckt ist
	 */
	public UltrasonicSensor(Port port) {
		this.us1 = new EV3UltrasonicSensor(port);
		this.sp = us1.getDistanceMode();
		this.sample = new float[sp.sampleSize()];

	}
	
	public static int getDirection() {
		int distance = 1;
		return distance;
	}

	/**
	 * Distanz in cm welche vom Ultraschallsensor gemessen wird.
	 * @return Abstand zum Hindernis
	 */
	public static float getDistance() {
		sp.fetchSample(sample, 0);
		float result = sample[0];
//		System.out.println("Distance: " + result * 100);
		return result * 100;
	}
	

}
