package Controlador;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Clases.*;

public class CsvProductos {

    // ===== ATRIBUTOS =====
    public static final String nombreArchivo = "Productos.csv";


    // ===== AGREGAR PRODUCTOS CSV =====
    public static void agregarProductosCsv(Producto producto){
        try {
            // ----- CREAR ARCHIVO -----
            File archivo = new File(nombreArchivo);


            // ----- SI EL ARCHIVO NO EXISTE -----
            if (!archivo.exists()) {
                BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo));
                // ----- ENCABEZADOS -----
                escritor.write("ID, NOMBRE, CANTIDAD");
                escritor.newLine();
                escritor.close();
            }


            // ----- ATRIBUTOS -----
            int idProducto          = producto.getId();
            String nombreProducto   = producto.getNombre();
            int cantidadProducto    = producto.getCantidad();


            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo, true));
            escritor.write(idProducto + "," + nombreProducto + "," + cantidadProducto);


            // ----- NUEVA LÍNEA DESPUÉS PARA AGREGAR UN ALUMNO -----
            escritor.newLine();
            escritor.close();


        } catch (IOException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }



    // ===== LISTADO PRODUCTOS CSV =====
    public static List<Object[]> listadoProductos(int filtroId) {
        List<Object[]> productosData  = new ArrayList<>();

        try {
            BufferedReader lector   = new BufferedReader(new FileReader(nombreArchivo));


            String linea;
            boolean primeraLinea    = true;

            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] campos = linea.split(",");

                if (campos.length >= 1) {
                    int idProducto          = Integer.parseInt(campos[0].trim());
                    String nombreProducto   = campos[1].trim();
                    int cantidadProducto    = Integer.parseInt(campos[2].trim());


                    if (filtroId == 0 || idProducto == filtroId) {
                        Object[] rowData = {idProducto, nombreProducto, cantidadProducto};
                        productosData.add(rowData);
                    }
                }
            }
            lector.close();

        } catch (IOException e) {
            System.err.println("Hubo un error al leer el archivo: " + e.getMessage());
        }
        return productosData;
    }


    // ===== ELIMINAR PRODUCTO CSV =====
    public static void eliminarProductoCsv(int idProducto){
        try{
            File archivo = new File(nombreArchivo);

            // Verificar si el archivo existe
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(null, "El archivo de matrículas no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Scanner lector                  = new Scanner(archivo);
            File archivoTemp                = new File(nombreArchivo + ".temp");
            BufferedWriter escritor         = new BufferedWriter(new FileWriter(archivoTemp));

            boolean productoEncontrado      = false;

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();

                if (linea.contains("," + idProducto + ",")) {
                    productoEncontrado      = true;
                } else {
                    escritor.write(linea);
                    escritor.newLine();
                }
            }
            lector.close();
            escritor.close();


            // Eliminar el archivo original y renombrar el temporal
            archivo.delete();
            archivoTemp.renameTo(archivo);

            if (productoEncontrado) {
                JOptionPane.showMessageDialog(null, "El producto de ID " + idProducto + " fue eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un producto con el ID " + idProducto, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
