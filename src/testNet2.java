
import com.sun.awt.*;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
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
import javax.swing.Timer;
import wifiupdater.Updates;

/**
 *
 * @author pc
 */
public class testNet2 extends javax.swing.JFrame {

    static Timer timer;
    static Setting st = new Setting();
    static WanTest openProperties = new WanTest();
    static testNet2 openDashboard = new testNet2();
    static Updates openUpdater = new Updates();
    static boolean frameVisible;
    private static int cnt;
    static PopupMenu popup = new PopupMenu();
    static MenuItem Showproperties = new MenuItem("Show Wifi Properties");
    static MenuItem Hideproperties = new MenuItem("Hide Wifi Properties");
    static MenuItem HideUtility = new MenuItem("Hide Utility");
    static MenuItem ShowUtility = new MenuItem("Show Utility");
    static MenuItem exitItem = new MenuItem("Exit");
    static MenuItem setting = new MenuItem("Open Setting");
    static MenuItem updateWifi = new MenuItem("Updater...");
    static TrayIcon trayIcon;
    static int clkCount = 0;

    public testNet2() {
        initComponents();
        // Scan();
        GuiLook();
        this.setSize(170, 83);
        frameVisible = true;

        //Scan();
        btnScan.setToolTipText("Scan for Networks");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        AWTUtilities.setWindowShape(this, new RoundRectangle2D.Float(0, 0, 170, 83, 10, 10));
//height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
//available size of the screen
        setLocation(screenSize.width - getWidth(), screenSize.height - taskBarSize - getHeight());
        NetworkList();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
        //Transparent Form
        AWTUtilities.setWindowOpacity(this, 0.9f);

    }

    static void Scan() {
        try {
            if (netIsAvailable()) {
                trayIcon.setToolTip("Connected to internet.");
            } else {
                trayIcon.setToolTip("Searching Networks.....");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void getConnect() {
        String networkList = listNetwork.getSelectedItem().toString();
        String cmd = "netsh wlan connect name=" + networkList + "";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);

            try {
                p3.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(testNet2.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();
            while (line != null) {

                // wlanResults.append(line + "\n");
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            // wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.\n" + ex.getMessage());

        }

    }

    public void toDisconnect() {
        String cmd = "netsh wlan disconnect";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
            p3.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            // wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.\n" + ex.getMessage());

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

    public static void NetworkList() {
        String cmd = "for /f \"tokens=2*delims=: \" %i in ('netsh wlan show networks^|find \"SSID\"')do @echo\\%j";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
            p3.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();
            while (line != null) {

                // wlanResults.append(line + ".");
                listNetwork.addItem(line);
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            // wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void SignalStatus() {
        String cmd = "for /f \"tokens=2*delims=: \" %i in ('netsh wlan show networks^|find \"Signal\"')do @echo\\%j";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
            p3.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();
            while (line != null) {

                // wlanResults.append(line + ".");
                // listNetwork.addItem(line);
                line = reader.readLine();
                System.out.println("SignalStatus" + line);
            }
        } catch (IOException ex) {

            // wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void GuiLook() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testNet2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void getWLANbssidInfo() {
        openProperties.wlanResults.setText("");
        String cmd = "netsh wlan show network mode=bssid";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
            p3.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                openProperties.wlanResults.append(line + "\n");
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            openProperties.wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void getPassword() {

        String cmd = "netsh wlan show profile name=" + listNetwork + " key=clear | findstr Key";

        try {

            Process p3;
            p3 = Runtime.getRuntime().exec("cmd /c " + cmd);
            p3.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                //openProperties.wlanResults.append(line + "\n");
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            // openProperties.wlanResults.append("Comand error\n" + ex);
            System.out.println("There was an IO exception.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WanTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void GetCurrentDateTimeActionPerformed(java.awt.event.ActionEvent evt) {
        DateFormat dateandtime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //jLabel1.setText(dateandtime.format(date));
    }

    static void refreshNetwork() {
        listNetwork.removeAllItems();
        NetworkList();
        if (netIsAvailable()) {
            System.err.println("Connected to Internet");
        } else {
            System.err.println("Not Connected");
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
        listNetwork = new javax.swing.JComboBox<>();
        btnScan = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WiFi");
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        listNetwork.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnScan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Refresh.jpg"))); // NOI18N
        btnScan.setToolTipText("Scan for Networks");
        btnScan.setMaximumSize(new java.awt.Dimension(26, 26));
        btnScan.setMinimumSize(new java.awt.Dimension(26, 26));
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
            }
        });

        btnDisconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Disconnect.jpg"))); // NOI18N
        btnDisconnect.setToolTipText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect.jpg"))); // NOI18N
        btnConnect.setToolTipText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(listNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(listNetwork, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDisconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
        refreshNetwork();

    }//GEN-LAST:event_btnScanActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        getConnect();

    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        if (netIsAvailable()) {
            toDisconnect();
            trayIcon.setToolTip("Not Connected to internet.");
            System.err.println("Disconnected");
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        testNet2 frame = new testNet2();
        frame.setVisible(true);
        //BufferedImage im = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
//        BufferedImage im = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
        BufferedImage im = new BufferedImage(50, 50, BufferedImage.TYPE_USHORT_555_RGB);
        trayIcon = new TrayIcon(im, "Searching networks...");

        try {

            //Add components to pop-up menu
            popup.add(Showproperties);
            popup.add(Hideproperties);
            popup.addSeparator();
            popup.add(ShowUtility);
            popup.add(HideUtility);
            popup.add(HideUtility);
            popup.addSeparator();
            //popup.add(setting);

            /// popup.add();
            popup.addSeparator();
            popup.add(exitItem);
            ShowUtility.setEnabled(false);
            Hideproperties.setEnabled(false);

        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }
        //trayIcon.setPopupMenu(popup);
        //********************123
        trayIcon.addActionListener((ActionEvent e) -> {
            //System.exit(0);

            System.out.println("Clicked on icon.");
        });
        //***********Show Wifi Properties menu click
        // addActionListener
        Showproperties.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on Show Properties.");
            openProperties.setLocationOfProperties();
            openProperties.setVisible(true);
            openProperties.wlanResults.setText("");
            getWLANbssidInfo();
            System.out.print(frameVisible);
            Showproperties.setEnabled(false);
            Hideproperties.setEnabled(true);

        });
        //***********End of properties menu Click
        //***********HideWifi Properties menu click
        // addActionListener
        Hideproperties.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on Hide Properties.");
            openProperties.setVisible(false);
            System.out.print(frameVisible);
            Showproperties.setEnabled(true);
            Hideproperties.setEnabled(false);
            // openProperties.wlanResults.setText("");
            // getWLANbssidInfo();
        });
        //***********End of properties menu Click
        //***********Hide menu click
        // addActionListener
        HideUtility.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on Hide.");
            frame.setVisible(false);
            frameVisible = false;
            openProperties.setLocationOfProperties();
            ShowUtility.setEnabled(true);
            HideUtility.setEnabled(false);
        });
        //***********End of hide menu Click
        //***********Show menu click
        // addActionListener
        ShowUtility.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on Show.");
            frame.setVisible(true);
            refreshNetwork();
            frameVisible = true;
            openProperties.setLocationOfProperties();
            ShowUtility.setEnabled(false);
            HideUtility.setEnabled(true);
        });
        //***********End of Show menu Click
        //***********Hide menu click
        // addActionListener
        setting.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on setting.");
            clkCount++;
            if (clkCount == 1) {
                st.setVisible(true);
                setting.setLabel("Close Setting");
            } else if (clkCount == 2) {
                st.setVisible(false);
                setting.setLabel("Open Setting");
                clkCount = 0;
            }
            st.setVisible(true);
            // frameVisible = false;
            openProperties.setLocationOfProperties();
            ShowUtility.setEnabled(true);
            HideUtility.setEnabled(false);
        });
        //***********End of hide menu Click
        //***********Exit menu click
        // addActionListener
        exitItem.addActionListener((ActionEvent e) -> {
            System.out.println("Clicked on Exit.");
            timer.stop();
            SystemTray.getSystemTray().remove(trayIcon);
            System.exit(0);
        });
        //***********End of exit menu Click

        trayIcon.setImageAutoSize(true);

        if (SystemTray.isSupported()) {
            SystemTray st = SystemTray.getSystemTray();
            try {
                st.add(trayIcon);
                trayIcon.setPopupMenu(popup);
            } catch (AWTException e1) {
            }
        }

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
 /*  try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testNet2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // new testNet2().setVisible(true);
            //**************

            //**********************************
        });

        //Refresh Internet Connectivity  Check
        ActionListener actListner = new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent event) {

                cnt += 1;
                System.out.println("Counter = " + cnt);
                refreshNetwork();
                getWLANbssidInfo();
                //openProperties.fill();
                //***************
                if (netIsAvailable()) {
                    trayIcon.setToolTip("Connected to internet.");
                } else {
                    trayIcon.setToolTip("Not Connected to internet.");
                }
                //*****************Restart timer for low memory uses.
                if (cnt == 3) {
                    cnt = 0;
                    timer.restart();

                }
            }

        };

        timer = new Timer(7000, actListner);//Every 7 second refresh networks

        timer.start();
        //**********************************
        Scan();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnScan;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JComboBox<String> listNetwork;
    // End of variables declaration//GEN-END:variables

}
