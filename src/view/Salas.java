package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Color;

public class Salas extends JDialog {
	
	public Salas() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setBackground(new Color(255, 255, 255));
		setBounds(new Rectangle(600, 250, 462, 317));
		setTitle("Salas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setResizable(false);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});	
	}
}
