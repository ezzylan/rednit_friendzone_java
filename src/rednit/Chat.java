package rednit;

import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.swing.BorderFactory;


public class Chat extends javax.swing.JFrame {
    AESEncryptDecrypt aes = new AESEncryptDecrypt();
    String secretKey = "friendZoned!!!";
    public static ArrayList<String> sortChat = new ArrayList<>();
    Login a = new Login();
    String username = a.username1;
    NearByUser b = new NearByUser();
    String receiver = b.receiver;
    Chatting2 obj = new Chatting2();

    /**
     * Creates new form Chat
     */
    public Chat() {
        initComponents();
        
        //EVERY JFRAME SIZE
        setResizable(false);
        setPreferredSize(new Dimension(661, 490));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //INSERT ABOVE CODE 
        
        //Display chat 
       try{
            ArrayList<String> list = new ArrayList();
            ArrayList<String> text = new ArrayList();

            Connection con = getConnection();
            Statement st = con.createStatement();
            
            ResultSet result = st.executeQuery("SELECT * FROM message");
            while(result.next()){
                String sender = result.getString("id_user");
                String receive = result.getString("id_receiver");
              if((sender.equals(username) || sender.equals(receiver)) && (receive.equals(username) || receive.equals(receiver))){
                     list.add(sender);
                     
                     String msg = result.getString("message");
                     //rephrase here
                     text.add(aes.decrypt(msg, secretKey));
               }

            }
            displaytext.setText("");
            for(int i=0; i<list.size();i++){
                if(list.get(i).equals(username)){
                    if(i==0){
                        displaytext.append(list.get(i)+": "+ text.get(i)); 
                     }else{
                        displaytext.append("\n"+list.get(i)+": "+ text.get(i));}
                }else{
                    if(i==0){
                        displaytext.append(list.get(i)+": "+ rephrase(text.get(i))); 
                    }else{
                        displaytext.append("\n"+list.get(i)+": "+ rephrase(text.get(i)));}
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
        displaytext.setEditable(false);
        updateButton.setFocusable(false);
        updateButton.setBorderPainted(false);
        updateButton.setBorder(BorderFactory.createEmptyBorder());
        updateButton.setContentAreaFilled(false);
        sendButton.setFocusable(false);
        sendButton.setBorderPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder());
        sendButton.setContentAreaFilled(false);
    }
    
public Connection getConnection() {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/dating?serverTimezone=UTC", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(NearByUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }


public static void bubbleSort(ArrayList<String> a,ArrayList<Timestamp> time){
      int l = time.size();
     for(int i=0;i<l-1;i++){
         for(int j=0;j<l-i-1;j++){
             if(time.get(j).compareTo(time.get(j+1))>0){ //compare time dalam arraylist
                 Timestamp tempo = time.get(j);               
                 Timestamp c=time.get(j+1);
                 
                 time.set(j,c);
                 time.set(j+1,tempo);
                 
                 //sort String
                 String temp = a.get(j);
                 String temp2 = a.get(j+1);
                 a.set(j, temp2);
                 a.set(j+1, temp);               
             }
         }
     }     
}
public static ArrayList copyList(ArrayList time){
        ArrayList<Timestamp> cloneList = (ArrayList<Timestamp>) time.clone();
    return cloneList;
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
        sendText = new javax.swing.JTextField();
        sendButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        displaytext = new javax.swing.JTextArea();
        updateButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(661, 490));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 46, 60));

        sendText.setBackground(new java.awt.Color(0, 0, 51));
        sendText.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        sendText.setForeground(new java.awt.Color(255, 255, 255));

        sendButton.setBackground(new java.awt.Color(196, 178, 188));
        sendButton.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        sendButton.setForeground(new java.awt.Color(51, 46, 60));
        sendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sent.png"))); // NOI18N
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 51));

        displaytext.setBackground(new java.awt.Color(0, 0, 51));
        displaytext.setColumns(20);
        displaytext.setFont(new java.awt.Font("Raleway", 0, 12)); // NOI18N
        displaytext.setForeground(new java.awt.Color(255, 255, 255));
        displaytext.setRows(5);
        jScrollPane1.setViewportView(displaytext);

        updateButton.setBackground(new java.awt.Color(196, 178, 188));
        updateButton.setFont(new java.awt.Font("Raleway", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(51, 46, 60));
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/approve-and-update.png"))); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 661, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // send message to db
        try{
            Connection connection = getConnection();
            String query = "INSERT INTO `message`(`id_user`, `id_receiver`, `message`) VALUES (?,?,?)";
            PreparedStatement pep = connection.prepareStatement(query);
            
            pep.setString(1, username);
            pep.setString(2, receiver);
            pep.setString(3, aes.encrypt(sendText.getText(), secretKey)); //send messages to database
            
            pep.execute();
            pep.close();
            sendText.setText("");
            updateButtonActionPerformed(evt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // getMessage
       try{
            ArrayList<String> list = new ArrayList();
            ArrayList<String> text = new ArrayList();

            Connection con = getConnection();
            Statement st = con.createStatement();
            
            ResultSet result = st.executeQuery("SELECT * FROM message");
            while(result.next()){
                String sender = result.getString("id_user");
                String receive = result.getString("id_receiver");
              if((sender.equals(username) || sender.equals(receiver)) && (receive.equals(username) || receive.equals(receiver))){
                     list.add(sender);
                     
                     String msg = result.getString("message");
                     //rephrase here
                     text.add(aes.decrypt(msg, secretKey));
               }

            }
            displaytext.setText("");
            for(int i=0; i<list.size();i++){
                if(list.get(i).equals(username)){
                    if(i==0){
                        displaytext.append(list.get(i)+": "+ text.get(i)); 
                     }else{
                        displaytext.append("\n"+list.get(i)+": "+ text.get(i));}
                }else{
                    if(i==0){
                        displaytext.append(list.get(i)+": "+ rephrase(text.get(i))); 
                    }else{
                        displaytext.append("\n"+list.get(i)+": "+ rephrase(text.get(i)));}
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_updateButtonActionPerformed

public String rephrase(String s){
     String[] array = s.split(" ");
     String output = "";
     for(String s1 : array){
         if(s1.equalsIgnoreCase("love") || s1.equalsIgnoreCase("like")){
             output += "hate "; 
         }else if(s1.equalsIgnoreCase("handsome") || s1.equalsIgnoreCase("pretty") || s1.equalsIgnoreCase("beautiful")){
             output += "ugly ";
         }else if(s1.equalsIgnoreCase("nice")){
             output += "bad ";   
         }else if(s1.equalsIgnoreCase("intelligent") || s1.equalsIgnoreCase("clever")){
             output += "stupid ";   
         }else if(s1.equalsIgnoreCase("loyal") || s1.equalsIgnoreCase("honest")){
             output += "cheater ";   
         }else if(s1.equalsIgnoreCase("courage") || s1.equalsIgnoreCase("brave")){
             output += "coward ";   
         }else{
             output += s1 + " ";
         }
     }	
     return output;	
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
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displaytext;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton sendButton;
    private javax.swing.JTextField sendText;
    private javax.swing.JToggleButton updateButton;
    // End of variables declaration//GEN-END:variables
}
