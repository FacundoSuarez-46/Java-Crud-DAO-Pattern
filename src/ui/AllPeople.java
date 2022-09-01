package ui;

import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import controllers.Persona;
import handlersImpl.DaoPersonaImpl;
import net.proteanit.sql.DbUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.border.EtchedBorder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllPeople extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField nombrebuscar;
	private JTextField apellidobuscar;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

//        Flat Laf Styles
		UIManager.put("ScrollBar.width", 25);
		UIManager.put("ScrollBar.trackArc", 999);
		UIManager.put("ScrollBar.thumbArc", 999);
		UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
		UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
		UIManager.put("ScrollBar.track", new Color(0xe0e0e0));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllPeople frame = new AllPeople();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public AllPeople() throws SQLException, IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 60, 557, 274);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Lista de personas", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 191, 255)));
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 537, 240);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
				
				  try {
					  AddPeople frame2 = new AddPeople();
					  frame2.setVisible(true);
					}catch(SQLException se) {
						se.printStackTrace();
					}catch(IOException es) {
						es.printStackTrace();
					}	
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setForeground(new Color(0, 191, 255));
		btnNewButton.setBounds(20, 345, 547, 29);
		contentPane.add(btnNewButton);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Buscar Persona", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBounds(10, 0, 314, 57);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 21, 54, 14);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(167, 21, 53, 14);
		panel_1.add(lblNewLabel_2);
		
		nombrebuscar = new JTextField();
		nombrebuscar.setText("");
		nombrebuscar.setBounds(71, 15, 86, 27);
		panel_1.add(nombrebuscar);
		nombrebuscar.setColumns(10);
		
		apellidobuscar = new JTextField();
		apellidobuscar.setBounds(230, 15, 74, 27);
		panel_1.add(apellidobuscar);
		apellidobuscar.setColumns(10);
		
		btnNewButton_1 = new JButton("BUSCAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DaoPersonaImpl d = new DaoPersonaImpl();
				
				String nombre = nombrebuscar.getText();
				String apellido = apellidobuscar.getText();
				
				ResultSet rs = d.findOne(apellido, nombre);		
				table.setModel(DbUtils.resultSetToTableModel(rs));	
				clear();

			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD, 15));
		btnNewButton_1.setForeground(new Color(0, 191, 255));
		btnNewButton_1.setBackground(new Color(255, 165, 0));
		btnNewButton_1.setBounds(334, 11, 87, 40);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("ACTUALIZAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table_load();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setForeground(new Color(0, 191, 255));
		btnNewButton_2.setBackground(new Color(255, 165, 0));
		btnNewButton_2.setFont(new Font("Consolas", Font.BOLD, 15));
		btnNewButton_2.setBounds(431, 11, 125, 38);
		contentPane.add(btnNewButton_2);

		UIManager.put("ScrollBar.width", 30);
		table.disable();
		table_load();

	}

	public static void table_load() throws SQLException {
		DaoPersonaImpl d = new DaoPersonaImpl();
		ResultSet rs = d.findAll();
		table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	void clear() {
		nombrebuscar.setText("");
		apellidobuscar.setText("");
		nombrebuscar.requestFocus();
	}
	
}
