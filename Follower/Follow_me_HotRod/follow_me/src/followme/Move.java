package followme;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Move {

	static RegulatedMotor motor1;
	static RegulatedMotor motor2;

	// Konstruktor (mache die Motoren ready)
	public Move(Port a, Port d) {
		this.motor1 = new EV3LargeRegulatedMotor(a);
		this.motor2 =new EV3LargeRegulatedMotor(d);
	}

	// Fahre gerade aus (beide Motoren identisch)
	public static void driveForward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.forward();
		motor2.forward();
	}

	// Fahre zurück aus (beide Motoren identisch)
	public static void driveBackward(int speed) {
		motor1.setSpeed(speed);
		motor2.setSpeed(speed);
		motor1.backward();
		motor2.backward();
	}

	// Fahre eine Kurve (ein Motor schneller als der andere)
	public static void driveCurve(int speedLeft, int speedRight) {
		motor1.setSpeed(speedRight);
		motor2.setSpeed(speedLeft);
		motor1.forward();
		motor2.forward();
	}

	// Höre auf vorwärts zu fahren (beide Motoren identisch)
	public static void stopDrivingForward() {
		motor1.stop();
		motor2.stop();
	}
	
	// Höre auf rückwärts zu fahren (beide Motoren identisch)
	public static void stopDrivingBackward() {
		motor1.stop();
		motor2.stop();

		motor1.close();
		motor2.close();
	}

	// eine ganze Drehung
	public static void turnAround(int speedLeft, int speedRight) {
		motor1.setSpeed(speedLeft);
		motor2.setSpeed(speedRight);
		motor1.forward();
		motor2.backward();
	}
}
