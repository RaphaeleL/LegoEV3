package modes;

import footballBot.Main;
import footballBot.Move;
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
	
	/**
	 * 
	 * @param motorPort An welchem Port der Motor für den Schuss angeschlossen ist.
	 * @param sensorPort Port für den Drucksensor.
	 * 
	 */
	public Shot(Port motorPort, Port sensorPort) {
		this.motor = new EV3LargeRegulatedMotor(motorPort);
		this.sensor = new EV3TouchSensor(sensorPort);
		this.touch = sensor.getTouchMode();
		this.sample = new float[touch.sampleSize()];
	}

	/**
	 * Wird geschosse, der Motor fährt solange bis der Drucksensot erreicht ist. Wurde geschossen wird der Angriff beendet 
	 * und in einen anderen Modus gewechselt. 
	 * Anschließend werden die Zangen des Roboter wieder in die Ausgansposition zurückgestellt und der Roboter dreht sich vom Tor ab.
	 */
	public static void shot() {
		sensor.fetchSample(sample, 0);
		Delay.msDelay(1000);

		if (sample[0] == 0.0) {
			motor.setSpeed(900);
			motor.forward();
			Move.stopDrivingForward();
		} else {
			backToPosition(-70);
			Move.turnAround(100, 100);
			ModesController.setIsInAttackMode(false);
			ModesController.setIsInDefenceMode(true);
			Delay.msDelay(4200);
		}
		
//		if (sample[0] == 0.0) {
//			motor.setSpeed(900);
//			motor.forward();
//			Move.stopDrivingForward();
//			
//			backToPosition();
//			Move.turnAround(100, 100);
//			Delay.msDelay(4200);
//		}
////		if(sample[0] == 1.0) {
////			backToPosition();
////		}
	}
	/**
	 * Wird geprüft ob der Drucksensor gedrückt ist.
	 * @return false falls der Sensor nicht gedrückt ist, andernfalls true.
	 */
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