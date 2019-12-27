package followme;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import sensors.IRSeeker;
import sensors.UltrasonicSensor;

public class Main {
    
	private static UltrasonicSensor ur = new UltrasonicSensor(SensorPort.S4);
	private static IRSeeker is = new IRSeeker(SensorPort.S3);
	private static Move move = new Move(MotorPort.A, MotorPort.D);

	public static void driveBackwardsAndTurn() {
		Move.stopDrivingForward();
		Move.driveBackward(100);
		Delay.msDelay(1000);
		Move.turnAround(150, 150);
		Delay.msDelay(2000);
	}
	
	public static void main(String[] args) {
		System.out.println("Follower");

		while (true) {
			while (is.getDistance() < 60.0 || Float.isNaN(is.getDistance())) {
				if(UltrasonicSensor.getDistance() <= 20.0) {
					driveBackwardsAndTurn();
				}
				IRSeeker.driveToBall(400);
			}
		}
	}
}


