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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;



public class DeshboardController implements Initializable {
    String time;
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
    private Text ClientSelectText;
    @FXML
    private JFXTextField facturefield;
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
         
            validerFacturebtn.setDisable(true);
         
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dat = new Date();
            date.setText(dFormat.format(dat));
            
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();
            time=dtf.format(now);
         
            espcRadio.setToggleGroup(radioGroup);
            cheqRadio.setToggleGroup(radioGroup);

            espcRadio.setSelected(true);
            numeroCheaque.setDisable(true);
         
         
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
     
     if(codeproduitfield.getText().isEmpty()|| designationfield.getText().isEmpty()||
                prixtransportfield.getText().isEmpty()||priunitairefield.getText().isEmpty()||
             qteProduit.getText().isEmpty()){
        Toast.makeText((Stage) codeproduitfield.getScene().getWindow(), "Veuilley saisir tout les champs SVP", 1500, 500, 500);
        codeproduitfield.requestFocus();
                   return;           
    }      
    
     String qteProduiT= qteProduit.getText(),priunitaire= priunitairefield.getText(),
             prixtransport= prixtransportfield.getText(),codeprd=codeproduitfield.getText(),desint=designationfield.getText();
     double TVA=(Double.valueOf(priunitaire)*Double.valueOf(qteProduiT)*0.19);
     double mntsontva=(Double.valueOf(priunitaire)*Double.valueOf(qteProduiT))+Double.valueOf(prixtransport);
     double montantTotale = (mntsontva+TVA);
//     taking value to new tab

        tableview tableview = new tableview(codeprd,desint,
        prixtransport,priunitaire,qteProduiT,String.valueOf(TVA),String.valueOf(montantTotale));
        ObservableList<tableview> tableviews = table.getItems();
        tableviews.add(tableview);
        table.setItems(tableviews);
        sommeTVA= sommeTVA + TVA;
        sommeMNT= sommeMNT + montantTotale;
        sommemntsontva= sommemntsontva + mntsontva;
        
        totalTVA.setText(String.valueOf(df.format(sommeTVA)));
        totalHT.setText(String.valueOf(df.format(sommemntsontva)));
        totalTTC.setText(String.valueOf(df.format(sommeMNT)));
       // vider champs
        codeproduitfield.setText("");
        designationfield.setText("");
        prixtransportfield.setText(""); 
        priunitairefield.setText(""); 
        qteProduit.setText("");
        codeproduitfield.requestFocus();
      
    }
   ////////////// remise
    @FXML
    void remise(ActionEvent event){
        double rem;
        
        double TOTAL= Double.valueOf(totalTTC.getText());
        rem= Double.valueOf(remise.getText());
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
        table.getItems().remove(selectedID);
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
                try {
                    Connection con= ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                     
                    ResultSet rs = st.executeQuery("select *from facture inner join quantite \n" +
                                "on '"+numeroFacture+"'=facture.numeroFacture\n" +"inner join produit\n" +
                                  "on produit.codeProduit=quantite.codeProduit and quantite.numeroFacture = '"+numeroFacture+"'");
                    table.getItems().clear();
                    if(rs.next()){
                        String tva="19%";
                        double total=(((Double.valueOf(rs.getString("prixUnitaire"))*Double.valueOf(rs.getString("qteProduit")))*0.19)+Double.valueOf(rs.getString("prixTransport")));
                                    oblist.add(new tableview(rs.getString("codeProduit"), rs.getString("designation"), rs.getString("prixTransport"),
                                                         rs.getString("prixUnitaire"), rs.getString("qteProduit"), tva, String.valueOf(total)));
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
                                        
                                        validerFacturebtn.setDisable(true);
                                        ajouterProduitbtn.setDisable(true);
                                        supprimerProduitbtn.setDisable(true);
                                        ClientSelectText.setText("      Client Sélectionné");
                                        //////ClientSelectText.setFill(Color.GREEN);
                                        ClientSelectText.setStyle("-fx-fill: #00FF0B;");
                        while(rs.next()){
                        
                        String tva1="19%";
                        double total1=(((Double.valueOf(rs.getString("prixUnitaire"))*Double.valueOf(rs.getString("qteProduit")))*0.19)+Double.valueOf(rs.getString("prixTransport")));
                                    oblist.add(new tableview(rs.getString("codeProduit"), rs.getString("designation"), rs.getString("prixTransport"),
                                                         rs.getString("prixUnitaire"), rs.getString("qteProduit"), tva1, String.valueOf(total1)));
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
//    btnReinitialiser
    @FXML
     private void bntr() throws IOException{
        
                    Stage stage = (Stage) facturefield.getScene().getWindow();
                    stage.close();  
                    
               
                    Stage primaryStage =new Stage();
                    FXMLLoader loader =new FXMLLoader();
                         
                    Pane root = loader.load(getClass().getResource("Deshboard.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Deshboard");
                    primaryStage.setScene(scene);
                    primaryStage.setMinHeight(720);
                    primaryStage.setMinWidth(1280);
                    primaryStage.show();
        
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
                Platform.exit();
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
            numeroCheaque.setDisable(true);
        }
        else{
            numeroCheaque.setDisable(false);
        }
    }
 
        @FXML
    private void validerBtn(){
        //*******************************************************************************************************
        //debut d'enregistrement dans la facture
        String modeReglemnt;
                if(espcRadio.isSelected()){
            modeReglemnt=espcRadio.getText();
        }
        else{
            modeReglemnt=cheqRadio.getText();
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
                    st.executeUpdate("insert into facture values('0','"+codeClient+"','"+date.getText()+"','"+modeReglemnt+"','"+numeroCheaque.getText()+"','"+totalHT.getText()+"','"+totalTVA.getText()+"','"+totalTTC.getText()+"','"+remise.getText()+"','"+montantTotale.getText()+"','"+totaleEnLettres.getText()+"')");
                    
                    Statement st2 = con.createStatement();
                    for (int i = 0; i <table.getItems().size() ; i++) {
                        String codP = table.getItems().get(i).getCodeProduit();
                        String qteP = table.getItems().get(i).getQteProduit();

                        st2.executeUpdate("insert into quantite values('"+nFacture+"','"+codP+"','"+qteP+"')");
                    }

                    JOptionPane.showMessageDialog(null,"Facture Enregistrée");          
                    Stage stage = (Stage) numeroCheaque.getScene().getWindow();
                    stage.close();  
                    //Creat a new Satge to Show the New frame "the Deshboard"
                    Stage primaryStage =new Stage();
                    FXMLLoader loader =new FXMLLoader();
                    //Parent root = loader.load(getClass().getResource("Deshboard.fxml"));        
                    Pane root = loader.load(getClass().getResource("Deshboard.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("Deashbord");
                    primaryStage.setScene(scene);
                    primaryStage.setMinHeight(720);
                    primaryStage.setMinWidth(1280);
                    primaryStage.show();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                    System.out.println("Error in connection"+e.toString());
        }
        //fin d'enregistrement dans la facture
        //******************************************************************************
        
    }
}
