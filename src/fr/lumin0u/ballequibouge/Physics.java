package fr.lumin0u.ballequibouge;

public class Physics
{
	public static double AIR_LOSS = 0.5;
	public static double FRICTION_LOSS = 10;
	public static Vector GRAVITY = new Vector(0, 0.5);
	public static int NB_BALLS = 5;
	public static int RAYON_MAX = 60;
	public static int RAYON_MIN = 20;
	public static boolean COLLISIONS = true;
	public static boolean ALTERNATIVE_GRAVITY = false;
	
	public static Vector GRAVITY()
	{
		double m = Math.cos((System.currentTimeMillis()%300000)/2000.0D);
		return ALTERNATIVE_GRAVITY ? GRAVITY.clone().multiply(m) : GRAVITY.clone();
	}
}
