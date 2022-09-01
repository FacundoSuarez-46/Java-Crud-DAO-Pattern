package daoInterface;

import connection.ConnectionManager;
import controllers.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface DaoPersona {

	ResultSet findAll();
	int countAll();
	void addPeople(Persona p);
	ResultSet findPeople(int id_persona);
	ResultSet findOne(String apellido1, String nombre1);
	boolean editPeople(Persona p);
	boolean delete(int id_persona);

}
