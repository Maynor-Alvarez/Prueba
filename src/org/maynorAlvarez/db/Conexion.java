
package org.maynorAlvarez.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author IS4TECH
 */
public class Conexion {
    public static Connection sql;
    
    public static String user="root";
    public static String pass="admin";
    
    public static Connection conectar() throws Exception{
        try{
            String databaseURL ="jdbc:mysql://localhost:3306/Prueba";
            
            
            sql= java.sql.DriverManager.getConnection(databaseURL,user,pass);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception (e);
        }
        return sql;
    }
   
   
   
}
