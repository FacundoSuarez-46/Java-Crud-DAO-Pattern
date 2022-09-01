package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import controllers.Persona;
import handlersImpl.DaoPersonaImpl;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ui.AllPeople;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;

public class AddPeople extends JFrame {

	private JPanel contentPane;
	private JTextField nombre1txt;
	private JTextField apellido1txt;
	private JTextField idtxt;
	private JTextField nombre2txt;
	private JTextField apellido2txt;
	private JTextField documentotxt;
	private JTextField id_txt;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPeople frame = new AddPeople();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddPeople() throws SQLException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 191, 255));
		panel.setBorder(new TitledBorder(null, "Agregar Persona", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 73, 557, 301);
		contentPane.add(panel);
		panel.setLayout(null); // modificado

		JButton btnNewButton = new JButton("Limpiar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 11));
		btnNewButton.setBounds(317, 174, 230, 27);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.setBackground(UIManager.getColor("Button.light"));
		btnNewButton_1.setForeground(new Color(0, 191, 255));
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String nombre1 = nombre1txt.getText();
					String nombre2 = nombre2txt.getText();
					String apellido1 = apellido1txt.getText();
					String apellido2 = apellido2txt.getText();
					String documento = documentotxt.getText();
					int idParsed = Integer.parseInt(idtxt.getText());
					
					Persona p = new Persona(idParsed, documento, nombre1, nombre2, apellido1, apellido2);
					DaoPersonaImpl d = new DaoPersonaImpl();
					d.addPeople(p);
					clear();
				}catch(NumberFormatException nfe) {
					nfe.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ingresa un ID");
				}

			
			}
		});
		btnNewButton_1.setBounds(20, 174, 218, 27);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Nombre 1");
		lblNewLabel_1.setForeground(new Color(0, 191, 255));
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 36, 89, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre 2");
		lblNewLabel_2.setForeground(new Color(0, 191, 255));
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_2.setBounds(317, 36, 78, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Apellido 1");
		lblNewLabel_3.setForeground(new Color(0, 191, 255));
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 86, 89, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellido 2");
		lblNewLabel_4.setForeground(new Color(0, 191, 255));
		lblNewLabel_4.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_4.setBounds(317, 86, 89, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Documento");
		lblNewLabel_5.setForeground(new Color(0, 191, 255));
		lblNewLabel_5.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_5.setBounds(317, 136, 78, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setForeground(new Color(0, 191, 255));
		lblNewLabel_6.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 136, 46, 14);
		panel.add(lblNewLabel_6);

		nombre1txt = new JTextField();
		nombre1txt.setBounds(109, 32, 129, 27);
		panel.add(nombre1txt);
		nombre1txt.setColumns(10);

		apellido1txt = new JTextField();
		apellido1txt.setBounds(109, 82, 129, 27);
		panel.add(apellido1txt);
		apellido1txt.setColumns(10);

		idtxt = new JTextField();
		idtxt.setBounds(109, 132, 129, 27);
		panel.add(idtxt);
		idtxt.setColumns(10);

		nombre2txt = new JTextField();
		nombre2txt.setBounds(416, 32, 131, 27);
		panel.add(nombre2txt);
		nombre2txt.setColumns(10);

		apellido2txt = new JTextField();
		apellido2txt.setBounds(416, 82, 131, 27);
		panel.add(apellido2txt);
		apellido2txt.setColumns(10);

		documentotxt = new JTextField();
		documentotxt.setBounds(416, 132, 131, 31);
		panel.add(documentotxt);
		documentotxt.setColumns(10);

		JButton btnNewButton_1_1 = new JButton("Actualizar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre1 = nombre1txt.getText();
				String nombre2 = nombre2txt.getText();
				String apellido1 = apellido1txt.getText();
				String apellido2 = apellido2txt.getText();
				String documento = documentotxt.getText();
				int id = Integer.parseInt(id_txt.getText());

				Persona p = new Persona(id, documento, nombre1, nombre2, apellido1, apellido2);
				DaoPersonaImpl dp = new DaoPersonaImpl();
				dp.editPeople(p);
				clear();

			}
		});
		btnNewButton_1_1.setForeground(new Color(0, 191, 255));
		btnNewButton_1_1.setFont(new Font("Consolas", Font.BOLD, 11));
		btnNewButton_1_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1_1.setBounds(339, 234, 99, 38);
		panel.add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Borrar");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DaoPersonaImpl dp = new DaoPersonaImpl();
				int id = Integer.parseInt(id_txt.getText());
				dp.delete(id);

			}
		});
		btnNewButton_1_1_1.setForeground(new Color(0, 191, 255));
		btnNewButton_1_1_1.setFont(new Font("Consolas", Font.BOLD, 11));
		btnNewButton_1_1_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1_1_1.setBounds(448, 234, 99, 38);
		panel.add(btnNewButton_1_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Actualizar/Borrar (ID)", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBounds(20, 212, 299, 78);
		panel.add(panel_2);
		panel_2.setLayout(null);

		id_txt = new JTextField();
		id_txt.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("null")
			@Override
			public void keyReleased(KeyEvent e){
				
				DaoPersonaImpl d = new DaoPersonaImpl();
				int idparsed = Integer.parseInt(id_txt.getText());
				d.findPeople(idparsed);
				
				ResultSet rs = d.findPeople(idparsed);
				
				if(rs != null) {
					try {
						idtxt.setText(rs.getString(1));
						documentotxt.setText(rs.getString(2));
						apellido1txt.setText(rs.getString(3));
						apellido2txt.setText(rs.getString(4));
						nombre1txt.setText(rs.getString(5));
						nombre2txt.setText(rs.getString(6));
					} catch (HeadlessException | SQLException e1) {
						e1.printStackTrace();
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Revisa el ID ingresado");
					}	
				}else {
					JOptionPane.showMessageDialog(null, "No existe una persona con ese ID");
				}	
			}
		});
		id_txt.setBounds(10, 25, 279, 42);
		panel_2.add(id_txt);
		id_txt.setColumns(10);

		JLabel lblNewLabel = new JLabel("CRUD-DAO");
		lblNewLabel.setForeground(new Color(0, 206, 209));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(159, 24, 153, 38);
		contentPane.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Ver lista de personas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(348, 11, 219, 62);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton_2 = new JButton("VER");
		btnNewButton_2.setBackground(new Color(255, 165, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					AllPeople frame2 = new AllPeople();
					frame2.setVisible(true);
				} catch (SQLException se) {
					se.printStackTrace();
				} catch (IOException es) {
					es.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Consolas", Font.BOLD, 19));
		btnNewButton_2.setForeground(new Color(0, 191, 255));
		btnNewButton_2.setBounds(10, 21, 199, 30);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CERRAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(255, 0, 0));
		btnNewButton_3.setBounds(10, 11, 89, 51);
		contentPane.add(btnNewButton_3);
	}

	void clear() {
		nombre1txt.setText("");
		nombre2txt.setText("");
		apellido1txt.setText("");
		apellido2txt.setText("");
		documentotxt.setText("");
		idtxt.setText("");
		nombre1txt.requestFocus();
	}
}
