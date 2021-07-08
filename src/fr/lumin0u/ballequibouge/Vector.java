package fr.lumin0u.ballequibouge;

public class Vector implements Cloneable
{
	private double x;
	private double y;

	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector(Point p0, Point p1)
	{
		x = p1.getX()-p0.getX();
		y = p1.getY()-p0.getY();
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
	
	public Vector add(Vector v)
	{
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	
	public Vector subtract(Vector v)
	{
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}

	public double size()
	{
		return Utils.distance(new Point(0, 0), new Point(x, y));
	}

	public double angle()
	{
		double angle = Math.atan(y / x);

		if(x * y < 0)
			angle = Math.PI - angle;
		
		return angle;
	}
	
	public Vector multiply(double m)
	{
		x *= m;
		y *= m;
		return this;
	}
	
	public Vector normalize()
	{
		double x = this.x;
		this.x = x/Math.sqrt(x*x+y*y);
		y = y/Math.sqrt(x*x+y*y);
		
		return this;
	}
	
	public double dot(Vector other)
	{
		return other.x * this.x + other.y * this.y;
	}

	public static Vector fromAngle(double angle, double radius)
	{
		return new Vector(Math.cos(Utils.fineAngle(angle)) * radius, Math.sin(Utils.fineAngle(angle)) * radius);
	}
	
	@Override
	public Vector clone()
	{
		try
		{
			return (Vector)super.clone();
		} catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString()
	{
		return "Vector{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
