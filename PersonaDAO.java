package modelo;

import java.sql.*;

public class PersonaDAO {
    //Cada modelo de datos tiene su modelo DAO con métodos para interactuar con las clases

    public boolean insertar(Persona persona){
        boolean devolver = false;
        //pasar 3 datos con preparedstatement y hacer el insert
        Connection con = BBDDConexion.conectar(); //se invoca a la funcion
        if (persona != null) {
            try { //control de excepciones
                //siempre se invoca un método Prepared
                PreparedStatement pst = con.prepareStatement("insert into personas values (?,?,?)");
                pst.setString(1, persona.getNombre());
                pst.setInt(2, persona.getEdad());
                pst.setInt(3, persona.getIdPoblacion().getId()); //CAMBIO

                int resultado = pst.executeUpdate();
                if (resultado > 0) {
                    devolver = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error al conectarse a la BBDD");
            } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
        return devolver;
    }

    public Persona buscar(String nombre) { //recibe el nombre porque es la clave primaria de la tabla
        Connection con = BBDDConexion.conectar();
        Statement st;
        Persona persona = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select t.nombre, t.edad, p.nombre 'nombrepoblacion', +"
                    + "p.id from personas t join poblaciones p on "
                    + "t.idPoblacion = p.id where lower(t.nombre) = lower('"+nombre+"');");
            if (rs.next()) {
                persona = new Persona();
                Poblacion poblacion = new Poblacion();
                poblacion.setId(rs.getInt("id")); //porque lo devuelve como id
                poblacion.setNombre(rs.getString("nombrepoblacion")); //nombre de la poblacion del campo de la consulta . 2 porque poblacion tiene 2 atributos (campos)
                
                persona.setEdad(rs.getInt("edad"));
                persona.setNombre(rs.getString("nombre"));
                persona.setIdPoblacion(poblacion); //aqui se le pasa el objeto
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
        return persona;
    }

    //Se elimina por clave primaria (String nombre)
    public int eliminar(String nombre) {
        Connection conexion = BBDDConexion.conectar();
        String sql = "DELETE FROM personas WHERE LOWER(nombre) = LOWER(?)";
        PreparedStatement pst = null;
        int res = 0;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setString(1, nombre);
            res = pst.executeUpdate(); //esta ejecución es lo que se acaba devolviendo
            System.out.println("Resultado al eliminar");
        } catch (SQLException ex) {
            System.out.println("Error al conectarse");
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al conectarse a la BBDD");
            }
        }
        return res;

    }
}
