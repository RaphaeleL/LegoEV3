package followme;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Move {

	static RegulatedMotor motor1;
	static RegulatedMotor motor2;
	
	public Move(Port a, Port d) {
		this.motor1 = new EV3LargeRegulatedMotor(a);
		this.motor2 =new EV3LargeRegulatedMotor(d);
	}

	public static void driveForward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.forward();
		motor2.forward();
	}

	public static void driveBackward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.backward();
		motor2.backward();
	}

	public static void driveCurve(int speedLeft, int speedRight) {
		motor1.setSpeed(speedRight);
		motor2.setSpeed(speedLeft);
		motor1.forward();
		motor2.forward();
	}

	public static void stopDrivingForward() {
		motor1.stop();
		motor2.stop();
	}
	
	public static void stopDrivingBackward() {
		motor1.stop();
		motor2.stop();

		motor1.close();
		motor2.close();
	}

	public static void turnAround(int speedLeft, int speedRight) {
		motor1.setSpeed(speedLeft);
		motor2.setSpeed(speedRight);
		motor1.forward();
		motor2.backward();
	}
}
