/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListeDuProduitsController implements Initializable {

    
    @FXML
    private ImageView close;
    @FXML
    private TableView<tableproduit> tableproduit;

    @FXML
    private TableColumn<tableproduit, Integer> codeProduit;

    @FXML
    private TableColumn<tableproduit, String> designation;

    @FXML
    private TableColumn<tableproduit, String> prixTransport;

    @FXML
    private TableColumn<tableproduit, String> prixUnitaire;
    
        ObservableList<tableproduit> oblist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from produit");
            while(rs.next()){
                
                oblist.add(new tableproduit(rs.getInt("codeProduit"), rs.getString("designation"), rs.getString("prixTransport"),
                        rs.getString("prixUnitaire")));
            }
      
           }catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
             codeProduit.setCellValueFactory(new PropertyValueFactory<tableproduit, Integer>("codeProduit"));
        designation.setCellValueFactory(new PropertyValueFactory<tableproduit, String>("designation"));
        prixTransport.setCellValueFactory(new PropertyValueFactory<tableproduit, String>("prixTransport"));
        prixUnitaire.setCellValueFactory(new PropertyValueFactory<tableproduit, String>("prixUnitaire"));
      
        tableproduit.setItems(oblist);
    }    
    
    @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) close.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
}
