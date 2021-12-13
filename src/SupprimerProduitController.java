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
public class SupprimerProduitController implements Initializable {


    @FXML
    private JFXTextField codeProduitField;
    @FXML
    private JFXTextField designationField;
    @FXML
    private JFXTextField prixTransportField;
    @FXML
    private JFXTextField prixUnitaireField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) designationField.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
    @FXML
    private void btnReinitialiser() throws IOException{
        codeProduitField.setText("");
        designationField.setText("");
        prixTransportField.setText("");
        prixUnitaireField.setText("");
        
        codeProduitField.setEditable(true);
    }
    
        @FXML
    private void btnRechercher(){
                 if(codeProduitField.getText().isEmpty()){
                    Toast.makeText((Stage) codeProduitField.getScene().getWindow(), "Veuilley Entrer un Code Produit SVP", 1500, 500, 500);
                    return;
                }
                String codeProduit = codeProduitField.getText();
                try {
                    Connection con= ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs=st.executeQuery("select *from produit where codeProduit='"+codeProduit+"'");
                    if(rs.next()){
                        designationField.setText(rs.getString(2));
                        prixTransportField.setText(rs.getString(3));
                        prixUnitaireField.setText(rs.getString(4));

                        codeProduitField.setEditable(false);
                    }else{
                        Toast.makeText((Stage) codeProduitField.getScene().getWindow(), "Produit n'existe pas", 1500, 500, 500);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
    
    @FXML
    private void btnSupprimer(){
                 if(codeProduitField.getText().isEmpty()){
                    Toast.makeText((Stage) codeProduitField.getScene().getWindow(), "Veuilley Entrer un Code Produit SVP", 1500, 500, 500);
                    return;
                }
                 
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Selectionner");
            //alert.setHeaderText("SVP, Vérifier");
            alert.setHeaderText(null);
            alert.setContentText("Voullez vous vraiment supprimer ce Produit !! ");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 
                String codeProduit = codeProduitField.getText();
                                    try {
                        Connection con=ConnectionProvider.getCon();
                        Statement st = con.createStatement();
                        st.executeUpdate("delete from produit where codeProduit='"+codeProduit+"'");

                        btnReinitialiser();
                    
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,""+e.toString());
                    }
            }else {
                    alert.close();
                   }
        
        
    }
    
}
