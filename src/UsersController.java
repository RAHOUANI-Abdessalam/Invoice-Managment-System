/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.ConnectionProvider;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class UsersController implements Initializable {
    int  iduser=-1;
    

    
    @FXML
    private CheckBox affichMdpsCheckBox;
    @FXML
    private JFXTextField userNameField;

    @FXML
    private ImageView checkMark;

    @FXML
    private JFXTextField newUserNameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField newPasswordField;

    @FXML
    private JFXPasswordField confirmPasswordField;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userNameField.requestFocus();
    }    
    
    
    
    @FXML
    private void btnClose(){
         //to close just one stage and other still running
         Stage stage = (Stage) confirmPasswordField.getScene().getWindow();
         stage.close();    
        // Platform.exit();
    }
    
//        @FXML
//    private void btnReinitialiser() throws IOException{
//        
//                    Stage stage = (Stage) confirmPasswordField.getScene().getWindow();
//                    stage.close();  
//                    
//                    //Creat a new Satge to Show the New frame "the Deshboard"
//                    Stage primaryStage =new Stage();
//                    FXMLLoader loader =new FXMLLoader();
//                    //Parent root = loader.load(getClass().getResource("Deshboard.fxml"));        
//                    Pane root = loader.load(getClass().getResource("Users.fxml"));
//                    Scene scene = new Scene(root);
//                    primaryStage.setTitle("Paramètres Utilisateur");
//                    primaryStage.setScene(scene);
//                    primaryStage.setResizable(false);
//                    primaryStage.show();
//        
//    }
    
    @FXML
    private void reSelectUser(){
        userNameField.setText("");
        checkMark.setVisible(false);
    }
    
    @FXML
    private void EmptyFields(){
        userNameField.setText("");
        newUserNameField.setText("");
        passwordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
        
        passwordField.setPromptText("");
        newPasswordField.setPromptText("");
        confirmPasswordField.setPromptText("");
        confirmPasswordField.setText("");
        
        passwordField.setDisable(false);
        newPasswordField.setDisable(false);
        confirmPasswordField.setDisable(false);
        checkMark.setVisible(false);
        affichMdpsCheckBox.setSelected(false);
    }
    
    @FXML
    private void btnEnregistrer (){
        boolean juste=verifyUser();
        if(juste == false){
            return;
        }
        
        modifieUser();  
        
    }
    
    
    @FXML   
    private void selectUser(){
        String username = userNameField.getText();
                try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from user where username like '"+username+"%'");
                    if(rs.next()){
                        userNameField.setText(rs.getString(2));
                        newUserNameField.setText(rs.getString(2));
                        checkMark.setVisible(true);
                        
                    }else{
                      
                        Toast.makeText((Stage) confirmPasswordField.getScene().getWindow(), "Utilisateur n'existe pas", 1500, 500, 500); 
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"1"+e.toString());
                   
                }
    }
    
    private boolean verifyUser(){
            if(newUserNameField.getText().isEmpty() || userNameField.getText().isEmpty()){

            String toastMsg = "Nom D'utilisateu est vide";
            int toastMsgTime = 1500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Toast.makeText((Stage) newUserNameField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
            return false;
        }
        if(passwordField.getText().isEmpty() || newPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()){
            String toastMsg = "Mot De Passe est vide";
            int toastMsgTime = 1500; //3.5 seconds
            int fadeInTime = 500; //0.5 seconds
            int fadeOutTime= 500; //0.5 seconds
            Toast.makeText((Stage) newUserNameField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
            return false;
        }
        
         try {
                    Connection con=ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs= st.executeQuery("select *from user where username='"+userNameField.getText()+"' and password='"+passwordField.getText()+"'");
                    if(rs.next()){ 
                        iduser = rs.getInt("idUser");
                        return true;
                    }else{
                        String toastMsg = "                   Désolé \n Vous avez mal saisi des données de compte";
                        int toastMsgTime = 2500; //2.5 seconds
                        int fadeInTime = 500; //0.5 seconds
                        int fadeOutTime= 500; //0.5 seconds
                        Toast.makeText((Stage) newUserNameField.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"2"+e.toString());
                    return false;
                }
    }
    
    private void modifieUser(){
        String newName,confirmpassword,newPassword;
        newName =newUserNameField.getText();
        confirmpassword =confirmPasswordField.getText();
        newPassword =newPasswordField.getText();
        if(!confirmpassword.equals(newPassword)){
            Toast.makeText((Stage) newUserNameField.getScene().getWindow(), "Les mots de passe ne sont pas identique", 1500, 500, 500);
            return;
        }
         try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("update user set username='"+newName+"',password='"+newPassword+"' where idUser='"+iduser+"'");
                    EmptyFields();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"3"+e.toString());
                }       
    }
    
    @FXML
    private void afficherMdps(){
        if (affichMdpsCheckBox.isSelected()){
       passwordField.setPromptText(passwordField.getText());
       passwordField.setText(""); 
        passwordField.setDisable(true);
        
               newPasswordField.setPromptText(newPasswordField.getText());
       newPasswordField.setText(""); 
        newPasswordField.setDisable(true);
        
               confirmPasswordField.setPromptText(confirmPasswordField.getText());
       confirmPasswordField.setText(""); 
        confirmPasswordField.setDisable(true);
        }else {
       passwordField .setText(passwordField.getPromptText());
       passwordField.setPromptText("");
       passwordField.setDisable(false);
       
              newPasswordField .setText(newPasswordField.getPromptText());
       newPasswordField.setPromptText("");
       newPasswordField.setDisable(false);
       
              confirmPasswordField .setText(confirmPasswordField.getPromptText());
       confirmPasswordField.setPromptText("");
       confirmPasswordField.setDisable(false);
       }
    }
    

    
    
}
