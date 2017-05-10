/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import Stock.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenek
 */
public class SaleLines {

    private int numVenta;
    private int numLinea;
    private Boolean esArt;
    private int codArtOServ;
    private String descripcion;
    private int cantUds;
    private Double precioUdII;
    private Double impLinea;

    public SaleLines() {
    }

    public SaleLines(int numVenta) {
        this.numVenta = numVenta;
    }

    public SaleLines(int numVenta, int numLinea, Boolean esArt,
            int codArtOServ, String descripcion, int cantUds,
            Double precioUdII, Double impLinea) {
        this.numVenta = numVenta;
        this.numLinea = numLinea;
        this.esArt = esArt;
        this.codArtOServ = codArtOServ;
        this.descripcion = descripcion;
        this.cantUds = cantUds;
        this.precioUdII = precioUdII;
        this.impLinea = impLinea;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }

    public Boolean isEsArt() {
        return esArt;
    }

    public void setEsArt(Boolean esArt) {
        this.esArt = esArt;
    }

    public int getCodArtOServ() {
        return codArtOServ;
    }

    public void setCodArtOServ(int codArtOServ) {
        this.codArtOServ = codArtOServ;
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

    public Double getPrecioUdII() {
        return precioUdII;
    }

    public void setPrecioUdII(Double precioUdII) {
        this.precioUdII = precioUdII;
    }

    public Double getImpLinea() {
        return impLinea;
    }

    public void setImpLinea(Double impLinea) {
        this.impLinea = impLinea;
    }

    /* Columnas en el jTable */
    public String[] getAllColumns() {
        String columns[] = {
            "NúmLínea",
            "EsUnArt?",
            "CódArt/Serv.",
            "Descripción",
            "CantUds.",
            "PrecioUd.(II)",
            "ImpLínea"};
        return columns;
    }

    /* Obtener la info.de las líneas de un registro de venta */
    public ArrayList<SaleLines> getSaleLData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i, asc;
        ArrayList<SaleLines> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de las líneas de un registro de venta
        data = new ArrayList<SaleLines>();
        pst = conDb.prepareStatement(
                "SELECT numLinea,"
                + " " + "esArt,"
                + " " + "codArticulo,"
                + " " + "codServicio,"
                + " " + "descripcion,"
                + " " + "cantUds,"
                + " " + "precioUdII,"
                + " " + "impLinea"
                + " " + "FROM ventasLin"
                + " " + "WHERE numVenta = ?"
                + " " + "ORDER BY numLinea");
        i = 1;
        pst.setInt(i++, this.numVenta);
        rs = pst.executeQuery();
        pst.close();
        while (rs.next()) {
            if (rs.getBoolean("esArt") == true) {
                asc = rs.getInt("codArticulo");
            } else {
                asc = rs.getInt("codServicio");
            }
            data.add(new SaleLines(
                    this.numVenta,
                    rs.getInt("numLinea"),
                    rs.getBoolean("esArt"),
                    asc,
                    rs.getString("descripcion"),
                    rs.getInt("cantUds"),
                    rs.getDouble("precioUdII"),
                    rs.getDouble("impLinea")
            ));
        }
        rs.close();

        // Retornar datos
        return data;
    }
    
    /* Obtener el nro.de venta más reciente donde 
     * se utiliza un código de artículo */
    public int verifyArt(Connection conDb, int ac)
            throws SQLException {

        // Definiciones locales
        int i;
        int sn;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener el nro.de venta más reciente donde 
        // se utiliza el código de artículo
        sn = 0;
        pst = conDb.prepareStatement(
                "SELECT MAX(numVenta)"
                + " " + "FROM ventasLin"
                + " " + "WHERE codArticulo = ?");
        i = 1;
        pst.setInt(i++, ac);
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        sn = rs.getInt(1);        
        rs.close();

        // Retornar datos
        return sn;
    }    
    
    /* Obtener el primer nro.de venta donde se utiliza un código de servicio */
    public int verifyServ(Connection conDb, int sc)
            throws SQLException {

        // Definiciones locales
        int i;
        int sn;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener el nro.de venta más alto donde 
        // se utiliza el código de artículo
        sn = 0;
        pst = conDb.prepareStatement(
                "SELECT MAX(numVenta)"
                + " " + "FROM ventasLin"
                + " " + "WHERE codServicio = ?");
        i = 1;
        pst.setInt(i++, sc);
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        sn = rs.getInt(1);        
        rs.close();

        // Retornar datos
        return sn;
    }    

    /* Eliminar la info.de las líneas de un registro de venta */
    public void delSaleLData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar la info.de las líneas de un registro de venta
        pst = conDb.prepareStatement(
                "DELETE FROM ventasLin"
                + " " + "WHERE numVenta = ?");
        i = 1;
        pst.setInt(i++, this.numVenta);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar la nueva info.de las líneas de un registro de venta */
    public void saveSaleLData(Connection conDb, Boolean isNewSale,
            ArrayList<SaleLines> dataNew) throws SQLException {

        // Definiciones locales
        int i, k, ac, sc;
        PreparedStatement pst;
        Stock stk;
        ArrayList<SaleLines> dataAnt;
        ArrayList<Stock> dataStk;

        // Eliminar la info.de las líneas anteriores del registro de venta y
        // actualizar el stock, si procede
        dataAnt = new ArrayList<SaleLines>();
        if (isNewSale == false) {
            dataAnt = getSaleLData(conDb);
        }
        if (dataAnt.size() > 0) {
            dataStk = new ArrayList<Stock>();
            for (i = 0; i < dataAnt.size(); i++) {
                if (dataAnt.get(i).esArt == true) {
                    dataStk.add(new Stock(
                            dataAnt.get(i).codArtOServ,
                            dataAnt.get(i).cantUds));
                }
            }
            delSaleLData(conDb);
            if (dataStk.size() > 0) {
                stk = new Stock();
                stk.saveStkData(conDb, dataStk, false);
            }
        }

        // Grabar la nueva info.de las líneas del registro de venta y
        // actualizar el stock, si procede
        if (dataNew.size() > 0) {
            dataStk = new ArrayList<Stock>();
            for (i = 0; i < dataNew.size(); i++) {
                if (dataNew.get(i).esArt == true) {
                    dataStk.add(new Stock(dataNew.get(i).codArtOServ,
                            (dataNew.get(i).cantUds * -1)));
                    ac = dataNew.get(i).codArtOServ;
                    sc = 0;
                } else {
                    ac = 0;
                    sc = dataNew.get(i).codArtOServ;
                }
                pst = conDb.prepareStatement(
                        "INSERT INTO ventasLin"
                        + " " + "(numVenta,"
                        + " " + "numLinea,"
                        + " " + "esArt,"
                        + " " + "codArticulo,"
                        + " " + "codServicio,"
                        + " " + "descripcion,"
                        + " " + "cantUds,"
                        + " " + "precioUdII,"
                        + " " + "impLinea)"
                        + " " + "VALUES(?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?,"
                        + " " + "?)");
                k = 1;
                pst.setInt(k++, this.numVenta);
                pst.setInt(k++, dataNew.get(i).numLinea);
                pst.setBoolean(k++, dataNew.get(i).esArt);
                pst.setInt(k++, ac);
                pst.setInt(k++, sc);
                pst.setString(k++, dataNew.get(i).descripcion);
                pst.setInt(k++, dataNew.get(i).cantUds);
                pst.setDouble(k++, dataNew.get(i).precioUdII);
                pst.setDouble(k++, dataNew.get(i).impLinea);
                pst.executeUpdate();
                pst.close();
            }
            if (dataStk.size() > 0) {
                stk = new Stock();
                stk.saveStkData(conDb, dataStk, false);
            }
        }
    }
}
