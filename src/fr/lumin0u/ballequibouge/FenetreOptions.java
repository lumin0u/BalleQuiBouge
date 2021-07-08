package fr.lumin0u.ballequibouge;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class FenetreOptions extends JFrame
{
	private static FenetreOptions instance;
	
	public FenetreOptions()
	{
		instance = this;
		
		this.setVisible(true);
		this.setTitle("Options");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel pan = new JPanel();
		
		JLabel loseInAirText = new JLabel("Perte de vitesse en l'air en pourcents :");
		JTextField loseInAir = new JTextField(Physics.AIR_LOSS +"");
		JLabel loseWallText = new JLabel("Perte de vitesse de frottements en pourcents :");
		JTextField loseWall = new JTextField(Physics.FRICTION_LOSS +"");
		JLabel gravityXText = new JLabel("Gravité (perte de X par tick) :");
		JTextField gravityX = new JTextField(Physics.GRAVITY.getX()+"");
		JLabel gravityYText = new JLabel("Gravité (perte de Y par tick) :");
		JTextField gravityY = new JTextField(Physics.GRAVITY.getY()+"");
		JLabel nbBallsText = new JLabel("Nombres de balles :");
		JTextField nbBalls = new JTextField(Physics.NB_BALLS+"");
		JLabel maxSizeText = new JLabel("                  Rayon maximum :");
		JTextField maxSize = new JTextField(Physics.RAYON_MAX+"");
		JLabel minSizeText = new JLabel("Rayon minimum :");
		JTextField minSize = new JTextField(Physics.RAYON_MIN+"");
		JCheckBox collisionCheckBox = new JCheckBox("Collisions");
		collisionCheckBox.setSelected(Physics.COLLISIONS);
		
		JButton apply = new JButton("Appliquer");
		JButton restart = new JButton("Recommencer");
		
		pan.add(loseInAirText);
		loseInAir.setPreferredSize(new Dimension(60, 20));
		pan.add(loseInAir);
		pan.add(loseWallText);
		loseWall.setPreferredSize(new Dimension(60, 20));
		pan.add(loseWall);
		pan.add(gravityXText);
		gravityX.setPreferredSize(new Dimension(60, 20));
		pan.add(gravityX);
		pan.add(gravityYText);
		gravityY.setPreferredSize(new Dimension(60, 20));
		pan.add(gravityY);
		pan.add(nbBallsText);
		nbBalls.setPreferredSize(new Dimension(60, 20));
		pan.add(nbBalls);
		pan.add(maxSizeText);
		maxSize.setPreferredSize(new Dimension(60, 20));
		pan.add(maxSize);
		pan.add(minSizeText);
		minSize.setPreferredSize(new Dimension(60, 20));
		pan.add(minSize);
		pan.add(collisionCheckBox);
		
		pan.add(apply);
		pan.add(restart);
		
		this.setContentPane(pan);
		
		apply.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Physics.AIR_LOSS = Double.parseDouble(loseInAir.getText());
				Physics.FRICTION_LOSS = Double.parseDouble(loseWall.getText());
				Physics.GRAVITY = new Vector(Double.parseDouble(gravityX.getText()), Double.parseDouble(gravityY.getText()));
				Physics.NB_BALLS = Integer.parseInt(nbBalls.getText());
				Physics.RAYON_MAX = Integer.parseInt(maxSize.getText());
				Physics.RAYON_MIN = Integer.parseInt(minSize.getText());
				Physics.COLLISIONS = collisionCheckBox.isSelected();
			}
		});

		restart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.getInstance().getMyThread().runLater(() -> Main.getInstance().restart());
			}
		});
		
		KeyListener kl = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == 10)
				{
					Physics.AIR_LOSS = Double.parseDouble(loseInAir.getText());
					Physics.FRICTION_LOSS = Double.parseDouble(loseWall.getText());
					Physics.GRAVITY = new Vector(Double.parseDouble(gravityX.getText()), Double.parseDouble(gravityY.getText()));
					Physics.NB_BALLS = Integer.parseInt(nbBalls.getText());
					Physics.COLLISIONS = collisionCheckBox.isSelected();
				}
			}
		};
		
		loseInAir.addKeyListener(kl);
	}
	
	public static FenetreOptions getInstance()
	{
		return instance;
	}
}
