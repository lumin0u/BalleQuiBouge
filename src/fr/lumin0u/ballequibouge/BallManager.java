package fr.lumin0u.ballequibouge;

import java.util.ArrayList;
import java.util.List;

public class BallManager
{
	private List<Ball> balls;
	private List<ObstacleLine> obstacles;
	//	private boolean go;
	
	public BallManager()
	{
		//		go = true;
		
		balls = new ArrayList<>();
		
		// Pan pan = Fenetre.getInstance().getPan();
		
		obstacles = new ArrayList<>();
		// obstacles.add(new ObstacleLine(0, 0, 0, pan.getHeight()));
		// obstacles.add(new ObstacleLine(0, 0, pan.getWidth(), 0));
		// obstacles.add(new ObstacleLine(pan.getWidth(), 0, pan.getWidth(), pan.getHeight()));
		// obstacles.add(new ObstacleLine(0, pan.getHeight(), pan.getWidth(), pan.getHeight()));
	}
	
	public void registerABall(Ball b)
	{
		//		while(!go)
		//		{
		//			try
		//			{
		//				Thread.sleep(1);
		//			} catch(InterruptedException e)
		//			{
		//				e.printStackTrace();
		//			}
		//		}
		
		balls.add(b);
	}
	
	public void registerAnObstacle(ObstacleLine o)
	{
		//		while(!go)
		//		{
		//			try
		//			{
		//				Thread.sleep(10);
		//			} catch(InterruptedException e)
		//			{
		//				e.printStackTrace();
		//			}
		//		}
		
		obstacles.add(o);
	}
	
	public void restartObstacles()
	{
		Pan pan = Fenetre.getInstance().getPan();
		
		for(ObstacleLine ol : obstacles)
		{
			pan.eraseObstacleLine(Fenetre.getInstance().graphics(), ol);
		}
		
		// obstacles = new ArrayList<>();
		// obstacles.add(new ObstacleLine(0, 0, 0, pan.getHeight()));
		// obstacles.add(new ObstacleLine(0, 0, pan.getWidth(), 0));
		// obstacles.add(new ObstacleLine(pan.getWidth(), 0, pan.getWidth(), pan.getHeight()));
		// obstacles.add(new ObstacleLine(0, pan.getHeight(), pan.getWidth(), pan.getHeight()));
	}
	
	public List<Ball> getBalls()
	{
		return balls;
	}
	
	public List<ObstacleLine> getObstacles()
	{
		return obstacles;
	}
	
	public void moveAndBounce()
	{
		//		go = false;
		
		Fenetre f = Fenetre.getInstance();
		
		for(int ballIndex = 0; ballIndex < balls.size(); ballIndex++)
		{
			Ball ball = balls.get(ballIndex);
			// MOUVEMENT
			
			//			else
			//				ball.addToV(0, Physics.GRAVITE_Y());
			
			// SI IMMOBILE
			
			//			if(ball.getVelocity().getX() < 0.001 && ball.getVelocity().getY() < Physics.GRAVITE_Y / 2 && ball.getVelocity().getX() > -0.001 && ball.getVelocity().getY() > 0 - Physics.GRAVITE_Y / 2)
			//				ball.setImmobile(true);
			//
			//			else
			//				ball.setImmobile(false);
			//
			//			if(ball.isImmobile())
			//			{
			//				double perte = 1;
			//
			//				ball.setRayon(ball.getRayon() * (1 - perte / 100));
			//			}
			
			// REBONDIR
			
//			ball.addToV(Physics.GRAVITY().getX(), Physics.GRAVITY().getY());
			
			if(ball.getLocation().getX() + ball.getRayon() / 2 > f.graphicsSize().getWidth())
			{
				ball.addToV(-Physics.GRAVITY().getX(), 0);
				ball.getVelocity().setX(0 - ball.getVelocity().getX());
				ball.getLocation().setX(f.graphicsSize().getWidth() - ball.getRayon() / 2);
			}
			
			else if(ball.getLocation().getX() - ball.getRayon() / 2 < 0)
			{
				ball.addToV(-Physics.GRAVITY().getX(), 0);
				ball.getVelocity().setX(0 - ball.getVelocity().getX());
				ball.getLocation().setX(ball.getRayon() / 2);
			}
			
			else
				ball.addToV(Physics.GRAVITY().getX(), 0);
			
			if(ball.getLocation().getY() + ball.getRayon() / 2 > f.graphicsSize().getHeight())
			{
				ball.addToV(0, -Physics.GRAVITY().getY());
				ball.getVelocity().setY(0 - ball.getVelocity().getY());
				ball.getLocation().setY(f.graphicsSize().getHeight() - ball.getRayon() / 2);
			}
			
			else if(ball.getLocation().getY() - ball.getRayon() / 2 < 0)
			{
				ball.addToV(0, -Physics.GRAVITY().getY());
				ball.getVelocity().setY(0 - ball.getVelocity().getY());
				ball.getLocation().setY(ball.getRayon() / 2);
			}
			
			else
				ball.addToV(0, Physics.GRAVITY().getY());
			
			ball.getVelocity().multiply(1-(Physics.AIR_LOSS / 100));
//			ball.addToV(0 - ball.getVelocity().getX() * (Physics.AIR_LOSS / 100), 0 - ball.getVelocity().getY() * (Physics.AIR_LOSS / 100));
			
			Point nextLocation = ball.getLocation().clone().add(ball.getVelocity());
			
			if(nextLocation.getX() + ball.getRayon() / 2 > f.graphicsSize().getWidth() || nextLocation.getX() - ball.getRayon() / 2 < 0 || nextLocation.getY() + ball.getRayon() / 2 > f.graphicsSize().getHeight() || nextLocation.getY() - ball.getRayon() / 2 < 0)
				ball.addToV(0 - ball.getVelocity().getX() * ((Physics.FRICTION_LOSS - Physics.AIR_LOSS) / 100), 0 - ball.getVelocity().getY() * ((Physics.FRICTION_LOSS - Physics.AIR_LOSS) / 100));
			
			//COLLISIONS
			
			if(Physics.COLLISIONS)
			{
				for(int ballIndex1 = 0; ballIndex1 < ballIndex; ballIndex1++)
				{
					Ball ball1 = balls.get(ballIndex1);
					
					if(ball1 == ball || ball1.getLocation().distance(ball.getLocation().clone().add(ball.getVelocity())) > ball1.getRayon() + ball.getRayon())
						continue;
					
					//					double currentForce = (ball.getVelocity().size() + ball1.getVelocity().size())*(1.0D - Physics.FRICTION_LOSS / 100);
					ball.setLocation(ball1.getLocation().clone().add(new Vector(ball1.getLocation(), ball.getLocation()).normalize().multiply(ball.getRayon() + ball1.getRayon())));
					Vector v0 = ball.getVelocity();
					Vector v1 = ball1.getVelocity();
					double m0 = ball.getRayon() * ball.getRayon();
					double m1 = ball1.getRayon() * ball1.getRayon();
					
					Vector vAB = new Vector(ball.getLocation(), ball1.getLocation()).normalize();
					
					Vector vaScalairized = vAB.clone().multiply(vAB.dot(v0));
					Vector vbScalairized = vAB.clone().multiply(vAB.dot(v1));
					
					Vector diffScalaires = vbScalairized.clone().subtract(vaScalairized);
					
					ball.setVelocity(v0.clone().add(diffScalaires));
					ball1.setVelocity(v1.clone().subtract(diffScalaires));
//					System.out.println(v0);
//					System.out.println(v0.clone().add(diffScalaires));
					/*
					double v0_ = v0 * (m0 - m1) / (m0 + m1) + v1 * (2 * m1) / (m0 + m1);
					double v1_ = v0 * (2 * m0) / (m0 + m1) + v1 * (m1 - m0) / (m0 + m1);
					//TODO https://babel.cegep-ste-foy.qc.ca/profs/rfoy/capsules/collisions.html
					ball.setVelocity(ball.getVelocity().normalize().multiply(v0_ / (v0_ + v1_)).add(new Vector(ball1.getLocation(), ball.getLocation()).normalize().multiply(v1_ / (v0_ + v1_))).multiply(0.5 * v0_));
					ball1.setVelocity(ball1.getVelocity().normalize().multiply(v1_ / (v0_ + v1_)).add(new Vector(ball.getLocation(), ball1.getLocation()).normalize().multiply(v0_ / (v0_ + v1_))).multiply(0.5 * v1_));
					*/
				}
			}
			
			// AVEC OBSTACLE
			
			for(ObstacleLine ol : obstacles)
			{
				double olA = (ol.getP1().getY() - ol.getP0().getY()) / (ol.getP1().getX() - ol.getP0().getX());
				double olB = ol.getP0().getY() - olA * ol.getP0().getX();
				//				double bA = ball.getVelocity().getY() / ball.getVelocity().getX();
				//				double bB = ball.getLocation().getY() - bA * ball.getLocation().getX();
				
				double angle = ol.asVector().angle() % Math.PI;
				
				Point inter;
				
				//				if(olA == bA)
				//					inter = null;
				//				else if(Double.isInfinite(olA))
				//					inter = new Point(ol.getP0().getX(), bA*ol.getP0().getX()+bB);
				//				else if(Double.isInfinite(bA))
				//					inter = new Point(ball.getLocation().getX(), olA*ball.getLocation().getX()+olB);
				//				else
				//					inter = new Point((olB-bB)/(bA-olA), olA*(olB-bB)/(bA-olA) + olB);
				
				if(angle == 0)
					inter = new Point(ball.getLocation().getX(), ol.asVector().getY());
				else if(angle == Math.PI / 2)
					inter = new Point(ol.asVector().getX(), ball.getLocation().getY());
				else
				{
					double olX = (ball.getLocation().getY() - olB) / olA;
					double olY = ball.getLocation().getX() * olA + olB;
					double xFactor = olX / (olX + olY);
					double yFactor = 1 - xFactor;
					inter = new Point(ball.getLocation().getX() * yFactor + olX * xFactor, ball.getLocation().getY() * xFactor + olY * yFactor);
				}
				
				if(inter != null)
					f.getPan().mark.add(inter);
				
				while(f.getPan().mark.size() > 20)
					f.getPan().mark.remove(0);
				
				if(inter.distance(ball.getLocation()) < ball.getRayon() && ol.getP0().distance(inter) < ol.asVector().size() && ol.getP1().distance(inter) < ol.asVector().size())
				{
					Vector vector = new Vector(inter, ball.getLocation());
					vector.normalize();
					ball.setVelocity(vector.multiply(ball.getVelocity().size() * (1.0D - Physics.FRICTION_LOSS / 100)));
					//					double ballAngle = Utils.fineAngle(ball.getVelocity().angle());
					//					double newAngle = 2 * angle - ballAngle;
					//					System.out.println("----------------");
					//					System.out.println((int) (ballAngle / Math.PI * 180));
					//					System.out.println((int) (angle / Math.PI * 180));
					//					System.out.println((int) (newAngle / Math.PI * 180));
					//					ball.setLocation(inter.clone().add(new Vector(inter, ball.getLocation()).normalize().multiply(ball.getRayon())));
					//					ball.setVelocity(Vector.fromAngle(newAngle, ball.getVelocity().size()));
				}
			}
			
			ball.getLocation().setX(ball.getLocation().getX() + ball.getVelocity().getX());
			ball.getLocation().setY(ball.getLocation().getY() + ball.getVelocity().getY());
		}
		
		//		go = true;
	}
	
	public void deleteBalls()
	{
		balls = new ArrayList<>();
	}
}
