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
import model.vo.Requerimiento1;

public class Requerimiento_1Dao {
    //se  requiere  conocer  la información de los líderes de los que ganan la suma igual o menor a 500000, ordenado por salario de menor a mayor. 
    public ArrayList<Requerimiento1> queryRequerimiento1() throws SQLException {

        ArrayList<Requerimiento1> respuesta = new ArrayList<Requerimiento1>();
        Connection conexion = JDBCUtilities.getConnection();

        try{       

            String consulta =   "SELECT Nombre, Primer_Apellido , ID_Lider, Salario "+
                                "FROM Lider "+
                                "WHERE Salario <= 500000 "+
                                "ORDER BY Salario ASC";


            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while(resultSet.next()){
                Requerimiento1 requerimiento1 = new Requerimiento1();
                requerimiento1.setNombre(resultSet.getString("Nombre"));
                requerimiento1.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                requerimiento1.setIdLider(resultSet.getInt("ID_Lider"));
                requerimiento1.setSalario(resultSet.getInt("Salario"));

                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(requerimiento1);
            }

            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.err.println("Error consultando los lideres: "+e);
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;
    }     
}
