/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenez
 */
public class SaleHeader {

    private int numVenta;
    private int codCliente;
    private String nombre;
    private String fechaVenta;
    private String obs;

    public SaleHeader() {
    }

    public SaleHeader(int numVenta) {
        this.numVenta = numVenta;
    }

    public SaleHeader(int numVenta, int codCliente, String nombre,
            String fechaVenta, String obs) {
        this.numVenta = numVenta;
        this.codCliente = codCliente;
        this.nombre = nombre;
        this.fechaVenta = fechaVenta;
        this.obs = obs;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    /* Columnas en el JTable */
    public String[] getAllColumns() {
        String columns[] = {
            "NúmDoc.",
            "CódCliente",
            "Nombre",
            "FechaDoc."};
        return columns;
    }

    /* Obtener la info.de todas las cabs.de registros de venta */
    public ArrayList<SaleHeader> getSalesHAllData(Connection conDb)
            throws SQLException {

        // Definiciones locales       
        ArrayList<SaleHeader> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de todas las cabs.de registros de venta          
        pst = conDb.prepareStatement(
                "SELECT a.[numVenta],"
                + " " + "a.[codCliente],"
                + " " + "b.[nombre],"
                + " " + "a.[fechaVenta]"
                + " " + "FROM ventasCab a"
                + " " + "LEFT OUTER JOIN clientes b"
                + " " + "ON b.[codCliente] = a.[codCliente]"
                + " " + "ORDER BY a.[numVenta] DESC");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<SaleHeader>();
        while (rs.next()) {
            data.add(new SaleHeader(
                    rs.getInt("numVenta"),
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("fechaVenta"),
                    null));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener un número para el nuevo registro de venta */
    public int getNewSaleNum(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int newSalesCode;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener un número para el nuevo registro de venta   
        pst = conDb.prepareStatement(
                "SELECT MAX(numVenta) + 1"
                + " " + "FROM ventasCab");
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        newSalesCode = rs.getInt(1);
        if (newSalesCode == 0) {
            newSalesCode = 1;
        }
        rs.close();

        // Retornar datos
        return newSalesCode;
    }

    /* Obtener la info.de la cab.de un registro de venta */
    public ArrayList<SaleHeader> getSaleHData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        ArrayList<SaleHeader> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de cab.del registro de venta          
        pst = conDb.prepareStatement(
                "SELECT a.[numVenta],"
                + " " + "a.[codCliente],"
                + " " + "b.[nombre],"
                + " " + "a.[fechaVenta],"
                + " " + "a.[obs]"
                + " " + "FROM ventasCab a"
                + " " + "LEFT OUTER JOIN clientes b"
                + " " + "ON b.[codCliente] = a.[codCliente]"
                + " " + "WHERE numVenta = ?");
        i = 1;
        pst.setInt(i++, this.numVenta);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<SaleHeader>();
        while (rs.next()) {
            data.add(new SaleHeader(
                    rs.getInt("numVenta"),
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("fechaVenta"),
                    rs.getString("obs")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener el nro.de venta más reciente donde 
     * se utiliza un código de cliente */
    public int verifyCust(Connection conDb, int cc)
            throws SQLException {

        // Definiciones locales
        int i;
        int sn;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener el nro.de venta más reciente donde 
        // se utiliza el código de cliente
        sn = 0;
        pst = conDb.prepareStatement(
                "SELECT MAX(numVenta)"
                + " " + "FROM ventasCab"
                + " " + "WHERE codCliente = ?");
        i = 1;
        pst.setInt(i++, cc);
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        sn = rs.getInt(1);       
        rs.close();

        // Retornar datos
        return sn;
    }

    /* Eliminar la info.de la cab.de un registro de venta */
    public void delSaleHData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar la info.de la cab.de un registro de venta
        pst = conDb.prepareStatement(
                "DELETE FROM ventasCab"
                + " " + "WHERE numVenta = ?");
        i = 1;
        pst.setInt(i++, this.numVenta);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar la nueva info.de la cab.de un registro de venta */
    public void saveSaleHData(Connection conDb, Boolean isNewSales)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar la nueva info.de la cab.del registro de venta
        if (isNewSales.equals(true)) {
            pst = conDb.prepareStatement(
                    "INSERT INTO ventasCab"
                    + " " + "(numVenta,"
                    + " " + "codCliente,"
                    + " " + "fechaVenta,"
                    + " " + "obs)"
                    + " " + "VALUES(?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?)");
        } else {
            pst = conDb.prepareStatement(
                    "UPDATE ventasCab"
                    + " " + "SET codCliente = ?,"
                    + " " + "fechaVenta = ?,"
                    + " " + "obs = ?"
                    + " " + "WHERE numVenta = ?");
        }
        i = 1;
        if (isNewSales.equals(true)) {
            pst.setInt(i++, this.numVenta);
        }
        pst.setInt(i++, this.codCliente);
        pst.setString(i++, this.fechaVenta);
        pst.setString(i++, this.obs);
        if (isNewSales.equals(false)) {
            pst.setInt(i++, this.numVenta);
        }
        pst.executeUpdate();
        pst.close();
    }
}
