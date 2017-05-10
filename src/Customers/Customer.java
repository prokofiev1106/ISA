/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author egimenez
 */
public class Customer {

    private int codCliente;
    private String nombre;
    private String dir;
    private String ciudad;
    private String codPostal;
    private String nif;
    private String telFijo;
    private String telMovil;
    private String eMail;
    private String alerta;
    private String fechaNac;
    private Boolean fumador;
    private Boolean autorizacion;
    private String obs;
    private long diasUltVenta;

    public Customer() {
    }

    public Customer(int codCliente) {
        this.codCliente = codCliente;
    }

    public Customer(int codCliente, String nombre, String dir, String ciudad, 
            String codPostal, String nif, String telFijo, String telMovil, 
            String eMail, String alerta, String fechaNac, Boolean fumador, 
            Boolean autorizacion, String obs,
            long diasUltVenta) {
        this.codCliente = codCliente;
        this.nombre = nombre;
        this.dir = dir;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.nif = nif;
        this.telFijo = telFijo;
        this.telMovil = telMovil;
        this.eMail = eMail;
        this.alerta = alerta;
        this.fechaNac = fechaNac;
        this.fumador = fumador;
        this.autorizacion = autorizacion;
        this.obs = obs;
        this.diasUltVenta = diasUltVenta;
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAlerta() {
        return alerta;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Boolean isFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public Boolean isAutorizacion() {
        return autorizacion;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public long getDiasUltVenta() {
        return diasUltVenta;
    }

    public void setDiasUltVenta(long diasUltVenta) {
        this.diasUltVenta = diasUltVenta;
    }

    /* Columnas en el jTable */
    public String[] getAllColumns() {
        String columns[] = {
            "CódCliente",
            "Nombre",
            "Dirección",
            "Ciudad",
            "CódPostal",
            "NIF",
            "TelFijo",
            "TelMóvil",
            "e-mail",
            "FNac.",
            "Fuma?",
            "Autorización?"};
        return columns;
    }

    /* Obtener la info.de todos los clientes */
    public ArrayList<Customer> getCustAllData(Connection conDb)
            throws SQLException {

        // Definiciones locales        
        ArrayList<Customer> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de todos los clientes 
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM clientes");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Customer>();
        while (rs.next()) {
            data.add(new Customer(
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("dir"),
                    rs.getString("ciudad"),
                    rs.getString("codPostal"),
                    rs.getString("nif"),
                    rs.getString("telFijo"),
                    rs.getString("telMovil"),
                    rs.getString("email"),
                    null,
                    rs.getString("fechaNac"),
                    rs.getBoolean("fumador"),
                    rs.getBoolean("autorizacion"),
                    null,
                    0));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener un código para el nuevo cliente */
    public int getNewCustCode(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int newCustCode;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener un código para el nuevo cliente   
        pst = conDb.prepareStatement(
                "SELECT MAX(codCliente) + 1"
                + " " + "FROM clientes");
        rs = pst.executeQuery();
        pst.close();
        rs.next();
        newCustCode = rs.getInt(1);
        if (newCustCode == 0) {
            newCustCode = 1;
        }
        rs.close();

        // Retornar datos
        return newCustCode;
    }

    /* Obtener la info.de un cliente */
    public ArrayList<Customer> getCustData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        ArrayList<Customer> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.del cliente           
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM clientes"
                + " " + "WHERE codCliente = ?");
        i = 1;
        pst.setInt(i++, this.codCliente);
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Customer>();
        while (rs.next()) {
            data.add(new Customer(
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("dir"),
                    rs.getString("ciudad"),
                    rs.getString("codPostal"),
                    rs.getString("nif"),
                    rs.getString("telFijo"),
                    rs.getString("telMovil"),
                    rs.getString("email"),
                    rs.getString("alerta"),
                    rs.getString("fechaNac"),
                    rs.getBoolean("fumador"),
                    rs.getBoolean("autorizacion"),
                    rs.getString("obs"),
                    0));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Eliminar la info.de un cliente */
    public void delCustData(Connection conDb)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Eliminar la info.del cliente   
        pst = conDb.prepareStatement(
                "DELETE FROM clientes"
                + " " + "WHERE codCliente = ?");
        i = 1;
        pst.setInt(i++, this.codCliente);
        pst.executeUpdate();
        pst.close();
    }

    /* Grabar la nueva info.de un cliente */
    public void saveCustData(Connection conDb, Boolean isNewCust)
            throws SQLException {

        // Definiciones locales
        int i;
        PreparedStatement pst;

        // Grabar la nueva info.del cliente   
        if (isNewCust.equals(true)) {
            pst = conDb.prepareStatement(
                    "INSERT INTO clientes"
                    + " " + "(codCliente,"
                    + " " + "nombre,"
                    + " " + "dir,"
                    + " " + "ciudad,"
                    + " " + "codPostal,"        
                    + " " + "nif,"
                    + " " + "telFijo,"
                    + " " + "telMovil,"
                    + " " + "eMail,"
                    + " " + "alerta,"
                    + " " + "fechaNac,"
                    + " " + "fumador,"
                    + " " + "autorizacion,"        
                    + " " + "obs)"
                    + " " + "VALUES(?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"        
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?,"
                    + " " + "?)");
        } else {
            pst = conDb.prepareStatement(
                    "UPDATE clientes"
                    + " " + "SET nombre = ?,"
                    + " " + "dir = ?,"
                    + " " + "ciudad = ?,"
                    + " " + "codPostal = ?,"        
                    + " " + "nif = ?,"
                    + " " + "telFijo = ?,"
                    + " " + "telMovil = ?,"
                    + " " + "eMail = ?,"
                    + " " + "alerta = ?,"        
                    + " " + "fechaNac = ?,"
                    + " " + "fumador = ?,"
                    + " " + "autorizacion = ?,"        
                    + " " + "obs = ?"
                    + " " + "WHERE codCliente = ?");
        }
        i = 1;
        if (isNewCust.equals(true)) {
            pst.setInt(i++, this.codCliente);
        }
        pst.setString(i++, this.nombre);
        pst.setString(i++, this.dir);
        pst.setString(i++, this.ciudad);
        pst.setString(i++, this.codPostal);
        pst.setString(i++, this.nif);
        pst.setString(i++, this.telFijo);
        pst.setString(i++, this.telMovil);
        pst.setString(i++, this.eMail);
        pst.setString(i++, this.alerta);
        pst.setString(i++, this.fechaNac);
        pst.setBoolean(i++, this.fumador);
        pst.setBoolean(i++, this.autorizacion);
        pst.setString(i++, this.obs);
        if (isNewCust.equals(false)) {
            pst.setInt(i++, this.codCliente);
        }
        pst.executeUpdate();
        pst.close();
    }

    /* Obtener la info.de los clientes que hoy cumplen años */
    public ArrayList<Customer> getCustBirthday(Connection conDb)
            throws SQLException {

        // Definiciones locales 
        int i;
        SimpleDateFormat sdt;
        Date d;
        ArrayList<Customer> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de los clientes que hoy cumplen años
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM clientes"
                + " " + "WHERE REGEXP_MATCHES(fechaNac, ?)");
        sdt = new SimpleDateFormat("dd-MM");
        d = new Date();
        i = 1;
        pst.setString(i++, "^" + sdt.format(d) + "-[0-9][0-9][0-9][0-9]");
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Customer>();
        while (rs.next()) {
            data.add(new Customer(
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("dir"),
                    rs.getString("ciudad"),
                    rs.getString("codPostal"),
                    rs.getString("nif"),
                    rs.getString("telFijo"),
                    rs.getString("telMovil"),
                    rs.getString("email"),
                    null,
                    rs.getString("fechaNac"),
                    rs.getBoolean("fumador"),
                    rs.getBoolean("autorizacion"),
                    null,
                    0));
        }
        rs.close();

        // Retornar datos
        return data;
    }

    /* Obtener la info.de los clientes cuyo último servicio/venta 
     * fue hace más de x días */
    public ArrayList<Customer> getCustWOSales(Connection conDb, int daysWOSales)
            throws SQLException {

        // Definiciones locales
        int i;
        Calendar c;
        SimpleDateFormat sdt;
        Date d1, d2;
        ArrayList<Customer> data;
        ResultSet rs;
        PreparedStatement pst;

        // Obtener la info.de los clientes cuyo último servicio/venta 
        // fue hace más de x días */
        pst = conDb.prepareStatement(
                "SELECT *"
                + " " + "FROM"
                + " " + "(SELECT a.*,"
                + " " + "(SELECT MAX(FORMAT(b.[fechaVenta], 'yyyyMMdd'))"
                + " " + "FROM ventasCab b"
                + " " + "WHERE b.[codCliente] = a.[codCliente]) AS ultFechaVenta"
                + " " + "FROM clientes a"
                + " " + "WHERE"
                + " " + "(SELECT COUNT(*)"
                + " " + "FROM ventasCab c"
                + " " + "WHERE c.[codCliente] = a.[codCliente]"
                + " " + "AND FORMAT(c.[fechaVenta], 'yyyyMMdd') >= ?) = 0)"
                + " " + "ORDER BY ultFechaVenta");
        c = Calendar.getInstance();
        c.add(Calendar.DATE, (daysWOSales * -1));
        i = 1;
        sdt = new SimpleDateFormat("yyyyMMdd");
        pst.setString(i++, sdt.format(c.getTime()));
        rs = pst.executeQuery();
        pst.close();
        data = new ArrayList<Customer>();
        while (rs.next()) {
            try {
                if (rs.getString("ultFechaVenta") != null) {
                    d1 = new Date();
                    d2 = sdt.parse(rs.getString("ultFechaVenta"));
                    this.diasUltVenta = (d1.getTime() - d2.getTime()) / 86400000;
                } else {
                    this.diasUltVenta = 0;
                }
            } catch (ParseException e) {
            }
            data.add(new Customer(
                    rs.getInt("codCliente"),
                    rs.getString("nombre"),
                    rs.getString("dir"),
                    rs.getString("ciudad"),
                    rs.getString("codPostal"),
                    rs.getString("nif"),
                    rs.getString("telFijo"),
                    rs.getString("telMovil"),
                    rs.getString("email"),
                    null,
                    rs.getString("fechaNac"),
                    rs.getBoolean("fumador"),
                    rs.getBoolean("autorizacion"),
                    null,
                    this.diasUltVenta));
        }
        rs.close();

        // Retornar datos
        return data;
    }
}
