/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import com.mysql.jdbc.Statement;
import connection.koneksi;
import connection.renderingKanan;
import connection.renderingTengah;
import connection.userSession;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import connection.headerRenderer;
import connection.DateRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Elok Anugrah
 */
public class adminHomepage extends javax.swing.JFrame {

    private String username = userSession.getUsername();
    private String fieldKodeTransaksi;
    private String comboNamaParfum;
    private String comboNamaBotol;
    private String fieldTanggal;
    private String fieldKodeParfum;
    private String fieldKodeBotol;
    private String fieldBibit;
    private String fieldCampuran;
    private String fieldJumlah;
    private String fieldTotal;
    public TableCellRenderer kanan = new renderingKanan();
    public TableCellRenderer tengah = new renderingTengah();
    private int baris;
    private String fieldFilter;
    private String comboFilter;
    String[] headerTable = {"Kode Transaksi", "Tanggal", "Nama Parfum", "Nama Botol", "Harga Botol", "Bibit", "Campuran", "Harga/ml", "Jumlah", "Total"};
    /**
     * Creates new form adminHomepage
     */
    public adminHomepage() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jButton7.setBackground(Color.LIGHT_GRAY);
        jLabel1.setText("Masuk sebagai : "+username);
        tablePesanan();
        headerTable();
        comboParfum();
        comboBotol();
        sortTable();
        parfumTerbanyak();
        botolTerbanyak();
        bulanTeramai();
        hitungTotal();
        /*DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.fireTableDataChanged();*/
        jDateChooser1.setDate(null);
        //Mengatur ukuran kolom tabel//
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(39);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(155);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(27);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(31);
        //Mengatur posisi teks pada kolom tabel dengan membuat objek baru renderingKanan() dan renderingTengah()
        jTable1.getColumnModel().getColumn(0).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(4).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(5).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(6).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(7).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(8).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(9).setCellRenderer( kanan );
        //Mengubah format date pada table dengan membuat objek baru DateRenderer()
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());
        //AbstrakDocument untuk melakukan perhitungan langsung ketika pada text field yang .getDocument()
        ((AbstractDocument) (jTextField8.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField6.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField4.getDocument())).setDocumentFilter(df);
}
    
    //Untuk mensorting dari tinggi ke rendah atau sebaliknya ketika kita meng-klik header kolom tabel
    public void sortTable(){
       jTable1.setAutoCreateRowSorter(true);
    }
    
    //Untuk rata tengah header kolom tabel
    public void headerTable(){
        JTable table = new JTable();
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new headerRenderer(jTable1));
    }
    
    //Untuk memfilter baik memasukkan atau menghapus dan melaukan perhitungan secara otomatis
    DocumentFilter df = new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {

                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcAndSetTotal();
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcAndSetTotal();
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcAndSetTotal();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);//get a single character of the string
                    //System.out.println(c);
                    if (!Character.isDigit(c)) {//if its an alphabetic character or white space
                        return false;
                    }
                }
                return true;
            }
            
            void calcAndSetTotal() {
                int multiple = 1;
                int sum = Integer.parseInt(jTextField8.getText());

                if (!jTextField6.getText().isEmpty()) {
                    multiple *= Integer.parseInt(jTextField6.getText());//we must add this
                }
                if (!jTextField4.getText().isEmpty()) {
                    multiple *= Integer.parseInt(jTextField4.getText());//we must add this
                }

                jTextField7.setText(String.valueOf(multiple));
                jTextField9.setText(String.valueOf(multiple+sum));
            }
    };
    
    //Menampilkan nama parfum pada combo box
    private void comboParfum(){
         try {
            Connection con = koneksi.GetConnection();
            java.sql.Statement stt = con.createStatement();
            String sql = "SELECT * FROM tb_parfum";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
               jComboBox1.addItem(res.getString(2));
               
               
            }res.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Menampilkan nama botol pada combo box
    private void comboBotol(){
         try {
            Connection con = koneksi.GetConnection();
            java.sql.Statement stt = con.createStatement();
            String sql = "SELECT * FROM tb_botol";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
               jComboBox2.addItem(res.getString(2));
               
               
            }res.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Menampilkan isi dari tabel transaksi
    public void tablePesanan(){
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, headerTable);
        jTable1.setModel(defaultTableModel);
        baris = jTable1.getRowCount();
        for (int i = 0;i < baris; i++){
            defaultTableModel.removeRow(i);
        }
        String sql = "SELECT t.kode_transaksi, t.tanggal, p.nama_parfum, b.nama_botol, b.harga_botol, t.bibit, t.campuran, p.harga_ml, t.jumlah, t.total  FROM tb_parfum p, tb_botol b, tb_transaksi t WHERE t.kode_parfum = p.kode_parfum AND t.kode_botol = b.kode_botol";
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
                while (res.next()) {
                    String kolom1 = res.getString("kode_transaksi");
                    String kolom2 = res.getString("tanggal");
                    String kolom3 = res.getString("nama_parfum");
                    String kolom4 = res.getString("nama_botol");
                    String kolom5 = res.getString("harga_botol");
                    String kolom6 = res.getString("bibit");
                    String kolom7 = res.getString("campuran");
                    String kolom8 = res.getString("harga_ml");
                    String kolom9 = res.getString("jumlah");
                    String kolom10 = res.getString("total");
                    
                    String kolom[] = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10};
                    defaultTableModel.addRow(kolom);
                }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Constructor yang berfungsi untuk memfilter/mencari pada tabel transaksi
    private void search(String sql){
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, headerTable);
        jTable1.setModel(defaultTableModel);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(39);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(155);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(27);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(31);
        jTable1.getColumnModel().getColumn(0).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(4).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(5).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(6).setCellRenderer( tengah );
        jTable1.getColumnModel().getColumn(7).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(8).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(9).setCellRenderer( kanan );
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());
        baris = jTable1.getRowCount();
        for (int i = 0;i < baris; i++){
            defaultTableModel.removeRow(i);
        }
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
                while (res.next()) {
                    String kolom1 = res.getString("kode_transaksi");
                    String kolom2 = res.getString("tanggal");
                    String kolom3 = res.getString("nama_parfum");
                    String kolom4 = res.getString("nama_botol");
                    String kolom5 = res.getString("harga_botol");
                    String kolom6 = res.getString("bibit");
                    String kolom7 = res.getString("campuran");
                    String kolom8 = res.getString("harga_ml");
                    String kolom9 = res.getString("jumlah");
                    String kolom10 = res.getString("total");
                    
                    String kolom[] = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10};
                    defaultTableModel.addRow(kolom);
                }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Menampilkan nama parfum yang paling banyak dipesan
    private void parfumTerbanyak(){
        String sql = "SELECT nama_parfum, COUNT(tb_transaksi.kode_parfum) AS Total FROM tb_transaksi, tb_parfum WHERE tb_transaksi.kode_parfum = tb_parfum.kode_parfum GROUP BY tb_transaksi.kode_parfum ORDER BY Total DESC LIMIT 1";
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                jLabel17.setText(res.getString("nama_parfum"));
                jLabel19.setText(res.getString("Total"));
            }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Menampilkan nama botol yang paling banyak dipesan
    private void botolTerbanyak(){
        String sql = "SELECT nama_botol, COUNT(tb_transaksi.kode_botol) AS Total FROM tb_transaksi, tb_botol WHERE tb_transaksi.kode_botol = tb_botol.kode_botol GROUP BY tb_transaksi.kode_botol ORDER BY Total DESC LIMIT 1";
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                jLabel25.setText(res.getString("nama_botol"));
                jLabel27.setText(res.getString("Total"));
            }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Menampilkan bulan dengan transaksi tertinggi
    private void bulanTeramai(){
        String sql = "SELECT MONTH( tanggal ) AS mt, COUNT( * ) AS Total FROM tb_transaksi GROUP BY MONTH( tanggal ) ORDER BY Total DESC LIMIT 1";
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                jLabel21.setText(res.getString("mt"));
                jLabel23.setText(res.getString("Total"));
            }
            if(jLabel21.getText().equals("1")){
                jLabel21.setText("January");
            }
            if(jLabel21.getText().equals("2")){
                jLabel21.setText("February");
            }
            if(jLabel21.getText().equals("3")){
                jLabel21.setText("March");
            }
            if(jLabel21.getText().equals("4")){
                jLabel21.setText("April");
            }
            if(jLabel21.getText().equals("5")){
                jLabel21.setText("May");
            }
            if(jLabel21.getText().equals("6")){
                jLabel21.setText("June");
            }
            if(jLabel21.getText().equals("7")){
                jLabel21.setText("July");
            }
            if(jLabel21.getText().equals("8")){
                jLabel21.setText("Augustus");
            }
            if(jLabel21.getText().equals("9")){
                jLabel21.setText("September");
            }
            if(jLabel21.getText().equals("10")){
                jLabel21.setText("October");
            }
            if(jLabel21.getText().equals("11")){
                jLabel21.setText("November");
            }
            if(jLabel21.getText().equals("12")){
                jLabel21.setText("December");
            }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //menghitung jumlah, nilai tertinggi, dan nilai terendah pada total
    private void hitungTotal(){
        String sql = "SELECT COUNT( kode_transaksi ) AS jumlah, MAX( total ) AS mak, MIN( total ) AS min FROM tb_transaksi";
        try {
            java.sql.Statement statement = (java.sql.Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while(res.next()){
                jLabel29.setText(res.getString("jumlah"));
                jLabel31.setText(res.getString("mak"));
                jLabel33.setText(res.getString("min"));
            }
            }catch (Exception e) {
            JOptionPane.showMessageDialog(jScrollPane1, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jToolBar2 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pesanan");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Transaksi", "Tanggal", "Nama Parfum", "Nama Botol", "Harga Botol", "Bibit", "Campuran", "Harga/ml", "Jumlah", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(31);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jToolBar1.setRollover(true);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471427331_note_parfum.png"))); // NOI18N
        jButton7.setText("Daftar Pesanan");
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setRequestFocusEnabled(false);
        jButton7.setVerifyInputWhenFocusTarget(false);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471427331_note.png"))); // NOI18N
        jButton6.setText("Daftar Parfum");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton6.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton6.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471427331_notebotol.png"))); // NOI18N
        jButton8.setText("Daftar Botol");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton8.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton8.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel Edit", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setText("Kode Transaksi :");

        jLabel3.setText("Tanggal :");

        jLabel4.setText("Nama Parfum :");

        jLabel5.setText("Nama Botol :");

        jLabel6.setText("Harga Botol :");

        jLabel7.setText("Bibit :");

        jLabel8.setText("Campuran :");

        jLabel9.setText("Harga Parfum/ml :");

        jLabel10.setText("Jumlah :");

        jLabel11.setText("Total :");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField1.setToolTipText("");
        jTextField1.setFocusable(false);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Silahkan pilih" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField2.setText("Kode");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField3.setText("Kode");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setText("Rp.");

        jLabel13.setText("Rp.");

        jLabel14.setText("Rp.");

        jLabel15.setText("Rp.");

        jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField8)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField9)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jToolBar2.setRollover(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471429043_shoppingcart--add.png"))); // NOI18N
        jButton1.setText("Pesanan Baru");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton1.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton1.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471426062_bag_icons-20.png"))); // NOI18N
        jButton5.setText("Parfum Baru");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton5.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton5.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471433817_perfume.png"))); // NOI18N
        jButton9.setText("Botol Baru");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton9.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton9.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton9);

        jToolBar3.setRollover(true);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471435985_check.png"))); // NOI18N
        jButton10.setText("Simpan");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton10.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton10.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton10);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471435997_delete.png"))); // NOI18N
        jButton11.setText("Hapus");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton11.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton11.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton11);

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471435989_refresh.png"))); // NOI18N
        jButton12.setText("Refresh");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setMaximumSize(new java.awt.Dimension(70, 53));
        jButton12.setMinimumSize(new java.awt.Dimension(70, 53));
        jButton12.setPreferredSize(new java.awt.Dimension(70, 53));
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton12);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Parfum Terlaris :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(0, 132, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 0));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Bulan Teramai :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Total :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(0, 194, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE)))
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(252, 142));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Botol Terlaris :");

        jLabel25.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(157, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(0, 194, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Jumlah Transaksi :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Transaksi Tertinggi:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("TransaksiTerendah:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 90, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30))
                            .addGap(0, 100, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel30)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(34, Short.MAX_VALUE)))
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Log Out");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Keluar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Account");

        jMenuItem4.setText("Ubah Akun Login");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Help");

        jMenuItem3.setText("Tentang");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(821, 821, 821)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        adminHomepage frame = new adminHomepage();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Object[] option = {"Log out","Batal"};
        int dialogResult = JOptionPane.showOptionDialog(rootPane, "Anda yakin ingin keluar?", "Log Out", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,option,option[0]);
        if(dialogResult == JOptionPane.YES_OPTION){
        adminLogin frame = new adminLogin();
        frame.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tambahPesanan frame = new tambahPesanan(this);
        frame.setVisible(true);
        //frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        baris = jTable1.getSelectedRow();
        //Agar secara otomatis spinner mensinkronkan pada campuran tabel transaksi
        String i = (String)jTable1.getValueAt(baris, 6);
        try{
            jSpinner1.setValue(Integer.valueOf(i));
            }catch(Exception ex){
             ex.printStackTrace();
            } 
        //Mengambil date pada tabel transaksi dan mengubah formatnya
        String s=(String)jTable1.getModel().getValueAt(baris, 1);
        try{
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd"); 
            java.util.Date d=f.parse(s);
            jDateChooser1.setDate(d);
            }catch(Exception ex){
             ex.printStackTrace();
            }   
        if (baris != -1){
            jTextField1.setText(jTable1.getValueAt(baris, 0).toString());
            jComboBox1.setSelectedItem(jTable1.getValueAt(baris, 2).toString());
            jComboBox2.setSelectedItem(jTable1.getValueAt(baris, 3).toString());
            jTextField4.setText(jTable1.getValueAt(baris, 5).toString());
            
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        comboNamaParfum = (String)jComboBox1.getSelectedItem();
        try{
        Connection con = koneksi.GetConnection();
            java.sql.Statement stt = con.createStatement();
            String sql = "SELECT kode_parfum, harga_ml FROM tb_parfum WHERE nama_parfum='"+comboNamaParfum+"';";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                jTextField2.setText(res.getString("kode_parfum"));
                jTextField6.setText(res.getString("harga_ml"));
                jTextField4.setText("");
                jTextField7.setText("");
            }res.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        comboNamaBotol = (String)jComboBox2.getSelectedItem();
        try{
        Connection con = koneksi.GetConnection();
            java.sql.Statement stt = con.createStatement();
            String sql = "SELECT kode_botol, harga_botol FROM tb_botol WHERE nama_botol='"+comboNamaBotol+"';";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                jTextField3.setText(res.getString("kode_botol"));
                jTextField8.setText(res.getString("harga_botol"));
            }res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        frameParfum frame = new frameParfum();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        fieldKodeTransaksi = jTextField1.getText();
        //mengambil date pada DateChooser dan mengubah formatnya
        if (jDateChooser1.getDate()!=null){
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        fieldTanggal = Format.format(jDateChooser1.getDate());
        }
        fieldKodeParfum = jTextField2.getText();
        fieldKodeBotol = jTextField3.getText();
        fieldBibit = jTextField4.getText();
        fieldCampuran = jSpinner1.getValue().toString();
        fieldJumlah = jTextField7.getText();
        fieldTotal = jTextField9.getText();
        try{
        if ("".equals(fieldTanggal) || "Kode".equals(fieldKodeParfum) || "Kode".equals(fieldKodeBotol) || "".equals(fieldBibit) || "".equals(fieldJumlah) || "".equals(fieldTotal)) {
            JOptionPane.showMessageDialog(null, "Harap lengkapi data", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else{
            Statement statement = (Statement) koneksi.GetConnection().createStatement();
            statement.executeUpdate("UPDATE tb_transaksi SET tanggal='"+fieldTanggal+"',kode_parfum='"+fieldKodeParfum+"',kode_botol='"+fieldKodeBotol+"',bibit='"+fieldBibit+"',campuran='"+fieldCampuran+"',jumlah='"+fieldJumlah+"',total='"+fieldTotal+"' WHERE kode_transaksi='"+fieldKodeTransaksi+"';");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            adminHomepage frame = new adminHomepage();
            frame.setVisible(true);
            this.dispose();
        }
               
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        fieldKodeTransaksi = jTextField1.getText();
        //mengambil date pada DateChooser dan mengubah formatnya
        if (jDateChooser1.getDate()!=null){
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        fieldTanggal = Format.format(jDateChooser1.getDate());
        }
        fieldKodeParfum = jTextField2.getText();
        fieldKodeBotol = jTextField3.getText();
        fieldBibit = jTextField4.getText();
        fieldCampuran = jSpinner1.getValue().toString();
        fieldJumlah = jTextField7.getText();
        fieldTotal = jTextField9.getText();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Anda yakin data ini dihapus?");
        if ("".equals(fieldTanggal) || "Kode".equals(fieldKodeParfum) || "Kode".equals(fieldKodeBotol) || "".equals(fieldBibit) || "".equals(fieldJumlah) || "".equals(fieldTotal)) {
            JOptionPane.showMessageDialog(null, "Harap pilih data yang akan dihapus!", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else if(dialogResult == JOptionPane.YES_OPTION){

            try {
                Statement statement = (Statement) koneksi.GetConnection().createStatement();
                statement.executeUpdate("DELETE FROM tb_transaksi WHERE kode_transaksi='"+fieldKodeTransaksi+"'");
                adminHomepage frame = new adminHomepage();
                frame.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Data error!");
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        adminHomepage frame = new adminHomepage();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tambahParfum frame = new tambahParfum(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        frameBotol frame = new frameBotol();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tambahBotol frame = new tambahBotol(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        // TODO add your handling code here:
        fieldFilter = jTextField5.getText();
        String sqlSearch = "SELECT kode_transaksi, tanggal, nama_parfum, nama_botol, harga_botol, bibit, campuran, harga_ml, jumlah, total FROM tb_transaksi INNER JOIN tb_parfum USING(kode_parfum) INNER JOIN tb_botol USING(kode_botol) WHERE kode_transaksi LIKE'%"+fieldFilter+"%' OR nama_parfum LIKE'%"+fieldFilter+"%' OR nama_botol LIKE'%"+fieldFilter+"%' OR total LIKE'%"+fieldFilter+"%';";
        search(sqlSearch);
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        aboutFrame frame = new aboutFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        ubahAkun frame = new ubahAkun(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(adminHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
}
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminHomepage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    // End of variables declaration//GEN-END:variables
}
