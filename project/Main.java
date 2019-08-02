import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;



	//this class is added to implement multi threading so as to use timer in the game

class TimerClass extends Thread{
	String timeScore=null;
	int signal=0;
	int sec=0,min=0;
	JTextField timer;
	
	TimerClass(int signal){
		this.signal=signal;
		Temp temp = new Temp();
		JPanel pan = new JPanel();
		temp.add(pan);
		temp.setSize(400,400);
		pan.setLayout(null);
		timer = new JTextField();
		pan.add(timer);
		//timer.setBounds(30,30,40,20);
		
	//temp.setVisible(true); 
	
	}
	void timerFun(JTextField timer){
			
			while(signal!=1){
			//System.out.println(gov);	
			//System.out.println(signal);
					if(min>9 && sec>9)
						timer.setText(Integer.toString(min)+":"+Integer.toString(sec));
					else if(min<=9 && sec<=9)
						timer.setText("0"+Integer.toString(min)+":"+"0"+Integer.toString(sec));
					else if(min>9 && sec<=9)
						timer.setText(Integer.toString(min)+":"+"0"+Integer.toString(sec));
					else if(min<=9 && sec>9)
						timer.setText("0"+Integer.toString(min)+":"+Integer.toString(sec));
				 try{
					Thread.sleep(1000);
					sec++;
					if(sec>59){
						sec=0;
						min++;
					}
				}
				catch(Exception e){
					System.out.print(e);
				} 
				
			}
		}
	public void run(){
		timerFun(timer);
	}
}
class Temp extends JFrame{
	
}
class Main extends JFrame implements ActionListener{
		int count =16 ;
		int[] minePos;
		int[] visited;
		int printMine=-2;
		int sec=0,min=0,hour=0;
		JButton[] btn= new JButton[count*count];
		JPanel in = new JPanel();  
		int signal=0;
		GameOver gov;
		//JTextField timer = new JTextField();
		TimerClass testing ; // for timer
		
		
		Main(GameOver go,int countOfMine){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testing = new TimerClass(0);
			testing.start();
			signal=0;
			sec=0;min=0;
			/*code for outer frame*/
			count=countOfMine;
			gov=go;
			createMine();
			setTitle("Minesweeper");
			setSize(100+count*30,120+count*30);
			setLocation(SetLocation.getCenterPoint(getSize()));
			setLayout(null);
			setResizable(false);
			setIconImage(new ImageIcon("icon.png").getImage());
			for(int i=0;i<count*count;i++){
				btn[i]=new JButton(Integer.toString(i));
				btn[i].setForeground(btn[i].getBackground());
			
			}
			JMenuBar menuBar = new JMenuBar();
			JMenuItem item1 = new JMenuItem("9 X 9 Board");
			JMenuItem item2 = new JMenuItem("16 X 16 Board");
			JMenu menu1 = new JMenu("Options");
			JMenu menu2 = new JMenu("About Us");
			JLabel time = new JLabel("Time:" );
			JMenuItem item21 = new JMenuItem("Developed by Rahul Singal");
			menu1.setMnemonic('o');
			menu2.setMnemonic('a');
			//System.out.println(btn[1].getBackground());
			/*Code for Inner box which contains buttons */
			add(menuBar);
			add(in);
			menuBar.setBounds(0,0,count*30+100,30);
			time.setFont(new Font("Times New Roman",0,18));
			in.setBounds(40,40,count*30,count*30);
			in.setLayout(new GridLayout(count,count));
			add(testing.timer);
			
			//add(timer);
			add(time);
			time.setBounds(40,320,50,20);
			if(count==9){
				time.setBounds(40,320,50,20);
				testing.timer.setBounds(90,320,40,20);
			}
			else{
				time.setBounds(40,530,50,20);
				testing.timer.setBounds(90,530,40,20);
			}
				
			testing.timer.setEditable(false);
			testing.timer.setHorizontalAlignment(JTextField.CENTER);
			//timer.setBounds(90,320,40,20);
			//timer.setEditable(false);
			//timer.setHorizontalAlignment(JTextField.CENTER);
			menu1.add(item1);
			menu1.add(item2);
			menuBar.add(menu1);
			menuBar.add(menu2);
			menu2.add(item21);

			for(int i=0;i<count*count;i++){				
				
				in.add(btn[i]);		
				btn[i].addActionListener(this);
				
			} 	
				
			item1.addActionListener(this);
			item2.addActionListener(this);
			
			//System.out.println(btn[i].getBounds());
			setVisible(true); // always make the outer frame visible at the end because once the UI is displayed 
							 //it should be modified by the EDT (Event Dispatch Thread ) only and we should not add buttons after displaying UI
			
		}
		
		int trackWin=0;
		public void transform(int btnIndex,int printMine){
					//visited[btnIndex]=1;
					in.remove(btn[btnIndex]);
					JTextField clicked = new JTextField();
					in.add(clicked);
					clicked.setEditable(false);
					clicked.setHorizontalAlignment(JTextField.CENTER);
					clicked.setBounds((btnIndex%count)*30 ,(btnIndex/count)*30,30,30);
					if(printMine==1)
						clicked.setForeground(Color.BLUE);
					else if(printMine==2)
						clicked.setForeground(Color.GREEN);
					else if(printMine==3)
						clicked.setForeground(Color.RED);
					else if(printMine==4)
						clicked.setForeground(Color.ORANGE);
					
					clicked.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,20));
					
					if(printMine!=0)
						clicked.setText(Integer.toString(printMine));
					if((trackWin==71 && count==9) || (trackWin==216 && count==16)){
						testing.signal=1;
						JOptionPane.showMessageDialog(null,"Congratulations! ","You Won.",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("Game Won");
						
					}
					
		}
		
		public void gameOver(int btnIndex, String timeScore){
			in.remove(btn[btnIndex]);
						Icon icon = new ImageIcon("mine.png");
						JButton mineClicked = new JButton(icon);
						mineClicked = new JButton(icon);
						mineClicked.setBounds((btnIndex%count)*30 ,(btnIndex/count)*30,30,30);
						in.add(mineClicked);
						
						for(int i=0;i<count*count;i++){
							if(minePos[i]!=1){
								//btn[i].setBackground(Color.GRAY);
								//btn[i].setForeground(Color.WHITE);
								//btn[i]
								btn[i].setEnabled(false);
								
							}
							
						}
						for(int i=0;i<count*count;i++){
							if(minePos[i]==1){
								btnIndex=i;
								in.remove(btn[btnIndex]);
								mineClicked = new JButton(icon);
								in.add(mineClicked); 							// why these two lines when interchanged 
								mineClicked.setBounds((btnIndex%count)*30 ,(btnIndex/count)*30,30,30);   //takes time to load while running the program
							}
							else{
								 btnIndex=i;
								 //btn[btnIndex].setEnabled(false);
								//btn[btnIndex].setLabel(null);
								// in.remove(btn[btnIndex]);
								// mineClicked = new JButton();
								// in.add(mineClicked); 						
								// mineClicked.setBounds((btnIndex%count)*30 ,(btnIndex/count)*30,30,30);   
								 // mineClicked.setEnabled(false);
							}
							
						}
						testing.signal=1;
						if(testing.min>9 && testing.sec>9)
							testing.timeScore=""+testing.min+":"+testing.sec;
						
						else if(testing.min<=9 && testing.sec<=9)
							testing.timeScore="0"+testing.min+":"+"0"+testing.sec;
						else if(testing.min>9 && testing.sec<=9)
							testing.timeScore=""+testing.min+":"+"0"+testing.sec;
						else if(testing.min<=9 && testing.sec>9)
							testing.timeScore="0"+testing.min+":"+testing.sec;
				gov.GameOverMethod(testing.timeScore);	
				//dispose();
				
		}
		
		
		public void noMineState(int btnIndex, int printMine){
			// printMine is used to store the mine count about the button expanded
				if(printMine==0){
						
				transform(btnIndex,printMine);
					if(  btnIndex+1<count*count && (btnIndex+1)/count==btnIndex/count && visited[btnIndex+1]!=1){
						printMine=mineSweeper(btnIndex+1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex+1,printMine);
						else if(printMine==0){
							
							transform(btnIndex+1,printMine);
							noMineState(btnIndex+1,printMine);
						}
					}
						
					if(btnIndex-1 >=0 && (btnIndex-1)/count==btnIndex/count && visited[btnIndex-1]!=1){
						printMine=mineSweeper(btnIndex-1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex-1,printMine);
						else if(printMine==0){
							transform(btnIndex-1,printMine);
							noMineState(btnIndex-1,printMine);
						}
					}
					if(btnIndex+count<count*count && visited[btnIndex+count]!=1  ){
						printMine=mineSweeper(btnIndex+count);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex+count,printMine);
						else if(printMine==0){
							
							transform(btnIndex+count,printMine);
							
							noMineState(btnIndex+count,printMine);
						}
					}
					if(btnIndex-count>=0 && visited[btnIndex-count]!=1  ){
						printMine=mineSweeper(btnIndex-count);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex-count,printMine);
						else if(printMine==0){
							transform(btnIndex-count,printMine);
							
							noMineState(btnIndex-count,printMine);
						}
					}
					if(btnIndex-count+1 >0 && (btnIndex-count+1)/count!=btnIndex/count && visited[btnIndex-count+1]!=1  ){
						printMine=mineSweeper(btnIndex-count+1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex-count+1,printMine);
						else if(printMine==0){
							transform(btnIndex-count+1,printMine);
							
							noMineState(btnIndex-count+1,printMine);
						}
					}
					//if( btnIndex-count-1>=0 && ((btnIndex-count-1)/count - btnIndex/count == 1 && visited[btnIndex-count-1]!=1)){
					if( btnIndex-count-1>=0 && btnIndex>=count && btnIndex%count!=0 && visited[btnIndex-count-1]!=1){
						printMine=mineSweeper(btnIndex-count-1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex-count-1,printMine);
						else if(printMine==0){
							transform(btnIndex-count-1,printMine);
							
							noMineState(btnIndex-count-1,printMine);
						}
						
					}
					//if( btnIndex+count+1<count*count && ((btnIndex+count+1)/count - btnIndex/count)!=2 && visited[btnIndex+count+1]!=1){
					if( btnIndex+count+1<count*count && (btnIndex+1)%count!=0 && (btnIndex+count+1)<=count*count && visited[btnIndex+count+1]!=1){
						printMine=mineSweeper(btnIndex+count+1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex+count+1,printMine);
						else if(printMine==0){
							transform(btnIndex+count+1,printMine);
							
							noMineState(btnIndex+count+1,printMine);
						}
					}
					if( btnIndex+count-1<count*count && ((btnIndex+count-1)/count)-btnIndex/count==1 && visited[btnIndex+count-1]!=1){
						printMine=mineSweeper(btnIndex+count-1);
						if(printMine!=-1 && printMine!=0)
							transform(btnIndex+count-1,printMine);
						else if(printMine==0){
							transform(btnIndex+count-1,printMine);
							
							noMineState(btnIndex+count-1,printMine);
						}
					}
				}
					
		}
		
		public void actionPerformed(ActionEvent ae){
			    
				if(ae.getActionCommand().equalsIgnoreCase("9 X 9 Board")){
					int choice=	JOptionPane.showConfirmDialog(null,"Are you sure to start a New Game ?","Start New Game",JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.YES_OPTION){
						dispose();
						GameOver gov9 = new GameOver();
						gov9.passRef(new Main(gov9,9));
					}
					else{
						
					}
				}
				else if(ae.getActionCommand().equalsIgnoreCase("16 X 16 Board")){
					int choice=	JOptionPane.showConfirmDialog(null,"Are you sure to start a New Game ?","Start New Game",JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.YES_OPTION){
						dispose();
						GameOver gov16 = new GameOver();
						gov16.passRef(new Main(gov16,16));
					}
					else{
						
					}
						
				}
				else{
						
					System.out.println(printMine);
					int btnIndex=Integer.parseInt(ae.getActionCommand());
					printMine=mineSweeper(btnIndex);
					
					if(printMine==0)
						noMineState(btnIndex, printMine);
					
					
					if(printMine!=-1){
						transform(btnIndex,printMine);
						/*in.revalidate();
						in.repaint();*/
				}
					//System.out.println(printMine);
					//btn[Integer.parseInt(ae.getActionCommand())].setLabel(Integer.toString(printMine));
				else{	
						//in.remove(btn[btnIndex]);
						//JLabel mineClicked = new JLabel();
						//in.add(mineClicked);
						//mineClicked.setIcon(new ImageIcon(getClass().getResource("/bomb.jpg")));
						//btn[btnIndex].setIcon(new ImageIcon(getClass().getResource("/bomb.jpg")));
						//btn[btnIndex].setHorizontalAlignment(JButton.CENTER);
						/*JPanel mineClicked = new JPanel();
						JImageComponent ic = new JImageComponent("bomb.jpg");
						in.add(mineClicked);
						mineClicked.setBounds((btnIndex%count)*30 ,(btnIndex/count)*30,30,30);
						mineClicked.add(ic);
						*/
						
						gameOver(btnIndex,testing.timeScore);
						
						
				}
			}	//System.out.println("Game Over");
		}
		
		
		// Function to generate mines at random positions, [Note:improve it by adding constraints of less than or equal to 3 mines around a button]
		void createMine(){
			int mineCount=0;
			int repeat=0,random=0;
			Random rand = new Random();
			
			if(count==9)
				mineCount=10;
			else if(count==16)
				mineCount=40;
			
			minePos=new int[count*count];
			visited=new int[count*count];
			
			
			for(int i=0;i<mineCount;i++){
				random=rand.nextInt(count*count);
				if(minePos[random]==0){
					minePos[random]=1;
					//System.out.println(random); // code to print mines position
				}
					
				else
					i--;
			}
		}
		
		
		// Main logic of the game, [Note: add state 3 code]
		int mineSweeper(int btnNum){
			visited[btnNum]=1;
			trackWin++;
			int mineCount=0;
			int top=0,left=0,right=0,bottom=0;
			if(minePos[btnNum]==1){
				return -1;
			} // code for game over
			
			else{
				//first row
				if(btnNum<count){
					top=1;
				}
				//first column
				if(btnNum%count==0){
					left=1;	
				}
				//last column
				if((btnNum+1)%count==0){
					right=1;
				}
				//last row
				if(btnNum>=(count*count-count)){
					bottom=1;
				}
				// middle
				if(top==0 && bottom==0 && left==0 && right==0){
					if(minePos[btnNum+1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum+count+1]==1)
						mineCount++;
					if(minePos[btnNum+count-1]==1)
						mineCount++;
					if(minePos[btnNum-count+1]==1)
						mineCount++;
					if(minePos[btnNum-count-1]==1)
						mineCount++;
					
				}
				
				if(top==1&&left==1){
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum+count+1]==1)
						mineCount++;
					if(minePos[btnNum+1]==1)
						mineCount++;
				}
				if(top==1 && left==0 && right ==0){
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum+count+1]==1)
						mineCount++;
					if(minePos[btnNum+1]==1)
						mineCount++;
					if(minePos[btnNum+count-1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
				}
				if(top==1&&right==1){
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum+count-1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
				}
				if(left==1 && top==0 && bottom==0){
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum+count+1]==1)
						mineCount++;
					if(minePos[btnNum+1]==1)
						mineCount++;
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum-count+1]==1)
						mineCount++;
				}
				
				if(right==1 && top==0 && bottom==0){
					if(minePos[btnNum+count]==1)
						mineCount++;
					if(minePos[btnNum+count-1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum-count-1]==1)
						mineCount++;
				}
				if(bottom==1&&left==1){
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum-count+1]==1)
						mineCount++;
					if(minePos[btnNum+1]==1)
						mineCount++;
				}
				if(bottom==1 && left==0 && right ==0){
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum-count+1]==1)
						mineCount++;
					if(minePos[btnNum+1]==1)
						mineCount++;
					if(minePos[btnNum-count-1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
				}
				if( bottom==1&&right==1){
					if(minePos[btnNum-count]==1)
						mineCount++;
					if(minePos[btnNum-count-1]==1)
						mineCount++;
					if(minePos[btnNum-1]==1)
						mineCount++;
				}
				
				
			}
			
			return mineCount;
			
		}		
}