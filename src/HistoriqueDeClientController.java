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
import javafx.print.PrinterJob;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class HistoriqueDeClientController implements Initializable {

    //adminstatic int idClient;

    @FXML
    private ImageView close;
    
    @FXML
    private Text nClientTxt;
        
    @FXML
    private HBox numClientTxt;
    @FXML
    private TableView<historiqueClient> historiqueClient;

    @FXML
    private TableColumn<historiqueClient, String> numeroFacture;

    @FXML
    private TableColumn<historiqueClient, String> date;

    @FXML
    private TableColumn<historiqueClient, String> montantTotale;

    @FXML
    private TableColumn<historiqueClient, String> modeDeReglement;
    
    ObservableList<historiqueClient> oblisT = FXCollections.observableArrayList();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                String clientIDstr=Integer.toString(ListDuClientsController.idclient);
                nClientTxt.setText(clientIDstr);
                
               try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from facture where numeroClient='"+clientIDstr+"'");
          
//            
            while(rs.next()){
                
                oblisT.add(new historiqueClient(rs.getString("numeroFacture"), rs.getString("date"), rs.getString("montantTotale"),
                        rs.getString("modeDeReglement")));
            }
      
           }catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
             numeroFacture.setCellValueFactory(new PropertyValueFactory<historiqueClient, String>("numeroFacture"));
        date.setCellValueFactory(new PropertyValueFactory<historiqueClient, String>("date"));
        montantTotale.setCellValueFactory(new PropertyValueFactory<historiqueClient, String>("montantTotale"));
        modeDeReglement.setCellValueFactory(new PropertyValueFactory<historiqueClient, String>("modeDeReglement"));
      
        historiqueClient.setItems(oblisT);       
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
            if(printerJob.showPrintDialog((Stage) close.getScene().getWindow()) && printerJob.printPage(numClientTxt) && printerJob.printPage(historiqueClient))
                printerJob.endJob();
    }
    
}
