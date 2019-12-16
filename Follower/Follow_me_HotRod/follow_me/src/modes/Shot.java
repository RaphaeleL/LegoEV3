package modes;

import followme.Main;
import followme.Move;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;

import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Shot {

	private static RegulatedMotor motor;
	private static EV3TouchSensor sensor;
	private static SensorMode touch;
	private static float[] sample;


	public Shot() {

	}

	public static void shot() {
	}
	
	public boolean isPressed() {
		if (sample[0] == 0.0) {
			return false;
		}else {
			return true;
		}
	}

	public static EV3TouchSensor getSensor() {
		return sensor;
	}

	public static void setSensor(EV3TouchSensor sensor) {
		Shot.sensor = sensor;
	}

	public static void backToPosition(int angle) {
		motor.rotate(angle);
	}
	

	
	public static void releaseBall() {
			motor.rotate(80);
	}
	
}