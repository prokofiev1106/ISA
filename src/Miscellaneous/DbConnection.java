/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Miscellaneous;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author egimenez
 */
public class DbConnection {

    private Connection conBd;

    public DbConnection() {
    }

    public Connection getConBd() throws SQLException {

        // Definiciones locales
        String jdbcURL;
                
        // URL del JDBC
        jdbcURL = "jdbc:ucanaccess://.\\bbdd.accdb;memory=false";
        
        // Abrir la conexión a la BBDD        
        conBd = DriverManager.getConnection(jdbcURL);

        // Retornar la conexión a la BBDD
        return conBd;
    }
}
