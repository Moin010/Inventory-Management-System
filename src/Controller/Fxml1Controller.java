
package Controller;

import Model.table;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Fxml1Controller implements Initializable {

    public static Connection con;
    @FXML
    private TableView<table> t;
    @FXML
    private TableColumn<table, String> a;
    @FXML
    private TableColumn<table, String> b;
    @FXML
    private TableColumn<table, String> c;

    @FXML
    private Button refresh;
    @FXML
    private Button exit;
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button sales;
    

    @FXML
    private TextField name;
    @FXML
    private TextField ID;
    @FXML
    private TextArea address;
    @FXML
    private TextField phone;
    @FXML
    private TextField search;

    private final ObservableList<table> data = FXCollections.observableArrayList();

    private void startup() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from bite order by name asc");
        String[] pop = new String[4];
        while (rs.next()) {
            pop[1 - 1] = rs.getString("ID");
            pop[2 - 1] = (rs.getString("name"));
            pop[3 - 1] = (rs.getString("address"));
            pop[4 - 1] = (rs.getString("phone"));
            data.add(new table(pop[0], pop[01], pop[02], pop[03]));
        }
        st.close();
        rs.close();
    }
    
    private void showNull() {
        name.setText(null);
        ID.setText(null);
        address.setText(null);
        phone.setText(null);
    }

    private void tableShow() {
        
        try {
            data.clear();
            t.refresh();
            startup();
            a.setCellValueFactory(new PropertyValueFactory<table, String>("ID"));
            b.setCellValueFactory(new PropertyValueFactory<table, String>("name"));
            c.setCellValueFactory(new PropertyValueFactory<table, String>("phone"));
            t.setItems(data);
            t.setRowFactory(tv -> {
                TableRow<table> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        table rowData = row.getItem();
                        name.setText(rowData.getName());
                        ID.setText(rowData.getID());
                        address.setText(rowData.getAddress());
                        phone.setText(rowData.getPhone());
                    }
                });
                return row;
            });
        } catch (SQLException ex) {
            Logger.getLogger(Fxml1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createConnection();
        tableShow();
        address.setWrapText(true);
        
    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/takeabite?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void nextScene(ActionEvent e) throws IOException
    {
    Parent next = FXMLLoader.load(getClass().getResource("/View/salesHistory.fxml"));
    Scene nextScene = new Scene(next);
    Stage window= (Stage) ((Node) e.getSource()).getScene().getWindow() ;
    window.setScene(nextScene);
    window.show();
    }
    
    
    
    
    @FXML
    private void refreshAction(ActionEvent e)
    {
    tableShow();
    search.setText(null);
    showNull();
    }
    @FXML
    private void BackAction(ActionEvent e) throws IOException {
        Parent next = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
        Scene nextScene = new Scene(next);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @FXML
    private void ExitAction(ActionEvent event) {
        Platform.exit();
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
    
    @FXML
    private void updateAction(ActionEvent event) {
        try {
            PreparedStatement pst = con.prepareStatement("update bite set name = ?, address=?,phone=? where ID=? ");
            pst.setString(1, name.getText());
            pst.setString(2, address.getText());
            pst.setString(3, phone.getText());
            pst.setString(4, ID.getText());
            pst.execute();
            pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            showAlert(alert,"Operation was Successful","Info Has been Updated","Go to table check if it has been Updated ot not.");
            showNull();
            tableShow();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            showAlert(alert,"Error alert","Somethiing went wrong!","");
            showNull();
        }
    }

    @FXML
    private void saveAction(ActionEvent event) {

        try {
            PreparedStatement pre = con.prepareStatement("insert into bite (name,id,address,phone) values(?,?,?,?)");
            pre.setString(1, (name.getText()));
            pre.setString(2, (ID.getText()));
            pre.setString(3, (address.getText()));
            pre.setString(4, (phone.getText()));
            pre.execute();
            System.out.println("magoma");
            Alert alert = new Alert(AlertType.INFORMATION);
            showAlert(alert,"Operation was Successful","One Person has been added!","Go to table check if it has been added ot not.");
            showNull();
            tableShow();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            showAlert(alert,"Error alert","Somethiing went wrong!","");
            showNull();
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        try {
            PreparedStatement rp = con.prepareStatement("delete from bite where ID=?");
            rp.setString(1, ID.getText());
            Alert alert = new Alert(AlertType.CONFIRMATION);
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
            showNull();
            tableShow();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            showAlert(alert,"Error alert","Something went wrong"," ");
            showNull();
        }
    }
    
    @FXML
    private void searchAction() throws SQLException
    {
    Statement st= con.createStatement();
    ResultSet rs= st.executeQuery("select * from bite where id='"+search.getText()+"'");
    boolean adi=true;
    String pop [] = new String[4];
    data.clear();
    t.refresh();
    while (rs.next()) {
            pop[1 - 1] = rs.getString("ID");
            pop[2 - 1] = (rs.getString("name"));
            pop[3 - 1] = (rs.getString("address"));
            pop[4 - 1] = (rs.getString("phone"));
            data.add(new table(pop[0], pop[01], pop[02], pop[03]));
            adi=false;
        }
    if(adi)
    {
     rs= st.executeQuery("select * from bite where name='"+search.getText()+"'");
      pop = new String[4];
      while (rs.next()) {
            pop[1 - 1] = rs.getString("ID");
            pop[2 - 1] = (rs.getString("name"));
            pop[3 - 1] = (rs.getString("address"));
            pop[4 - 1] = (rs.getString("phone"));
            data.add(new table(pop[0], pop[01], pop[02], pop[03]));
            adi=false;
        }
    }
    if(adi)
    {
     rs= st.executeQuery("select * from bite where phone='"+search.getText()+"'");
      pop = new String[4];
      while (rs.next()) {
            pop[1 - 1] = rs.getString("ID");
            pop[2 - 1] = (rs.getString("name"));
            pop[3 - 1] = (rs.getString("address"));
            pop[4 - 1] = (rs.getString("phone"));
            data.add(new table(pop[0], pop[01], pop[02], pop[03]));
            adi=false;
        }
    }
        st.close();
        rs.close();
        t.setItems(data);
        search.setText(null);
    }
}
