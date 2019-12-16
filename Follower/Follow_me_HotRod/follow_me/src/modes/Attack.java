package modes;

import followme.Move;
import sensors.*;

public class Attack {
    /**
     * 
     * @param ir Infarotsensor von Lego.
     * @param ur Ultraschallsensor von Lego.
     * @param move Iniziert Motoren zu Fortbewegung.
     * @param channel Verschiedene Kanle der Infarotfernbedienungen, welche man anfahren mchte.
     * @param shot Initiert den Schussmechanismus, welchen den dazugehrigen Motor enthlt.
     * @param speed Geschwindigkeit, mit welcher sich der Roboter fortbewegen soll.
     * 
     * Methode ist zustndig darfr den Beacon der Infarotfernbedienung des Gegnerischen Tors anzufahren und bei einem Abstand von
     * 20cm oder weniger einen Schuss aufs Tor zu machen.
     * Ebenfalls wird mit dem Ultraschallsensor geprft ob sich ein Hinderniss nher als 20cm ist, falls ja wird etwas zurckgefahren 
     * und sich gedreht.
     */
	public static void playAttack(IRSensor ir, UltrasonicSensor ur, Move move, int channel, Shot shot, int speed) {
		
//		ModesController.setIsInAttackMode(true);
//		if(shot.isPressed()) {
//			shot.backToPosition();
//		}
		
		if(ur.getDistance() <= 20.0)
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		
		if( ir.getDistance(channel + 1) <= 25.0)
			DetectBeacon.detectBeacon(ir, ur, move, channel, (speed - 150));
		else
			DetectBeacon.detectBeacon(ir, ur, move, channel, speed);
					

		if (ir.getDistance((channel + 1)) <= 20.0) {
			shot.shot();
			ModesController.setIsInAttackMode(false);
			modes.ModesController.setIsInDefenceMode(true);
		}

	}

}
