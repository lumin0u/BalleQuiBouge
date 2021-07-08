package fr.lumin0u.ballequibouge;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Pan
{
	public List<Point> mark = new ArrayList<>();
	
	public void drawBall(Graphics g, Ball ball)
	{
		drawBall(g, ball.getRayon(), ball.getLocation().getX(), ball.getLocation().getY(), ball.getColor());
	}
	
	public void drawBall(Graphics g, double dRayon, double dx, double dy, Color color)
	{
		int rayon = (int)dRayon;
		int x = (int)dx;
		int y = (int)dy;
		
		g.setColor(color);
//		g.fillRect(x-1, y-1, 2, 2);
		g.fillOval(x-rayon, y-rayon, rayon*2, rayon*2);
		
		int red = color.getRed()-30;
		int green = color.getGreen()-30;
		int blue = color.getBlue()-30;
		
		if(red < 0) {red = 0; green-=15; blue-=15;}
		if(green < 0) {green = 0; red-=15; blue-=15;}
		if(blue < 0) {blue = 0; red-=15; green-=15;}
		
		if(red < 0) red = 0;
		if(green < 0) green = 0;
		if(blue < 0) blue = 0;
		
		g.setColor(new Color(red, green, blue));
		g.drawOval(x-rayon, y-rayon, rayon*2, rayon*2);
	}
	
	public void drawBallWithoutBorders(Graphics g, double dRayon, double dx, double dy, Color color)
	{
		int rayon = (int)dRayon;
		int x = (int)dx;
		int y = (int)dy;
		
		g.setColor(color);
		g.fillOval(x-rayon/2, y-rayon/2, rayon+2, rayon+2);
	}
	
	List<Double> lasts = new ArrayList<>();
	
	public void everyTime(double relativeTime)
	{
		Graphics g = Fenetre.getInstance().graphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, (int)Fenetre.getInstance().graphicsSize().getWidth(), (int)Fenetre.getInstance().graphicsSize().getHeight());

		g.setColor(Color.black);
		double currentForce = Main.getInstance().getBallManager().getBalls().stream().mapToDouble(ball -> ball.getVelocity().size()).sum();
		g.drawString((lasts.stream().mapToDouble(Double::doubleValue).max().orElse(0))+"", 0, 10);
		lasts.add(currentForce);
		if(lasts.size() > 10)
			lasts.remove(0);
//		g.setColor(Color.black);
//		g.drawString(MyThread.INITIAL_DELAY-Main.getInstance().getMyThread().tps()+"", 30, 50);
		
		g.setColor(Color.red);
		for(Point p : mark)
			g.fillRect((int)p.getX(), (int)p.getY(), 1, 1);
		
		for(Ball b : Main.getInstance().getBallManager().getBalls())
			drawBall(g, b.getRayon(), b.getLocation().getX()+b.getVelocity().getX()*relativeTime, b.getLocation().getY()+b.getVelocity().getY()*relativeTime, b.getColor());
		g.setColor(Color.black);
		for(ObstacleLine ol : Main.getInstance().getBallManager().getObstacles())
			g.drawLine((int)ol.getP0().getX(), (int)ol.getP0().getY(), (int)ol.getP1().getX(), (int)ol.getP1().getY());
	}
	
	public void drawObstacleLine(Graphics g, ObstacleLine ol)
	{
		g.drawLine((int)ol.getP0().getX(), (int)ol.getP0().getY(), (int)ol.getP1().getX(), (int)ol.getP1().getY());
	}
	
	public void eraseObstacleLine(Graphics g, ObstacleLine ol)
	{
		g.setColor(Color.WHITE);
		g.drawLine((int)ol.getP0().getX(), (int)ol.getP0().getY(), (int)ol.getP1().getX(), (int)ol.getP1().getY());
	}
	
	public void drawPoint(Graphics g, int x, int y)
	{
		g.fillRect(x-2, y-2, 2, 2);
	}
}
