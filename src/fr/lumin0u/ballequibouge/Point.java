package fr.lumin0u.ballequibouge;

public class Point implements Cloneable
{
	private double x;
	private double y;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public Point setX(double x)
	{
		this.x = x;
		return this;
	}

	public Point setY(double y)
	{
		this.y = y;
		return this;
	}
	
	public Point add(Point p)
	{
		this.x += p.x;
		this.y += p.y;
		return this;
	}
	
	public Point add(Vector p)
	{
		this.x += p.getX();
		this.y += p.getY();
		return this;
	}
	
	public Vector toVector()
	{
		return new Vector(x, y);
	}
	
	public double distance(Point p)
	{
		return Utils.distance(this, p);
	}

	@Override
	public String toString()
	{
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public Point clone()
	{
		try
		{
			return (Point)super.clone();
		}catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
