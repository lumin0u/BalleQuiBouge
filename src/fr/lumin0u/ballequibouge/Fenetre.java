package fr.lumin0u.ballequibouge;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame
{
	private Pan pan;
	private static Fenetre instance;
	private BufferedImage image;
	public static boolean FULLSCREEN = true;
	private final int drawOffsetX;
	private final int drawOffsetY;
	
	public Fenetre()
	{
		instance = this;
		
		pan = new Pan();
		
		this.setTitle("Balles");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		if(FULLSCREEN)
		{
			drawOffsetX = 0;
			drawOffsetY = 0;
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setUndecorated(true);
		}
		else
		{
			drawOffsetX = 30;
			drawOffsetY = 40;
		}
		this.setVisible(true);
		
		image = getGraphicsConfiguration().createCompatibleImage(getWidth() - drawOffsetX * 2, getHeight() - drawOffsetY * 2);
		((Graphics2D) image.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//		pan.setSize(graphicsSize());
		
		this.addComponentListener(new ComponentListener()
		{
			@Override
			public void componentShown(ComponentEvent e)
			{
				
			}
			
			@Override
			public void componentResized(ComponentEvent e)
			{
				//				pan.setSize(getSize());
			}
			
			@Override
			public void componentMoved(ComponentEvent e)
			{
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e)
			{
				
			}
		});
		
		this.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				Main.getInstance().getMyThread().setDelay(50);
				Main.getInstance().getMyThread().fpsDebug(true);
				
				if(e.getKeyCode() == KeyEvent.VK_F11)
				{
					FULLSCREEN = true;
					Fenetre.this.dispose();
					Main.getInstance().getFenetreOptions().dispose();
					Main.getInstance().start();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				Main.getInstance().getMyThread().setDelay(MyThread.INITIAL_DELAY);
				Main.getInstance().getMyThread().fpsDebug(false);
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				
			}
		});
		
		this.addMouseListener(new MouseListener()
		{
			int x;
			int y;
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				Main.getInstance().getMyThread().runLater(new Runnable()
				{
					@Override
					public void run()
					{
						if(e.getButton() == 1)
							if(x != e.getX() || y != e.getY())
								Main.getInstance().getBallManager().registerAnObstacle(new ObstacleLine(new Point(x, y), new Point(e.getX(), e.getY())));
						
						if(e.getButton() == 3)
						{
							new Ball(new Point(x + drawOffsetX, y + drawOffsetY), Color.BLACK, 30, new Vector((e.getX() - x) / 10.0D, (e.getY() - y) / 10.0D), true);
						}
					}
				});
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				x = e.getX();
				y = e.getY();
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}
		});
	}
	
	public static Fenetre getInstance()
	{
		return instance;
	}
	
	public Pan getPan()
	{
		return pan;
	}
	
	public Graphics graphics()
	{
		return image.getGraphics();
	}
	
	public Dimension graphicsSize()
	{
		return new Dimension(getWidth() - drawOffsetX * 2, getHeight() - drawOffsetY * 2);
	}
	
	public void updateGraphics()
	{
		if(image.getWidth() != getWidth() - drawOffsetX * 2 || image.getHeight() != getHeight() - drawOffsetY * 2)
		{
			((Graphics2D) image.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			image = getGraphicsConfiguration().createCompatibleImage(getWidth() - drawOffsetX * 2, getHeight() - drawOffsetY * 2);
		}
		getGraphics().drawImage(image, drawOffsetX, drawOffsetY, this);
	}
}
