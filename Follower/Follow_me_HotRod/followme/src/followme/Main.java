package followme;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import modes.Shot;
import sensors.IRSeeker;
import sensors.IRSensor;
import sensors.UltrasonicSensor;

public class Main {
    
	private static boolean play = true;

	private static IRSensor ir = new IRSensor(SensorPort.S1);
	private static UltrasonicSensor ur = new UltrasonicSensor(SensorPort.S4);
	private static IRSeeker is = new IRSeeker(SensorPort.S3);
	private static Move move = new Move(MotorPort.A, MotorPort.D);
	private static Shot shot = new Shot();

	private static int gegnerChannel = 1;
	private static int eigenesTorChannel = 2;
	private static int gegnerischesTorChannel = 3;

	public static void main(String[] args) {

		modes.ModesController.setIsInGamingMode(true);
		System.out.println("In Main start");

		while (modes.ModesController.getIsInGamingMode() == true) {

			modes.ModesController.setIsInDriveToBallMode(true);

			while (modes.ModesController.getIsInDriveToBallMode() == true) {
				System.out.println("Drive to Ball");
				while (is.getDistance() < 60.0 || Float.isNaN(is.getDistance())) {
					modes.DriveToBall.driveToBall(is, ur, move); //
					System.out.println("Driving to Ball");
					// is.driveToBall(400);
				}
				// Shot.backToPosition(-35);
				// Delay.msDelay(100);

				modes.ModesController.setIsInDriveToBallMode(false);
				modes.ModesController.setIsInAttackMode(true);
			}

			// while (modes.ModesController.getIsInAttackMode() == true) {
			// System.out.println("play attack");
			// // modes.ModesController.setIsInAttackMode(true);
			// Sound.beep();

			while (modes.ModesController.getIsInAttackMode() == true) {
				System.out.println("Playing Attack");
				if (is.getDistance() <= 40.0) {
//					Sound.beep();
					modes.ModesController.setIsInAttackMode(false);
					modes.ModesController.setIsInDriveToBallMode(true);
					// Shot.backToPosition(50);
				} else {
					modes.Attack.playAttack(ir, ur, move, 0, shot, 400);
				}
			}
			// if (modes.ModesController.getIsInAttackMode() == true) {
			// modes.ModesController.setIsInAttackMode(false);
			// modes.ModesController.setIsInDefenceMode(true);
			// }
			// }
			// }
			while (modes.ModesController.getIsInDefenceMode() == true) {
				System.out.println("Play defence");
				modes.ModesController.setIsInDefenceMode(true);
				long before = System.currentTimeMillis();
				while (modes.ModesController.getIsInDefenceMode()) {
					System.out.println("Playing defence");
					modes.Defence.playDef(ir, ur, move, 2, 400);
				}
				modes.ModesController.setIsInDefenceMode(false);
				modes.ModesController.setIsInDriveToBallMode(true);
			}

		}
	}

	// Gegner hat den Ball, also zum eigenen Tor fahren
	private static boolean playDefence() {
		return equalInRange(ir.getDirectionFromBeacon(gegnerChannel), ur.getDirection(), 3)
				&& equalInRange(ir.getDistance(gegnerChannel), (int) ur.getDistance(), 3);
	}

	// Hat den Ball selbst, also zum Tor fahren
	private static boolean playAttack() {
		return ur.getDirection() < 3 && ur.getDirection() > -3 && ur.getDistance() < 10;
	}

	// Gegner hat nicht den Ball, also zum Ball fahren
	private static boolean driveToBall() {
		return !equalInRange(ir.getDirectionFromBeacon(gegnerChannel), ur.getDirection(), 3);
	}

	private static boolean driveToBallWhile() {
		return !playAttack() && modes.ModesController.getIsInDriveToBallMode();
	}

	public static boolean equalInRange(int directionFix, int directionVariable, int range) {
		return ((directionVariable + range) >= directionFix && (directionVariable - range) <= directionFix);
	}

	public static boolean setPlay(boolean status) {
		return play = status;
	}

}