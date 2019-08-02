import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class GameOver implements ActionListener{
	JButton btn1,btn3;
	JLabel lb1,lb2,lb3;
	JPanel jp1,jp2;
	ImageIcon ii;
	JFrame gameOver;
	Main ref;
	public void GameOverMethod(String timeScore){
		gameOver = new JFrame();
		gameOver.getContentPane().setBackground(Color.white);
		lb1=new JLabel("Better Luck Next Time!");
		lb2=new JLabel("Time Elapsed: " + timeScore);//after receiving times value convert it into string and put on label
		jp1=new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		jp1.setBackground(Color.white);
		jp1.add(lb1);
		jp1.add(lb2);
		gameOver.add(jp1);			
		ii=new ImageIcon("2.jpg");
		lb3=new JLabel(ii);
		gameOver.add(lb3,"East");				
		lb1.setFont(new Font("Serif", Font.BOLD, 20));
		lb2.setFont(new Font("Serif", Font.BOLD, 20));
		jp2=new JPanel();
		jp2.setLayout(new GridLayout(1,5));				
		jp2.setBackground(Color.white);
		btn1=new JButton("New game");
		btn3=new JButton("Exit");						
		jp2.add(btn1);
		jp2.add(btn3);
		gameOver.add(jp2,"South");
		gameOver.setSize(560,320);
		gameOver.setLocation(SetLocation.getCenterPoint(gameOver.getSize()));
		gameOver.setUndecorated(false);
		gameOver.setResizable(false);
		gameOver.setVisible(true);
		btn3.addActionListener(this);
		btn1.addActionListener(this);
	}
	public void passRef(Main main){
		ref=main;
		
		//ref.dispose();
		
	}
	
		public void actionPerformed(ActionEvent ae){
			Main newGame=null;
			if(ae.getActionCommand().equalsIgnoreCase("New game")){
				try{
					ref.dispose();
				}
				catch(Exception e){System.out.println(e);}
				gameOver.setVisible(false);
				GameOver gov = new GameOver();
				
				newGame = new Main(gov,9);
				gov.passRef(newGame);
				//newGame.timerFun();
			}

			else
     			System.exit(0);
				//newGame.timerFun();	
		}
}
	

