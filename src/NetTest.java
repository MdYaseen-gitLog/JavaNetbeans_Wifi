import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
public class NetTest extends javax.swing.JFrame {

    /**
     * Creates new form ActiveForm
     */
    public NetTest() {
        initComponents();
        NetworkList();
       /* ActionEvent evt = null;
        GetCurrentDateTimeActionPerformed(evt);*/
       
//***********Start
/*
DateFormat dateandtime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
Timer t = new Timer(500, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        CurrentDateTime.setText(dateandtime.format(date));
        WanTest conn=new WanTest();
        ConnectionNet cn=new ConnectionNet();
        String data = null;
        String comingData=cn.getWLANbssidInfo(data);
       // conn.getWLANbssidInfo();
       //wlanResults.append(comingData+"\n");
         try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
      
    }
});
t.start();
               */
       //***********End
       
    }
private void GetCurrentDateTimeActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    DateFormat dateandtime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    CurrentDateTime.setText(dateandtime.format(date));
}

public void getConnect(){
     String cmd = "netsh wlan connect name=Gazi";

        try {

            Process p3;
             p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
             p3.waitFor();
             BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
             String line = reader.readLine();
             while(line!=null){
                 
                 wlanResults.append(line + "\n");
                 System.out.println(line);
                 line = reader.readLine();
             }
        } catch (IOException ex) {

            wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public void getWLANbssidInfo(){
     String cmd = "netsh wlan show network mode=bssid";

        try {

            Process p3;
             p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
             p3.waitFor();
             BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
             String line = reader.readLine();
             while(line!=null){
                 
                 wlanResults.append(line + "\n");
                 System.out.println(line);
                 line = reader.readLine();
             }
        } catch (IOException ex) {

            wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public void toDisconnect(){
     String cmd = "netsh wlan disconnect";

        try {

            Process p3;
             p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
             p3.waitFor();
             BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
             String line = reader.readLine();
             while(line!=null){
                 
                 wlanResults.append(line + "\n");
                 System.out.println(line);
                 line = reader.readLine();
             }
        } catch (IOException ex) {

            wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

}
public void NetworkList(){
     String cmd = "for /f \"tokens=2*delims=: \" %i in ('netsh wlan show networks^|find \"SSID\"')do @echo\\%j";

        try {

            Process p3;
             p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
             p3.waitFor();
             BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
             String line = reader.readLine();
             while(line!=null){
                 
                 wlanResults.append(line + ".");
                 jComboBox1.addItem(line);
                 System.out.println(line);
                 line = reader.readLine();
             }
        } catch (IOException ex) {

            wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

}

private static boolean netIsAvailable() {
    try {
        final URL url = new URL("http://www.google.com");
        final URLConnection conn = url.openConnection();
        conn.connect();
        conn.getInputStream().close();
        return true;
    } catch (MalformedURLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        return false;
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

        CurrentDateTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        btnScan = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CurrentDateTime.setText("jLabel1");

        wlanResults.setColumns(20);
        wlanResults.setRows(5);
        jScrollPane1.setViewportView(wlanResults);

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        btnScan.setText("Scan");
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDisconnect)
                        .addGap(18, 18, 18)
                        .addComponent(CurrentDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnScan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConnect)
                        .addGap(211, 211, 211))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnScan)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDisconnect)
                    .addComponent(CurrentDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
      if (!netIsAvailable()) {
          getConnect();
            //System.err.println("Connected to Internet");
        } 
        
        
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
          if (netIsAvailable()) {
           toDisconnect();
            System.err.println("Disconnected");
        } 
       
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
            jComboBox1.removeAllItems();
        NetworkList();
        if (netIsAvailable()) {
            System.err.println("Connected to Internet");
        } else {
            System.err.println("Not Connected");
        }
    }//GEN-LAST:event_btnScanActionPerformed

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NetTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel CurrentDateTime;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnScan;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JTextArea wlanResults = new javax.swing.JTextArea();
    // End of variables declaration//GEN-END:variables
}
