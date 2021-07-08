package fr.lumin0u.ballequibouge;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main
{
	public static void main(String[] args)
	{
		Fenetre.FULLSCREEN = Arrays.stream(args).anyMatch(str -> str.equalsIgnoreCase("fullscreen"));
		Physics.ALTERNATIVE_GRAVITY = Arrays.stream(args).anyMatch(str -> str.equalsIgnoreCase("alternative"));
		
		Main main = new Main();
		instance = main;
		main.start();
	}
	
	private BallManager ballManager;
	private static Main instance;
	private MyThread myThread;
	private Fenetre fenetre;
	private FenetreOptions fenetreOptions;
	
	public void start()
	{
		fenetre = new Fenetre();
		fenetreOptions = new FenetreOptions();
		ballManager = new BallManager();
		myThread = new MyThread();
		
		Random r = new Random();
		
//		new Ball(100, 100, Color.BLACK, 30, 5, 0, true);
		
		for(int i = 0; i < Physics.NB_BALLS; i++)
		{
			new Ball(new Point(r.nextDouble()*Fenetre.getInstance().getWidth(), r.nextDouble()*Fenetre.getInstance().getHeight()), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), r.nextInt(Physics.RAYON_MAX-Physics.RAYON_MIN)+Physics.RAYON_MIN, new Vector(r.nextInt(60)-30, r.nextInt(40)-20), true);
		}
		
		FenetreOptions.getInstance().setSize(FenetreOptions.getInstance().getSize());
		myThread.start();
	}
	
	public static Main getInstance()
	{
		return instance;
	}
	
	public BallManager getBallManager()
	{
		return ballManager;
	}
	
	public MyThread getMyThread()
	{
		return myThread;
	}
	
	public MyThread reinitializeMyThread()
	{
		myThread = new MyThread();
		return myThread;
	}
	
	public Fenetre getFenetre()
	{
		return fenetre;
	}
	
	public FenetreOptions getFenetreOptions()
	{
		return fenetreOptions;
	}
	
	public void restart()
	{
		ballManager.deleteBalls();
		ballManager.restartObstacles();
		
		Random r = new Random();
		
		for(int i = 0; i < Physics.NB_BALLS; i++)
		{
			int velox=15;
			int veloy=10;
			Vector velocity = new Vector(r.nextInt(velox*2)-velox, r.nextInt(veloy*2)-veloy);
			new Ball(new Point(r.nextDouble()*Fenetre.getInstance().getWidth(), r.nextDouble()*Fenetre.getInstance().getHeight()), new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), r.nextInt(Physics.RAYON_MAX-Physics.RAYON_MIN)+Physics.RAYON_MIN, velocity, true);
		}
	}
}
