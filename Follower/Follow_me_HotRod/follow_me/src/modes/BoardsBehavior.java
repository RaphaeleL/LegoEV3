package modes;

import followme.Move;
import lejos.utility.Delay;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class BoardsBehavior {
	
	public static void driveBackwardsAndTurn(UltrasonicSensor ur, Move move) {
			move.stopDrivingForward();
			move.driveBackward(100);
			Delay.msDelay(1000);
			move.turnAround(150, 150);
			Delay.msDelay(2000);
	}

}
