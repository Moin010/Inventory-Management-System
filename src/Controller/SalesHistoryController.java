package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import static Controller.Fxml1Controller.con;
import Model.amount;

/**
 * FXML Controller class
 *
 * @author immah
 */
public class SalesHistoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    DateTimeFormatter formatter;
    Connection con;
    
    @FXML
    private ImageView img;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField amount;
    @FXML
    private TextField details;
    
    @FXML 
    private DatePicker dp;
    @FXML
    private TableView t;
    
    @FXML
    private TableColumn<amount, String> a;
    @FXML
    private TableColumn<amount, String> b;
    @FXML
    private TableColumn<amount, String> c;
    @FXML
    private TableColumn<amount, String> d;
    
    
    
    private final ObservableList<amount> data = FXCollections.observableArrayList();
    
    
    @FXML
    private Button refresh;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.con=Fxml1Controller.con ;
        StringConverter <LocalDate> converter= new StringConverter<LocalDate>()
                {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            @Override
            public String toString(LocalDate object) {
            if(object!=null)
                {
                    return dateFormatter.format(object);
                }
            else return "";
            }

            @Override
            public LocalDate fromString(String string) {
            if(string!=null&&!string.isEmpty())
                {
                return LocalDate.parse(string, dateFormatter);
                }
            else return null;
            }
                
                };
        dp.setConverter(converter);
        dp.setPromptText("dd/MM/yyyy");
        Image im  = new Image("img/takeabite.jpg");
        this.img.setImage(im);
    }    
    
    @FXML
    private void idAction() throws SQLException
    {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select name from bite where id="+id.getText());
        while (rs.next()) {
            name.setText( rs.getString("name"));
        }
        st.close();
        rs.close();
        tableShow();
    }
    
    
    private void tableShow() {
      
        try {
            data.clear();
            t.refresh();
            startup();
            a.setCellValueFactory(new PropertyValueFactory<>("amount"));
            b.setCellValueFactory(new PropertyValueFactory<>("date"));
            c.setCellValueFactory(new PropertyValueFactory<>("total"));
            d.setCellValueFactory(new PropertyValueFactory<>("details"));
            t.setItems(data);
            t.setRowFactory(tv -> {
                TableRow<amount> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        amount rowData = row.getItem();
                        amount.setText(""+rowData.getAmount());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDate localDate = LocalDate.parse(rowData.getDate(), formatter);
                        dp.setValue(localDate);
                        
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(Fxml1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void startup() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select amount,date,details from sales where id="+id.getText());
        Statement stt = con.createStatement();
        ResultSet rss = stt.executeQuery("select sum(amount) as total from sales where id="+id.getText());
        rss.next();
        int counter =0;
        String[] pop = new String[4];
        while (rs.next()) {
            pop[1 - 1] = rs.getString("amount");
            pop[2 - 1] = rs.getString("date");
            pop[3] = rs.getString("details");
            
            if(counter==0)pop[3 - 1] = (rss.getString("total"));
            else pop[2]=null;
            counter++;
            data.add(new amount((pop[0]), pop[01],( pop[02]),pop[3]));
        }
        st.close();
        rs.close();
        //chartLoad();
    }
    
    @FXML
    private void backAction(ActionEvent e) throws IOException
    {
    Parent next = FXMLLoader.load(getClass().getResource("/View/Fxml1.fxml"));
    Scene nextScene = new Scene(next);
    Stage window= (Stage) ((Node) e.getSource()).getScene().getWindow() ;
    window.setScene(nextScene);
    window.show();
    }
    
    @FXML
    private void refreshAction(ActionEvent e)
    {
    id.setText(null);
    name.setText(null);
    amount.setText(null);
    details.setText(null);
    dp.setValue(null);
    data.clear();
    t.refresh();
    }
    
    @FXML
         private void addAction(ActionEvent e)
    {
         try {
            PreparedStatement pre = con.prepareStatement("insert into sales (id,amount,date,details) values(?,?,?,?)");
            pre.setString(1, id.getText());
            pre.setInt(2, (Integer.parseInt(amount.getText())));
            //YYYY-MM-DD
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String formattedValue = (dp.getValue()).format(formatter);
            pre.setString(3,formattedValue);
            pre.setString(4, details.getText());
            pre.execute();
            System.out.println("magoma");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            showAlert(alert,"Operation was Successful","One Person has been added!","Go to table check if it has been added ot not.");
            tableShow();
            amount.setText(null);
            dp.setValue(null);
        }
         catch (Exception ee) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            showAlert(alert,"Error alert","","");
            System.out.println(ee);
          //  showNull();
        }
    }
         
         @FXML
         private void deleteAction(ActionEvent e)
         {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String formattedValue = (dp.getValue()).format(formatter);
         
         try {
            PreparedStatement rp = con.prepareStatement("delete from sales where id='"+id.getText()+"' and amount="+amount.getText()+" and date='"+formattedValue+"'");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Image APPLICATION_ICON = new Image("img/takeabite.jpg");
            Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
            dialogStage.getIcons().add(APPLICATION_ICON);
            alert.setTitle("Delete Info");
            alert.setHeaderText("Are you sure want to delet this membership?");
            alert.setContentText(name.getText());

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                rp.executeUpdate();
            } else if (option.get() == ButtonType.CANCEL) {

            } else {

            }
            rp.close();
            //showNull();
            tableShow();
            amount.setText(null);
            dp.setValue(null);
         }
         catch (Exception ee) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            showAlert(alert,"Error alert","Something went wrong"," ");
            //showNull();
        }
         }
         private void showAlert(Alert alert,String title,String header,String context)
    {
       Image APPLICATION_ICON = new Image("img/takeabite.jpg");
       Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
       dialogStage.getIcons().add(APPLICATION_ICON); 
       alert.setTitle(title);
       alert.setHeaderText(header);
       alert.setContentText(context);
       alert.showAndWait();
    }
}
