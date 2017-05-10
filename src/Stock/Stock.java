/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenez
 */
public class Stock {

    private int codArticulo;
    private String descripcion;
    private int cantUds;

    public Stock() {
    }

    public Stock(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public Stock(int codArticulo, int cantUds) {
        this.codArticulo = codArticulo;
        this.cantUds = cantUds;
    }

    public Stock(int codArticulo, String descripcion, int cantUds) {
        this.codArticulo = codArticulo;
        this.descripcion = descripcion;
        this.cantUds = cantUds;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantUds() {
        return cantUds;
    }

    public void setCantUds(int cantUds) {
        this.cantUds = cantUds;
    }

    /* Columnas en el jTable */
    public String[] getAllColumns() {
        String columns[] = {
            "CódArtículo",
            "CantUds"};
        return columns;
    }

    /* Obtener la info.de todo el stock */
    public ArrayList<Stock> getStkAllData(Connection conDb) 
            throws SQLException {

        // Definiciones locales        
        ArrayList<Stock> data;        
        ResultSet rs;
        PreparedStatement pst;        

        // Obtener la info.de todo el stock 
        pst = conDb.prepareStatement(
                "SELECT a.[codArticulo],"
                + " " + "b.[descripcion],"
                + " " + "a.[cantUds]"
                + " " + "FROM [stock] a"
                + " " + "LEFT OUTER JOIN [articulos] b"
                + " " + "ON b.[codArticulo] = a.[codArticulo]");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Stock>();
        while (rs.next()) {
            data.add(new Stock(
                    rs.getInt("codArticulo"),
                    rs.getString("descripcion"),
                    rs.getInt("cantUds")));
        }
        rs.close();        

        // Retornar datos
        return data;
    }

    /* Obtener la info.del stock de un artículo */
    public ArrayList<Stock> getStkData(Connection conDb) 
            throws SQLException {

        // Definiciones locales
        int i;
        ArrayList<Stock> data;        
        ResultSet rs;
        PreparedStatement pst;        

        // Obtener la info.del stock del artículo       
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM stock"
                + " " + "WHERE codArticulo = ?");
        i = 1;
        pst.setInt(i++, this.codArticulo);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Stock>();
        while (rs.next()) {
            data.add(new Stock(
                    rs.getInt("codArticulo"),
                    rs.getInt("cantUds")));
        }
        rs.close();        

        // Retornar datos
        return data;
    }

    /* Eliminar la info.de un artículo */
    public void delStkData(Connection conDb) throws SQLException {

        // Definiciones locales
        int i;        
        PreparedStatement pst;

        // Eliminar la info.de stock del artículo   
        pst = conDb.prepareStatement(
                "DELETE FROM stock"
                + " " + "WHERE codArticulo = ?");
        i = 1;
        pst.setInt(i++, this.codArticulo);
        pst.executeUpdate();
        pst.close();        
    }

    /* Grabar la nueva info.de stock de los artículos */
    public void saveStkData(Connection conDb, ArrayList<Stock> data,
            Boolean isNewArt) throws SQLException {

        // Definiciones locales
        int i, k;
        PreparedStatement pst;

        // Grabar la nueva info.del stock de los artículos
        if (data.size() > 0) {
            for (i = 0; i < data.size(); i++) {
                if (isNewArt.equals(true)) {
                    pst = conDb.prepareStatement(
                            "INSERT INTO stock"
                            + " " + "(codArticulo,"
                            + " " + "cantUds)"
                            + " " + "VALUES(?,"
                            + " " + "?)");
                } else {
                    pst = conDb.prepareStatement(
                            "UPDATE stock"
                            + " " + "SET cantUds = cantUds + ?"
                            + " " + "WHERE codArticulo = ?");
                }
                k = 1;
                if (isNewArt.equals(true)) {
                    pst.setInt(k++, data.get(i).codArticulo);
                }
                pst.setInt(k++, (Integer) data.get(i).cantUds);
                if (isNewArt.equals(false)) {
                    pst.setInt(k++, data.get(i).codArticulo);
                }
                pst.executeUpdate();
                pst.close();
            }
        }
    }
}
