package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Rectangle;

public class Funcionarios extends JDialog {
	
	public Funcionarios() {
		setBounds(new Rectangle(600, 250, 462, 317));
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Funcionários");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
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
