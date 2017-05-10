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
public class ServType {

    private int tipoServicio;
    private String descripcion;

    public ServType() {
    }

    public ServType(int tipoServicio, String descripcion) {
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getAllColumns() {
        String columns[] = {
            "TipoServicio",
            "Descripción"};
        return columns;
    }

    /* Obtener la info.de todos los tipos de servicio */
    public ArrayList<ServType> getAllServTypes(Connection conDb)
            throws SQLException {

        // Definiciones locales        
        ArrayList<ServType> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de los tipos de servicio 
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM tiposServicio");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<ServType>();
        while (rs.next()) {
            data.add(new ServType(
                    rs.getInt("tipoServicio"),
                    rs.getString("descripcion")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Grabar los nuevos tipos de servicio */
    public void saveServType(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar los nuevos tipos de servicio
        pst = conDb.prepareStatement(
                "UPDATE tiposServicio"
                + " " + "SET descripcion = ?"
                + " " + "WHERE tipoServicio = ?");
        i = 1;
        pst.setString(i++, this.descripcion);
        pst.setInt(i++, this.tipoServicio);
        pst.executeUpdate();
        pst.close();
    }
}
