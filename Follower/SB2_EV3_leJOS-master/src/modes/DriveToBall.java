package modes;

import footballBot.Move;
import sensors.IRSeeker;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class DriveToBall {
	
	/**
	 * 
	 * @param ir Infarotsensor von Lego.
     * @param ur Ultraschallsensor von Lego.
     * @param move Iniziert Motoren zu Fortbewegung.
     * @param channel Verschiedene Kan�le der Infarotfernbedienungen, welche man anfahren m�chte.
     * @param shot Initiert den Schussmechanismus, welchen den dazugeh�rigen Motor enth�lt.
     * @param speed Geschwindigkeit, mit welcher sich der Roboter fortbewegen soll.
     * 
     * Wird nicht verwendet, war die alte Methode ohne den InfarotSeeker
	 */
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
	
	/**
	 *  
	 * @param is InfarotSeeker f�r den Infarotball von Hightechnic
	 * @param ur Ultraschallsensor von Lego.
     * @param move Iniziert Motoren zu Fortbewegung.
     * 
     * Wird zum Ball gefahren und falls sich ein Hindernis n�her als 20cm befindet wird zur�ckgefahren und sich gedreht.
     * 
	 */
	public static void driveToBall(IRSeeker is, UltrasonicSensor ur, Move move) {
		if(ur.getDistance() <= 20.0) {
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		}
		is.driveToBall(400);
	}

}
