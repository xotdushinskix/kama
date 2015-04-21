package kama;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mountatin extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer=new Timer(20, this);
	
	public Mountatin(){
		mainTimer.start();
		magaMany.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
		}
	}
	
	Image img = new ImageIcon("res/Wiki-background.png").getImage();
	
	Player p = new Player();
	
	Thread magaMany = new Thread(this);
	
	List<Maga>maga = new ArrayList<Maga>();
	
	public void paint(Graphics g){
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1,0,null);
		g.drawImage(img, p.layer2,0,null);
		g.drawImage(p.img, p.x, p.y, null);
		
		Iterator<Maga>i  =maga.iterator();
		while(i.hasNext()){
			Maga m = i.next();
			if(m.x>=2048 || m.x<=-2048){
				i.remove();
			}else{
				m.move();
				g.drawImage(m.img, m.x, m.y, null);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.move();
		repaint();
		testFightKamaMaga();
	}

	private void testFightKamaMaga() {
		Iterator<Maga>i=maga.iterator();
		while(i.hasNext()){
			Maga m=i.next();
			if(p.getRect().intersects(m.getRect())){
				JOptionPane.showMessageDialog(null, "��� � ����� ����� �� ���������� ����!!");
				System.exit(1);
			}
		}
		
	}

	@Override
	public void run() {
		while(true){
			Random rand=new Random();
			try{
				Thread.sleep(rand.nextInt(2000));
				maga.add(new Maga(1100, rand.nextInt(439), rand.nextInt(45), this));
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
		
	}
}
