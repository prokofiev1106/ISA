/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

import Miscellaneous.DbConnection;
import Miscellaneous.ServTypesByCust;
import Sales.SaleHeader;
import java.awt.Component;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author egimenez
 */
public class CustList extends javax.swing.JDialog {

    /**
     * Creates new form CustList_jd
     *
     * @param parent
     * @param modal
     */
    private Boolean isToSel;
    private int codCliente_sel;
    private String nombre_sel;

    /* Constructor std. */
    public CustList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /* Constructor propio */
    public CustList(java.awt.Frame parent, boolean modal, Boolean isToSel) {
        super(parent, modal);
        this.isToSel = isToSel;
        this.codCliente_sel = 0;
        this.nombre_sel = null;
        initComponents();
        loadCustomers();
        if (isToSel == false) {
//          this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            this.jbNewCust.setVisible(false);
            this.jbElimCust.setVisible(false);
//          this.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCustSearch = new javax.swing.JPanel();
        jtfCustSearch = new javax.swing.JTextField();
        jlCustSearch = new javax.swing.JLabel();
        jpFunctions = new javax.swing.JPanel();
        jbNewCust = new javax.swing.JButton();
        jbElimCust = new javax.swing.JButton();
        jpCustList = new javax.swing.JPanel();
        jspCustList = new javax.swing.JScrollPane();
        jtCustList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtfCustSearch.setToolTipText("");
        jtfCustSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jtfCustSearchCaretUpdate(evt);
            }
        });

        jlCustSearch.setText("Filtro");

        javax.swing.GroupLayout jpCustSearchLayout = new javax.swing.GroupLayout(jpCustSearch);
        jpCustSearch.setLayout(jpCustSearchLayout);
        jpCustSearchLayout.setHorizontalGroup(
            jpCustSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCustSearchLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jlCustSearch)
                .addGap(18, 18, 18)
                .addComponent(jtfCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(929, Short.MAX_VALUE))
        );
        jpCustSearchLayout.setVerticalGroup(
            jpCustSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCustSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpCustSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCustSearch))
                .addContainerGap())
        );

        jpFunctions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 1, true));

        jbNewCust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus-icon.png"))); // NOI18N
        jbNewCust.setToolTipText("Crear un nuevo cliente");
        jbNewCust.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbNewCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewCustActionPerformed(evt);
            }
        });

        jbElimCust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minus-icon.png"))); // NOI18N
        jbElimCust.setToolTipText("Eliminar un cliente");
        jbElimCust.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbElimCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbElimCustActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFunctionsLayout = new javax.swing.GroupLayout(jpFunctions);
        jpFunctions.setLayout(jpFunctionsLayout);
        jpFunctionsLayout.setHorizontalGroup(
            jpFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFunctionsLayout.createSequentialGroup()
                .addComponent(jbNewCust, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbElimCust, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpFunctionsLayout.setVerticalGroup(
            jpFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbNewCust, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addComponent(jbElimCust, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        jpCustList.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 1, true), "Relación Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 153, 255))); // NOI18N

        jtCustList.setAutoCreateRowSorter(true);
        jtCustList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtCustList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCustListMouseClicked(evt);
            }
        });
        jspCustList.setViewportView(jtCustList);

        javax.swing.GroupLayout jpCustListLayout = new javax.swing.GroupLayout(jpCustList);
        jpCustList.setLayout(jpCustListLayout);
        jpCustListLayout.setHorizontalGroup(
            jpCustListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jpCustListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspCustList, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );
        jpCustListLayout.setVerticalGroup(
            jpCustListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
            .addGroup(jpCustListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspCustList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCustList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCustSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpFunctions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFunctions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCustList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* Evento que se dispara cuando se realiza 
     * un cambio en el campo de búsqueda */
    private void jtfCustSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jtfCustSearchCaretUpdate

        // Definiciones locales
        TableRowSorter<TableModel> trs;

        // Filtrar clientes s/campo de búsqueda
        trs = new TableRowSorter<TableModel>(((DefaultTableModel) jtCustList.getModel()));
        trs.setRowFilter(RowFilter.regexFilter(jtfCustSearch.getText()));
        jtCustList.setRowSorter(trs);
    }//GEN-LAST:event_jtfCustSearchCaretUpdate

    /* Evento que se dispara cuando se acciona el
     * pulsador de nuevo cliente */
    private void jbNewCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewCustActionPerformed

        // Definiciones locales
        int i;
        DefaultTableModel dtm;
        CustMaint cm;
        ArrayList<Customer> dataCust_ret;
        Object[] data;

        // Acceder a la ficha de nuevo cliente y luego se actualiza 
        // la info.del cliente en el JTable
            cm = new CustMaint((Frame)this.getParent(), true, jtCustList, 0, true);
        cm.setTitle("Nuevo Cliente");
        cm.pack();
        cm.setVisible(true);
        dataCust_ret = cm.getCustData();
        if (dataCust_ret.size() > 0) {
            dtm = (DefaultTableModel) jtCustList.getModel();
            data = new Object[jtCustList.getColumnCount()];
            i = 0;
            data[i++] = dataCust_ret.get(0).getCodCliente();
            data[i++] = dataCust_ret.get(0).getNombre();
            data[i++] = dataCust_ret.get(0).getDir();
            data[i++] = dataCust_ret.get(0).getCiudad();
            data[i++] = dataCust_ret.get(0).getCodPostal();
            data[i++] = dataCust_ret.get(0).getNif();
            data[i++] = dataCust_ret.get(0).getTelFijo();
            data[i++] = dataCust_ret.get(0).getTelMovil();
            data[i++] = dataCust_ret.get(0).geteMail();
            data[i++] = dataCust_ret.get(0).getFechaNac();
            data[i++] = dataCust_ret.get(0).isFumador();
            data[i++] = dataCust_ret.get(0).isAutorizacion();
            dtm.addRow(data);

            // Seleccionar la nueva fila en el Jtable
            i = jtCustList.getRowCount() - 1;
            jtCustList.setRowSelectionInterval(i, i);
        }
    }//GEN-LAST:event_jbNewCustActionPerformed

    /* Evento que se dispara cuando se acciona el
     * pulsador de eliminación de un cliente */
    private void jbElimCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbElimCustActionPerformed

        // Definiciones locales
        int answer, cc, sn, row;
        Boolean verOk;
        DefaultTableModel dtm;
        DbConnection dbc;
        Connection conDb;
        ServTypesByCust stbc;
        Customer cust;
        SaleHeader sh;

        // Determinar la fila seleccionada del JTable
        row = jtCustList.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null,
                    "Es necesario seleccionar un cliente",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Popup grabación info.del cliente
        answer = JOptionPane.showConfirmDialog(null,
                "¿Realmente desea eliminar el cliente seleccionado?",
                null,
                JOptionPane.YES_NO_OPTION);
        if (answer != 0) {
            return;
        }

        // Abrir la conexión a la BBDD  
        try {
            dbc = new DbConnection();
            conDb = dbc.getConBd();
            conDb.setAutoCommit(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No fue posible abrir la conexión a la Base de Datos",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificación Ok por defecto
        verOk = true;
        
        // Verificar si el cliente se utiliza en algún registro de venta
        sn = 0;
        cc = (Integer) jtCustList.getValueAt(row, 0);
        sh = new SaleHeader();
        try {
            sn = sh.verifyCust(conDb, cc);
        } catch (SQLException e) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null,
                    "No fue posible verificar la utilización del cliente"
                    + " " + "en las cabeceras de servicios/venta",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            verOk = false;
        }
        if (sn != 0) {
            JOptionPane.showMessageDialog(null,
                    "No es posible eliminar el cliente, ya que"
                    + " " + "se utiliza en el registro de servicios/venta"
                    + " " + "con núm.doc.:" + " " + sn,
                    null,
                    JOptionPane.ERROR_MESSAGE);
            verOk = false;
        }
        if (verOk == false) {
            // Cerrar la conexión a la BBDD        
            try {
                conDb.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "No fue posible cerrar la conexión a la Base de Datos",
                        null,
                        JOptionPane.ERROR_MESSAGE);                
            }
            return;
        }        
        
        try {
            // Eliminar los tipos de servicio del cliente
            cc = (Integer) jtCustList.getValueAt(row, 0);
            stbc = new ServTypesByCust(cc);
            stbc.delServTypesByCust(conDb);

            // Eliminar la info.del cliente
            cust = new Customer(cc);
            cust.delCustData(conDb);

            // Confirmar la actualización en la BBDD
            conDb.commit();

            // Eliminar el cliente del JTable
            dtm = (DefaultTableModel) jtCustList.getModel();
            dtm.removeRow(row);
        } catch (SQLException e) {
            // Falló la actualización en la BBDD
            try {
                conDb.rollback();
            } catch (SQLException e2) {
            }
            JOptionPane.showMessageDialog(null,
                    "No fue posible eliminar la info.del cliente)",
                    null,
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar la conexión a la BBDD        
            try {
                conDb.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "No fue posible cerrar la conexión a la Base de Datos",
                        null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbElimCustActionPerformed

    /* Evento que se dispara cuando se realiza 
     * un doble Click en una fila del JTable */
    private void jtCustListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCustListMouseClicked

        // Definiciones locales
        int cc, row, col;
        String cn;
        CustMaint cm;
        ArrayList<Customer> dataCust_ret;

        // Con doble click se accede a la ficha del cliente y
        // luego se actualiza la info.del cliente en el JTable o
        // se devuelve el código y nombre del cliente si
        // el JDialog se llamó para seleccionar un cliente
        if (evt.getClickCount() == 2) {
            row = jtCustList.getSelectedRow();
            cc = (Integer) jtCustList.getValueAt(row, 0);
            cn = jtCustList.getValueAt(row, 1).toString();
            if (this.isToSel == false) {
                cm = new CustMaint((Frame)this.getParent(), true, jtCustList, cc, false);
                cm.setTitle("Visualizar/Actualizar Cliente");
                cm.pack();
                cm.setVisible(true);
                dataCust_ret = cm.getCustData();
                if (dataCust_ret.size() > 0) {
                    row = jtCustList.getSelectedRow();
                    col = 0;
                    jtCustList.setValueAt(dataCust_ret.get(0).getCodCliente(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getNombre(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getDir(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getCiudad(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getCodPostal(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getNif(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getTelFijo(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getTelMovil(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).geteMail(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).getFechaNac(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).isFumador(), row, col++);
                    jtCustList.setValueAt(dataCust_ret.get(0).isAutorizacion(), row, col++);
                }
            } else {
                this.codCliente_sel = cc;
                this.nombre_sel = cn;
                this.dispose();
            }
        }
    }//GEN-LAST:event_jtCustListMouseClicked

    /* Cargar la info.de todos los clientes en el jTable */
    private void loadCustomers() {

        // Definiciones locales
        int i, j;
        DbConnection dbc;
        Connection conDb;
        Customer cust;
        String[] columns;
        ArrayList<Customer> dataCust;
        Object[][] data;

        // Abrir la conexión a la BBDD  
        try {
            dbc = new DbConnection();
            conDb = dbc.getConBd();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No fue posible abrir la conexión a la Base de Datos",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Cargar la info.de todos los clientes en el jTable
            cust = new Customer();
            columns = cust.getAllColumns();
            dataCust = cust.getCustAllData(conDb);
            data = new Object[dataCust.size()][columns.length];
            for (i = 0; i < dataCust.size(); i++) {
                j = 0;
                data[i][j++] = dataCust.get(i).getCodCliente();
                data[i][j++] = dataCust.get(i).getNombre();
                data[i][j++] = dataCust.get(i).getDir();
                data[i][j++] = dataCust.get(i).getCiudad();
                data[i][j++] = dataCust.get(i).getCodPostal();
                data[i][j++] = dataCust.get(i).getNif();
                data[i][j++] = dataCust.get(i).getTelFijo();
                data[i][j++] = dataCust.get(i).getTelMovil();
                data[i][j++] = dataCust.get(i).geteMail();
                data[i][j++] = dataCust.get(i).getFechaNac();
                data[i][j++] = dataCust.get(i).isFumador();
                data[i][j++] = dataCust.get(i).isAutorizacion();
            }
            jtCustList.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    columns) {
                        Class[] types = new Class[]{
                            java.lang.Integer.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.Boolean.class,
                            java.lang.Boolean.class};
                        boolean[] canEdit = new boolean[]{
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false};

                        @Override
                        public Class getColumnClass(int columnIndex) {
                            return types[columnIndex];
                        }

                        @Override
                        public boolean isCellEditable(int rowIndex,
                                int columnIndex) {
                            return canEdit[columnIndex];
                        }
                    }
            );

            // Ajustar automáticamente la anchura de las columnas
            jtCustList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            resizeColWidthjTable(jtCustList);

            // Situar el cursor en el campo de búsqueda
            jtfCustSearch.requestFocus();
        } catch (SQLException e) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null,
                    "No fue posible cargar la lista de clientes",
                    null,
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar la conexión a la BBDD        
            try {
                conDb.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "No fue posible cerrar la conexión a la Base de Datos",
                        null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public int getCodCliente_sel() {
        return codCliente_sel;
    }

    public String getNombre_sel() {
        return nombre_sel;
    }

    /* Ajustar la anchura de las columnas del jTable */
    private void resizeColWidthjTable(JTable table) {

        // Definiciones locales
        int column, row;
        int width, prefWidth, maxWidth;
        TableColumn tc;
        TableCellRenderer tcr;
        Component c;

        // Ajustar la anchura de las columnas del jTable
        for (column = 0; column < table.getColumnCount(); column++) {
            tc = table.getColumnModel().getColumn(column);
            prefWidth = tc.getMinWidth();
            maxWidth = tc.getMaxWidth();
            for (row = 0; row < table.getRowCount(); row++) {
                tcr = table.getCellRenderer(row, column);
                c = table.prepareRenderer(tcr, row, column);
                width = c.getPreferredSize().width
                        + table.getIntercellSpacing().width;
                prefWidth = Math.max(prefWidth, width);
                if (prefWidth >= maxWidth) {
                    prefWidth = maxWidth;
                    break;
                }
            }
            tc.setPreferredWidth(prefWidth);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustList dialog = new CustList(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbElimCust;
    private javax.swing.JButton jbNewCust;
    private javax.swing.JLabel jlCustSearch;
    private javax.swing.JPanel jpCustList;
    private javax.swing.JPanel jpCustSearch;
    private javax.swing.JPanel jpFunctions;
    private javax.swing.JScrollPane jspCustList;
    private javax.swing.JTable jtCustList;
    private javax.swing.JTextField jtfCustSearch;
    // End of variables declaration//GEN-END:variables
}
