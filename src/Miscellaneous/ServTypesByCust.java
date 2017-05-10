/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Miscellaneous;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenez
 */
public class ServTypesByCust {

    private int codCliente;
    private int[] tipoServicio;

    public ServTypesByCust() {
    }

    public ServTypesByCust(int codCliente) {
        this.codCliente = codCliente;
    }

    public ServTypesByCust(int codCliente, int[] tipoServicio) {
        this.codCliente = codCliente;
        this.tipoServicio = tipoServicio;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int[] getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int[] tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /* Obtener los tipos de servicio del cliente */
    public ArrayList<ServTypesByCust> getServTypesByCust(Connection conDb)
            throws SQLException {

        // Definiciones locales   
        int i;
        ArrayList<ServTypesByCust> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener los tipos de servicio del cliente        
        pst = conDb.prepareStatement(
                "SELECT tipoServicio"
                + " " + "FROM cliente_tiposServ"
                + " " + "WHERE codCliente = ?"
                + " " + "ORDER BY tipoServicio");
        i = 1;
        pst.setInt(i++, this.codCliente);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<ServTypesByCust>();
        while (rs.next()) {
            data.add(new ServTypesByCust(
                    rs.getInt("tipoServicio")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Eliminar los tipos de servicio del cliente */
    public void delServTypesByCust(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar los tipos de servicio del cliente
        pst = conDb.prepareStatement(
                "DELETE FROM cliente_tiposServ"
                + " " + "WHERE codCliente = ?");
        i = 1;
        pst.setInt(i++, this.codCliente);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar los nuevos tipos de servicio del cliente */
    public void saveServTypesByCust(Connection conDb) 
            throws SQLException {

        // Definiciones locales
        int i, j;        
        PreparedStatement pst;
       
        // Eliminar los tipos de servicio anteriores del cliente
        pst = conDb.prepareStatement(
                "DELETE FROM cliente_tiposServ"
                + " " + "WHERE codCliente = ?");
        i = 1;
        pst.setInt(i++, this.codCliente);
        pst.executeUpdate();
        pst.close();

        // Grabar los nuevos tipos de servicio del cliente     
        for (i = 0; i < this.tipoServicio.length; i++) {
            pst = conDb.prepareStatement(
                    "INSERT INTO cliente_tiposServ"
                    + " " + "(codCliente,"
                    + " " + "tipoServicio)"
                    + " " + "VALUES(?,"
                    + " " + "?)");
            j = 1;
            pst.setInt(j++, this.codCliente);
            pst.setInt(j++, this.tipoServicio[i]);
            pst.executeUpdate();
            pst.close();
        }
    }
}
