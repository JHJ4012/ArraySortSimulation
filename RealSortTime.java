package MyOwnProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class RealSortTime extends JFrame{

	Integer[] fortime = new Integer[100000];
	Integer[] fortime2 = new Integer[100000];
	Integer[] temp = null;

	JButton button1, button2, button3, bubblebutton, selectionbutton, insertbutton, quickbutton, mergebutton;
	JLabel forbubble, forselection, forinsert, forquick, formerge, explanation;
	Timer timer;
	int ordernum;
	static float time = 0;
	
	public RealSortTime() {
		
		setSize(500, 520);
		setTitle("속도 비교하기!");
		setLocation(260, 180);
		setLayout(null);
		
		timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				time += 0.001;
	
				if(ordernum == 1) {
					forbubble.setText(String.format("%.3f",time));
				}else if(ordernum == 2) {
					forselection.setText(String.format("%.3f",time));
				}else if(ordernum == 3) {
					forinsert.setText(String.format("%.3f",time));
				}else if(ordernum == 4) {
					forquick.setText(String.format("%.3f",time));
				}else if(ordernum == 5) {
					formerge.setText(String.format("%.3f",time));
				}
			}
		});
		
		button1 = new JButton("배열 생성");
		button1.setSize(100, 50);
		button1.setLocation(50, 100);
		button1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				randomNum();
			}
		});
		
		button2 = new JButton("원래 상태");
		button2.setSize(100, 50);
		button2.setLocation(50, 175);
		button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.arraycopy(fortime2, 0, fortime, 0, fortime2.length);
			}
		});
		
		button3 = new JButton("다시 생성");
		button3.setSize(100, 50);
		button3.setLocation(50, 250);
		button3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				randomNum();
				
				forbubble.setText("");
				forselection.setText("");
				forinsert.setText("");
				forquick.setText("");
				formerge.setText("");
			}
			
		});
		
		bubblebutton = new JButton("Bubble");
		bubblebutton.setSize(100, 30);
		bubblebutton.setLocation(200, 10);
		bubblebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread bubble = new Bubble();
				time = 0;
				ordernum = 1;
				timer.start();
				bubble.start();
			}
		});
		
		
		selectionbutton = new JButton("Selection");
		selectionbutton.setSize(100, 30);
		selectionbutton.setLocation(200, 100);
		selectionbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread select = new Selection();
				time = 0;
				ordernum = 2;
				timer.start();
				select.start();
			}
		});
		
		insertbutton = new JButton("insert");
		insertbutton.setSize(100, 30);
		insertbutton.setLocation(200, 190);
		insertbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread insert = new Insert();
				time = 0;
				ordernum = 3;
				timer.start();
				insert.start();
			}
		});
		
		quickbutton = new JButton("quick");
		quickbutton.setSize(100, 30);
		quickbutton.setLocation(200, 280);
		quickbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread quick = new Quick();
				time = 0;
				ordernum = 4;
				timer.start();
				quick.start();
			}
		});
		
		mergebutton = new JButton("Merge");
		mergebutton.setSize(100, 30);
		mergebutton.setLocation(200, 370);
		mergebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread merge = new Merge();
				time = 0;
				ordernum = 5;
				timer.start();
				merge.start();
			}
		});
		
		forbubble = new JLabel();
		forbubble.setSize(150, 30);
		forbubble.setLocation(200, 50);
		forbubble.setBorder(BorderFactory.createEtchedBorder(1));
		forselection = new JLabel();
		forselection.setSize(150, 30);
		forselection.setLocation(200, 140);
		forselection.setBorder(BorderFactory.createEtchedBorder(1));
		forinsert = new JLabel();
		forinsert.setSize(150, 30);
		forinsert.setLocation(200, 230);
		forinsert.setBorder(BorderFactory.createEtchedBorder(1));
		forquick = new JLabel();
		forquick.setSize(150, 30);
		forquick.setLocation(200, 320);
		forquick.setBorder(BorderFactory.createEtchedBorder(1));
		formerge = new JLabel();
		formerge.setSize(150, 30);
		formerge.setLocation(200, 410);
		formerge.setBorder(BorderFactory.createEtchedBorder(1));
		explanation = new JLabel("배열 10만개를 만들어 속도를 비교해보세요!");
		explanation.setSize(400, 30);
		explanation.setLocation(120, 450);
		explanation.setForeground(Color.RED);
		
		add(button1);
		add(button2);
		add(button3);
		add(bubblebutton);
		add(selectionbutton);
		add(insertbutton);
		add(quickbutton);
		add(mergebutton);
		add(forbubble);
		add(forselection);
		add(forinsert);
		add(forquick);
		add(formerge);
		add(explanation);
		
		setVisible(true);
	}
	
	public void randomNum() {	
		
		for(int z = 0; z < 100000; z++) {
			fortime[z] = z+1;
		}
		
		Collections.shuffle(Arrays.asList(fortime));
		
		System.arraycopy(fortime, 0, fortime2, 0, fortime.length);
		
	}
	
	class Bubble extends Thread {
		public void run() {
			for(int i = 0 ; i < fortime.length-1 ; i++) {
				for(int j = 0 ; j < fortime.length-1-i;j++) {
					if(fortime[j] > fortime[j+1]) {
						int temp = fortime[j];
						fortime[j]= fortime[j+1];
						fortime[j+1] = temp;
					}
				}
			}
			timer.stop();
		}
	}
	class Selection extends Thread {
		public void run() {
			for(int i = 0 ; i < fortime.length -1 ; i++) {
				for(int j = i+1 ; j < fortime.length ; j++) {
					if(fortime[i] > fortime[j]) {
						int temp = fortime[j];
						fortime[j] = fortime[i];
						fortime[i] = temp;
					}
				}
			}
			timer.stop();
		}
	}
	
	class Insert extends Thread {
		public void run() {
			for (int i = 1; i < fortime.length; i++) { 			
				int temp = fortime[i];
				int j = i;
				
			 		for (; j > 0 && fortime[j-1] > temp; j--) {
			 			fortime[j] = fortime[j-1];
			 		} 
			 	fortime[j] = temp;
			}
			timer.stop();
		}
	}
	
	class Quick extends Thread{
		 public void run() {
			 sort(fortime, 0, fortime.length-1);
			 timer.stop();
		 }
		
		 public void sort(Integer[] fortime, int l, int r) {
				if(l >= r) {
					return;
				}
				
				int left = l;
				int right = r;
				int pivot = fortime[(l+r)/2];
				
				do {
					while(fortime[left] < pivot) {
						left++;
					}
					while(fortime[right] > pivot) {
						right--;
					}
		
					if(left <= right) {
						int temp = fortime[left];
						fortime[left] = fortime[right];
						fortime[right] = temp;
						left++;
						right--;
					}
				}while(left <= right);
				
				if(l< right) {
					sort(fortime, l, right);
				}
				if(r > left) { 
					sort(fortime, left, r);
				}
			} 
	 }
	
	 class Merge extends Thread{ 
			
		 Integer[] formergearray = new Integer[100000];
	 
		 public void run() {
			 mergeSort(fortime, 0, fortime.length-1);
			 timer.stop();
		 }

		 public void mergeSort(Integer fortime[], int m, int n) {
			 if(m < n) {
				 int middle = (m+n)/2;
				 mergeSort(fortime, m, middle);
				 mergeSort(fortime, middle + 1, n);
				 merge(fortime, m, middle, n);
			 }
		 }
		
		 public void merge(Integer[] fortime , int m, int middle, int n) {
			 int i = m;
			 int j = middle + 1;
			 int k = m;
			
			 while(i <= middle && j <= n) {
				 if(fortime[i] <= fortime[j]) {
					 formergearray[k] = fortime[i];
					 i++;
				 }else {
					 formergearray[k] = fortime[j];
					 j++;
				 }
				 k++;
			 }
			
			 if(i > middle) {
				 for(int t = j; t <= n; t++) {
					 formergearray[k] = fortime[t];
					 k++;
				 }
			 }else {
				 for(int t = i; t <= middle; t++) {
					 formergearray[k] = fortime[t];
					 k++;
				 }
			 }
			
			 for(int t = m; t <= n; t++) {
				 fortime[t] = formergearray[t];
			 }
		 }
	 }
}

