
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author immah
 */
public class MechabooController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button back;
    
    @FXML 
    private ImageView img;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Image im  = new Image("img/mechaboo.png");
       this.img.setImage(im);
    }    
    
    @FXML
    private void BackAction(ActionEvent e) throws IOException
    {
    Parent next = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
             Scene nextScene = new Scene(next);
             Stage window= (Stage) ((Node) e.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
    }
}
