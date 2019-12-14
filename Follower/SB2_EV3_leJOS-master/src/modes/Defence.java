package modes;

import footballBot.Move;
import lejos.utility.Delay;
import sensors.*;

public class Defence{
	
	/**
	 * Konstruktor zum initialisieren der Sensoren
	 * @param ir Port des Infarotsensors von Lego
	 * @param ur Port des Ultraschallsensor
	 * @param move Initialisierten Fortbewegungsmotoren
	 * @param channel Auf welchem Kanal die Infarotfernbedinung eingestellt ist zu der Roboter fahren soll
	 */
	public Defence(IRSensor ir, UltrasonicSensor ur, Move move, int channel) {
		super();
		this.ir = ir;
		this.ur = ur;
		this.move = move;
		this.channel = channel;
	}

	public IRSensor ir;
	public UltrasonicSensor ur;
	public Move move;
	public int channel;
	
	/**
	 * Der Robotor fährt vor sein eigenes Tor und dreht sich um 180 Grad, um sein Tor zu verteidigen
	 * @param ir Port des Infarotsensors von Lego
	 * @param ur Port des Ultraschallsensor
	 * @param move Initialisierten Fortbewegungsmotoren
	 * @param channel Auf welchem Kanal die Infarotfernbedinung eingestellt ist zu der Roboter fahren soll
	 * @param speed Geschwindigkeit mit der, der Roboter sich fortbewegt
	 * 
	 */
	public static void playDef(IRSensor ir, UltrasonicSensor ur, Move move, int channel, int speed) {

		ModesController.setIsInDefenceMode(true);
		DetectBeacon.detectBeacon(ir, ur, move, channel, speed);
		
		if(ur.getDistance() <= 20.0)
			BoardsBehavior.driveBackwardsAndTurn(ur, move);
		
		
		if (ir.getDistance(channel +1) <= 20.0) {
			move.turnAround(100, 100);
			Delay.msDelay(3800);
			ModesController.setIsInDefenceMode(false);
			ModesController.setIsInDriveToBallMode(true);
//			ModesController.setIsInGamingMode(false);
		}
	}
	
	
}
