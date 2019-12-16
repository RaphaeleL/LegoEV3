package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class IRSensor {

	private EV3IRSensor ir1;
	private static SensorMode seek;
	private static float[] sample;

	public IRSensor(Port port) {
		this.ir1 = new EV3IRSensor(port);
		this.seek = ir1.getSeekMode();
		this.sample = new float[seek.sampleSize()];

	}

	public static int getDirectionFromBeacon(int channel) {
		seek.fetchSample(sample, 0);
		int direction = (int) sample[channel];
//		System.out.println("Direction: " + direction);
		return direction;
	}

	public static int getDistance(int channel) {
		seek.fetchSample(sample, 0);
		int distance = (int) sample[channel];
//		System.out.println("Distance: " + distance);
		if(distance > 0)
			return distance;
		
		return (distance + 100);
	}

}
