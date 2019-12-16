package modes;

import followme.Move;
import sensors.IRSeeker;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class DriveToBall {

	public static void driveToBall(IRSensor ir, UltrasonicSensor ur, Move move, int channel, Shot shot, int speed) {

		ModesController.setIsInDriveToBallMode(true);
//		if(shot.isPressed()) {
//			shot.backToPosition();
//		}
		
		
		if(ur.getDistance() <= 20.0)
			BoardsBehavior.driveBackwardsAndTurn(ur, move);

//		if (ur.getDistance() <= 1.0) {
//			ModesController.setIsInDriveToBallMode(false);
//			ModesController.setIsInPossesion(true);
//			Attack.playAttack(ir, ur, move, channel, shot, speed);
//		}

	}
	
	public static void driveToBall(IRSeeker is, UltrasonicSensor ur, Move move) {
		if(ur.getDistance() <= 20.0) {
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		}
		is.driveToBall(400);
	}

}
