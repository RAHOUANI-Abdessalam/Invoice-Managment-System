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
import javafx.scene.control.Alert.AlertType;
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
public class AjouterClientController implements Initializable {

    @FXML
    private Text numClientText;
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
    int id;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lastIDclient();
    }    
    
    @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) raisonScocialField.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
    @FXML
    private void btnAjouterClient(){
                if(raisonScocialField.getText().isEmpty() || adresseField.getText().isEmpty() || matriculeFiscalField.getText().isEmpty() || n_articleField.getText().isEmpty() || registrDeCommrcField.getText().isEmpty() ){
                    Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Veuilley saisir tout les champs SVP", 1500, 500, 500);
                    return;
                }
                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into client values('0','"+raisonScocialField.getText()+"','"+adresseField.getText()+"','"+matriculeFiscalField.getText()+"','"+n_articleField.getText()+"','"+registrDeCommrcField.getText()+"')");
                    //Toast.makeText((Stage) raisonScocialField.getScene().getWindow(), "Clien Numéro "+id+" a été Ajouté avec succès", 2000, 500, 500);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Ajouter Client");
                    alert.setHeaderText(null);
                    alert.setContentText("Clien Numéro "+id+" a été Ajouté avec succès");

                    alert.showAndWait();
                    btnReinitialiser();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                    System.out.println("Error in connection"+e.toString());
        }
    }
    
    @FXML
    private void btnReinitialiser() throws IOException{
        lastIDclient();
        raisonScocialField.setText("");
        adresseField.setText("");
        matriculeFiscalField.setText("");
        n_articleField.setText("");
        registrDeCommrcField.setText("");
    }
    
    private void lastIDclient(){
                        try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select *from client ORDER BY numeroClient DESC LIMIT 1");
                    if(rs.next()){
                        id = rs.getInt("numeroClient");
                        id=id+1;
                        String str = String.valueOf(id);
                        numClientText.setText(str);
                    }else
                        numClientText.setText("1");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
}
