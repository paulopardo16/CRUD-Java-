package CONFIG;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {
        conexion miConexion = new conexion();
        String[] opciones = {"Crear Registro","Ver Registros" ,"Actualizar Registro", "Eliminar Registro", "Salir"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una función:",
                "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                opciones, opciones[0]);

        switch (seleccion) {
            case 0:
                if (miConexion.crearRegistro()) {
                    System.out.println("Registro creado con éxito.");
                } else {
                    System.out.println("Error al crear el registro.");
                }
                break;
            case 1:
                System.out.println("Registros existentes:");
                miConexion.leerRegistros();
                break;
            case 2:
                // Ejemplo de actualización de un registro
                if (miConexion.actualizarRegistro()) {
                    System.out.println("Registro actualizado con éxito.");
                } else {
                    System.out.println("Error al actualizar el registro.");
                }
                break;
            case 3:
                if (miConexion.eliminarRegistro()) {
                    System.out.println("Registro eliminado con éxito.");
                } else {
                    System.out.println("Error al eliminar el registro.");
                }
                break;
            case 4:
                System.out.println("Saliendo del programa.");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
