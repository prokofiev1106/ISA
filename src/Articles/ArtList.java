/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Articles;

import Miscellaneous.DbConnection;
import Sales.SaleLines;
import Stock.Stock;
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
public class ArtList extends javax.swing.JDialog {

    /**
     * Creates new form ArtList
     */
    private Boolean isToSel;
    private int codArticulo_sel;
    private String descripcion_sel;
    private Double precioCompraII_sel;
    private Double precioVentaII_sel;

    /* Constructor std. */
    public ArtList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /* Constructor propio */
    public ArtList(java.awt.Frame parent, boolean modal, Boolean isToSel) {
        super(parent, modal);
        this.isToSel = isToSel;
        this.codArticulo_sel = 0;
        this.descripcion_sel = null;
        this.precioCompraII_sel = 0.0;
        this.precioVentaII_sel = 0.0;
        initComponents();
        loadArticles();
        if (isToSel == false) {
//          this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            this.jbNewArt.setVisible(false);
            this.jbElimArt.setVisible(false);
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

        jpArtList = new javax.swing.JPanel();
        jspArtList = new javax.swing.JScrollPane();
        jtArtList = new javax.swing.JTable();
        jpaArtSearch = new javax.swing.JPanel();
        jtfArtSearch = new javax.swing.JTextField();
        jlArtSearch = new javax.swing.JLabel();
        jpFunctions = new javax.swing.JPanel();
        jbNewArt = new javax.swing.JButton();
        jbElimArt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jpArtList.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 1, true), "Relación Artículos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 153, 255))); // NOI18N
        jpArtList.setToolTipText("");

        jtArtList.setAutoCreateRowSorter(true);
        jtArtList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtArtList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtArtListMouseClicked(evt);
            }
        });
        jspArtList.setViewportView(jtArtList);

        javax.swing.GroupLayout jpArtListLayout = new javax.swing.GroupLayout(jpArtList);
        jpArtList.setLayout(jpArtListLayout);
        jpArtListLayout.setHorizontalGroup(
            jpArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jpArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspArtList))
        );
        jpArtListLayout.setVerticalGroup(
            jpArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
            .addGroup(jpArtListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspArtList, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        jtfArtSearch.setToolTipText("");
        jtfArtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jtfArtSearchCaretUpdate(evt);
            }
        });

        jlArtSearch.setText("Filtro");

        javax.swing.GroupLayout jpaArtSearchLayout = new javax.swing.GroupLayout(jpaArtSearch);
        jpaArtSearch.setLayout(jpaArtSearchLayout);
        jpaArtSearchLayout.setHorizontalGroup(
            jpaArtSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpaArtSearchLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jlArtSearch)
                .addGap(18, 18, 18)
                .addComponent(jtfArtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(759, Short.MAX_VALUE))
        );
        jpaArtSearchLayout.setVerticalGroup(
            jpaArtSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaArtSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpaArtSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfArtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlArtSearch))
                .addContainerGap())
        );

        jpFunctions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 1, true));
        jpFunctions.setToolTipText("");

        jbNewArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus-icon.png"))); // NOI18N
        jbNewArt.setToolTipText("Crear un nuevo artículo");
        jbNewArt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbNewArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNewArtActionPerformed(evt);
            }
        });

        jbElimArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minus-icon.png"))); // NOI18N
        jbElimArt.setToolTipText("Eliminar un artículo");
        jbElimArt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbElimArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbElimArtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFunctionsLayout = new javax.swing.GroupLayout(jpFunctions);
        jpFunctions.setLayout(jpFunctionsLayout);
        jpFunctionsLayout.setHorizontalGroup(
            jpFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFunctionsLayout.createSequentialGroup()
                .addComponent(jbNewArt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbElimArt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpFunctionsLayout.setVerticalGroup(
            jpFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbNewArt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jbElimArt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpArtList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpaArtSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpFunctions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpFunctions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpaArtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpArtList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* Evento que se dispara cuando se realiza 
     * un doble Click en una fila del JTable */
    private void jtArtListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtArtListMouseClicked

        // Definiciones locales
        int col, ac, row;
        String ad;
        Double pp, sp;
        ArtMaint am;
        ArrayList<Article> dataArt_ret;

        // Con doble click se accede a la ficha del artículo y
        // luego se actualiza la info.del artículo en JTable o
        // se devuelve el código, descripción y precio venta 
        // del artículo si el JDialog se llamó para seleccionar 
        // un artículo
        if (evt.getClickCount() == 2) {
            row = jtArtList.getSelectedRow();
            ac = (Integer) jtArtList.getValueAt(row, 0);
            ad = jtArtList.getValueAt(row, 1).toString();
            pp = (Double) jtArtList.getValueAt(row, 4);
            sp = (Double) jtArtList.getValueAt(row, 5);
            if (this.isToSel == false) {
                am = new ArtMaint((Frame) this.getParent(), true, jtArtList, ac, false);
                am.setTitle("Visualizar/Actualizar Artículo");
                am.pack();
                am.setVisible(true);
                dataArt_ret = am.getArtData();
                if (dataArt_ret.size() > 0) {
                    row = jtArtList.getSelectedRow();
                    col = 0;
                    jtArtList.setValueAt(dataArt_ret.get(0).getCodArticulo(), row, col++);
                    jtArtList.setValueAt(dataArt_ret.get(0).getDescripcion(), row, col++);
                    jtArtList.setValueAt(dataArt_ret.get(0).getTipoIVA_desc(), row, col++);
                    jtArtList.setValueAt(dataArt_ret.get(0).getTipoServicio_desc(), row, col++);
                    jtArtList.setValueAt(dataArt_ret.get(0).getPrecioCompraII(), row, col++);
                    jtArtList.setValueAt(dataArt_ret.get(0).getPrecioVentaII(), row, col++);
                }
            } else {
                this.codArticulo_sel = ac;
                this.descripcion_sel = ad;
                this.precioCompraII_sel = pp;
                this.precioVentaII_sel = sp;
                this.dispose();
            }
        }
    }//GEN-LAST:event_jtArtListMouseClicked

    /* Evento que se dispara cuando se realiza 
     * un cambio en el campo de búsqueda */
    private void jtfArtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jtfArtSearchCaretUpdate

        // Definiciones locales
        TableRowSorter<TableModel> trs;

        // Filtrar artículos s/campo de búsqueda
        trs = new TableRowSorter<TableModel>(((DefaultTableModel) jtArtList.getModel()));
        trs.setRowFilter(RowFilter.regexFilter(jtfArtSearch.getText()));
        jtArtList.setRowSorter(trs);
    }//GEN-LAST:event_jtfArtSearchCaretUpdate

    /* Evento que se dispara cuando se acciona el
     * pulsador de nuevo artículo */
    private void jbNewArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNewArtActionPerformed

        // Definiciones locales   
        int i;
        DefaultTableModel dtm;
        ArtMaint am;
        ArrayList<Article> dataArt_ret;
        Object[] data;

        // Acceder a la ficha del nuevo artículo y luego se actualiza 
        // la info.del artículo en el JTable
        am = new ArtMaint((Frame) this.getParent(), true, jtArtList, 0, true);
        am.setTitle("Nuevo Artículo");
        am.pack();
        am.setVisible(true);
        dataArt_ret = am.getArtData();
        if (dataArt_ret.size() > 0) {
            dtm = (DefaultTableModel) jtArtList.getModel();
            data = new Object[jtArtList.getColumnCount()];
            i = 0;
            data[i++] = dataArt_ret.get(0).getCodArticulo();
            data[i++] = dataArt_ret.get(0).getDescripcion();
            data[i++] = dataArt_ret.get(0).getTipoIVA_desc();
            data[i++] = dataArt_ret.get(0).getTipoServicio_desc();
            data[i++] = dataArt_ret.get(0).getPrecioCompraII();
            data[i++] = dataArt_ret.get(0).getPrecioVentaII();
            dtm.addRow(data);

            // Seleccionar la nueva fila en el Jtable
            i = jtArtList.getRowCount() - 1;
            jtArtList.setRowSelectionInterval(i, i);
        }
    }//GEN-LAST:event_jbNewArtActionPerformed

    /* Evento que se dispara cuando se acciona el
     * pulsador de eliminación de un artículo */
    private void jbElimArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbElimArtActionPerformed

        // Definiciones locales
        int answer, ac, sn, pn, row;
        Boolean verOk;
        DefaultTableModel dtm;
        DbConnection dbc;
        Connection conDb;
        Article art;
        Stock stk;
        ArrayList<Stock> dataStk;
        SaleLines sl;

        // Determinar la fila seleccionada del JTable
        row = jtArtList.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null,
                    "Es necesario seleccionar un artículo",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Popup grabación info.del artículo
        answer = JOptionPane.showConfirmDialog(null,
                "¿Realmente desea eliminar el artículo seleccionado?",
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

        // Verificar si el artículo aún posee stock
        dataStk = new ArrayList<Stock>();
        ac = (Integer) jtArtList.getValueAt(row, 0);
        stk = new Stock(ac);
        try {
            dataStk = stk.getStkData(conDb);
        } catch (SQLException e) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null,
                    "No fue posible verificar el stock del artículo",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            verOk = false;
        }
        if (dataStk.size() > 0) {
            if (dataStk.get(0).getCantUds() > 0) {
                JOptionPane.showMessageDialog(null,
                        "No es posible eliminar el artículo, ya que"
                        + " " + "aún posee stock",
                        null,
                        JOptionPane.ERROR_MESSAGE);
                verOk = false;
            }
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

        // Verificar si el artículo se utiliza en algún registro de venta
        sn = 0;
        ac = (Integer) jtArtList.getValueAt(row, 0);
        sl = new SaleLines();
        try {
            sn = sl.verifyArt(conDb, ac);
        } catch (SQLException e) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null,
                    "No fue posible verificar la utilización del artículo"
                    + " " + "en las líneas de servicios/venta",
                    null,
                    JOptionPane.ERROR_MESSAGE);
            verOk = false;
        }
        if (sn != 0) {
            JOptionPane.showMessageDialog(null,
                    "No es posible eliminar el artículo, ya que"
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
            // Eliminar el artículo del stock
            stk.delStkData(conDb);

            // Eliminar la info.del artículo            
            art = new Article(ac);
            art.delArtData(conDb);

            // Confirmar la actualización en la BBDD
            conDb.commit();

            // Eliminar el artículo del JTable
            dtm = (DefaultTableModel) jtArtList.getModel();
            dtm.removeRow(row);
        } catch (SQLException e) {
            // Falló la actualización en la BBDD
            try {
                conDb.rollback();
            } catch (SQLException e2) {
            }
            JOptionPane.showMessageDialog(null,
                    "No fue posible eliminar la info.del artículo",
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
    }//GEN-LAST:event_jbElimArtActionPerformed

    /* Cargar la info.de todos los artículos en el jTable */
    private void loadArticles() {

        // Definiciones locales
        int i, j;
        DbConnection dbc;
        Connection conDb;
        Article art;
        String[] columns;
        ArrayList<Article> dataArt;
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
            // Cargar la info.de todos los artículos en el jTable
            art = new Article();
            columns = art.getAllColumns();
            dataArt = art.getArtAllData(conDb);
            data = new Object[dataArt.size()][columns.length];
            for (i = 0; i < dataArt.size(); i++) {
                j = 0;
                data[i][j++] = dataArt.get(i).getCodArticulo();
                data[i][j++] = dataArt.get(i).getDescripcion();
                data[i][j++] = dataArt.get(i).getTipoIVA_desc();
                data[i][j++] = dataArt.get(i).getTipoServicio_desc();
                data[i][j++] = dataArt.get(i).getPrecioCompraII();
                data[i][j++] = dataArt.get(i).getPrecioVentaII();
                data[i][j++] = dataArt.get(i).getCantUds();
            }
            jtArtList.setModel(new javax.swing.table.DefaultTableModel(
                    data,
                    columns) {
                        Class[] types = new Class[]{
                            java.lang.Integer.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.Double.class,
                            java.lang.Double.class,
                            java.lang.Integer.class};
                        boolean[] canEdit = new boolean[]{
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
            jtArtList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            resizeColWidthjTable(jtArtList);

            // Situar el cursor en el campo de búsqueda
            jtfArtSearch.requestFocus();
        } catch (SQLException e) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null,
                    "No fue posible cargar la lista de artículos",
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

    /* */
    public int getCodArticulo_sel() {
        return codArticulo_sel;
    }

    /* */
    public String getDescripcion_sel() {
        return descripcion_sel;
    }

    /* */
    public Double getPrecioCompraII_sel() {
        return precioCompraII_sel;
    }

    /* */
    public Double getPrecioVentaII_sel() {
        return precioVentaII_sel;
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
            java.util.logging.Logger.getLogger(ArtList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArtList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArtList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArtList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArtList dialog = new ArtList(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jbElimArt;
    private javax.swing.JButton jbNewArt;
    private javax.swing.JLabel jlArtSearch;
    private javax.swing.JPanel jpArtList;
    private javax.swing.JPanel jpFunctions;
    private javax.swing.JPanel jpaArtSearch;
    private javax.swing.JScrollPane jspArtList;
    private javax.swing.JTable jtArtList;
    private javax.swing.JTextField jtfArtSearch;
    // End of variables declaration//GEN-END:variables
}
