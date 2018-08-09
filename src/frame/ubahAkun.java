/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import connection.koneksi;
import connection.userSession;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Elok Anugrah
 */
public class ubahAkun extends javax.swing.JFrame {

    private String username = userSession.getUsername();
    private String password = userSession.getPassword();
    private String usernameLama;
    private String fieldUsername;
    private String passwordLama;
    private String passwordBaru;
    private String ulangiPassword;
    String password1 = "";
    String password2 = "";
    private adminHomepage zframe;
    private frameParfum pframe;
    private frameBotol bframe;
    /**
     * Creates new form ubahAkun
     */
    public ubahAkun() {
        initComponents();
        setLocationRelativeTo(null);
        jTextField2.setText(username);
    }
    
    //Constructor yang menerima adminHomepage
    public ubahAkun(adminHomepage newZframe) {
        zframe = newZframe;
        initComponents();
        setLocationRelativeTo(null);
        jTextField2.setText(username);
    }
    
    //Constructor yang menerima frameParfum
    public ubahAkun(frameParfum newPframe) {
        pframe = newPframe;
        initComponents();
        setLocationRelativeTo(null);
        jTextField2.setText(username);
    }
    
    //Constructor yang menerima frameBotol
    public ubahAkun(frameBotol newBframe) {
        bframe = newBframe;
        initComponents();
        setLocationRelativeTo(null);
        jTextField2.setText(username);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ubah Akun");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Username :");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubah Password"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Password lama :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Password baru :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Ulangi Password :");

        jProgressBar1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(51, 51, 51));
        jProgressBar1.setMaximum(7);
        jProgressBar1.setToolTipText("");
        jProgressBar1.setName(""); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Keterangan :");

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jPasswordField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField1CaretUpdate(evt);
            }
        });

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jPasswordField2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jPasswordField2CaretUpdate(evt);
            }
        });

        jPasswordField3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jPasswordField2)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Username Baru :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField1CaretUpdate
        // TODO add your handling code here:
        password1 = new String(jPasswordField1.getPassword());
        jProgressBar1.setValue(password1.length());
        //Melaukan pengecekan password baru dan mengulanginya
        if(!password1.isEmpty()){
            if(password1.equals(password2)){
                jProgressBar1.setString("Cocok");
                jProgressBar1.setStringPainted(true);
                jProgressBar1.setForeground(Color.GREEN);
            }
            else{
                jProgressBar1.setString("Tidak cocok");
                jProgressBar1.setForeground(Color.red);
            }
        }
    }//GEN-LAST:event_jPasswordField1CaretUpdate

    private void jPasswordField2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jPasswordField2CaretUpdate
        // TODO add your handling code here:
        password2 = new String(jPasswordField2.getPassword());
        jProgressBar1.setValue(password2.length());
        //Melakukan pengecekan password baru dan mengulanginya
        if(!password2.isEmpty()){
            if(password2.equals(password1)){
                jProgressBar1.setString("Cocok");
                jProgressBar1.setStringPainted(true);
                jProgressBar1.setForeground(Color.GREEN);
            }
            else{
                jProgressBar1.setString("Tidak cocok");
                jProgressBar1.setForeground(Color.red);
            }
        }
    }//GEN-LAST:event_jPasswordField2CaretUpdate

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        usernameLama = jTextField2.getText();
        fieldUsername = jTextField3.getText();
        passwordLama = jPasswordField3.getText();
        passwordBaru = jPasswordField1.getText();
        ulangiPassword = jPasswordField2.getText();
        
        if("".equals(fieldUsername) || "".equals(passwordLama) || "".equals(passwordBaru) || "".equals(ulangiPassword)){
            JOptionPane.showMessageDialog(null, "Harap lengkapi seluruh field!", "Error", JOptionPane.WARNING_MESSAGE);
        }
//        else if(passwordBaru.toString() != ulangiPassword.toString()){
//            JOptionPane.showMessageDialog(null, "Harap ulangi password dengan benar!", "Error", JOptionPane.WARNING_MESSAGE);
//        }
        else{
            try{
            java.sql.Statement statement = (Statement) koneksi.GetConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM admin WHERE username = '"+ usernameLama + "' and password = '"+ passwordLama +"'");
            while(res.next()){
                passwordLama = res.getString("username");
                passwordLama = res.getString("password");
                }
            res.last();
            if(res.getRow()==1){
                if(passwordBaru.equals(ulangiPassword)){
                statement.executeUpdate("UPDATE admin SET username='"+fieldUsername+"', password='"+passwordBaru+"';");
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                //Untuk semua window yang terbuka akan di-close dan membuka adminLogin
                if (zframe != null) {
                    zframe.setVisible(false);
                    java.awt.Window win[] = java.awt.Window.getWindows(); 
                    for(int i=0;i<win.length;i++){ 
                    win[i].dispose();}
                    adminLogin frame = new adminLogin();
                    frame.setVisible(true);
                }
                if (pframe != null) {
                    pframe.setVisible(false);
                    java.awt.Window win[] = java.awt.Window.getWindows(); 
                    for(int i=0;i<win.length;i++){ 
                    win[i].dispose();}
                    adminLogin frame = new adminLogin();
                    frame.setVisible(true);
                }
                if (bframe != null) {
                    bframe.setVisible(false);
                    java.awt.Window win[] = java.awt.Window.getWindows(); 
                    for(int i=0;i<win.length;i++){ 
                    win[i].dispose();}
                    adminLogin frame = new adminLogin();
                    frame.setVisible(true);
                }
                }else{
                    JOptionPane.showMessageDialog(null, "Ulangi password salah!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Password lama salah!", "Error", JOptionPane.WARNING_MESSAGE);
            }  
        }
        catch(SQLException e){
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "koneksi gagal");   
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ubahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ubahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ubahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ubahAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ubahAkun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
