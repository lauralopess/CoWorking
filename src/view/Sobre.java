package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {
	
	public Sobre() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 31, 493, 24);
		titulo.setBackground(new Color(0, 0, 0));
		titulo.setForeground(new Color(0, 0, 0));
		titulo.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		getContentPane().add(titulo);
		
		JLabel descricao1 = new JLabel("possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.\r\n");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		descricao1.setBounds(0, 127, 493, 33);
		getContentPane().add(descricao1);
		
		JLabel descricao2 = new JLabel("O software CoWorking trata-se de um protótipo cujo o objetivo é");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		descricao2.setBounds(0, 98, 493, 33);
		getContentPane().add(descricao2);
		
		JLabel versao = new JLabel("Versão 1.0.0\r\n");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		versao.setBounds(0, 212, 493, 33);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		atualizacao.setBounds(0, 237, 493, 33);
		getContentPane().add(atualizacao);
		
		JLabel imgMIT = new JLabel("");
		imgMIT.setHorizontalAlignment(SwingConstants.CENTER);
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(430, 245, 53, 48);
		getContentPane().add(imgMIT);
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	
			
