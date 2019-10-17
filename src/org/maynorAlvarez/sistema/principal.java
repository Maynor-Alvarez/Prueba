
package org.maynorAlvarez.sistema;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.maynorAlvarez.bean.Persona;

import org.maynorAlvarez.db.Conexion;
import static org.maynorAlvarez.db.Conexion.conectar;


/**
 *
 * @author IS4TECH
 */
public class principal {
    private static Scanner leo= new Scanner(System.in);
    private static int idPersona;
    private static String nombre;
    private static String apellido;
    private static int numero;
    private static int opcion; 
    private static int editar;
    
    public static void main(String[] args) throws SQLException, Exception {
    conectar();    
        do{
            System.out.println("=============================================");
            System.out.println("Elija una opcion");
            System.out.println("---------------------------------------------");
            System.out.println("1.AGREGAR");
            System.out.println("---------------------------------------------");
            System.out.println("2.LISTAR");
            System.out.println("---------------------------------------------");
            System.out.println("3.ELIMINAR");
            System.out.println("---------------------------------------------");
            System.out.println("4.EDITAR");
            System.out.println("---------------------------------------------");
            System.out.println("5.SALIR");
            System.out.println("=============================================");
            opcion= leo.nextInt();
            switch (opcion){
                case 1: 
                    addPersona();
                    break;
                case 2:
                    listPersona();
                    break;
                case 3:
                   deletePersona();
                    break;
                case 4:
                    editPersona();
                    break;
                case 5:
                    System.out.println("=============================================");
                    System.out.println("TERMINO EL PROGRAMA!");
                    System.out.println("=============================================");
                    System.exit(0);
            }
        }while(opcion!=5);
        
    }
    
    private static void listPersona() throws SQLException {
        Connection conectar = null; 
        ResultSet rs;
        try{
           conectar = Conexion.conectar();
           conectar.setAutoCommit(false);
           
           CallableStatement procedimiento = conectar.prepareCall("{call sp_listPersona}");
           rs =procedimiento.executeQuery();
           
           while(rs.next()){
               System.out.println("=============================================");
               System.out.println("---------------------------------------------");
               System.out.println("ID"+":"+rs.getInt(1));
               System.out.println("---------------------------------------------");
               System.out.println("NOMBRE"+":"+rs.getString(2));
               System.out.println("---------------------------------------------");
               System.out.println("APELLIDO"+":"+rs.getString(3));
               System.out.println("---------------------------------------------");
               System.out.println("NUMERO"+":"+rs.getInt(4));
               System.out.println("---------------------------------------------");
               System.out.println("=============================================");
           }
           
           conectar.commit();
                    
        }catch(Exception e){
            e.printStackTrace();
        }finally{
           conectar.close();
        }
    }
    
    public static void addPersona() throws SQLException{
        Connection conectar = null;
        
        try{
            conectar = Conexion.conectar();
            conectar.setAutoCommit(false);
            System.out.println("---------------------------------------------");
            System.out.println("introduzca su nombre");
            System.out.println("---------------------------------------------");
            nombre=leo.next();
            System.out.println("---------------------------------------------");
            System.out.println("introduzca su apellido");
            System.out.println("---------------------------------------------");
            apellido=leo.next();
            System.out.println("---------------------------------------------");
            System.out.println("introduzca su numero");
            System.out.println("---------------------------------------------");
            int numero = leo.nextInt();
            
            
            CallableStatement procedimiento = conectar.prepareCall("{call sp_addPersona(?,?,?)}");
            
            procedimiento.setString(1, nombre);
            procedimiento.setString(2, apellido);
            procedimiento.setInt(3, numero);
            procedimiento.execute();
            
            conectar.commit();
            System.out.println("---------------------------------------------");
            System.out.println("Persona Agregada con exito!!");
            System.out.println("---------------------------------------------");
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conectar.close();
        }
    }
    
    public static void deletePersona() throws SQLException{
    Connection conectar = null;
    
    try{
        conectar = Conexion.conectar();
        conectar.setAutoCommit(false);
        System.out.println("---------------------------------------------");
        System.out.println("Introduzca el id de la persona a eliminar");
        System.out.println("---------------------------------------------");
        int idPersona = leo.nextInt();
        CallableStatement procedimiento = conectar.prepareCall("{call sp_deletePersona(?)}");
        
        procedimiento.setInt(1, idPersona);
        procedimiento.execute();
        
        conectar.commit();
        System.out.println("---------------------------------------------");
        System.out.println("Persona Eliminada con exito");
        System.out.println("---------------------------------------------");
        
    }catch(Exception e){
        e.printStackTrace();
    }finally {
        conectar.close();
    }
}
    
    public Persona findPersona(int idPersona){
        Connection conectar = null;
        Persona resultado = null;
        try{
            CallableStatement procedimiento = conectar.prepareCall("{call sp_findPersona(?)}");
            procedimiento.setInt(1, idPersona);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
               resultado = new Persona(registro.getInt("idPersona"),
                                        registro.getString("nombre"),
                                        registro.getString("apellido"),
                                        registro.getInt("numero"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public static void editPersona() throws SQLException{
        Connection conectar = null;

        try{
            conectar = Conexion.conectar();
            conectar.setAutoCommit(false);

            System.out.println("---------------------------------------------");
            System.out.println("introduzca el id a modificar");
            System.out.println("---------------------------------------------");
            idPersona = leo.nextInt();
            

                System.out.println("---------------------------------------------");
                System.out.println("Introduzca un nuevo nombre");
                System.out.println("---------------------------------------------");
                nombre = leo.next();
                System.out.println("---------------------------------------------");
                System.out.println("Introduzca un nuevo apellido");
                System.out.println("---------------------------------------------");
                apellido = leo.next();
                System.out.println("---------------------------------------------");
                System.out.println("Introduzca un nuevo numero");
                System.out.println("---------------------------------------------");
                numero = leo.nextInt();
                
         
                    
            CallableStatement procedimiento = conectar.prepareCall("{call sp_editPersona(?,?,?,?)}");
            procedimiento.setInt(1, idPersona);
            procedimiento.setString(2, nombre);
            procedimiento.setString(3, apellido);
            procedimiento.setInt(4, numero);
            procedimiento.execute();
            
            conectar.commit();
            System.out.println("---------------------------------------------");
            System.out.println("Persona Editada con exito");
            System.out.println("---------------------------------------------");
         
     
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            conectar.close();
        }
        
    }
 
   
}

