package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class IRSensor {

	private EV3IRSensor ir1;
	private static SensorMode seek;
	private static float[] sample;

	/**
	 * Initialisieren des Lego Infarotsensor
	 * @param port Port in welchem der Sensor im EV3-Baustein eingesteckt ist
	 */
	public IRSensor(Port port) {
		this.ir1 = new EV3IRSensor(port);
		this.seek = ir1.getSeekMode();
		this.sample = new float[seek.sampleSize()];

	}

	/**
	 * Richtung des Infarotbeacon der Fernbedinung finden
	 * @param channel Kanal der Fernbedinung [(0 -> Kanal 1), (2 -> Kanal 2), (4 -> Kanal 3), (6 -> Kanal 4)]
	 * @return Richtung des Infarotbeacons der Fernbedinung
	 */
	public static int getDirectionFromBeacon(int channel) {
		seek.fetchSample(sample, 0);
		int direction = (int) sample[channel];
//		System.out.println("Direction: " + direction);
		return direction;
	}

	/**
	 * Distanz zur Infarotfernbedinung messen
	 * @param channel Kanal der Fernbedienung [(1 -> Kanal 1), (3 -> Kanal 2), (5 -> Kanal 3), (7 -> Kanal 4)]
	 * @return Abstand zur Infarotfernbedienung
	 */
	public static int getDistance(int channel) {
		seek.fetchSample(sample, 0);
		int distance = (int) sample[channel];
//		System.out.println("Distance: " + distance);
		if(distance > 0)
			return distance;
		
		return (distance + 100);
	}

}
