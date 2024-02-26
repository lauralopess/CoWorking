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
import javax.swing.JTextPane;

public class Salas extends JDialog {
	private JTextField inputOcup;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;

	public Salas() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Salas");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 614, 448));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		tipoSala.setBounds(34, 58, 65, 17);
		getContentPane().add(tipoSala);

		JLabel codSala = new JLabel("Código:");
		codSala.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		codSala.setBounds(34, 179, 55, 20);
		getContentPane().add(codSala);

		JLabel andarSala = new JLabel("Andar: \r\n");
		andarSala.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		andarSala.setBounds(323, 182, 46, 14);
		getContentPane().add(andarSala);

		JLabel ocupSala = new JLabel("Ocupação máxima:");
		ocupSala.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		ocupSala.setBounds(298, 276, 115, 17);
		getContentPane().add(ocupSala);

		JLabel numSala = new JLabel("Número:");
		numSala.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		numSala.setBounds(70, 278, 46, 17);
		getContentPane().add(numSala);

		inputOcup = new JTextField();
		inputOcup.setColumns(10);
		inputOcup.setBounds(410, 275, 123, 20);
		getContentPane().add(inputOcup);

		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(255, 255, 255));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		imgCreate.setBounds(317, 334, 65, 54);
		getContentPane().add(imgCreate);

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adicionarFuncionario();
			}
		}); 

		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(255, 255, 255));
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		imgUpdate.setBounds(411, 334, 65, 54);
		getContentPane().add(imgUpdate);

		imgUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//atualizarFuncionario();
			}
		});

		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(255, 255, 255));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		imgDelete.setBounds(501, 334, 65, 54);
		getContentPane().add(imgDelete);

		imgDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deletarFuncionario();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 74, 343, 64);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(255, 255, 255));
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(486, 98, 34, 33);
		getContentPane().add(btnPesquisar);

		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(511, 58, 55, 19);
		getContentPane().add(inputID);
		inputID.setColumns(10);

		JLabel iDSala = new JLabel("ID:");
		iDSala.setHorizontalAlignment(SwingConstants.LEFT);
		iDSala.setBounds(486, 60, 34, 14);
		getContentPane().add(iDSala);
		
		JComboBox inputCategotia = new JComboBox();
		inputCategotia.setBackground(new Color(255, 255, 255));
		inputCategotia.setModel(new DefaultComboBoxModel(new String[] {"", "Sala de reunião", "Sala de conferência", "Espaço de eventos", "Escritório privado"}));
		inputCategotia.setBounds(91, 56, 343, 19);
		getContentPane().add(inputCategotia);
		
		JComboBox inputCod = new JComboBox();
		inputCod.setModel(new DefaultComboBoxModel(new String[] {"", "REU", "CONF", "EVENT", "PRIV"}));
		inputCod.setBounds(91, 179, 183, 21);
		getContentPane().add(inputCod);
		
		JComboBox inputAndar = new JComboBox();
		inputAndar.setModel(new DefaultComboBoxModel(new String[] {"", "Subsolo", "Térreo", "1º andar", "2º andar", "3º andar"}));
		inputAndar.setBounds(366, 179, 200, 22);
		getContentPane().add(inputAndar);
		
		inputNum = new JTextField();
		inputNum.setBounds(127, 276, 123, 21);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//btnBuscarFuncionario();
			}
		});

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//setarCaixasTexto();
			}
		});

	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JScrollPane scrollPane;
	private JTable tblSalas;
	private JTextField inputID;
	private JTextField inputNum;

	/* private void adicionarFuncionario() {
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

		else if (inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuário obrigatório!");
			inputOcup.requestFocus();
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
				executarSQL.setString(5, inputOcup.getText());

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


			try {
				Connection conexaoBanco = dao.conectar();

				PreparedStatement executarSQL = conexaoBanco.prepareStatement(update);

				executarSQL.setString(1, inputNome.getText());
				executarSQL.setString(2, inputLogin.getText());
				executarSQL.setString(3, inputSenha.getText());
				executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
				executarSQL.setString(5, inputOcup.getText());
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

	private void setarCaixasTexto() {
		// Criar uma variável para recebera linha da tabela
		int setarLinha = tblSalas.getSelectedRow();

		inputNome.setText(tblSalas.getModel().getValueAt(setarLinha, 1).toString());
		// inputEmail.setText(tblSalas.getModel().getValueAt(setarLinha,
		// 2).toString());
		inputID.setText(tblSalas.getModel().getValueAt(setarLinha, 0).toString());
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
				inputOcup.setText(resultadoExecucao.getString(6));
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
		inputOcup.setText(null);
		inputNome.requestFocus();
		// inputPerfil.setSelectedIndex(-1);
	}
	
*/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
