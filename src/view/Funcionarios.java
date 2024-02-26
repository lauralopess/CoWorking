package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;

	public Funcionarios() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Funcionários");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 614, 448));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		nomeFunc.setBounds(34, 58, 46, 17);
		getContentPane().add(nomeFunc);

		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		loginFunc.setBounds(34, 179, 46, 20);
		getContentPane().add(loginFunc);

		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		senhaFunc.setBounds(323, 182, 46, 14);
		getContentPane().add(senhaFunc);

		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		emailFunc.setBounds(323, 252, 46, 17);
		getContentPane().add(emailFunc);

		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		perfilFunc.setBounds(34, 250, 46, 17);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(74, 57, 402, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);

		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNaTabela();
			}
		});

		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(366, 251, 200, 20);
		getContentPane().add(inputEmail);

		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		inputLogin.setBounds(74, 180, 200, 20);
		getContentPane().add(inputLogin);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(366, 180, 200, 20);
		getContentPane().add(inputSenha);

		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(255, 255, 255));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(317, 334, 65, 54);
		getContentPane().add(imgCreate);

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
			}
		});

		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(255, 255, 255));
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(411, 334, 65, 54);
		getContentPane().add(imgUpdate);

		imgUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarFuncionario();
			}
		});

		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(255, 255, 255));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(501, 334, 65, 54);
		getContentPane().add(imgDelete);

		imgDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarFuncionario();
			}
		});

		inputPerfil = new JComboBox();
		inputPerfil.setBackground(new Color(255, 255, 255));
		inputPerfil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		inputPerfil.setModel(
		new DefaultComboBoxModel(new String[] { "", "Administrador", "Gerência", "Atendimento", "Suporte" }));
		inputPerfil.setBounds(74, 249, 200, 20);
		getContentPane().add(inputPerfil);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 75, 402, 64);
		getContentPane().add(scrollPane);

		tblFuncionarios = new JTable();
		scrollPane.setViewportView(tblFuncionarios);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(255, 255, 255));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(486, 98, 34, 33);
		getContentPane().add(btnPesquisar);

		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(511, 58, 55, 19);
		getContentPane().add(inputID);
		inputID.setColumns(10);

		JLabel idFunc = new JLabel("ID:");
		idFunc.setHorizontalAlignment(SwingConstants.LEFT);
		idFunc.setBounds(486, 60, 34, 14);
		getContentPane().add(idFunc);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarFuncionario();
			}
		});

		tblFuncionarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	private JScrollPane scrollPane;
	private JTable tblFuncionarios;
	private JTextField inputID;

	private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";

		if (inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuário obrigatório!");
			inputNome.requestFocus();
		}

		else if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigatório!");
			inputLogin.requestFocus();
		}

		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigatória!");
			inputSenha.requestFocus();
		}

		else if (inputEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuário obrigatório!");
			inputEmail.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execusão do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

				// Substituir os pontos de interrogação pelo conteúdo das caixas de texto
				// (inputs)
				executarSQL.setString(1, inputNome.getText());
				executarSQL.setString(2, inputLogin.getText());
				executarSQL.setString(3, inputSenha.getText());
				executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
				executarSQL.setString(5, inputEmail.getText());

				// Executar os comandos SQL e inserir o funcionário no banco de dados
				executarSQL.executeUpdate();

				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");

				limparCampos();

				conexaoBanco.close();
			}

			catch (SQLIntegrityConstraintViolationException error) {
				JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário.");
			}

			catch (Exception e) {
				System.out.print(e);

			}

		}

	}

	private void atualizarFuncionario() {
		String update = "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), perfil = ?, email = ? where idFuncionario = ?";

		if (inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuário obrigatório!");
			inputLogin.requestFocus();
		}

		else if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigatório!");
			inputLogin.requestFocus();
		}

		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigatória!");
			inputSenha.requestFocus();
		}

		else if (inputEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuário obrigatório!");
			inputLogin.requestFocus();
		}

		else {

			try {
				Connection conexaoBanco = dao.conectar();

				PreparedStatement executarSQL = conexaoBanco.prepareStatement(update);

				executarSQL.setString(1, inputNome.getText());
				executarSQL.setString(2, inputLogin.getText());
				executarSQL.setString(3, inputSenha.getText());
				executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
				executarSQL.setString(5, inputEmail.getText());
				executarSQL.setString(6, inputID.getText());

				executarSQL.executeUpdate();

				JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");

				limparCampos();

				conexaoBanco.close();
			}

			catch (Exception e) {
				System.out.print(e);

			}

		}
	}

	private void deletarFuncionario() {
		String delete = "delete from funcionario where idFuncionario = ?;";

		try {
			Connection conexaoBanco = dao.conectar();

			PreparedStatement executarSQL = conexaoBanco.prepareStatement(delete);

			executarSQL.setString(1, inputID.getText());

			executarSQL.executeUpdate();

			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");

			limparCampos();

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.print(e);

		}
	}

	private void buscarFuncionarioNaTabela() {
		String readTabela = "select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario where nomeFunc like ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);

			// Substituir o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputNome.getText() + "%");

			// Executar o comando SQL
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utilização da biblioteca rs2xml para "popular"
			// a tabela
			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void setarCaixasTexto() {
		// Criar uma variável para recebera linha da tabela
		int setarLinha = tblFuncionarios.getSelectedRow();

		inputNome.setText(tblFuncionarios.getModel().getValueAt(setarLinha, 1).toString());
		// inputEmail.setText(tblFuncionarios.getModel().getValueAt(setarLinha,
		// 2).toString());
		inputID.setText(tblFuncionarios.getModel().getValueAt(setarLinha, 0).toString());
	}

	// Criar método para buscar funcionários pelo botão pesquisar

	private void btnBuscarFuncionario() {
		String readBtn = "select * from funcionario where idFuncionario = ?;";

		try {
			// Estabelecer conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução do comando SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			// Substituir o ponto de interrogação pelo conteúdo da caixa de texto (nome)
			executarSQL.setString(1, inputID.getText());

			// Executar o comando SQL e exibir o resultadono formulário funcionario (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
				inputEmail.setText(resultadoExecucao.getString(6));
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limparCampos() {
		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		inputPerfil.setSelectedItem("");
		inputEmail.setText(null);
		inputNome.requestFocus();
		// inputPerfil.setSelectedIndex(-1);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
