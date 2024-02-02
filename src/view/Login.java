package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;

import model.DAO;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel tituloLogin;
	private JButton btnLogin;
	private JLabel imgDatabase;
	
	public Login() {
		addWindowListener(new WindowAdapter(){
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});
		
		setTitle("Login");
		setBounds(new Rectangle(600, 250, 430, 301));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel txtLogin = new JLabel("Login:");
		txtLogin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtLogin.setBounds(84, 96, 338, 29);
		getContentPane().add(txtLogin);
		
		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtSenha.setBounds(84, 145, 338, 29);
		getContentPane().add(txtSenha);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(135, 101, 183, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(135, 150, 183, 20);
		getContentPane().add(inputSenha);
		
		tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setBounds(0, 27, 432, 29);
		getContentPane().add(tituloLogin);
		
		btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(162, 208, 89, 23);
		getContentPane().add(btnLogin);
		
		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(20, 196, 54, 55);
		getContentPane().add(imgDatabase);
	}
	
	
	
	
	
	
	DAO dao = new DAO();

	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();
			
			if (conexaoBanco == null) {
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOff.png")));
			}
			
			else {
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
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

