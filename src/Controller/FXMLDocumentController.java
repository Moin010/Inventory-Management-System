
package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author immah
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView img;
    
    
    @FXML 
    private Button closeButton;
    
    @FXML 
    private Button loginButton;
    
    @FXML
    private Button mechaboo;
    
    @FXML
    private TextField txt;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private void mechabooScene(ActionEvent event) throws IOException
    {
         Parent next = FXMLLoader.load(getClass().getResource("/View/Mechaboo.fxml"));
             Scene nextScene = new Scene(next);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
        
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if(txt.getText().equals("moin")&&pass.getText().equals("420"))
        {
             Parent next = FXMLLoader.load(getClass().getResource("/View/Fxml1.fxml"));
             Scene nextScene = new Scene(next);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
        }
        else
        {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Error !");
        al.setContentText("Error  ");
        al.setHeaderText("Something went wrong");
        Image APPLICATION_ICON = new Image("img/takeabite.jpg");
        Stage dialogStage = (Stage) al.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);
        al.show();
        txt.setText(null);
        pass.setText(null);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Image im  = new Image("img/takeabite.jpg");
       this.img.setImage(im);
    }
    
    @FXML
    private void closeButtonAction(){
     Platform.exit();
}
}
