package followme;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Main {
	
	// Initialisierung der Hi Technik Infrarod Sensors
	private static IRSeeker is = new IRSeeker(SensorPort.S3);
	// Initialisierung der Motoren
	private static Move move = new Move(MotorPort.A, MotorPort.D);
	// Initialisierung des Ultraschallsensors
	private static UltrasonicSensor ur = new UltrasonicSensor(SensorPort.S2);
	
	// Fahre etwas zurück und drehe dich (z. B. gegen etwas gefahren)
	public static void driveBackwardsAndTurn() {
		Move.stopDrivingForward();
		Move.driveBackward(100);
		Delay.msDelay(1000);
		Move.turnAround(150, 150);
		Delay.msDelay(2000);
	}
	
	public static void main(String[] args) {
		// Gebe "Follower" auf dem Brick Display aus
		System.out.println("Follower");
		// Endlosschleife
		while (true) {
			// Distanz okay? 
			if (ur.getDistance() > 30.0 || Float.isNaN(ur.getDistance())) {
				IRSeeker.driveToBall(400);
			// Distanz nicht okay!
			} else {
				Move.stopDrivingForward();
			}
		}
	}
}


