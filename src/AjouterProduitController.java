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
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private Text codeProduitText;
    @FXML
    private JFXTextField designationField;
    @FXML
    private JFXTextField prixTransportField;
    @FXML
    private JFXTextField prixUnitaireField;
    int code;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lastIDProduit();
    }    
    
        @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) codeProduitText.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
        @FXML
    private void btnReinitialiser() throws IOException{
        lastIDProduit();
        designationField.setText("");
        prixTransportField.setText("");
        prixUnitaireField.setText("");
    }
    
        @FXML
    private void btnAjouterProduit(){
                if(designationField.getText().isEmpty() || prixTransportField.getText().isEmpty() || prixUnitaireField.getText().isEmpty()){
                    Toast.makeText((Stage) designationField.getScene().getWindow(), "Veuilley saisir tout les champs SVP", 1500, 500, 500);
                    return;
                }
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into produit values('"+codeProduitText.getText()+"','"+designationField.getText()+"','"+prixTransportField.getText()+"','"+prixUnitaireField.getText()+"')");
                    //Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Clien Numéro "+id+" a été Ajouté avec succès", 2000, 500, 500);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajouter Produit");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit Numéro "+codeProduitText.getText()+" a été Ajouté avec succès");

                    alert.showAndWait();
                    
                    btnReinitialiser();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                    System.out.println("Error in connection"+e.toString());
        }
    }
    
    private void lastIDProduit(){
                    try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from produit ORDER BY codeProduit DESC LIMIT 1");
            if(rs.next()){
                code = rs.getInt("codeProduit");
                code=code+1;
                String str = String.valueOf(code);
                codeProduitText.setText(str);
            }else
                codeProduitText.setText("1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,""+e.toString());
        }
    }
    
}
