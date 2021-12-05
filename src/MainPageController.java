/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author issam
 */
public class MainPageController implements Initializable {

    @FXML
    private AnchorPane background;

    @FXML
    private TableColumn<?, ?> code;

    @FXML
    private TableColumn<?, ?> Désignation;

    @FXML
    private TableColumn<?, ?> PrixT;

    @FXML
    private TableColumn<?, ?> PrixU;

    @FXML
    private TableColumn<?, ?> Quantité;

    @FXML
    private TableColumn<?, ?> TVA;

    @FXML
    private TableColumn<?, ?> Total;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
