package modes;

/**
 * Statemaschine um die Spielmodi des Roboters zu steuern
 *
 */
public class ModesController {
	private static boolean isInAttackMode;
	private static boolean isInDefenceMode;
	private static boolean isInDriveToBallMode;
	private static boolean isInPossesion;
	private static boolean isInGamingMode;
	
	public static boolean getIsInAttackMode() {
		return isInAttackMode;
	}
	public static void setIsInAttackMode(boolean isInAttackMode) {
		ModesController.isInAttackMode = isInAttackMode;
	}
	public static boolean getIsInDefenceMode() {
		return isInDefenceMode;
	}
	public static void setIsInDefenceMode(boolean isInDefenceMode) {
		ModesController.isInDefenceMode = isInDefenceMode;
	}
	public static boolean getIsInDriveToBallMode() {
		return isInDriveToBallMode;
	}
	public static void setIsInDriveToBallMode(boolean isInBallFollowingMode) {
		ModesController.isInDriveToBallMode = isInBallFollowingMode;
	}
	public static boolean getIsInPossesion() {
		return isInPossesion;
	}
	public static void setIsInPossesion(boolean isInPossesion) {
		ModesController.isInPossesion = isInPossesion;
	}
	
	public static boolean getIsInGamingMode() {
		return isInGamingMode;
	}
	public static void setIsInGamingMode(boolean isInGamingMode) {
		ModesController.isInGamingMode = isInGamingMode;
	}

}
