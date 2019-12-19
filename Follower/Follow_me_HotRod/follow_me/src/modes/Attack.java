package modes;

import followme.Move;
import sensors.*;

public class Attack {
	
	public static void playAttack(IRSensor ir, UltrasonicSensor ur, Move move, int channel, int speed) {
		
		if(ur.getDistance() <= 20.0)
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		
		if( ir.getDistance(channel + 1) <= 25.0)
			DetectBeacon.detectBeacon(ir, ur, move, channel, (speed - 150));
		else
			DetectBeacon.detectBeacon(ir, ur, move, channel, speed);
					

		if (ir.getDistance((channel + 1)) <= 20.0) {
			ModesController.setIsInAttackMode(false);
			modes.ModesController.setIsInDefenceMode(true);
		}

	}

}
