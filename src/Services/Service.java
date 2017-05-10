/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenez
 */
public class Service {

    private int codServicio;
    private String descripcion;
    private int tipoIVA;
    private String tipoIVA_desc;
    private int tipoServicio;
    private String tipoServicio_desc;
    private double precioCosteII;
    private double precioVentaII;
    private String obs;

    public Service() {
    }

    public Service(int codServicio) {
        this.codServicio = codServicio;
    }

    public Service(int codServicio, String descripcion,
            String tipoIVA_desc, String tipoServicio_desc,
            double precioCosteII, double precioVentaII, String obs) {
        this.codServicio = codServicio;
        this.descripcion = descripcion;
        this.tipoIVA_desc = tipoIVA_desc;
        this.tipoServicio_desc = tipoServicio_desc;
        this.precioCosteII = precioCosteII;
        this.precioVentaII = precioVentaII;
        this.obs = obs;
    }

    public Service(int codServicio, String descripcion,
            int tipoIVA, int tipoServicio, double precioCosteII,
            double precioVentaII, String obs) {
        this.codServicio = codServicio;
        this.descripcion = descripcion;
        this.tipoIVA = tipoIVA;
        this.tipoServicio = tipoServicio;
        this.precioCosteII = precioCosteII;
        this.precioVentaII = precioVentaII;
        this.obs = obs;
    }

    public int getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(int codServicio) {
        this.codServicio = codServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoIVA() {
        return tipoIVA;
    }

    public void setTipoIVA(int tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public String getTipoIVA_desc() {
        return tipoIVA_desc;
    }

    public void setTipoIVA_desc(String tipoIVA_desc) {
        this.tipoIVA_desc = tipoIVA_desc;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicio_desc() {
        return tipoServicio_desc;
    }

    public void setTipoServicio_desc(String tipoServicio_desc) {
        this.tipoServicio_desc = tipoServicio_desc;
    }

    public double getPrecioCosteII() {
        return precioCosteII;
    }

    public void setPrecioCosteII(double precioCosteII) {
        this.precioCosteII = precioCosteII;
    }

    public double getPrecioVentaII() {
        return precioVentaII;
    }

    public void setPrecioVentaII(double precioVentaII) {
        this.precioVentaII = precioVentaII;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    /* Columnas en el jTable */
    public String[] getAllColumns() {
        String columns[] = {
            "CódServicio",
            "Descripción",
            "TipoIVA",
            "TipoServicio",
            "PrecioCostexUd(II)",
            "PrecioVentaxUd(II)"};
        return columns;
    }

    /* Obtener la info.de todos los servicios */
    public ArrayList<Service> getServAllData(Connection conDb)
            throws SQLException {

        // Definiciones locales        
        ArrayList<Service> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de todos los servicios          
        pst = conDb.prepareStatement(
                "SELECT a.[codServicio],"
                + " " + "a.[descripcion],"
                + " " + "a.[tipoIVA] & '. ' & b.[descripcion] AS tipoIVA_desc,"
                + " " + "a.[tipoServicio] & '. ' & c.[descripcion] AS tipoServicio_desc,"
                + " " + "a.[precioCosteII],"
                + " " + "a.[precioVentaII]"
                + " " + "FROM [servicios] a"
                + " " + "LEFT OUTER JOIN [tiposIVA] b"
                + " " + "ON b.[tipoIVA] = a.[tipoIVA]"
                + " " + "LEFT OUTER JOIN [tiposServicio] c"
                + " " + "ON c.[tipoServicio] = a.[tipoServicio]");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Service>();
        while (rs.next()) {
            data.add(new Service(
                    rs.getInt("codServicio"),
                    rs.getString("descripcion"),
                    rs.getString("tipoIVA_desc"),
                    rs.getString("tipoServicio_desc"),
                    rs.getDouble("precioCosteII"),
                    rs.getDouble("precioVentaII"),
                    null));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener un código para el nuevo servicio */
    public int getNewServCode(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int newServCode;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener un código para el nuevo servicio  
        pst = conDb.prepareStatement(
                "SELECT MAX(codServicio) + 1"
                + " " + "FROM servicios");
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        newServCode = rs.getInt(1);
        if (newServCode == 0) {
            newServCode = 1;
        }
        rs.close();

        // Retornar datos
        return newServCode;
    }

    /* Obtener la info.de un servicio */
    public ArrayList<Service> getServData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        ArrayList<Service> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.del servicio         
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM servicios"
                + " " + "WHERE codServicio = ?");
        i = 1;
        pst.setInt(i++, this.codServicio);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Service>();
        while (rs.next()) {
            data.add(new Service(
                    rs.getInt("codServicio"),
                    rs.getString("descripcion"),
                    rs.getInt("tipoIVA"),
                    rs.getInt("tipoServicio"),
                    rs.getDouble("precioCosteII"),
                    rs.getDouble("precioVentaII"),
                    rs.getString("obs")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Eliminar la info.de un servicio */
    public void delServData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar la info.del servicio   
        pst = conDb.prepareStatement(
                "DELETE FROM servicios"
                + " " + "WHERE codServicio = ?");
        i = 1;
        pst.setInt(i++, this.codServicio);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar la nueva info.de un servicio */
    public void saveServData(Connection conDb, Boolean isNewServ)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar la nueva info.del servicio   
        if (isNewServ.equals(true)) {
            pst = conDb.prepareStatement(
                    "INSERT INTO servicios"
                    + " " + "(codServicio,"
                    + " " + "descripcion,"
                    + " " + "tipoIVA,"
                    + " " + "tipoServicio,"
                    + " " + "precioCosteII,"
                    + " " + "precioVentaII,"
                    + " " + "obs)"
                    + " " + "VALUES(?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?)");
        } else {
            pst = conDb.prepareStatement(
                    "UPDATE servicios"
                    + " " + "SET descripcion = ?,"
                    + " " + "tipoIVA = ?,"
                    + " " + "tipoServicio = ?,"
                    + " " + "precioCosteII = ?,"
                    + " " + "precioVentaII = ?,"
                    + " " + "obs = ?"
                    + " " + "WHERE codServicio = ?");
        }
        i = 1;
        if (isNewServ.equals(true)) {
            pst.setInt(i++, this.codServicio);
        }
        pst.setString(i++, this.descripcion);
        pst.setInt(i++, this.tipoIVA);
        pst.setInt(i++, this.tipoServicio);
        pst.setDouble(i++, this.precioCosteII);
        pst.setDouble(i++, this.precioVentaII);
        pst.setString(i++, this.obs);
        if (isNewServ.equals(false)) {
            pst.setInt(i++, this.codServicio);
        }
        pst.executeUpdate();
        pst.close();
    }
}
