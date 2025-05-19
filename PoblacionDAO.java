package modelo;

import java.sql.*;
import java.util.ArrayList;

public class PoblacionDAO {

    //1 único método para recoger las poblaciones. Esto es porque es lo único que nos interesa de esa tabla
    public ArrayList<Poblacion> getPoblaciones() {
        ArrayList<Poblacion> lista = null;
        Connection con = BBDDConexion.conectar();

        Statement st = null; //tb try on resources
        ResultSet rs = null;

        try {
            st = con.createStatement();
            //se le pasa la sentencia al ResultSet con executeQuery
            rs = st.executeQuery("select * from poblaciones order by id"); //se ordenan por id las poblaciones para que esten en orden
            lista = new ArrayList<>();//instanciar la lista
            while (rs.next()) {
                Poblacion p = new Poblacion();
                p.setId(rs.getInt("id")); //los nombres de las variables, que son los campos de la tabla
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la BBDD");
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error");
            }

        }

        return lista;
    }
}
