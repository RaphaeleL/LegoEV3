package modes;

import footballBot.Move;
import lejos.utility.Delay;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class BoardsBehavior {
	
	/**
	 * Falls der Ultraschallsensor des Roboters zu nah an ein Hinderniss fährt
	 * stoppt er, fährt ein Stück zurück und macht eine halbe Drehung.
	 * @param ur Port des Ultraschallsensor
	 * @param move Initialisierten Fortbewegungsmotoren
	 */
	public static void driveBackwardsAndTurn(UltrasonicSensor ur, Move move) {

		//TODO: Hier noch jeweils die Channels anpassen
		// turningLogic() ganz raus, da sowieso nach Beacon ausrichten?
			move.stopDrivingForward();
			move.driveBackward(100);
			Delay.msDelay(1000);
			move.turnAround(150, 150);
			Delay.msDelay(2000);

		
	}

}
