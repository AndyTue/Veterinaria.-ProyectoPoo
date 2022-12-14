/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Administrador;

/**
 * FXML Controller class
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorVistaRegistrarAdministrador extends ControladorAdministrador implements Initializable {

    @FXML
    private TableView<Administrador> tablaAdministradores;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellido;
    @FXML
    private TableColumn<?, ?> colPassword;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Label Apellido;
    @FXML
    private TextField textApellido;
    @FXML
    private Label Nombre;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textID;
    @FXML
    private Label ID;
    @FXML
    private Label Password;
    @FXML
    private PasswordField textPassword;
    @FXML
    private Button btnMenu;
    
    protected ObservableList<Administrador> administradoresVista = FXCollections.observableArrayList(administradores);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        this.colID.setCellValueFactory(new PropertyValueFactory("ID"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colPassword.setCellValueFactory(new PropertyValueFactory("password"));
        
        administradoresVista.setAll(administradores);
        this.tablaAdministradores.setItems(administradoresVista);
    }    

    @FXML
    private void seleccionar(MouseEvent event) {
        //Selecciona al administrador
        Administrador a = this.tablaAdministradores.getSelectionModel().getSelectedItem();
        
        if(a != null){//Si ya se ha seleccionado algo
            //Pone en los campos de texto
            this.textID.setText(a.getID());
            this.textID.setEditable(false);//El campo ID no es editable
            this.textNombre.setText(a.getNombre());
            this.textApellido.setText(a.getApellido());
            this.textPassword.setText(a.getPassword());
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Administrador a = this.tablaAdministradores.getSelectionModel().getSelectedItem();
        
        if(a == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Debe seleccionar a un administrador");
            alert.showAndWait();
        }else{
            eliminar(a.getID());
            administradoresVista.clear();
            administradoresVista.setAll(administradores);
            this.tablaAdministradores.refresh();
            
            textID.clear();
            textNombre.clear();
            textApellido.clear();
            textPassword.clear();        
                   
            textID.setEditable(true);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Administrador eliminado");
            alert.showAndWait();
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        //Selección de la persona
        Administrador a = this.tablaAdministradores.getSelectionModel().getSelectedItem();
        
        if(a == null){ //Si no ha seleccionado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Debe seleccionar a un administrador");
            alert.showAndWait();
        }else{
            //Cuando ya se seleccionó a un administrador     
                String ID = this.textID.getText();
                String nombre = this.textNombre.getText();
                String apellidos = this.textApellido.getText();
                String password = this.textPassword.getText();
                
                
                //Por si algún campo está vacío
                if(this.textNombre.getText() == null || this.textNombre.getText().trim().isEmpty() || this.textApellido.getText() == null || this.textApellido.getText().trim().isEmpty()|| this.textPassword.getText() == null || this.textPassword.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("No puede haber campos vacíos");
                    alert.showAndWait();
                }else{ //Los campos no están vacíos
                    if(!existe(ID)){
                        agregar(ID, nombre, apellidos, password); //Agrega a la lista
                        
                        //Limpieza de los campos
                        textID.clear();
                        textNombre.clear();
                        textApellido.clear();
                        textPassword.clear();
                       
                        administradoresVista.clear();
                        administradoresVista.setAll(administradores);
                        this.tablaAdministradores.refresh(); //Refresco de la tabla
                        textID.setEditable(true); //Regresa a editable el ID
                        
                         //Mensaje de agregado
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("Administrador agregado");
                        alert.showAndWait();
                        
                    }else{
                        //Modificación de la persona
                        administradores.get(buscar(ID)).setNombre(nombre);
                        administradores.get(buscar(ID)).setApellido(apellidos);
                        administradores.get(buscar(ID)).setApellido(apellidos);
                        administradores.get(buscar(ID)).setPassword(password);
                    
                        //Limpieza de los campos
                        textID.clear();
                        textNombre.clear();
                        textApellido.clear();
                        textPassword.clear();
                        
                        administradoresVista.clear();
                        administradoresVista.setAll(administradores);
                        this.tablaAdministradores.refresh(); //Refresco de la tabla
                        textID.setEditable(true); //Regresa a editable el ID
                        
                        //Mensaje de editado
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("Administrador modificado");
                        alert.showAndWait();
                    }
                }
                       
        }
    }

    @FXML
    private void agregar(ActionEvent event) {
        //Lectura de los campos de texto
            String ID = this.textID.getText();
            String nombre = this.textNombre.getText();
            String apellidos = this.textApellido.getText();
            String password = this.textPassword.getText();
            
            //Por si algún campo de texto está vacio
            if(this.textNombre.getText() == null || this.textNombre.getText().trim().isEmpty() || this.textApellido.getText() == null || this.textApellido.getText().trim().isEmpty() || this.textID.getText() == null || this.textID.getText().trim().isEmpty()|| this.textPassword.getText() == null || this.textPassword.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("No puede haber campos vacíos");
                alert.showAndWait();
            }else{

                if(!existe(ID)){//Si el administrador no existe, lo busca por ID
                    agregar(ID, nombre, apellidos, password); //Agrega a la lista
                    
                    administradoresVista.clear();
                    administradoresVista.setAll(administradores);
                    this.tablaAdministradores.setItems(administradoresVista); //Los pone en la tabla
                    //Limpieza de los campos de textos
                    textID.clear();
                    textNombre.clear();
                    textApellido.clear();
                    textPassword.clear();
                  
                    
                    //Mensaje de agregado
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Información");
                    alert.setContentText("Administrador agregado");
                    alert.showAndWait();
                }else{ //Si el administrador ya existe no se crea otra con la misma información
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El administrador ya existe");
                    alert.showAndWait();
                }
            }
    
    }
    
    public void cerrarVentana() {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/VistaMenu.fxml"));
            
            ControladorVistaMenu controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.btnMenu.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}