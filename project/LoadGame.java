import javax.swing.*;
import java.util.*;
import java.awt.*;
public class LoadGame extends JFrame{
	ImageIcon ii;
	JLabel jbl;
	JProgressBar jpb;
	LoadGame(){
		ii=new ImageIcon("1.png");
		jbl=new JLabel(ii);
		add(jbl);
		jpb=new JProgressBar();
		jpb.setMinimum(0);
		jpb.setMaximum(99);
		jpb.setValue(0);
		add(jpb,"South");
		setSize(490,260);
		setLocation(SetLocation.getCenterPoint(getSize()));
		setUndecorated(true);
		setVisible(true);//setVisible() should be used before threading concept.
		for(int i=0;i<99;i++){
			jpb.setValue(i);
			try{
				Thread.sleep(0);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		dispose();
	}
	
	public static void main(String args[]){
		
		int defaultBoard=9;
		new LoadGame();
		GameOver go=new GameOver();
		
		Main main = new Main(go,defaultBoard);
		go.passRef(main);
		//main.timerFun();
		
	}
} 