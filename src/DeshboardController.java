/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;



public class DeshboardController implements Initializable {
    @FXML
    private Text date;
    @FXML
    private JFXTextField facturefield;
    @FXML
    private JFXTextField nclientfield;

    @FXML
    private JFXTextField raisonsocialfeild;

    @FXML
    private JFXTextField codeproduitfield;

    @FXML
    private JFXTextField designationfield;

    @FXML
    private JFXTextField prixtransportfield;

    @FXML
    private JFXTextField priunitairefield;

    @FXML
    private JFXTextField quantitefield;

    @FXML
    private TableView<tableview> table;

    @FXML
    private TableColumn<tableview, String> codp;

    @FXML
    private TableColumn<tableview, String> des;

    @FXML
    private TableColumn<tableview, String> prixt;

    @FXML
    private TableColumn<tableview, String> prixu;

    @FXML
    private TableColumn<tableview, String> quant;

    @FXML
  
    @Override
     public void initialize(URL url, ResourceBundle rb) {
//         Validation
          RequiredFieldValidator validator = new RequiredFieldValidator();
          validator.setMessage("missing info....");
          designationfield.getValidators().add(validator);
          NumberValidator numvad = new NumberValidator();
          numvad.setMessage("numb only");
//          raisonsocialfeild validation
           raisonsocialfeild.getValidators().add(numvad);
            raisonsocialfeild.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      raisonsocialfeild.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
//          prixtransportfield validation
          prixtransportfield.getValidators().add(numvad);
         prixtransportfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      prixtransportfield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
//         nclientfield validation
          nclientfield.getValidators().add(numvad);
           nclientfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      nclientfield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });      
//           codeproduitfield validation
          codeproduitfield.getValidators().add(numvad);
          codeproduitfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      codeproduitfield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
//          priunitairefield validation
          priunitairefield.getValidators().add(numvad);
          priunitairefield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      priunitairefield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
//          quantitefield validation
          quantitefield.getValidators().add(numvad);
          quantitefield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      quantitefield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
                          
// designationfield validation
          designationfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      designationfield.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
//       setting table vakue
        codp.setCellValueFactory(new PropertyValueFactory<tableview, String>("codecodeproduit"));
        des.setCellValueFactory(new PropertyValueFactory<tableview, String>("Désignation"));
        prixt.setCellValueFactory(new PropertyValueFactory<tableview, String>("PrixTransport"));
        prixu.setCellValueFactory(new PropertyValueFactory<tableview, String>("PrixUnitaire"));
        quant.setCellValueFactory(new PropertyValueFactory<tableview, String>("quantité"));
        
    }
     
    //addtab button
    @FXML
    void addtabs(ActionEvent event) {
     
     if(codeproduitfield.getText().isEmpty()|| designationfield.getText().isEmpty()||
                prixtransportfield.getText().isEmpty()||priunitairefield.getText().isEmpty()||
             quantitefield.getText().isEmpty()){
        Toast.makeText((Stage) codeproduitfield.getScene().getWindow(), "Veuilley saisir tout les champs SVP", 1500, 500, 500);
                   return;           
    }      
     
//     taking value to new tab
        tableview tableview = new tableview(Integer.parseInt(codeproduitfield.getText()),designationfield.getText(),
                Double.parseDouble(prixtransportfield.getText()),Double.parseDouble(priunitairefield.getText()),
                Double.parseDouble(quantitefield.getText()));
        ObservableList<tableview> tableviews = table.getItems();
        tableviews.add(tableview);
        table.setItems(tableviews);
      
    }
//remove from table
    @FXML
    void removetabs(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
        table.getItems().remove(selectedID);
    }
     
    @FXML
    private void bntAjouterC() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("AjouterClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
    @FXML
     private void bntlistc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Pane root = loader.load(getClass().getResource("ListDuClients.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntmodc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ModifierClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntsupc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("SupprimerClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntlistp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ListeDuProduits.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntajoutp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("AjouterProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntmodp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ModifierProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
     @FXML
     private void bntsupp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("SupprimerProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Se Connecter");
                        primaryStage.setScene(scene);
                    
                        primaryStage.show();
    }
//    cherche facture
    @FXML
    private void btnRechercher(){
                 if(facturefield.getText().isEmpty()){
                    Toast.makeText((Stage) facturefield.getScene().getWindow(), "Veuilley Entrer un Code Produit SVP", 1500, 500, 500);
                    return;
                }
                String numeroFacture = facturefield.getText();
                try {
                    Connection con= ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                     
                    ResultSet rs=st.executeQuery("select *from facture where numeroFacture='"+numeroFacture+"'");
                    if(rs.next()){
                        nclientfield.setText(rs.getString(2));
                        raisonsocialfeild.setText(rs.getString(3));
                        codeproduitfield.setText(rs.getString(4));
                        designationfield.setText(rs.getString(5));
                        prixtransportfield.setText(rs.getString(6));
                        priunitairefield.setText(rs.getString(7));
                        quantitefield.setText(rs.getString(8));
                        date.setText(rs.getString(3));
//                        nclientfield.setText(rs.getString(2));
//                        nclientfield.setText(rs.getString(2));
//                        designationField.setText(rs.getString(2));
//                        prixTransportField.setText(rs.getString(3));
//                        prixUnitaireField.setText(rs.getString(4));

                        facturefield.setEditable(false);
                    }else{
                        Toast.makeText((Stage) facturefield.getScene().getWindow(), "facture n'existe pas", 1500, 500, 500);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
//    btnReinitialiser
     private void btnReinitialiser() throws IOException{
        
                    Stage stage = (Stage) facturefield.getScene().getWindow();
                    stage.close();  
                    
               
                    Stage primaryStage =new Stage();
                    FXMLLoader loader =new FXMLLoader();
                         
                    Pane root = loader.load(getClass().getResource("Deshboard.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Supprimer Produit");
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
        
    }
     
}
