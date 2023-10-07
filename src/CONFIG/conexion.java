package CONFIG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {

    Connection con;

    public conexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrocrud", "root", "");
        } catch (SQLException e) {
            System.err.println("Error al conectar la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public boolean crearRegistro() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID:"));
            String nombre = JOptionPane.showInputDialog("Ingrese el Nombre:");
            String apellido = JOptionPane.showInputDialog("Ingrese el Apellido:");
            String consultaSQL = "INSERT INTO persona (id, nombre, apellido) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(consultaSQL);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            int filasAfectadas = statement.executeUpdate();
            statement.close();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear el registro: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID debe ser un número entero.");
            return false;
        }
    }

    // Método para leer todos los registros
    public void leerRegistros() {
        try {
            String consultaSQL = "SELECT * FROM persona";
            PreparedStatement statement = con.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al leer los registros: " + e.getMessage());
        }
    }

    // Método para actualizar un registro
    public boolean actualizarRegistro() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del registro que desea actualizar:"));
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo Nombre:");
            String nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo Apellido:");
            String consultaSQL = "UPDATE persona SET nombre=?, apellido=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(consultaSQL);
            statement.setString(1, nuevoNombre);
            statement.setString(2, nuevoApellido);
            statement.setInt(3, id);
            int filasAfectadas = statement.executeUpdate();
            statement.close();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el registro: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un registro
    public boolean eliminarRegistro() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del registro que desea eliminar:"));
            String consultaSQL = "DELETE FROM persona WHERE id=?";
            PreparedStatement statement = con.prepareStatement(consultaSQL);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            statement.close();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el registro: " + e.getMessage());
            return false;
        }
    }

}
