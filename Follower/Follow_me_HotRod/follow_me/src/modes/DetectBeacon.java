package modes;

import followme.Move;
import sensors.*;

public class DetectBeacon {
	
	public static boolean beaconIsOnRightSide;

	public static void detectBeacon(IRSensor ir, UltrasonicSensor ur, Move move, int channel, int speed) {
		
		if(ur.getDistance() <= 13.0)
			BoardsBehavior.driveBackwardsAndTurn(ur, move);


		if (ir.getDirectionFromBeacon(channel) > 3) {
			beaconIsOnRightSide = true;
			move.driveCurve((speed / 2), 0);
		}
		if (ir.getDirectionFromBeacon(channel) < -3) {
			beaconIsOnRightSide = false;
			move.driveCurve(0, (speed / 2));
		}

		if ((ir.getDirectionFromBeacon(channel) <= 4 && ir.getDirectionFromBeacon(channel) >= -4)) {
			beaconIsOnRightSide = false;
			move.driveForward(speed);
		}
	}
	
	public static boolean getIsOnRightSide() {
		return beaconIsOnRightSide;
	}
	
	public static void setIsOnRightSide(boolean isOnRightSide) {
		DetectBeacon.beaconIsOnRightSide = isOnRightSide;
	}

}
