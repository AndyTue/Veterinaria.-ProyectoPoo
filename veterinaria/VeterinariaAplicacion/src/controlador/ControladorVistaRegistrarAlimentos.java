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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.AlimentosVet;

/**
 * FXML Controller class
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorVistaRegistrarAlimentos extends ControladorAlimento implements Initializable {

//    @FXML
//    private ComboBox CmTamano;
    @FXML 
    private TableView<AlimentosVet> tbPAlimentos;
    @FXML
    private TableColumn<?, ?> colIDP;
    @FXML
    private TableColumn<?, ?> colTipoP;
    @FXML
    private TableColumn<?, ?> colNombreP;
    @FXML
    private TableColumn<?, ?> colPrecioP;
    @FXML
    private TableColumn<?, ?> colCantidadP;
    @FXML
    private Button btnAgregarPos;
    @FXML
    private Button btnEditarPos;
    @FXML
    private Button btnEliminarPos;
    @FXML
    private Label lbIDp;
    @FXML 
    private TextField txtIDp;
    @FXML
    private Label lbTipop;
    @FXML 
    private TextField txtTipop;
    @FXML
    private Label lbNombrep;
    @FXML 
    private TextField txtNombrep;
    @FXML
    private Label lbPreciop;
    @FXML 
    private TextField txtPreciop;
    @FXML
    private Label lbCantidadp;
    @FXML 
    private Spinner <Integer> SpCantidadP;
    @FXML
    private Label lbInventariop;
    @FXML
    private Label lbTotalp;
    @FXML
    private Button btnMenu;
    
    protected ObservableList<AlimentosVet> Lalimentos = FXCollections.observableArrayList(alimentos);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        ObservableList<String> tamano = FXCollections.observableArrayList("Chico","Mediano","Grande");
//        CmTamano.setItems(tamano);

       this.colIDP.setCellValueFactory(new PropertyValueFactory("ID"));
       this.colTipoP.setCellValueFactory(new PropertyValueFactory("tipo"));
       this.colNombreP.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colPrecioP.setCellValueFactory(new PropertyValueFactory("precio"));
       this.colCantidadP.setCellValueFactory(new PropertyValueFactory("cantidad"));
       
      SpinnerValueFactory<Integer> numeros = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
       numeros.setValue(1);
       SpCantidadP.setValueFactory(numeros);
       
       Lalimentos.setAll(alimentos);
       this.tbPAlimentos.setItems(Lalimentos);
    }    
    
    @FXML
    private void Agregar(ActionEvent event) throws NumberFormatException {
        
            //Por si algún campo de texto está vacio
            if(this.txtPreciop.getText() == null || this.txtPreciop.getText().trim().isEmpty() || this.txtIDp.getText() == null || this.txtIDp.getText().trim().isEmpty()|| this.txtTipop.getText() == null || this.txtTipop.getText().trim().isEmpty()  || this.txtNombrep.getText() == null || this.txtNombrep.getText().trim().isEmpty()){
                System.out.println("Esto si es de looooooooooooocos");
                
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("No puede haber campos vacíos");
                alert.showAndWait();
            }else{
                try{
                //Lectura de los campos de texto
                
                Integer auxID =Integer.parseInt(this.txtIDp.getText());
                String IDP = String.valueOf(auxID);
                String tipoP = this.txtTipop.getText();
                String nombreP = this.txtNombrep.getText();
                Double precioP = Double.parseDouble(this.txtPreciop.getText());
                Integer cantidadP =SpCantidadP.getValue();                
                
                if(!existe(IDP)){//Si el cliente no existe, lo busca por ID
                    agregar(IDP, tipoP, nombreP, precioP, cantidadP); //Agrega a la lista
                    double inventario1 = 0;
                    double productos1 =0;
                    for (int i = 0; i < alimentos.size(); i++) {
                        productos1 = alimentos.get(i).getPrecio() * alimentos.get(i).getCantidad() ;
                        inventario1 = inventario1 + productos1;
                    }
                    
                    
                    Lalimentos.clear();
                    Lalimentos.setAll(alimentos);
                    this.tbPAlimentos.setItems(Lalimentos); //Los pone en la tabla
                    //Limpieza de los campos de textos
                    txtIDp.clear();
                    txtTipop.clear();
                    txtNombrep.clear();
                    txtPreciop.clear();
                    lbTotalp.setText("");
                    lbTotalp.setText("  "+inventario1);
                    
                    //Mensaje de agregado
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Información");
                    alert.setContentText("El producto ha sido agregado");
                    alert.showAndWait();
                }else{ //Si el cliente ya existe no se crea otra con la misma información
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("El Producto ya existe");
                    alert.showAndWait();
                }
                }catch(NumberFormatException  e){
                    e.getMessage();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("Verifique campos  ID y PRECIO ,solo acepta numeros enteros");
                    alert.showAndWait();
                }
                
            }
    }
    
    
    @FXML
    private void seleccionarP(MouseEvent event) {
        //Selecciona al cliente
        AlimentosVet c = this.tbPAlimentos.getSelectionModel().getSelectedItem();
        
        if(c != null){//Si ya se ha seleccionado algo
            //Pone en los campos de texto
            this.txtIDp.setText(c.getID());
            this.txtIDp.setEditable(false);//El campo ID no es editable
            this.txtTipop.setText(c.getTipo());
            this.txtNombrep.setText(c.getNombre());           
            this.txtPreciop.setText(String.valueOf(c.getPrecio()));//se cambia , de numerido  a String
           this.SpCantidadP.getValueFactory().setValue(c.getCantidad());
           
        }
    }
    
    
    @FXML
    private void modificarP(ActionEvent event) throws NumberFormatException{
        //Selección de la persona
        AlimentosVet c = this.tbPAlimentos.getSelectionModel().getSelectedItem();
        
        if(c == null){ //Si no ha seleccionado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Debe seleccionar a un Producto");
            alert.showAndWait();
        }else{
            try{
            //Cuando ya se seleccionó a un cliente     
               Integer auxID =Integer.parseInt(this.txtIDp.getText());
                String IDP = String.valueOf(auxID);
                String tipoP = this.txtTipop.getText();
                String nombreP = this.txtNombrep.getText();
                Double precioP = Double.parseDouble(this.txtPreciop.getText());
                Integer cantidadP =SpCantidadP.getValue();  
                
                
                //Por si algún campo está vacío
                if(this.txtPreciop.getText() == null || this.txtPreciop.getText().trim().isEmpty() || this.txtIDp.getText() == null || this.txtIDp.getText().trim().isEmpty()|| this.txtTipop.getText() == null || this.txtTipop.getText().trim().isEmpty()  || this.txtNombrep.getText() == null || this.txtNombrep.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("No puede haber campos vacíos");
                    alert.showAndWait();
                }else{ //Los campos no están vacíos
                    if(!existe(IDP)){
                        agregar(IDP, tipoP, nombreP, precioP, cantidadP); //Agrega a la lista
                        
                        
                        
                    double inventario = 0;
                    double productos =0;
                    for (int i = 0; i < alimentos.size(); i++) {
                        productos = alimentos.get(i).getPrecio() * alimentos.get(i).getCantidad() ;
                        inventario = inventario + productos;
                    }
                        //Limpieza de los campos de textos
                        txtIDp.clear();
                        txtTipop.clear();
                        txtNombrep.clear();
                        txtPreciop.clear();
                        lbTotalp.setText("");
                        lbTotalp.setText("  "+inventario);

                        Lalimentos.clear();
                        Lalimentos.setAll(alimentos);
                        this.tbPAlimentos.setItems(Lalimentos); //Los pone en la tabla
                        this.tbPAlimentos.refresh();
                         //Refresco de la tabla
                         //textID.setEditable(false); //Regresa a editable el ID

                         //Mensaje de agregado
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("Producto agregado");
                        alert.showAndWait();
                        
                    }else{
                        //Modificación de la persona
                        alimentos.get(buscar (IDP)).setTipo(tipoP);
                        alimentos.get(buscar (IDP)).setNombre(nombreP);
                        alimentos.get(buscar(IDP)).setPrecio(precioP);
                        alimentos.get(buscar(IDP)).setCantidad(cantidadP);
                        
                       
                    double inventario2 = 0;
                    double productos2 =0;
                    for (int i = 0; i < alimentos.size(); i++) {
                        productos2 = alimentos.get(i).getPrecio() * alimentos.get(i).getCantidad() ;
                        inventario2 = inventario2 + productos2;
                    }
                        //Limpieza de los campos de textos
                        txtIDp.clear();
                        txtTipop.clear();
                        txtNombrep.clear();
                        txtPreciop.clear();
                        lbTotalp.setText("");
                        lbTotalp.setText("  "+inventario2);

                        Lalimentos.clear();
                        Lalimentos.setAll(alimentos);
                        this.tbPAlimentos.setItems(Lalimentos); //Los pone en la tabla
                        this.tbPAlimentos.refresh();
                        txtIDp.setEditable(true); //Regresa a editable el ID
                        
                        //Mensaje de editado
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("El Producto Delicia se ha modificado correctamente");
                        alert.showAndWait();
                    }
                }
                
            }catch(NumberFormatException e){
                    e.getMessage();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Inténtelo de nuevo");
                    alert.setContentText("Verifique campos  ID y PRECIO ,solo acepta numeros enteros");
                    alert.showAndWait();
            }
            
        }
    }
    
    @FXML
    private void eliminarP(ActionEvent event) {
        AlimentosVet c = this.tbPAlimentos.getSelectionModel().getSelectedItem();
        
        if(c == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Debe seleccionar a un Producto");
            alert.showAndWait();
        }else{
                        eliminar(c.getID());
                        Lalimentos.clear();
                        Lalimentos.setAll(alimentos);
                        this.tbPAlimentos.setItems(Lalimentos); //Los pone en la tabla
                        this.tbPAlimentos.refresh();
                        txtIDp.setEditable(true); //Regresa a editable el ID
                        
            
                    double inventario2 = 0;
                    double productos2 =0;
                    for (int i = 0; i < alimentos.size(); i++) {
                        productos2 = alimentos.get(i).getPrecio() * alimentos.get(i).getCantidad() ;
                        inventario2 = inventario2 + productos2;
                    }
                    //Limpieza de los campos de textos
                        txtIDp.clear();
                        txtTipop.clear();
                        txtNombrep.clear();
                        txtPreciop.clear();
                        lbTotalp.setText("");
                        lbTotalp.setText("  "+inventario2);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Información");
                        alert.setContentText("Producto eliminado");
                        alert.showAndWait();
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