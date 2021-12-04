

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


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
    private void btnEntrer(){
        if(usernameField.getText().isEmpty()){
            //errorMsg.show("Nom D'utilisateu est vide", 1500);
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Se Connecter");
//            //alert.setHeaderText("SVP, Vérifier");
//            alert.setHeaderText(null);
//            alert.setContentText("Nom D'utilisateu est vide");
//            alert.showAndWait();
            String toastMsg = "Nom D'utilisateu est vide";
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
        
        System.out.println("Username: "+usernameField.getText());       
        System.out.println("Password: "+motdepasseField.getText());
        
        String toastMsg = "Bienvenu "+usernameField.getText();
        int toastMsgTime = 3500; //3.5 seconds
        int fadeInTime = 500; //0.5 seconds
        int fadeOutTime= 500; //0.5 seconds
        Toast.makeText((Stage) motdepasseField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
    
    @FXML
    private void btnClose(){
//         Stage stage = (Stage) motdepasseField.getScene().getWindow();
//         stage.close();
         Platform.exit();
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
