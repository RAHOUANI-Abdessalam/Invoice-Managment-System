/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListDuClientsController implements Initializable {
    public static int idclient=0;

    @FXML
    private ImageView close;
    @FXML
    private HBox listeDeClientsTxt;
    @FXML
    private TableView<tableclient> tableclient;

    @FXML
    private TableColumn<tableclient, Integer> nClient;

    @FXML
    private TableColumn<tableclient, String> Raisonscocial;

    @FXML
    private TableColumn<tableclient, String> Adresse;

    @FXML
    private TableColumn<tableclient, String> MatriculeFiscal;

    @FXML
    private TableColumn<tableclient, String> nArticle;

    @FXML
    private TableColumn<tableclient, String> RegistredeCommerce;
     
     ObservableList<tableclient> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from client");
            while(rs.next()){
                
                oblist.add(new tableclient(rs.getInt("numeroClient"), rs.getString("raisonSociale"), rs.getString("adresse"),
                        rs.getString("matriculeFiscale"), rs.getString("nArticle"), rs.getString("registreDeCommerce")));
            }
      
           }catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
             nClient.setCellValueFactory(new PropertyValueFactory<tableclient, Integer>("numeroClient"));
        Raisonscocial.setCellValueFactory(new PropertyValueFactory<tableclient, String>("raisonSociale"));
        Adresse.setCellValueFactory(new PropertyValueFactory<tableclient, String>("adresse"));
        MatriculeFiscal.setCellValueFactory(new PropertyValueFactory<tableclient, String>("matriculeFiscale"));
        nArticle.setCellValueFactory(new PropertyValueFactory<tableclient, String>("nArticle"));     
        RegistredeCommerce.setCellValueFactory(new PropertyValueFactory<tableclient, String>("registreDeCommerce"));
        tableclient.setItems(oblist);
        ///////////////////////////
        
        
//looooooooooooool?
    }    
    
    @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) close.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
    @FXML
    private void btnImprimmer(){
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if(printerJob.showPrintDialog((Stage) close.getScene().getWindow()) && printerJob.printPage(listeDeClientsTxt) && printerJob.printPage(tableclient))
                printerJob.endJob();
    }
    
    @FXML
    private void hisclientbtn() throws IOException{
        
            idclient = tableclient.getSelectionModel().getSelectedItem().numeroClient;
            Stage stage = (Stage) listeDeClientsTxt.getScene().getWindow();
            stage.close();  
        
            Stage primaryStage =new Stage();
            FXMLLoader loader =new FXMLLoader();
            Parent root = loader.load(getClass().getResource("HistoriqueDeClient.fxml"));    

            Scene scene = new Scene(root);
            primaryStage.setTitle("Historique de Client");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

    }
    
}
