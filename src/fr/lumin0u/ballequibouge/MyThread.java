package fr.lumin0u.ballequibouge;

import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread
{
	private long delay;
	public static long FRAME_PER_SECOND = 60;
	public static final long INITIAL_DELAY = 10;
	private long lastLastTick = System.currentTimeMillis();
	private long lastTick = System.currentTimeMillis();
	private boolean fpsDebug;
	private List<Runnable> runLater = new ArrayList<>();
	// private long lastWait = System.currentTimeMillis();
	
	@Override
	public void run()
	{
		delay = INITIAL_DELAY;
		long time = 0;
		long lastWait = System.currentTimeMillis();
		
		while(true)
		{
			time++;
			
			if(System.currentTimeMillis() > lastWait + 1)
			{
				try
				{
					Thread.sleep(1);
					for(Runnable runnable : new ArrayList<>(runLater))
						runnable.run();
					runLater.clear();
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			if(time % delay == 0)
			{
				Main.getInstance().getBallManager().moveAndBounce();
				lastLastTick = lastTick;
				lastTick = System.currentTimeMillis();
			}
			if((time % (FRAME_PER_SECOND > 1000 ? 1 : (1000 / FRAME_PER_SECOND)) == 0 && !fpsDebug) || (fpsDebug && time % delay == 0))
			{
				Fenetre.getInstance().updateGraphics();
				Fenetre.getInstance().getPan().everyTime((double)time % (double)delay / (double)delay);
			}
			// lastWait = System.currentTimeMillis();
		}
	}
	
	public long tps()
	{
		return lastTick == lastLastTick ? 0 : lastTick - lastLastTick;
	}
	
	public void setDelay(long delay)
	{
		this.delay = delay;
	}
	
	public void fpsDebug(boolean debug)
	{
		this.fpsDebug = debug;
	}
	
	public void runLater(Runnable runnable)
	{
		runLater.add(runnable);
	}
}
