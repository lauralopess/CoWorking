package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Rectangle;

public class Reservas extends JDialog {
	
	public Reservas() {
		setBounds(new Rectangle(600, 250, 462, 317));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Reservas");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/img/logo.png")));
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas dialog = new Reservas();
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
