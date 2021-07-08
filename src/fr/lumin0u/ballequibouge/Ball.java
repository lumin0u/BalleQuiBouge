package fr.lumin0u.ballequibouge;

import java.awt.Color;

public class Ball
{
	private Point loc;
	private Color color;
	private double rayon;
	private Vector v;
	private boolean immobile;

	public Ball(Point loc, Color color, double rayon, Vector v, boolean register)
	{
		this.loc = loc;
		this.color = color;
		this.rayon = Math.abs(rayon);
		this.v = v;
		immobile = false;
		
		if(register)
			Main.getInstance().getBallManager().registerABall(this);
	}
	
	public Point getLocation()
	{
		return loc;
	}
	
	public void setLocation(Point p)
	{
		loc = p;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public double getRayon()
	{
		return rayon;
	}

	public void setRayon(double rayon)
	{
		this.rayon = Math.abs(rayon);
	}
	
	public Vector getVelocity()
	{
		return v;
	}

	public void setVelocity(Vector v)
	{
		this.v = v;
	}
	
	public void addToV(double x, double y)
	{
		v.setX(v.getX()+x);
		v.setY(v.getY()+y);
	}
	
	public void addToV(Vector vector)
	{
		v.setX(v.getX()+vector.getX());
		v.setY(v.getY()+vector.getY());
	}

	public boolean isImmobile()
	{
		return false;
	}

	public void setImmobile(boolean immobile)
	{
		this.immobile = immobile;
	}
}
