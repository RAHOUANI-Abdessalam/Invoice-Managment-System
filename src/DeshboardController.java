/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;



public class DeshboardController implements Initializable {
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
      
      
        tableview tableview = new tableview(Integer.parseInt(codeproduitfield.getText()),designationfield.getText(),
                Double.parseDouble(prixtransportfield.getText()),Double.parseDouble(priunitairefield.getText()),
                Double.parseDouble(quantitefield.getText()));
        ObservableList<tableview> tableviews = table.getItems();
        tableviews.add(tableview);
        table.setItems(tableviews);
      
    }

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
    
    
     
}
