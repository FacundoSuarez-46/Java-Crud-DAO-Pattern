package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final String driverName = "oracle.jdbc.driver.OracleDriver";
    private final String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String username = "MINIPROYECTO";
    private final String password = "MINIPROYECTO";

    private Connection con = null;

    public ConnectionManager() {
        try {
            Class.forName(driverName);
        } catch (Exception e) {
            System.out.println("No tienes el driver en tu proyecto");
            e.printStackTrace();
        }
    }

    public Connection connect() {
        try {
            con = DriverManager.getConnection(connectionURL, username, password);

            System.out.println("Conexion creada con exito, es posible acceder a la base de datos!");
        } catch (SQLException e) {
            System.out.println("No logramos instanciar una conexion");
            e.printStackTrace();
        }

        return con;
    }

    public void disconnect() {
        try {
            this.con.close();

            System.out.println("Conexion cerrada con exito, ya no es posible acceder a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion!");
            e.printStackTrace();

        }
    }
}
