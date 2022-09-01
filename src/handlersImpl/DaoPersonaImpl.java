package handlersImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.ConnectionManager;
import controllers.Persona;
import connection.ConnectionManager;
import daoInterface.DaoPersona;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import principal.Main;
import ui.AddPeople;
import ui.AllPeople;

public class DaoPersonaImpl implements DaoPersona {

	private static final String COUNT_PEOPLE = "SELECT COUNT(ID_PERSONA) AS CUENTA FROM PERSONA";
	private static final String FIND_ALL = "SELECT * FROM PERSONA";
	private static final String FIND_PEOPLE = "SELECT * FROM PERSONA WHERE ID_PERSONA=?";
	private static final String FIND_ONE = "SELECT * FROM PERSONA WHERE LOWER(APELLIDO1)=? AND LOWER(NOMBRE1) =?";
	private static final String DELETE_PEOPLE = "DELETE FROM PERSONA WHERE ID_PERSONA=?";
	private static final String UPDATE_PEOPLE = "UPDATE PERSONA SET DOCUMENTO=?,APELLIDO1=?,APELLIDO2=?,NOMBRE1=?,NOMBRE2=? WHERE ID_PERSONA=?";
	private static final String INSERT_PEOPLE = "INSERT INTO PERSONA"
			+ "(ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2)" + "VALUES (?, ?, ?, ?, ?, ?)";

	@Override
	public int countAll() {

		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = COUNT_PEOPLE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				int count = 0;

				while (rs.next()) {
					count = rs.getInt(1);
				}
				System.out.println("Cantidad de registros en la tabla PERSONA: " + count);
				return count;
			} catch (SQLException e) {
				System.out.println("Hubo un error en la query COUNT_PEOPLE");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ResultSet findAll() {
		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = FIND_ALL;

			try {

				PreparedStatement pst = cn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Hubo un error en la query FIND_ALL");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addPeople(Persona p) {

		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = INSERT_PEOPLE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);

				pst.setInt(1, p.getIdPersona());
				pst.setString(2, p.getDocumento());
				pst.setString(3, p.getApellido1());
				pst.setString(4, p.getApellido2());
				pst.setString(5, p.getNombre1());
				pst.setString(6, p.getNombre2());

				int rs = pst.executeUpdate();

				if (rs > 0) {
					JOptionPane.showMessageDialog(null, p.getNombre1() + " ingresad@ correctamente");
					AllPeople.table_load();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al insertar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean delete(int id_persona) {

		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = DELETE_PEOPLE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);

				pst.setInt(1, id_persona);

				int rs = pst.executeUpdate();

				if (rs > 0) {
					AllPeople.table_load();
					JOptionPane.showMessageDialog(null, "Persona eliminada!");
				}

			} catch (SQLException e) {
				System.out.println("Hubo un error en el DELETE_PEOPLE");
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al borrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al borrar");
		}

		return false;
	}

	@Override
	public boolean editPeople(Persona p) {
		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = UPDATE_PEOPLE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);

				pst.setString(1, p.getDocumento());
				pst.setString(2, p.getApellido1());
				pst.setString(3, p.getApellido2());
				pst.setString(4, p.getNombre1());
				pst.setString(5, p.getNombre2());
				pst.setInt(6, p.getIdPersona());

				int rs = pst.executeUpdate();

				if (rs > 0) {
					JOptionPane.showMessageDialog(null, p.getNombre1() + " actualizad@ correctamente");
					AllPeople.table_load();
					return true;
				}

			} catch (SQLException e) {
				System.out.println("Hubo un error en el UPDATE_PEOPLE");
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al actualizar");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al actualizar");
			return false;
		}
		return false;
	}

	@Override
	public ResultSet findPeople(int id_persona) {

		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = FIND_PEOPLE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);

				pst.setInt(1, id_persona);
				ResultSet rs = pst.executeQuery();

				if (rs.next() == true) {
					return rs;
				}

			} catch (SQLException e) {
				System.out.println("Hubo un error en la query FIND_ALL");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(String apellido1, String nombre1) {

		try {
			ConnectionManager cm = new ConnectionManager();
			Connection cn = cm.connect();

			String query = FIND_ONE;

			try {

				PreparedStatement pst = cn.prepareStatement(query);

				pst.setString(1, apellido1.toLowerCase());
				pst.setString(2, nombre1.toLowerCase());
				ResultSet rs = pst.executeQuery();

				return rs;

			} catch (SQLException e) {
				System.out.println("Hubo un error en la query FIND_ONE");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
