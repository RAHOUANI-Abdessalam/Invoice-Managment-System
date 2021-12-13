

import com.itextpdf.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;


public class LoginController implements Initializable {
    
    @FXML
    private VBox bottomCenterVbox;
    
    //private JFXSnackbar errorMsg;

    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField motdepasseField;
    
    @FXML
    private CheckBox affichMdpsCheckBox;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //errorMsg = new JFXSnackbar(bottomCenterVbox);

    }    
    
    @FXML
    private void btnEntrer() throws IOException{
    entrer();
    }
    @FXML
    private void btnEntrerFromPass(){
         entrer();
    }
    @FXML
    private void btnEntrerFromUser(){
         entrer();
    }
    private void entrer(){
            if(usernameField.getText().isEmpty()){
            //errorMsg.show("Nom D'utilisateu est vide", 1500);
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Se Connecter");
//            //alert.setHeaderText("SVP, Vérifier");
//            alert.setHeaderText(null);
//            alert.setContentText("Nom D'utilisateu est vide");
//            alert.showAndWait();
            String toastMsg = "Nom D'utilisateur est vide";
            int toastMsgTime = 1500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Toast.makeText((Stage) motdepasseField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
            return;
        }
        if(motdepasseField.getText().isEmpty()){
            String toastMsg = "Mot De Passe est vide";
            int toastMsgTime = 1500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Toast.makeText((Stage) motdepasseField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
            return;
        }
        
         try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from user where username='"+usernameField.getText()+"' and password='"+motdepasseField.getText()+"'");
                    if(rs.next()){
                        
                       //close the Login Frame "Stage"
                        con.close();


                        //Creat a new Satge to Show the New frame "the Deshboard"
                        Stage primaryStage =new Stage();
                        FXMLLoader loader =new FXMLLoader();
                        Parent root = loader.load(getClass().getResource("Deshboard.fxml"));        
                        //Pane root = loader.load(getClass().getResource("Deshboard.fxml"));
                        Scene scene = new Scene(root);
//                        Screen screen = Screen.getPrimary();
//                        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
//
//                        primaryStage.setX(bounds.getMinX());
//                        primaryStage.setY(bounds.getMinY());
//                        primaryStage.setWidth(bounds.getWidth());
//                        primaryStage.setHeight(bounds.getHeight());
                        primaryStage.setTitle("Deshboard");
                        primaryStage.setScene(scene);
                        primaryStage.setMinHeight(720);
                        primaryStage.setMinWidth(1280);
                        primaryStage.setMaximized(true);
                        primaryStage.show();
                        
                        Stage stage = (Stage) motdepasseField.getScene().getWindow();
                        stage.close();
                    }else{
                        String toastMsg = "                   Désolé \n Vous avez mal saisi les données";
                        int toastMsgTime = 2500; //2.5 seconds
                        int fadeInTime = 500; //0.5 seconds
                        int fadeOutTime= 500; //0.5 seconds
                        Toast.makeText((Stage) motdepasseField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,""+e.toString());
                }
    }
    
    @FXML
    private void btnClose(){
//         to close just one stage and other still running
//         Stage stage = (Stage) motdepasseField.getScene().getWindow();
//         stage.close();
            Alert alert = new Alert(AlertType.CONFIRMATION);
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
    private void afficherMdps(){
        if (affichMdpsCheckBox.isSelected()){
       motdepasseField.setPromptText(motdepasseField.getText());
       motdepasseField.setText(""); 
        motdepasseField.setDisable(true);
        }else {
       motdepasseField .setText(motdepasseField.getPromptText());
       motdepasseField.setPromptText("");
       motdepasseField.setDisable(false);
       }
    }
}
