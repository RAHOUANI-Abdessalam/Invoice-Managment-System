/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class SupprimerClientController implements Initializable {

    @FXML
    private JFXTextField numClientField;
    @FXML
    private JFXTextField raisonScocialField;
    @FXML
    private JFXTextField adresseField;
    @FXML
    private JFXTextField matriculeFiscalField;
    @FXML
    private JFXTextField n_articleField;
    @FXML
    private JFXTextField registrDeCommrcField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
        @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) raisonScocialField.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
        @FXML
    private void btnReinitialiser() throws IOException{
        
                    Stage stage = (Stage) raisonScocialField.getScene().getWindow();
                    stage.close();  
                    
                    //Creat a new Satge to Show the New frame "the Deshboard"
                    Stage primaryStage =new Stage();
                    FXMLLoader loader =new FXMLLoader();
                    //Parent root = loader.load(getClass().getResource("Deshboard.fxml"));        
                    Pane root = loader.load(getClass().getResource("SupprimerClient.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Supprimer Client");
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
        
    }
    
        @FXML
    private void btnRechercher(){
                 if(numClientField.getText().isEmpty()){
                    Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Veuilley Entrer un N° Client SVP", 1500, 500, 500);
                    return;
                }
        String clientId = numClientField.getText();
        try {
            Connection con= ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select *from client where numeroClient='"+clientId+"'");
            if(rs.next()){
                raisonScocialField.setText(rs.getString(2));
                adresseField.setText(rs.getString(3));
                matriculeFiscalField.setText(rs.getString(4));
                n_articleField.setText(rs.getString(5));
                registrDeCommrcField.setText(rs.getString(6));
                
                numClientField.setEditable(false);
            }else{
                Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Client n'existe pas", 1500, 500, 500);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,""+e.toString());
        }
    }
    
            
    @FXML
    private void btnSupprimer(){
                 if(numClientField.getText().isEmpty()){
                    Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Veuilley Entrer un N° Client SVP", 1500, 500, 500);
                    return;
                }
 
                String nClient = numClientField.getText();
                int a = JOptionPane.showConfirmDialog(null, "Voullez vous vraiment supprimer ce client !! ","Selectionner",JOptionPane.YES_NO_OPTION);
                if(a==0){
                    try {
                        Connection con=ConnectionProvider.getCon();
                        Statement st = con.createStatement();
                        st.executeUpdate("delete from client where numeroClient='"+nClient+"'");
                        JOptionPane.showMessageDialog(null,"Client a été supprimé");

                        Stage stage = (Stage) raisonScocialField.getScene().getWindow();
                        stage.close();  

                        //Creat a new Satge to Show the New frame "the Deshboard"
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        //Parent root = loader.load(getClass().getResource("Deshboard.fxml"));        
                        Pane root = loader.load(getClass().getResource("SupprimerClient.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Supprimer Client");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,""+e.toString());
                    }
                }
        
        
    }
    
}
