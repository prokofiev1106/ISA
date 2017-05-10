/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author egimenez
 */
public class Article {

    private int codArticulo;
    private String descripcion;
    private int tipoIVA;
    private String tipoIVA_desc;
    private int tipoServicio;
    private String tipoServicio_desc;
    private double precioCompraII;
    private double precioVentaII;
    private String obs;
    private int cantUds;

    public Article() {
    }

    public Article(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public Article(int codArticulo, String descripcion,
            String tipoIVA_desc, String tipoServicio_desc,
            double precioCompraII, double precioVentaII, String obs,
            int cantUds) {
        this.codArticulo = codArticulo;
        this.descripcion = descripcion;
        this.tipoIVA_desc = tipoIVA_desc;
        this.tipoServicio_desc = tipoServicio_desc;
        this.precioCompraII = precioCompraII;
        this.precioVentaII = precioVentaII;
        this.obs = obs;
        this.cantUds = cantUds;
    }

    public Article(int codArticulo, String descripcion,
            int tipoIVA, int tipoServicio,
            double precioCompraII, double precioVentaII, String obs,
            int cantUds) {
        this.codArticulo = codArticulo;
        this.descripcion = descripcion;
        this.tipoIVA = tipoIVA;
        this.tipoServicio = tipoServicio;
        this.precioCompraII = precioCompraII;
        this.precioVentaII = precioVentaII;
        this.obs = obs;
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

    public double getPrecioCompraII() {
        return precioCompraII;
    }

    public void setPrecioCompraII(double precioCompraII) {
        this.precioCompraII = precioCompraII;
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
            "Descripción",
            "TipoIVA",
            "TipoServicio",
            "PrecioCompraxUd(II)",
            "PrecioVentaxUd(II)",
            "Stock(Uds)"};
        return columns;
    }

    /* Obtener la info.de todos los artículos */
    public ArrayList<Article> getArtAllData(Connection conDb)
            throws SQLException {

        // Definiciones locales        
        ArrayList<Article> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de todos los artículos         
        pst = conDb.prepareStatement(
                "SELECT a.[codArticulo],"
                + " " + "a.[descripcion],"
                + " " + "a.[tipoIVA] & '. ' & b.[descripcion] AS tipoIVA_desc,"
                + " " + "a.[tipoServicio] & '. ' & c.[descripcion] AS tipoServicio_desc,"
                + " " + "a.[precioCompraII],"
                + " " + "a.[precioVentaII],"
                + " " + "d.[cantUds]"
                + " " + "FROM [articulos] a"
                + " " + "LEFT OUTER JOIN [tiposIVA] b"
                + " " + "ON b.[tipoIVA] = a.[tipoIVA]"
                + " " + "LEFT OUTER JOIN [tiposServicio] c"
                + " " + "ON c.[tipoServicio] = a.[tipoServicio]"
                + " " + "LEFT OUTER JOIN [stock] d"
                + " " + "ON d.[codArticulo] = a.[codArticulo]");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Article>();
        while (rs.next()) {
            data.add(new Article(
                    rs.getInt("codArticulo"),
                    rs.getString("descripcion"),
                    rs.getString("tipoIVA_desc"),
                    rs.getString("tipoServicio_desc"),
                    rs.getDouble("precioCompraII"),
                    rs.getDouble("precioVentaII"),
                    null,
                    rs.getInt("cantUds")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener un código para el nuevo artículo */
    public int getNewArtCode(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int newArtCode;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener un código para el nuevo artículo  
        pst = conDb.prepareStatement(
                "SELECT MAX(codArticulo) + 1"
                + " " + "FROM articulos");
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        newArtCode = rs.getInt(1);
        if (newArtCode == 0) {
            newArtCode = 5001;
        }
        rs.close();

        // Retornar datos
        return newArtCode;
    }

    /* Obtener la info.de un artículo */
    public ArrayList<Article> getArtData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        ArrayList<Article> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.del artículo        
        pst = conDb.prepareStatement(
                "SELECT a.[codArticulo],"
                + " " + "a.[descripcion],"
                + " " + "a.[tipoIVA],"
                + " " + "a.[tipoServicio],"
                + " " + "a.[precioCompraII],"
                + " " + "a.[precioVentaII],"
                + " " + "a.[obs],"
                + " " + "b.[cantUds]"
                + " " + "FROM [articulos] a"
                + " " + "LEFT OUTER JOIN [stock] b"
                + " " + "ON b.[codArticulo] = a.[codArticulo]"
                + " " + "WHERE a.[codArticulo] = ?");
        i = 1;
        pst.setInt(i++, this.codArticulo);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Article>();
        while (rs.next()) {
            data.add(new Article(
                    rs.getInt("codArticulo"),
                    rs.getString("descripcion"),
                    rs.getInt("tipoIVA"),
                    rs.getInt("tipoServicio"),
                    rs.getDouble("precioCompraII"),
                    rs.getDouble("precioVentaII"),
                    rs.getString("obs"),
                    rs.getInt("cantUds")));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Eliminar la info.de un artículo */
    public void delArtData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar la info.del artículo   
        pst = conDb.prepareStatement(
                "DELETE FROM articulos"
                + " " + "WHERE codArticulo = ?");
        i = 1;
        pst.setInt(i++, this.codArticulo);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar la nueva info.de un artículo */
    public void saveArtData(Connection conDb, Boolean isNewArt)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar la nueva info.del artículo   
        if (isNewArt.equals(true)) {
            pst = conDb.prepareStatement(
                    "INSERT INTO articulos"
                    + " " + "(codArticulo,"
                    + " " + "descripcion,"
                    + " " + "tipoIVA,"
                    + " " + "tipoServicio,"
                    + " " + "precioCompraII,"
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
                    "UPDATE articulos"
                    + " " + "SET descripcion = ?,"
                    + " " + "tipoIVA = ?,"
                    + " " + "tipoServicio = ?,"
                    + " " + "precioCompraII = ?,"
                    + " " + "precioVentaII = ?,"
                    + " " + "obs = ?"
                    + " " + "WHERE codArticulo = ?");
        }
        i = 1;
        if (isNewArt.equals(true)) {
            pst.setInt(i++, this.codArticulo);
        }
        pst.setString(i++, this.descripcion);
        pst.setInt(i++, this.tipoIVA);
        pst.setInt(i++, this.tipoServicio);
        pst.setDouble(i++, this.precioCompraII);
        pst.setDouble(i++, this.precioVentaII);
        pst.setString(i++, this.obs);
        if (isNewArt.equals(false)) {
            pst.setInt(i++, this.codArticulo);
        }
        pst.executeUpdate();
        pst.close();
    }
}
