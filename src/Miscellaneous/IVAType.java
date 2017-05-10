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
public class IVAType {

    private int tipoIVA;
    private String descripcion;
    private int porcIVA;

    public IVAType() {
    }

    public IVAType(int tipoIVA, String descripcion, int porcIVA) {
        this.tipoIVA = tipoIVA;
        this.descripcion = descripcion;
        this.porcIVA = porcIVA;
    }

    public int getTipoIVA() {
        return tipoIVA;
    }

    public void setTipoIVA(int tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcIVA() {
        return porcIVA;
    }

    public void setPorcIVA(int porcIVA) {
        this.porcIVA = porcIVA;
    }

    public String[] getAllColumns() {
        String columns[] = {
            "TipoIVA",
            "Descripción",
            "%IVA"};
        return columns;
    }

    /* Obtener la info.de todos los tipos de IVA */
    public ArrayList<IVAType> getAllIVATypes(Connection conDb)
            throws SQLException {

        // Definiciones locales        
        ArrayList<IVAType> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de los tipos de IVA 
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM tiposIVA");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<IVAType>();
        while (rs.next()) {
            data.add(new IVAType(
                    rs.getInt("tipoIVA"),
                    rs.getString("descripcion"),
                    rs.getInt("porcIVA")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Grabar los nuevos tipos de IVA */
    public void saveIVATypes(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar los nuevos tipos de IVA
        pst = conDb.prepareStatement(
                "UPDATE tiposIVA"
                + " " + "SET descripcion = ?,"
                + " " + "porcIVA = ?"
                + " " + "WHERE tipoIVA = ?");
        i = 1;
        pst.setString(i++, this.descripcion);
        pst.setInt(i++, this.porcIVA);
        pst.setInt(i++, this.tipoIVA);
        pst.executeUpdate();
        pst.close();
    }
}
