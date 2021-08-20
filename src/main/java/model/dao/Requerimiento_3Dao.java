package model.dao;

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import util.JDBCUtilities;

//Encapsulamiento de los datos
import model.vo.Requerimiento3;

public class Requerimiento_3Dao {
    //se  requiere  conocer  la información de los líderes de los que ganan la suma igual o menor a 500000, ordenado por salario de menor a mayor. 
    public ArrayList<Requerimiento3> queryRequerimiento3() throws SQLException {

        ArrayList<Requerimiento3> respuesta = new ArrayList<Requerimiento3>();
        Connection conexion = JDBCUtilities.getConnection();

        try{       

            String consulta =   "SELECT ID_Lider, Nombre, Primer_Apellido "+
                                "FROM Lider "+
                                "WHERE Primer_Apellido Like '%z' "+
                                "ORDER BY Nombre ASC";


            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while(resultSet.next()){
                Requerimiento3 requerimiento3 = new Requerimiento3();
                requerimiento3.setIdLider(resultSet.getInt("ID_Lider"));
                requerimiento3.setNombre(resultSet.getString("Nombre"));
                requerimiento3.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                
                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(requerimiento3);
            }

            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.err.println("Error consultando los proyectos de tipo Apartaestudio en Quibdo: "+e);
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;
    }     
}
