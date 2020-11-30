
package fenetre;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Djamal
 */
public class MainFenetre extends javax.swing.JFrame {


    public MainFenetre() {
        initComponents();
        fetch(); 
    }
    private void fetch(){
        databases.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet1", "root", "");
            String sql = "select * from dbnames";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("nom"));
//                DBName data = new DBName( rs.getString("nom"));
  //              databases.add(data);
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //method to show an info alert
    public void alert(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg);
    }
    //method to show an error alert
    public void alert(String msg, String title) {
        JOptionPane.showMessageDialog(rootPane, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt){
        String dataSelected = (String) jComboBox1.getSelectedItem();
        //alert(dataSelected);
        jLabel4.setText(dataSelected);
        tables.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataSelected+"", "root", "");
            //Retrieving the data
              st = con.createStatement();
            ResultSet rs = st.executeQuery("Show tables");              
          //  alert("Connection reussie\n"+"Voici les tables");
            while (rs.next()) {
               // System.err.println(rs.getString(1));
                DBName table = new DBName(rs.getString(1));   
                tables.add(table);
            }
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for (DBName table : tables) {
                Object[] row = new Object[2];
                row[0] = table.getNom();
                model.addRow(row);
            }
//            System.out.println(column_count);
           // 
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
       // alert("Si vous voulez changer de table, redemarrez le projet");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent  evt) {
        jLabel7.setText("");
        //System.out.println("base"+(String)jComboBox1.getSelectedItem());
        try {           
            System.out.println(jTextArea1.getText());
            //"SELECT * FROM auteurs;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(String)jComboBox1.getSelectedItem()+"", "root", "");
            //Retrieving the data
            st = con.createStatement();            
            ResultSet rs = st.executeQuery(jTextArea1.getText());         
            //Retrieving the ResultSetMetaData object
            ResultSetMetaData rsmd = rs.getMetaData();
              //getting the column number
            int column_count = rsmd.getColumnCount();
            //
            DefaultTableModel model = new DefaultTableModel();
            //System.out.println(rsmd.getColumnName(1));
            for (int i = 1; i <= column_count; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }
            jTable1.setModel(model);  
           
            //admin
            //select * from auteurs;
            //                jTable1.
                //DBName table = new DBName(rs.getString(0),rs.getString(1));   
                //tables.add(table);
            
            /*DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for (DBName table : tables) {
                Object[] row = new Object[1];
                row[0] = table.getNom();
                model.addRow(row);
            }*/
            //alert("Si vous voulez changer de table, redemarrez le projet");
            //con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MainFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mini Projet Sql");

        jLabel2.setText("Selectionnez votre base de données");

        jLabel3.setText("Base de données choisie : ");

        jLabel4.setText("Aucune");

        jLabel5.setText("Ecrivez votre requête dans cette zone de saisie");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jLabel6.setText("Votre requete apparait egalement dans le tableau ci-dessus");
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
              "Nom"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel7.setText("Tables de la base de données");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selectionnez une table"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             jComboBox1ActionPerformed(evt);
            }
        });
        jButton1.setText("Tester");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             jButton1ActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(152, 152, 152)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(jLabel7))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                                         
             
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private Connection con;
    private Statement st;
    public String dataSelected;
    private Object columsName;
    ArrayList<DBName> databases = new ArrayList<>();
    ArrayList<DBName> tables = new ArrayList<>();
    // End of variables declaration                   
}
