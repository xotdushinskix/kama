package kama;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Java Kama");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1024,579);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
        f.add(new Mountatin());
		f.setVisible(true);
	}

}
