/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gerogius, Vincent, Jerdy
 */
import Network.*;
import Util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class ClientFrame extends javax.swing.JFrame {

    /**
     * Creates new form ClientFrame
     */
    static Thread background;
    
    static Network.Client client;
    
    final JFileChooser filechooser = new JFileChooser();
    
    public ClientFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        send = new javax.swing.JButton();
        upload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgarea = new javax.swing.JTextArea();
        msgsend = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        onarea = new javax.swing.JTextArea();
        chatscope = new javax.swing.JButton();
        recipient = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        send.setText("send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        send.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendKeyPressed(evt);
            }
        });

        upload.setText("upload");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });

        msgarea.setColumns(20);
        msgarea.setRows(5);
        jScrollPane1.setViewportView(msgarea);

        msgsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgsendActionPerformed(evt);
            }
        });

        onarea.setColumns(20);
        onarea.setRows(5);
        onarea.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                onareaMouseMoved(evt);
            }
        });
        onarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onareaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(onarea);

        chatscope.setText("Global");
        chatscope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatscopeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(upload, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgsend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(chatscope)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recipient))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chatscope)
                            .addComponent(recipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(msgsend))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            if(client.getMessagescope().equalsIgnoreCase("GLOBAL")){
                if(!msgsend.getText().isEmpty()){
                    client.getOutput().writeUTF("SEND_MESSAGE");
                    client.getOutput().writeUTF("GLOBAL");
                    client.getOutput().writeUTF(msgsend.getText());
                    client.getOutput().flush();
                    msgsend.setText("");
                }
            }
            else if(client.getMessagescope().equalsIgnoreCase("PRIVATE") && !this.recipient.getText().equalsIgnoreCase("")){
                if(!msgsend.getText().isEmpty()){
                    client.getOutput().writeUTF("SEND_MESSAGE");
                    client.getOutput().writeUTF("PRIVATE");
                    client.getOutput().writeUTF(this.recipient.getText());
                    client.getOutput().writeUTF(msgsend.getText());
                    client.getOutput().flush();
                    msgsend.setText("");
                }
            }
        } catch (IOException ex) {
            Log.error("Can't send message");
        }
    }//GEN-LAST:event_sendActionPerformed

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        // TODO add your handling code here:
        
        
        try{
            
            int return_value = filechooser.showOpenDialog(this);

            if (return_value == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                Log.print("FILE FOUND");

                byte[] file_byte = new byte[(int)file.length()];
                Log.print("SIZE FOUND: " + Integer.toString((int)file.length()));

                FileInputStream fis = new FileInputStream(file);
                fis.read(file_byte);
                Log.print("FILE READ");

                client.getOutput().writeUTF("SEND_FILE");
                Log.print("PACKAGE SENT 1/5");
                
                if(client.getMessagescope().equalsIgnoreCase("GLOBAL")){
                    client.getOutput().writeUTF("GLOBAL");
                    
                }

                else if(client.getMessagescope().equalsIgnoreCase("PRIVATE") && !this.recipient.getText().equalsIgnoreCase("")){
                    client.getOutput().writeUTF("PRIVATE");
                    client.getOutput().writeUTF(this.recipient.getText());
                }
                Log.print("PACKAGE SENT 2/5");
                
                client.getOutput().writeUTF(file.getName());
                Log.print("PACKAGE SENT 3/5");

                client.getOutput().writeUTF(Integer.toString((int)file.length()));
                Log.print("PACKAGE SENT 4/5");

                client.getOS().write(file_byte);
                Log.print("PACKAGE SENT 5/5");

                

            }
            
            
            
        } catch(Exception ex){
            Log.error("File Sending Failed");
        }
    }//GEN-LAST:event_uploadActionPerformed

    private void msgsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgsendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgsendActionPerformed

    private void chatscopeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatscopeActionPerformed
        // TODO add your handling code here:
        if(client.getMessagescope().equalsIgnoreCase("GLOBAL")){
            client.setMessagescope("PRIVATE");
            this.chatscope.setText("Private");
        }
        else if(client.getMessagescope().equalsIgnoreCase("PRIVATE")){
            client.setMessagescope("GLOBAL");
            this.chatscope.setText("Global");
        }
    }//GEN-LAST:event_chatscopeActionPerformed

    private void onareaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onareaMouseMoved
        try {
            // TODO add your handling code here:
            client.getOutput().writeUTF("ONLINE");
        } catch (IOException ex) {
            Log.error(ex.getMessage());
        }
    }//GEN-LAST:event_onareaMouseMoved

    private void sendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendKeyPressed

    private void onareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onareaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_onareaMouseClicked

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
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        client = new Client();
        background = new Thread(client);
        background.start();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chatscope;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextArea msgarea;
    private javax.swing.JTextField msgsend;
    public static javax.swing.JTextArea onarea;
    private javax.swing.JTextField recipient;
    private javax.swing.JButton send;
    private javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables
}
