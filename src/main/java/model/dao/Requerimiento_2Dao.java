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
import model.vo.Requerimiento2;

public class Requerimiento_2Dao {
    //se  requiere  conocer  la información de los líderes de los que ganan la suma igual o menor a 500000, ordenado por salario de menor a mayor. 
    public ArrayList<Requerimiento2> queryRequerimiento2() throws SQLException {

        ArrayList<Requerimiento2> respuesta = new ArrayList<Requerimiento2>();
        Connection conexion = JDBCUtilities.getConnection();

        try{       

            String consulta =   "SELECT Id_Proyecto, Constructora, Ciudad, Estrato "+
                                "FROM Proyecto p  "+
                                "INNER JOIN Tipo t "+
                                "ON t.ID_Tipo = p.ID_Tipo "+
                                "WHERE Ciudad = 'Cartagena' "+
                                "ORDER BY Estrato ASC";


            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while(resultSet.next()){
                Requerimiento2 requerimiento2 = new Requerimiento2();
                requerimiento2.setIdProyecto(resultSet.getInt("Id_Proyecto"));
                requerimiento2.setConstructora(resultSet.getString("Constructora"));
                requerimiento2.setCiudad(resultSet.getString("Ciudad"));
                requerimiento2.setEstrato(resultSet.getInt("Estrato"));

                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(requerimiento2);
            }

            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.err.println("Error consultando los proyectos: "+e);
        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;
    }     
}
