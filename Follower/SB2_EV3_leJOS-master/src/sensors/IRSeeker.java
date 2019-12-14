package sensors;

import footballBot.Move;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicIRSeekerV2;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class IRSeeker {

	private HiTechnicIRSeekerV2 ir1;
	private static SensorMode seek;
	private static float[] sample;
	private byte[] buf = new byte[1]; 

	@SuppressWarnings("deprecation")
	/**
	 * Initialisieren des Hightechnic Infarotsensor für den Infarotball
	 * @param port Port in welchem der Sensor im EV3-Baustein eingesteckt ist
	 */
	public IRSeeker(Port port) {
		
		this.ir1 = new HiTechnicIRSeekerV2(port);
		this.seek = ir1.getModulatedMode();
		this.sample = new float[seek.sampleSize()];
		ir1.getData(0x48, buf, 1);
		ir1.getAddress();

	}
	
	/**
	 * Das Register für die Distanz des IRSeeker wird ausgegeben.
	 * @return Die Signalstärke des Beacon vom IRBall, damit man weiß wie weit dieser entfernt ist.
	 */
	public float getDistance() {
		ir1.getData(0x48, buf, 1);
		float distance = Float.NaN;
		if(buf[0] > 0)
			distance = buf[0];		
//		System.out.println("Beacon strenght = " + distance);
//		Delay.msDelay(3000);
		return distance;
	}

	/**
	 * Richtung des Infarotbeacon vom Infarotball finden
	 * @return Richtung des Infarotbeacon
	 */
	public static int getBeacon() {
		seek.fetchSample(sample, 0);
		int direction = (int) sample[0];
//		System.out.println("Direction: " + direction);
		return direction;
	}

	/**
	 * TODO: Nochmal besprechen
	 */
	public static void driveToBall(int speed) {
		if (getBeacon() == 0) {
			Move.driveForward(speed);
		} else if (getBeacon() > 0) {
			Move.driveCurve(0, speed);
		} else {
			Move.driveCurve(speed, 0);
		}
	}

}
