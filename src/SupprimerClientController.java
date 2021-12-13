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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        numClientField.setText("");
        raisonScocialField.setText("");
        adresseField.setText("");
        matriculeFiscalField.setText("");
        n_articleField.setText("");
        registrDeCommrcField.setText("");
        
        numClientField.setEditable(true);
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
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Selectionner");
            //alert.setHeaderText("SVP, Vérifier");
            alert.setHeaderText(null);
            alert.setContentText("Voullez vous vraiment supprimer ce client !!");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                     String nClient = numClientField.getText();
                    try {
                        Connection con=ConnectionProvider.getCon();
                        Statement st = con.createStatement();
                        st.executeUpdate("delete from client where numeroClient='"+nClient+"'");
                        //JOptionPane.showMessageDialog(null,"Client a été supprimé");

                        btnReinitialiser();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,""+e.toString());
                    }
            }else {
                    alert.close();
                   }
 

                }
        
        
    }
    

