/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import project.ConnectionProvider;



public class DeshboardController implements Initializable {
    static int Fid;
    static String nom;
    int codeClient=0;
    String raisonScociale="";
    String adresse="";
    String matriculeFiscal="";
    String nArticle="";
    String registreDeCommerce="";
    @FXML
    private RadioButton espcRadio;
    @FXML
    private RadioButton cheqRadio;
    @FXML
    private Text date;
    @FXML
    private JFXButton validerFacturebtn;
    @FXML
    private JFXButton ajouterProduitbtn;
    @FXML
    private JFXButton supprimerProduitbtn;
    @FXML
    private JFXButton imprimmerbtn;
    @FXML
    private Text ClientSelectText;
    @FXML
    public JFXTextField facturefield;
    @FXML
    private JFXTextField nclientfield;
    @FXML
    private JFXTextField numeroCheaque;
    @FXML
    private JFXTextField totaleEnLettres;

    @FXML
    private JFXTextField totalHT;

    @FXML
    private JFXTextField totalTVA;

    @FXML
    private JFXTextField totalTTC;

    @FXML
    private JFXTextField remise;

    @FXML
    private JFXTextField montantTotale;

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
    private JFXTextField qteProduit;

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
    private TableColumn<tableview, String> tva;

    @FXML
    private TableColumn<tableview, String> total;
 
    ToggleGroup radioGroup = new ToggleGroup();
    @FXML
    
  private static final DecimalFormat df = new DecimalFormat("0.00");
    @Override
     public void initialize(URL url, ResourceBundle rb) {
         
         int numFactFromHist=HistoriqueDeClientController.numFacture;
         if(numFactFromHist!=0){
             facturefield.setText(String.valueOf(numFactFromHist));
             recherFacture(String.valueOf(numFactFromHist));
         }
         
            validerFacturebtn.setDisable(true);
         
            SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dat = new Date();
            date.setText(dFormat.format(dat));
         
            espcRadio.setToggleGroup(radioGroup);
            cheqRadio.setToggleGroup(radioGroup);

            espcRadio.setSelected(true);
            numeroCheaque.setDisable(true);
         
         
//         Validation
//          RequiredFieldValidator validator = new RequiredFieldValidator();
//          validator.setMessage("missing info....");
//          designationfield.getValidators().add(validator);
          NumberValidator numvad = new NumberValidator();
          numvad.setMessage("nombres seulement");
//          raisonsocialfeild validation
//           raisonsocialfeild.getValidators().add(numvad);
//            raisonsocialfeild.focusedProperty().addListener(new ChangeListener<Boolean>() {
//              @Override
//              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                  if(!newValue){
//                      raisonsocialfeild.validate();
//                  }
//                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//              }
//          });
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
          qteProduit.getValidators().add(numvad);
          qteProduit.focusedProperty().addListener(new ChangeListener<Boolean>() {
              @Override
              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  if(!newValue){
                      qteProduit.validate();
                  }
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
                          
// designationfield validation
//          designationfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
//              @Override
//              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                  if(!newValue){
//                      designationfield.validate();
//                  }
//                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//              }
//          });
//       setting table vakue
        codp.setCellValueFactory(new PropertyValueFactory<tableview, String>("codeProduit"));
        des.setCellValueFactory(new PropertyValueFactory<tableview, String>("designation"));
        prixt.setCellValueFactory(new PropertyValueFactory<tableview, String>("PrixTransport"));
        prixu.setCellValueFactory(new PropertyValueFactory<tableview, String>("PrixUnitaire"));
        quant.setCellValueFactory(new PropertyValueFactory<tableview, String>("qteProduit"));
        tva.setCellValueFactory(new PropertyValueFactory<tableview, String>("totalTVA"));
        total.setCellValueFactory(new PropertyValueFactory<tableview, String>("montantTotale"));
        
    }
      double sommeTVA=0,sommeMNT=0,sommemntsontva=0;
    //addtab button
    @FXML
    void addtabs(ActionEvent event) {
     ajouterTable();
     
    }
    @FXML
    void addtabsFromQte(ActionEvent event) {
     ajouterTable();
     
    }
    private void ajouterTable(){
        double TVA=0;
        if(codeproduitfield.getText().isEmpty()|| designationfield.getText().isEmpty()||
                prixtransportfield.getText().isEmpty()||priunitairefield.getText().isEmpty()||
             qteProduit.getText().isEmpty()){
            
        Toast.makeText((Stage) codeproduitfield.getScene().getWindow(), "Veuilley saisir tout les champs SVP", 1500, 500, 500);
        codeproduitfield.requestFocus();
                   return;           
    }      
       
     String qteProduiT= qteProduit.getText(),priunitaire= priunitairefield.getText(),
             prixtransport= prixtransportfield.getText(),codeprd=codeproduitfield.getText(),desint=designationfield.getText();
      if(cheqRadio.isSelected()){
            TVA=0.19;
          
            
        }
    
     //verifier si un produit existe dans la table
     if(table.getItems().size() > 0){
            for (int i = 0; i <table.getItems().size() ; i++) {
           String a = table.getItems().get(i).getCodeProduit();
           String b = table.getItems().get(i).getPrixTransport();
           String c = table.getItems().get(i).getPrixUnitaire();
           String d = table.getItems().get(i).getQteProduit();
           if(codeprd.equals(a) && prixtransport.equals(b) && priunitaire.equals(c) && qteProduiT.equals(d)){
                       Toast.makeText((Stage) codeproduitfield.getScene().getWindow(), "Produit "+a+" déja ajouté dans table !!!", 1500, 500, 500);
                       return;
           }
             //Toast.makeText((Stage) codeproduitfield.getScene().getWindow(), "Sélectionnez le produite "+a+" dans la table pour modifier", 1500, 500, 500);
           if(codeprd.equals(a) && (!prixtransport.equals(b) || !priunitaire.equals(c) || !qteProduiT.equals(d))){
                 //***************************
                 //remove the old line product
                        int selectedID = i;
                       
                        String totalSupp = table.getItems().get(selectedID).getMontantTotale();

//                        double totalht= Double.valueOf(totalHT.getText());
                        
                        double totalttc= Double.valueOf(totalTTC.getText());
//                        double remis= Double.valueOf(remise.getText());
//                        double montantTotal= Double.valueOf(montantTotale.getText());

                        sommeMNT=totalttc-Double.valueOf(totalSupp);
                        
                        sommemntsontva=sommeMNT-sommeTVA;


//                        totalTTC.setText(String.valueOf(df.format(newtotalTTC)));
//                        totalTVA.setText(String.valueOf(df.format(newtotalTva)));
//                        totalHT.setText(String.valueOf(df.format(newtotalHT)));
//                        remis= (remis/100);
//                        montantTotal= newtotalTTC-(newtotalTTC*remis);
//                        montantTotale.setText(String.valueOf(montantTotal));

                        table.getItems().remove(selectedID);
                        //table.getFocusModel().equals(false);
                //*********************************
           }
           
       }
     }
     int nbrCameon=0;
     double qte=Double.valueOf(qteProduiT);
     
     while(qte > 0){
         qte = qte-7; ;
         nbrCameon++;
     }
     
     double prixtransporttotal=Double.valueOf(prixtransport)*nbrCameon;
//     double TVA=(Double.valueOf(priunitaire)*Double.valueOf(qteProduiT)*0.19);
     double mntsontva=(Double.valueOf(priunitaire)*Double.valueOf(qteProduiT))+Double.valueOf(prixtransporttotal);
     double montantTotal = mntsontva*TVA;
//     taking value to new tab
String T="00%";
   if(cheqRadio.isSelected()){
       T="19%";
   }
        tableview tableview = new tableview(codeprd,desint,
        String.valueOf(prixtransporttotal),priunitaire,qteProduiT,T,String.valueOf(mntsontva));
        
        ObservableList<tableview> tableviews = table.getItems();
        tableviews.add(tableview);
        table.setItems(tableviews);
        sommemntsontva= sommemntsontva + mntsontva;
        sommeTVA= sommemntsontva*TVA;
        sommeMNT=sommemntsontva+sommeTVA;
        totalTVA.setText(String.valueOf(df.format(sommeTVA)));
        totalHT.setText(String.valueOf(df.format(sommemntsontva)));
        totalTTC.setText(String.valueOf(df.format(sommeMNT)));
        
        double TOTAL= Double.valueOf(totalTTC.getText());
        double rem= Double.valueOf(remise.getText());
        rem= (rem/100);
        TOTAL= TOTAL-(TOTAL*rem);
//        TOTAL=TOTAL*TVA;
        
        montantTotale.setText(String.valueOf(df.format(TOTAL)));
        try {
            String tot= Nombre.CALCULATE.getValue(TOTAL, "Dinar");
            totaleEnLettres.setText(tot);
        } catch (Exception ex) {
            Logger.getLogger(DeshboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // vider champs
        codeproduitfield.setText("");
        designationfield.setText("");
        prixtransportfield.setText(""); 
        priunitairefield.setText(""); 
        qteProduit.setText("");
        codeproduitfield.requestFocus();
      
    }
    @FXML
    void selectFromTab(){
             String codeprd,desint,prixtransport,priunitaire,qteProduiT;
             int selectedID = table.getSelectionModel().getSelectedIndex();
             
        codeprd = table.getItems().get(selectedID).getCodeProduit();
        desint = table.getItems().get(selectedID).getDesignation();
        prixtransport = table.getItems().get(selectedID).getPrixTransport();
        priunitaire = table.getItems().get(selectedID).getPrixUnitaire();
        qteProduiT = table.getItems().get(selectedID).getQteProduit();
        
        codeproduitfield.setText(codeprd);
        designationfield.setText(desint);
        prixtransportfield.setText(prixtransport);
        priunitairefield.setText(priunitaire);
        qteProduit.setText(qteProduiT);
        qteProduit.requestFocus();
    }
   ////////////// remise
    @FXML
    void remise(ActionEvent event){
        //double rem;
        
        double TOTAL= Double.valueOf(totalTTC.getText());
        double rem= Double.valueOf(remise.getText());
        rem= (rem/100);
        TOTAL= TOTAL-(TOTAL*rem);
        
        
        montantTotale.setText(String.valueOf(df.format(TOTAL)));
        try {
            String tot= Nombre.CALCULATE.getValue(TOTAL, "Dinar");
            totaleEnLettres.setText(tot);
        } catch (Exception ex) {
            Logger.getLogger(DeshboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//remove from table
    @FXML
    void removetabs(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
//        String tvaSupp = table.getItems().get(selectedID).getTotalTVA();
        String totalSupp = table.getItems().get(selectedID).getMontantTotale();
        
        double totalht= Double.valueOf(totalHT.getText());
//        double totaltva= Double.valueOf(totalTVA.getText());
//        double totalttc= Double.valueOf(totalTTC.getText());
        double remis= Double.valueOf(remise.getText());
        double montantTotal;
        


        double newtotalHT=totalht-Double.valueOf(totalSupp);
        double newtotalTva=newtotalHT*0.19;
        double newtotalTTC=newtotalHT*1.19;
                
        //totalTTC.setText(String.valueOf(df.format(newtotalTTC)));
        //totalTVA.setText(String.valueOf(df.format(newtotalTva)));
        totalHT.setText(String.valueOf(df.format(newtotalHT)));
        totalTVA.setText(String.valueOf(df.format(newtotalTva)));
        totalTTC.setText(String.valueOf(df.format(newtotalTTC)));
        
        remis= (remis/100);
        montantTotal= newtotalTTC-(newtotalTTC*remis);
        montantTotale.setText(String.valueOf(montantTotal));
        
        table.getItems().remove(selectedID);
        
        codeproduitfield.setText("");
        designationfield.setText("");
        prixtransportfield.setText("");
        priunitairefield.setText("");
        qteProduit.setText("");
        codeproduitfield.requestFocus();
        //tvaSupp="0.00";

        
        if(0==table.getItems().size()){
            sommeTVA=0;sommeMNT=0;sommemntsontva=0;
            remise.setText("0");
                    totalSupp="0.00";
                    newtotalTTC=0.00;
                    newtotalTva=0.00;
                    newtotalHT=0.00;
        }
        
                try {
            String tot= Nombre.CALCULATE.getValue(montantTotal, "Dinar");
            totaleEnLettres.setText(tot);
        } catch (Exception ex) {
            Logger.getLogger(DeshboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @FXML
    private void bntAjouterC() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("AjouterClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Ajouter Client");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
    @FXML
     private void bntlistc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Pane root = loader.load(getClass().getResource("ListDuClients.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Liste De Clents");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntmodc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ModifierClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Modifier Client");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntsupc() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("SupprimerClient.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Supprimer Client");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntlistp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ListeDuProduits.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Liste de Produits");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntajoutp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("AjouterProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Ajouter Produit");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntmodp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("ModifierProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Modifier Produit");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
     @FXML
     private void bntsupp() throws IOException{
     
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("SupprimerProduit.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Supprimer Produit");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
//    cherche facture
     ObservableList<tableview> oblist = FXCollections.observableArrayList();
    @FXML
    private void btnRechercher(){
                 if(facturefield.getText().isEmpty()){
                    Toast.makeText((Stage) facturefield.getScene().getWindow(), "Veuilley Entrer N° de Facture SVP", 1500, 500, 500);
                    return;
                }
                String numeroFacture = facturefield.getText();
                recherFacture(numeroFacture);
    }
    @FXML
    private void btnRechercherFromField(){
                 if(facturefield.getText().isEmpty()){
                    Toast.makeText((Stage) facturefield.getScene().getWindow(), "Veuilley Entrer N° de Facture SVP", 1500, 500, 500);
                    return;
                }
                String numeroFacture = facturefield.getText();
                recherFacture(numeroFacture);
    }
    
    @FXML
    private void reSelectClient(){
        if(!nclientfield.getText().equals("")||!raisonsocialfeild.getText().equals("")){
            nclientfield.setText("");
            raisonsocialfeild.setText("");
            ClientSelectText.setText("Client non Sélectionné");
            ClientSelectText.setStyle("-fx-fill: #ff0000;"); 
            validerFacturebtn.setDisable(true);
        }

        
    }
//    btnReinitialiser
    @FXML
     private void bntr() throws IOException{
         SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
         Date dat = new Date();
         date.setText(dFormat.format(dat));
         
         facturefield.setText("");
         nclientfield.setText("");
         raisonsocialfeild.setText("");
         ClientSelectText.setText("Client non Sélectionné");
         ClientSelectText.setStyle("-fx-fill: #ff0000;"); 
         codeproduitfield.setText("");
         designationfield.setText("");
         prixtransportfield.setText("");
         priunitairefield.setText("");
         qteProduit.setText("");
         espcRadio.setSelected(true);
         numeroCheaque.setDisable(true);
         totalHT.setText("");
         totalTVA.setText("");
         totalTTC.setText("");
         remise.setText("0");
         montantTotale.setText("");
        totaleEnLettres.setText("");
        imprimmerbtn.setVisible(false);
        validerFacturebtn.setDisable(true);
        
        table.getItems().clear();
        numeroCheaque.setText("");
        ajouterProduitbtn.setDisable(false);
        supprimerProduitbtn.setDisable(false);
        remise.setEditable(true);
        totaleEnLettres.setEditable(true);
         sommemntsontva= 0;
        sommeTVA= 0;
        sommeMNT=0;
    }
     
    @FXML
    private void btnClose(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fermer");
            //alert.setHeaderText("SVP, Vérifier");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous Vraiment fermer");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                //close the entier app
                //Platform.exit();
                Stage stage = (Stage) totaleEnLettres.getScene().getWindow();
                stage.close();   
            } else {
                // ... user chose CANCEL or closed the dialog
                alert.close();
            }
         
    }
    
    @FXML
    private void clientRechNumero(){
                String idClient = nclientfield.getText();
                try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from client where numeroClient like '"+idClient+"%'");
                    if(rs.next()){
                        nclientfield.setText(rs.getString(1));
                        raisonsocialfeild.setText(rs.getString(2));
                        ClientSelectText.setText("      Client Sélectionné");
                        ClientSelectText.setStyle("-fx-fill: #00FF0B;");
                        
                        codeClient = rs.getInt(1);
                        raisonScociale=raisonsocialfeild.getText();
                        adresse=rs.getString(3);
                        matriculeFiscal=rs.getString(4);
                        nArticle=rs.getString(5);
                        registreDeCommerce=rs.getString(6);
                        
                        validerFacturebtn.setDisable(false);
                        codeproduitfield.requestFocus();
                        
                    }else{
                        nclientfield.setText("");
                        raisonsocialfeild.setText("");
                        
                        ClientSelectText.setText("Client non Sélectionné");
                        ClientSelectText.setStyle("-fx-fill: #ff0000;");
                        
                        validerFacturebtn.setDisable(true);
                        
                        Toast.makeText((Stage) facturefield.getScene().getWindow(), "Client n'existe pas", 1500, 500, 500);
                        
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
    
    @FXML
    private void clientRechRaison(){
                String nomClient = raisonsocialfeild.getText();
                try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from client where raisonSociale like '"+nomClient+"%'");
                    if(rs.next()){
                        nclientfield.setText(rs.getString(1));
                        raisonsocialfeild.setText(rs.getString(2));
                        ClientSelectText.setText("      Client Sélectionné");
                        ClientSelectText.setStyle("-fx-fill: #00FF0B;");
                        
                        codeClient = rs.getInt(1);
                        raisonScociale=raisonsocialfeild.getText();
                        adresse=rs.getString(3);
                        matriculeFiscal=rs.getString(4);
                        nArticle=rs.getString(5);
                        registreDeCommerce=rs.getString(6);

                        validerFacturebtn.setDisable(false);
                        codeproduitfield.requestFocus();
                    }else{
                        nclientfield.setText("");
                        raisonsocialfeild.setText("");
                        
                        ClientSelectText.setText("Client non Sélectionné");
                        ClientSelectText.setStyle("-fx-fill: #ff0000;");
                        
                        validerFacturebtn.setDisable(true);
                        Toast.makeText((Stage) facturefield.getScene().getWindow(), "Client n'existe pas", 1500, 500, 500);
                        
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
    
    @FXML
    private void produitRechCode(){
        String codeProduit = codeproduitfield.getText();
                try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from produit where codeProduit like '"+codeProduit+"%'");
                    if(rs.next()){
                        codeproduitfield.setText(rs.getString(1));
                        designationfield.setText(rs.getString(2));
                        prixtransportfield.setText(rs.getString(3));
                        priunitairefield.setText(rs.getString(4));
                        qteProduit.setText("1");
                        qteProduit.requestFocus();
                        
                        //jTextField1.setEditable(false);
                    }else{
                        codeproduitfield.setText("");
                        designationfield.setText("");
                        prixtransportfield.setText(""); 
                        priunitairefield.setText(""); 
                        qteProduit.setText("");
                        Toast.makeText((Stage) facturefield.getScene().getWindow(), "Produit n'existe pas", 1500, 500, 500);
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
    
    @FXML
    private void radioChange(){
        if(espcRadio.isSelected()){
            numeroCheaque.setText("");
            numeroCheaque.setDisable(true);
            table.getItems().forEach(item -> item.setTotalTVA("00%"));
        }
        else{
           
            table.getItems().forEach(item -> item.setTotalTVA("19%"));
            numeroCheaque.setDisable(false);
            
        }
    }
    
        private void recherFacture(String numeroFacture){
        try {
                    Connection con= ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                     
                    ResultSet rs = st.executeQuery("select *from facture inner join quantite \n" +
                                "on '"+numeroFacture+"'=facture.numeroFacture\n" +"inner join produit\n" +
                                  "on produit.codeProduit=quantite.codeProduit and quantite.numeroFacture = '"+numeroFacture+"'");
                    table.getItems().clear();
                    if(rs.next()){
                       
//                        double total=(((Double.valueOf(rs.getString("prixUnitaire"))*Double.valueOf(rs.getString("qteProduit")))*0.19)+Double.valueOf(rs.getString("prixTransport")));
                                    oblist.add(new tableview(rs.getString("codeProduit"), rs.getString("designation"), rs.getString("prixTransport"),
                                                         rs.getString("prixUnitaire"), rs.getString("qteProduit"),rs.getString("tva") , rs.getString("totalP")));
                                        nclientfield.setText(rs.getString(2));
                                        String idClient = rs.getString(2);
                                        date.setText(rs.getString(3));
                                        String modReglemnt = rs.getString(4);
                                        if(modReglemnt.equals("chèque")|| modReglemnt.equals("Chèque")){
                                            cheqRadio.setSelected(true);
                                        }
                                        if(modReglemnt.equals("espèces")|| modReglemnt.equals("Espèces")){
                                            espcRadio.setSelected(true);
                                            numeroCheaque.setDisable(true);
                                        }
                                        numeroCheaque.setText(rs.getString(5));
                                        totalHT.setText(rs.getString(6));
                                        totalTVA.setText(rs.getString(7));
                                        totalTTC.setText(rs.getString(8));
                                        remise.setText(rs.getString(9));
                                        montantTotale.setText(rs.getString(10));
                                        montantTotale.setText(rs.getString(10));
                                        totaleEnLettres.setText(rs.getString(11)); 
                                       
                                        
                                        imprimmerbtn.setVisible(true);
                                        validerFacturebtn.setDisable(true);
                                        ajouterProduitbtn.setDisable(true);
                                        supprimerProduitbtn.setDisable(true);
                                        ClientSelectText.setText("      Client Sélectionné");
                                        //////ClientSelectText.setFill(Color.GREEN);
                                        ClientSelectText.setStyle("-fx-fill: #00FF0B;");
                        while(rs.next()){
                        
                     
//                        double total1=(((Double.valueOf(rs.getString("prixUnitaire"))*Double.valueOf(rs.getString("qteProduit")))*0.19)+Double.valueOf(rs.getString("prixTransport")));
                                    oblist.add(new tableview(rs.getString("codeProduit"), rs.getString("designation"), rs.getString("prixTransport"),
                                                         rs.getString("prixUnitaire"), rs.getString("qteProduit"), rs.getString("tva"), rs.getString("totalP")));
                                                                  nclientfield.setText(rs.getString(2));
                                
                               }
                    
//                     rs.close();
              
                            ResultSet rs2=st.executeQuery("select *from client where numeroClient='"+idClient+"'");
                            if(rs2.next()){
                               raisonsocialfeild.setText(rs2.getString(2));
                            }else{
                                Toast.makeText((Stage) nclientfield.getScene().getWindow(), "Client n'existe pas", 1500, 500, 500);
                            }
                            //fin de recherchment client
                            
   
                            totalHT.setEditable(false);
                            totalTVA.setEditable(false);
                            totalTTC.setEditable(false);
                            remise.setEditable(false);
                            montantTotale.setEditable(false);
                            montantTotale.setEditable(false);
                            totaleEnLettres.setEditable(false);
                    }else{
                        Toast.makeText((Stage) facturefield.getScene().getWindow(), "facture n'existe pas", 1500, 500, 500);
                    }

            
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
        codp.setCellValueFactory(new PropertyValueFactory<tableview, String>("codeProduit"));
        des.setCellValueFactory(new PropertyValueFactory<tableview, String>("designation"));
        prixt.setCellValueFactory(new PropertyValueFactory<tableview, String>("prixTransport"));
        prixu.setCellValueFactory(new PropertyValueFactory<tableview, String>("prixUnitaire"));
        quant.setCellValueFactory(new PropertyValueFactory<tableview, String>("qteProduit"));
         tva.setCellValueFactory(new PropertyValueFactory<tableview, String>("totalTVA"));
        total.setCellValueFactory(new PropertyValueFactory<tableview, String>("montantTotale"));
        table.setItems(oblist);
              
    }
 
        @FXML
    private void validerBtn(){
        //*******************************************************************************************************
        //debut d'enregistrement dans la facture
        String modeReglemnt,T;
       
                if(espcRadio.isSelected()){
            modeReglemnt=espcRadio.getText();
            T="00%";
        }
        else{
            modeReglemnt=cheqRadio.getText();
            T="19%";
        }
        int nFacture=1;
        try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select *from facture ORDER BY numeroFacture DESC LIMIT 1");
                if(rs.next()){
                    nFacture = rs.getInt("numeroFacture");
                    nFacture=nFacture+1;
                }else
                    nFacture=1;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,""+e.toString());
        }
        try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into facture values('0','"+codeClient+"','"+date.getText()+"','"+modeReglemnt+"','"+numeroCheaque.getText()+"','"+totalHT.getText()+"','"+totalTVA.getText()+"','"+totalTTC.getText()+"','"+remise.getText()+"','"+montantTotale.getText()+"','"+totaleEnLettres.getText()+"','"+T+"')");
                    
                    Statement st2 = con.createStatement();
                    for (int i = 0; i <table.getItems().size() ; i++) {
                        String codP = table.getItems().get(i).getCodeProduit();
                        String qteP = table.getItems().get(i).getQteProduit();
                        String totalP = table.getItems().get(i).getMontantTotale();

                        st2.executeUpdate("insert into quantite values('"+nFacture+"','"+codP+"','"+qteP+"','"+totalP+"')");
                    }

                    //JOptionPane.showMessageDialog(null,"Facture Enregistrée"); 


                    bntr();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                    System.out.println("Error in connection"+e.toString());
        }
        //fin d'enregistrement dans la facture
        //******************************************************************************
        
        
        try {
                        Connection con = ConnectionProvider.getCon();
                       Fid=nFacture;
                       nom=raisonsocialfeild.getText();
                        new jasper (nFacture,con);
                    
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Facture");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Facture N°"+String.valueOf(nFacture)+ " Enregistrée dans C:/Users/PC_nom");
//
//                    alert.showAndWait();
                  }catch (Exception e) {
                            JOptionPane.showMessageDialog(null,""+e.toString());
                            System.out.println("Error in connection"+e.toString());
                }
        
    }
    
    @FXML
    private void btnImprimmer(){
        
        int nFacture= Integer.valueOf(facturefield.getText());
                try {
                        Connection con = ConnectionProvider.getCon();
                       Fid=nFacture;
                       nom=raisonsocialfeild.getText();
                       
//                        JasperDesign jasperReport =  JRXmlLoader.load("src/InvoiceF.jrxml");
//                       Fid=nFacture;
//                       String query= "SELECT *FROM  gestion_stock2.facture\n" +
//"	INNER JOIN gestion_stock2.quantite ON \n" +
//"	 gestion_stock2.quantite.`numeroFacture` = gestion_stock2.facture.`numeroFacture` \n" +
//"	INNER JOIN gestion_stock2.produit ON \n" +
//"	 gestion_stock2.produit.`codeProduit` = gestion_stock2.quantite.`codeProduit` \n" +
//"	INNER JOIN gestion_stock2.client ON \n" +
//"	 gestion_stock2.client.`numeroClient` = gestion_stock2.facture.`numeroClient` ";
//                       JRDesignQuery updateQuery = new  JRDesignQuery();
//                       updateQuery.setText(query);
//                       jasperReport.setQuery(updateQuery);
//                       JasperReport jreport = JasperCompileManager.compileReport(jasperReport);
//                       JasperPrint jprint = JasperFillManager.fillReport(jreport, null,con);
//                       JasperViewer.viewReport(jprint);
//                        new jasper (nFacture,con);

                        new jasper (nFacture,con);
                        bntr();
                  }catch (Exception e) {
                            JOptionPane.showMessageDialog(null,""+e.toString());
                            System.out.println("Error in connection"+e.toString());
                }
    }
    
        @FXML
    private void btnUserSettings() throws IOException{
        
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("Users.fxml"));        
                    
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("Paramètres Utilisateur");
                        primaryStage.setScene(scene);
                        primaryStage.setResizable(false);
                        primaryStage.show();
    }
    

}