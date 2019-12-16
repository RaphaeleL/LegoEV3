package modes;

import followme.Move;
import lejos.utility.Delay;
import sensors.*;

public class Defence{
	
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
		}
	}
	
	
}
