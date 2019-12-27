package sensors;

import followme.Move;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicIRSeekerV2;
import lejos.hardware.sensor.SensorMode;
public class IRSeeker {

	private HiTechnicIRSeekerV2 ir1;
	private static SensorMode seek;
	private static float[] sample;
	private byte[] buf = new byte[1]; 

	@SuppressWarnings("deprecation")
	public IRSeeker(Port port) {
		this.ir1 = new HiTechnicIRSeekerV2(port);
		this.seek = ir1.getModulatedMode();
		this.sample = new float[seek.sampleSize()];
		ir1.getData(0x48, buf, 1);
		ir1.getAddress();
	}
	
	public float getDistance() {
		ir1.getData(0x48, buf, 1);
		float distance = Float.NaN;
		if(buf[0] > 0) {
			distance = buf[0];
		}
		return distance;
	}

	public static int getBeacon() {
		seek.fetchSample(sample, 0);
		int direction = (int) sample[0];
		return direction;
	}

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
