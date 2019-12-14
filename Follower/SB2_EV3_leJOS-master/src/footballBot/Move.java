package footballBot;
import modes.Shot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Move {

	static RegulatedMotor motor1;
	static RegulatedMotor motor2;
	
/***
 * Initialisieren der Motorpositionen im EV3 Baustein
 * @param a Port des linken Motors (von vorn betrachtet)
 * @param d Port des rechten Motors (von vorn betrachtet)
 */
	public Move(Port a, Port d) {
		this.motor1 = new EV3LargeRegulatedMotor(a);
		this.motor2 =new EV3LargeRegulatedMotor(d);
	}

	public static void driveForward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.forward();
		motor2.forward();
//		motor1.setAcceleration(150);
//		motor2.setAcceleration(150);
	}

	public static void driveBackward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.backward();
		motor2.backward();
	}

	public static void driveCurve(int speedLeft, int speedRight) {
		motor1.setSpeed(speedLeft);
		motor2.setSpeed(speedRight);
		motor1.forward();
		motor2.forward();
	}

	public static void stopDrivingForward() {
//		Shot.grabBall();
		motor1.stop();
		motor2.stop();

//		motor1.close();
//		motor2.close();
//		Shot.releaseBall();
	}
	
	public static void stopDrivingBackward() {
		motor1.stop();
		motor2.stop();

		motor1.close();
		motor2.close();
		Shot.releaseBall();
	}

	public static void turnAround(int speedLeft, int speedRight) {
		motor1.setSpeed(speedLeft);
		motor2.setSpeed(speedRight);
		motor1.forward();
		motor2.backward();
	}
}