package modes;

import footballBot.Move;
import sensors.*;

public class DetectBeacon {
	
	public static boolean beaconIsOnRightSide;

	/***
	 * Mist die Richtung des zu findenden Infarotbeacons und fährt dieses an
	 * @param ir Port des Infarotsensors
	 * @param ur Port des Ultraschallsensors
	 * @param move Initialisierung der Fortbewegungsmotoren
	 * @param channel Auf welchem Kanal die Infarotfernbedinung eingestellt ist zu der Roboter fahren soll
	 * @param speed Geschwindigkeit mit der, der Roboter sich fortbewegt
	 */
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
