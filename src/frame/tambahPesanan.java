/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import javax.swing.UIManager;
import com.mysql.jdbc.Statement;
import connection.koneksi;
import frame.adminHomepage;
import frame.adminLogin;
import java.awt.Window;
import java.awt.image.ImageObserver;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import frame.tambahParfum;
import javax.swing.JRootPane;

/**
 *
 * @author Elok Anugrah
 */
public class tambahPesanan extends javax.swing.JFrame {

    private String fieldTanggal;
    private String fieldKodeParfum;
    private String comboNamaParfum;
    private String fieldKodeBotol;
    private String comboNamaBotol;
    private String fieldBibit;
    private String fieldCampuran;
    private String fieldJumlah;
    private String fieldTotal;
    private adminHomepage aframe;
    /**
     * Creates new form tambahPesanan
     */
    public tambahPesanan() {
        initComponents();
        setLocationRelativeTo(null);
        comboParfum();
        comboBotol();
        jDateChooser1.setDate(null);
        //AbastractDocument untuk melakukan perhitungan langsung pada text field tertentu
        ((AbstractDocument) (jTextField5.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField1.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField2.getDocument())).setDocumentFilter(df);
    }
    
    //Constructor yang menerima dari adminHomepage
    public tambahPesanan(adminHomepage newAframe) {
        aframe = newAframe;
        initComponents();
        setLocationRelativeTo(null);
        comboParfum();
        comboBotol();
        jDateChooser1.setDate(null);
        ((AbstractDocument) (jTextField5.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField1.getDocument())).setDocumentFilter(df);
        ((AbstractDocument) (jTextField2.getDocument())).setDocumentFilter(df);
    }
    
    //Memfilter document baik memasukkan atau mengghapus dan melakukan perhitungan langsung
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
                int sum = Integer.parseInt(jTextField5.getText());

                if (!jTextField1.getText().isEmpty()) {
                    multiple *= Integer.parseInt(jTextField1.getText());//we must add this
                }
                if (!jTextField2.getText().isEmpty()) {
                    multiple *= Integer.parseInt(jTextField2.getText());//we must add this
                }

                jTextField4.setText(String.valueOf(multiple));
                jTextField6.setText(String.valueOf(multiple+sum));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Pesanan");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Total :");

        jLabel13.setText("Rp.");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Simpan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField7.setText("Kode");

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jTextField8.setText("Kode");

        jLabel5.setText("Rp.");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Bibit :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Campuran :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Harga Parfum/ml :");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel9.setText("Rp.");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tanggal :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama Parfum :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama Botol :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Jumlah :");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Silahkan pilih" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MMM-yyyy");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Harga Botol :");

        jLabel11.setText("Rp.");

        jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471426062_bag_icons-20kecil.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1471433817_perfumekecil.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(217, 217, 217)
                                        .addComponent(jLabel7))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addGap(0, 107, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        comboNamaParfum = (String)jComboBox1.getSelectedItem();
        if(comboNamaParfum.equals("Silahkan pilih")){
            jTextField7.setText("Kode");
        }
        try{
        Connection con = koneksi.GetConnection();
            java.sql.Statement stt = con.createStatement();
            String sql = "SELECT kode_parfum, harga_ml FROM tb_parfum WHERE nama_parfum='"+comboNamaParfum+"';";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                jTextField7.setText(res.getString("kode_parfum"));
                jTextField1.setText(res.getString("harga_ml"));
                jSpinner1.setValue(0);
                jTextField4.setText("");
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
                jTextField8.setText(res.getString("kode_botol"));
                jTextField5.setText(res.getString("harga_botol"));
            }res.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (jDateChooser1.getDate()!=null){
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        fieldTanggal = Format.format(jDateChooser1.getDate());
        }
        fieldKodeParfum = jTextField7.getText();
        fieldKodeBotol = jTextField8.getText();
        fieldBibit = jTextField2.getText();
        fieldCampuran = (jSpinner1.getValue()).toString();
        fieldJumlah = jTextField4.getText();
        fieldTotal = jTextField6.getText();
        try{
        if ("".equals(fieldTanggal) || "Kode".equals(fieldKodeParfum) || "Kode".equals(fieldKodeBotol) || "".equals(fieldBibit) || "".equals(fieldJumlah) || "".equals(fieldTotal)) {
            JOptionPane.showMessageDialog(null, "Harap Lengkapi Data", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else{
            Statement statement = (Statement) koneksi.GetConnection().createStatement();
            statement.executeUpdate("INSERT INTO tb_transaksi VALUES('null','"+fieldTanggal+"','"+fieldKodeParfum+"','"+fieldKodeBotol+"','"+fieldBibit+"','"+fieldCampuran+"','"+fieldJumlah+"','"+fieldTotal+"');");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah!");
            //Jika adminHomepage tidak kosong maka:
            if (aframe != null) { 
            aframe.setVisible(false); 
            adminHomepage frame = new adminHomepage();
            frame.setVisible(true);
            this.dispose();
            }
            //Jika melakukan penambahan parfum atau botol melalui frame tambahPesanan maka semua window yang terbuka akan di-close dan membuka adminHomepage baru
            if (aframe == null) { 
            java.awt.Window win[] = java.awt.Window.getWindows(); 
            for(int i=0;i<win.length;i++){ 
            win[i].dispose();}
            adminHomepage frame = new adminHomepage();
            frame.setVisible(true);
            }
        }
               
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!","Peringatan",JOptionPane.ERROR_MESSAGE);
          }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jDateChooser1.setDate(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField2.setText("");
        jSpinner1.setValue(0);
        jTextField1.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tambahParfum frame = new tambahParfum(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tambahBotol frame = new tambahBotol(this);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(tambahPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambahPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambahPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambahPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambahPesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

    private adminHomepage adminHomepage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
