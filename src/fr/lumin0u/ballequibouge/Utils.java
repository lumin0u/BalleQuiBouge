package fr.lumin0u.ballequibouge;

public class Utils
{
	public static double distance(Point p0, Point p1)
	{
		return Math.sqrt(square(p1.getX()-p0.getX())+square(p1.getY()-p0.getY()));
	}
	
	public static double square(double a)
	{
		return a*a;
	}
	
	public static double fineAngle(double a)
	{
		if(!Double.isFinite(a) || Double.isNaN(a))
			return 0;
		
		while(a < 0)
			a+=2*Math.PI;
		while(a > 2*Math.PI)
			a-=2*Math.PI;
		
		return a;
	}
}
