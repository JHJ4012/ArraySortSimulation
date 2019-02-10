package MyOwnProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SortTest extends JFrame{
	
	int[] array = new int[100];
	int[] array2 = new int[100];
	Timer timer;	//타이머를 위한 변수들.
	static float time = 0; 

	public SortTest() {
		
		setSize(1000, 800);
		setTitle("배열 정렬 시뮬레이션");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		new RandomNum();
		BasicScreen panel = new BasicScreen();
		
		add(panel);
		setVisible(true);
	}
	
	class RandomNum{//숫자 랜덤으로 중복 안되게 정하기.
		
		public RandomNum() {
			Random r = new Random();
			for(int i = 0 ; i < array.length; i++) {
				array[i] = Math.abs(r.nextInt()%100) + 1;
		
				for(int j = 0 ; j < i ; j++) {
					if(array[i] == array[j]) {
						i--;
					}
				}
			}
			
			for(int i = 0; i < array.length; i++) {
				array2[i] = array[i];
			}
		}
	}

	 int x;	//위치 좌표들
	 int y;
	 int forpaint = 1;
	 int forscore;
	 
	 JButton button1 , button2, button3, button4, button5, button6, button7, button8;
	 JLabel explanation, warn, timelabel, forbubblelabel, forselectionlabel, forinsertlabel, forquicklabel, formergelabel;
	 JTextField forbubblefield, forselectionfield, forinsertfield, forquickfield, formergefield;
	 
	 class BasicScreen extends JPanel{
		 
		public BasicScreen() {
			setLayout(null);
			
			explanation = new JLabel("별들을 정렬해주세요!");
			explanation.setFont(new Font("Serif", Font.PLAIN, 20));
			explanation.setForeground(Color.YELLOW);
			explanation.setSize(200, 50);
			explanation.setLocation(400, 530);
			
			warn = new JLabel("※ 이 시뮬레이션에서 나오는 시간은 실제와 다릅니다. 실제 속도를 비교하고 싶으시면 real time 버튼을 눌러주세요");
			warn.setFont(new Font("Serif",Font.PLAIN, 15));
			warn.setForeground(Color.RED);
			warn.setSize(710, 20);
			warn.setLocation(160, 570);
			
			button1 = new JButton("Bubble Sort");
			button1.setSize(120, 50);
			button1.setLocation(100, 600);
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						Thread bubblechange = new BubbleChange();
						forscore = 1;	
						timer.start();
						bubblechange.start();
				}
			});
			
			button2 = new JButton("Selection Sort");
			button2.setSize(120, 50);
			button2.setLocation(270, 600);
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Thread selectchange = new SelectChange();
					forscore = 2;
					timer.start();
					selectchange.start();
				}
			});
			
			button3 = new JButton("Insert Sort");
			button3.setSize(120, 50);
			button3.setLocation(440, 600);
			button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Thread insertchange = new InsertChange();
					forscore = 3;
					timer.start();
					insertchange.start();
				}
			});
			
			button4 = new JButton("Quick Sort");
			button4.setSize(120, 50);
			button4.setLocation(610, 600);
			button4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Thread quickchange = new QuickChange();
					forscore = 4;
					timer.start();
					quickchange.start();
				}
			});
			
			button5 = new JButton("Merge Sort");
			button5.setSize(120, 50);
			button5.setLocation(780, 600);
			button5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Thread mergechange = new MergeChange();
					forscore = 5;
					timer.start();
					mergechange.start();
				}
			});
			
			button6 = new JButton("Get back");
			button6.setSize(100, 50);
			button6.setLocation(300, 700);
			button6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					forpaint = 2;
					repaint();
					timelabel.setText("0.00");
					
					for(int i = 0 ; i < array2.length; i++) {
						array[i] = array2[i];
					}
				}
			});
			
			button7 = new JButton("New array");
			button7.setSize(100, 50);
			button7.setLocation(450, 700);
			button7.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					new RandomNum();
					repaint();
					timelabel.setText("0.00");
					forbubblefield.setText("");
					forselectionfield.setText("");
					forinsertfield.setText("");
					forquickfield.setText("");
					formergefield.setText("");
				}
				
			});
			
			button8 = new JButton("real time");
			button8.setSize(100, 50);
			button8.setLocation(600, 700);
			button8.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					new RealSortTime();
				}
			});
			
			timelabel = new JLabel(String.valueOf(time));
			timelabel.setSize(40, 20);
			timelabel.setLocation(930 , 10);
			timelabel.setForeground(Color.RED);
			
			timer = new Timer(10, new ActionListener() {	
				 public void actionPerformed(ActionEvent e) { 
					 
					time += 0.01;
					timelabel.setText(String.format("%.2f",time));
					
					if(forscore == 1) {
						forbubblefield.setText(String.format("%.2f",time));
					}else if(forscore == 2) {
						forselectionfield.setText(String.format("%.2f",time));
					}else if(forscore == 3) {
						forinsertfield.setText(String.format("%.2f",time));
					}else if(forscore == 4) {
						forquickfield.setText(String.format("%.2f",time));
					}else if(forscore == 5) {
						formergefield.setText(String.format("%.2f",time));
					}
				} 
			 });
			
			forbubblelabel = new JLabel("Bubble");
			forbubblelabel.setSize(50, 20);
			forbubblelabel.setLocation(920, 50);
			forbubblelabel.setForeground(Color.YELLOW);
			forbubblefield = new JTextField();
			forbubblefield.setSize(40, 20);
			forbubblefield.setLocation(920, 80);
			
			forselectionlabel = new JLabel("Selection");
			forselectionlabel.setSize(60, 20);
			forselectionlabel.setLocation(920, 120);
			forselectionlabel.setForeground(Color.YELLOW);
			forselectionfield = new JTextField();
			forselectionfield.setSize(40, 20);
			forselectionfield.setLocation(920, 150);
			
			forinsertlabel = new JLabel("Insert");
			forinsertlabel.setSize(60, 20);
			forinsertlabel.setLocation(920, 190);
			forinsertlabel.setForeground(Color.YELLOW);
			forinsertfield = new JTextField();
			forinsertfield.setSize(40, 20);
			forinsertfield.setLocation(920, 220);
			
			forquicklabel = new JLabel("Quick");
			forquicklabel.setSize(60, 20);
			forquicklabel.setLocation(920, 260);
			forquicklabel.setForeground(Color.YELLOW);
			forquickfield = new JTextField();
			forquickfield.setSize(40, 20);
			forquickfield.setLocation(920, 290);
			
			formergelabel = new JLabel("Merge");
			formergelabel.setSize(60, 20);
			formergelabel.setLocation(920, 330);
			formergelabel.setForeground(Color.YELLOW);
			formergefield = new JTextField();
			formergefield.setSize(40, 20);
			formergefield.setLocation(920, 360);
			
			add(explanation);
			add(warn);
			add(button1);
			add(button2);
			add(button3);
			add(button4);
			add(button5);
			add(button6);
			add(button7);
			add(button8);
			add(timelabel);
			add(forbubblelabel);
			add(forbubblefield);
			add(forselectionlabel);
			add(forselectionfield);
			add(forinsertlabel);
			add(forinsertfield);
			add(forquicklabel);
			add(forquickfield);
			add(formergelabel);
			add(formergefield);
		}
		 
		int i;
		Image background=new ImageIcon("src/SortPicture/sky.jpg").getImage();
		
		public void paintComponent(Graphics g){   	
	         super.paintComponent(g);
	         g.drawImage(background,0,0,getWidth(),getHeight(),this);
	         
	         if(forpaint == 1) {
	         	for(i = 0; i < array.length; i++) {
	         		numLocation();
	         		g.setColor(Color.YELLOW);
	         		g.fillOval(x, y, 5, 5);
	         	}
	         }else if(forpaint == 2) {
	        	 for(i = 0 ; i < array.length; i++) {
	        		 numLocation2();
	        		 g.setColor(Color.YELLOW);
	        		 g.fillOval(x, y, 5, 5);
	        	 }
	        	 forpaint = 1;
	         }
	    }
		
		public void numLocation() {
			y = 505 - (array[i]*5);
			x = 9 + (i*9);
		}
		
		public void numLocation2() {	
			y = 505 - (array2[i]*5);
			x = 9 + (i*9);
		}
	 }
	 
	 class BubbleChange extends Thread{	//버블정렬 버튼 눌렀을 때.
		 
		 public void run() {
			 time = 0;
			 
			 for(int i = 0 ; i < array.length-1 ; i++) {
					for(int j = 0 ; j < array.length-1-i;j++) {
						if(array[j] > array[j+1]) {
							int temp = array[j];
							array[j]= array[j+1];
							array[j+1] = temp;
							repaint();
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			 timer.stop();
		 }
	 }
	 
	 class SelectChange extends Thread{	//선택정렬 눌렀을 때.
		 
		 public void run() {
			 time = 0;
			 
			 for(int i = 0 ; i < array.length -1 ; i++) {
					for(int j = i+1 ; j < array.length ; j++) {
						if(array[i] > array[j]) {
							int temp = array[j];
							array[j] = array[i];
							array[i] = temp;
							repaint();
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			 timer.stop();
		 }
	 }

	 class InsertChange extends Thread{	//삽입 정렬 눌렀을 때.
		 
		 public void run() {
			time = 0;
				 
			for (int i = 1; i < array.length; i++) { 			
				int temp = array[i];
				int j = i;
					
				 	for (; j > 0 && array[j-1] > temp; j--) {
						array[j] = array[j-1];
						repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} 
				array[j] = temp;
				repaint();
			}
			timer.stop();
		}
	 }	
	 
	 class QuickChange extends Thread{
		 public void run() {
			 time = 0;
			 sort(array, 0, array.length-1);
			 timer.stop();
		 }
		
		 public void sort(int array[], int l, int r) {
				if(l >= r) {
					return;
				}
				
				int left = l;
				int right = r;
				int pivot = array[(l+r)/2];
				
				do {
					while(array[left] < pivot) {
						left++;
					}
					while(array[right] > pivot) {
						right--;
					}
		
					if(left <= right) {
						int temp = array[left];
						array[left] = array[right];
						array[right] = temp;
						left++;
						right--;
						
						repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}while(left <= right);
				
				if(l< right) {
					sort(array, l, right);
				}
				if(r > left) { 
					sort(array, left, r);
				}
			}
	 }
	 
	 
	 class MergeChange extends Thread{ 
	
		 int[] formergearray = new int[100];
	 
		 public void run() {
			 time = 0;
			 mergeSort(array, 0, array.length-1);
			 timer.stop();
		 }

		 public void mergeSort(int arr[], int m, int n) {
			 if(m < n) {
				 int middle = (m+n)/2;
				 mergeSort(arr, m, middle);
				 mergeSort(arr, middle + 1, n);
				 merge(arr, m, middle, n);
			 }
		 }
		
		 public void merge(int[] arr , int m, int middle, int n) {
			 int i = m;
			 int j = middle + 1;
			 int k = m;
			
			 while(i <= middle && j <= n) {
				 if(arr[i] <= arr[j]) {
					 formergearray[k] = arr[i];
					 i++;
					 repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 }else {
					 formergearray[k] = arr[j];
					 j++;
					 repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 }
				 k++;
			 }
			
			 if(i > middle) {
				 for(int t = j; t <= n; t++) {
					 formergearray[k] = arr[t];
					 k++;
					 repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 }
			 }else {
				 for(int t = i; t <= middle; t++) {
					 formergearray[k] = arr[t];
					 k++;
					 repaint();
						
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 }
			 }
			
			 for(int t = m; t <= n; t++) {
				 arr[t] = formergearray[t];
				 repaint();
					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			 }
		 }
	 }
	 
	 public static void main(String[] args) {
		 SortTest t = new SortTest();
	 }
}