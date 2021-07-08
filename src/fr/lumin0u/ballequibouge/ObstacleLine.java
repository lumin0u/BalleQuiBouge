package fr.lumin0u.ballequibouge;

public class ObstacleLine
{
	private Point p0;
	private Point p1;
	
	public ObstacleLine(Point p0, Point p1)
	{
		this.p0 = p0;
		this.p1 = p1;
	}

	public Point getP0()
	{
		return p0;
	}

	public Point getP1()
	{
		return p1;
	}
	
	public Vector asVector()
	{
		return new Vector(p1.getX()-p0.getX(), p1.getY()-p0.getY());
	}
}
