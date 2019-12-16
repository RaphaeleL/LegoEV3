package modes;

import followme.Move;
import sensors.IRSeeker;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class DriveToBall {

	public static void driveToBall(IRSensor ir, UltrasonicSensor ur, Move move, int channel, Shot shot, int speed) {

		ModesController.setIsInDriveToBallMode(true);
		if(ur.getDistance() <= 20.0){
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		}
	}
	
	public static void driveToBall(IRSeeker is, UltrasonicSensor ur, Move move) {
		if(ur.getDistance() <= 20.0) {
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		}
		is.driveToBall(400);
	}

}
