/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Medicamentos;
import modelo.AlimentosVet;
import static controlador.ControladorMedicamentos.listMedicamentos;
import static controlador.ControladorAlimento.alimentos;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.Venta;

/**
 * FXML Controller class
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorVistaVender extends ControladorVenta implements Initializable {

    @FXML
    private TableView<Venta> tablaVenta;
    @FXML
    private TableColumn<?, ?> colProducto;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TextField textID;
    @FXML
    private Button btnCancelarVenta;
    @FXML
    private Button btnVenta;
    @FXML
    private Button btnCorteCaja;
    @FXML
    private TextField textCantidad;
    @FXML
    private TableView<Medicamentos> tablaMedicamentos;
    @FXML
    private TableColumn<?, ?> colMedicamentoTipo;
    @FXML
    private TableColumn<?, ?> colMedicamentoNombre;
    @FXML
    private TableColumn<?, ?> colMedicamentoTamano;
    @FXML
    private TableColumn<?, ?> colMedicamentoCantidad;
    @FXML
    private TableColumn<?, ?> colMedicamentoPrecio;
    @FXML
    private TableView<AlimentosVet> tablaAlimento;
    @FXML
    private TableColumn<?, ?> colAlimentoTipo;
    @FXML
    private TableColumn<?, ?> colAlimentoNombre;
    @FXML
    private TableColumn<?, ?> colAlimentoCantidad;
    @FXML
    private TableColumn<?, ?> colAlimentoPrecio;
    @FXML
    private Label importe;

    public ObservableList<Medicamentos> medicamentosVistaVenta;
    public ObservableList<AlimentosVet> alimentosVistaVenta;
    public ObservableList<Venta> ventasVista;
    @FXML
    private Button btnAgregarMedicamentos;
    @FXML
    private Button btnAgregarAlimento;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Poner los pasteles a vista
        medicamentosVistaVenta = FXCollections.observableArrayList(listMedicamentos);

        this.colMedicamentoTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colMedicamentoNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colMedicamentoCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colMedicamentoPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

        medicamentosVistaVenta.setAll(listMedicamentos);
        this.tablaMedicamentos.setItems(medicamentosVistaVenta);

        //Poner los postres a vista
        alimentosVistaVenta = FXCollections.observableArrayList(alimentos);

        this.colAlimentoTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colAlimentoNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colAlimentoCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colAlimentoPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

        alimentosVistaVenta.setAll(alimentos);
        this.tablaAlimento.setItems(alimentosVistaVenta);

        //Lista de ventas
        ventasVista = FXCollections.observableArrayList(ventaTemporal);

        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

    }

    @FXML
    private void seleccionarMedicamentos(MouseEvent event) {
        //Selecciona al cliente
        Producto p = this.tablaMedicamentos.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void seleccionarAlimentos(MouseEvent event) {
        Producto p = this.tablaAlimento.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void deseleccionar(MouseEvent event) {
        Producto p = null;
    }

    @FXML
    private void agregarMedicamentoVenta(ActionEvent event) {
        Medicamentos p = this.tablaMedicamentos.getSelectionModel().getSelectedItem();


        if(p != null){
            if(this.textCantidad.getText() == null || this.textCantidad.getText().trim().isEmpty() || this.textID.getText() == null || this.textID.getText().trim().isEmpty()){//campo Cantidad o ID está vacío
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Es necesario llenar el campo de cantidad");
                alert.showAndWait();
            }else{//cantidad no está vacío
                try{
                    Integer cantidad = Integer.parseInt(this.textCantidad.getText());
                    if(cantidad <= p.getCantidad()){//existen suficientes en inventario
                        String ID = this.textID.getText();

                    }else{//no existen suficientes en inventario
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Inténtelo de nuevo");
                        alert.setContentText("No tiene inventario suficiente de ese producto");
                        alert.showAndWait();
                    }

                }catch(NumberFormatException e){ //Por si ingresamos algo que no sea un número en cantidad o está vacío
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Ingrese un número en cantidad");
                        alert.showAndWait();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Debe seleccionar un medicamento");
            alert.showAndWait();
        }
    }

    @FXML
    private void agregarAlimentoVenta(ActionEvent event) {
        AlimentosVet p = this.tablaAlimento.getSelectionModel().getSelectedItem();

        if(p != null){
            if(this.textCantidad.getText() == null || this.textCantidad.getText().trim().isEmpty() || this.textID.getText() == null || this.textID.getText().trim().isEmpty()){//campo Cantidad o ID está vacío
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Inténtelo de nuevo");
                alert.setContentText("Es necesario llenar el campo de cantidad");
                alert.showAndWait();
            }else{//cantidad no está vacío
                try{
                    Integer cantidad = Integer.parseInt(this.textCantidad.getText());
                    if(cantidad <= p.getCantidad()){//existen suficientes en inventario
                        String ID = this.textID.getText();

                    }else{//no existen suficientes en inventario
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Inténtelo de nuevo");
                        alert.setContentText("No tiene inventario suficiente de ese producto");
                        alert.showAndWait();
                    }

                }catch(NumberFormatException e){ //Por si ingresamos algo que no sea un número en cantidad o está vacío
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText("Ingrese un número en cantidad");
                        alert.showAndWait();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Debe seleccionar un alimento");
            alert.showAndWait();
        }

    }

    @FXML
    private void seleccionarProductoVenta(MouseEvent event) {
        Venta v = this.tablaVenta.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void eliminarProductoVenta(ActionEvent event) {
        Venta v = this.tablaVenta.getSelectionModel().getSelectedItem();
        if(v == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Debe seleccionar un producto de la lista de venta");
            alert.showAndWait();
        }else{
            eliminarVentaTemporal(v);
            ventasVista.clear();
            ventasVista.setAll(ventaTemporal);
            this.tablaVenta.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Producto eliminado de la venta");
            alert.showAndWait();
        }
    }

    @FXML
    private void realizarVenta(ActionEvent event) {
        ControladorMedicamentos pastel = new ControladorMedicamentos();
        ControladorAlimento postre = new ControladorAlimento();

        String ID = this.textID.getText();
        if(this.textID.getText() == null || this.textID.getText().trim().isEmpty()){//ID está vacío
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Inténtelo de nuevo");
            alert.setContentText("Es necesario llenar el campo de ID del cliente");
            alert.showAndWait();
        }else{//ID no está vacío
            //Comienza la venta y todo lo que implica
            Double importe = 0.;
            Integer newCantidad;

            //Para cada producto que se este comprando:
            for(int i=0;i<ventaTemporal.size();i++){
                importe = importe + (ventaTemporal.get(i).getCantidad() * ventaTemporal.get(i).getPrecio());//el importe total

                //Actualizar las cantidades de los productos en el inventario después de la venta
                if(pastel.existe(ventaTemporal.get(i).getIdProducto())){//si es Pastel
                    newCantidad = listMedicamentos.get(pastel.buscar(ventaTemporal.get(i).getIdProducto())).getCantidad() - ventaTemporal.get(i).getCantidad();
                    listMedicamentos.get(pastel.buscar(ventaTemporal.get(i).getIdProducto())).setCantidad(newCantidad);
                }else if(postre.existe(ventaTemporal.get(i).getIdProducto())){//si es Postre y Delicia
                    newCantidad = alimentos.get(postre.buscar(ventaTemporal.get(i).getIdProducto())).getCantidad() - ventaTemporal.get(i).getCantidad();
                    alimentos.get(postre.buscar(ventaTemporal.get(i).getIdProducto())).setCantidad(newCantidad);
                }
            }

            //Pasa lo temporal a las ventas
            for(int i=0;i<ventaTemporal.size();i++){
                String cliente = ventaTemporal.get(i).getCliente();
                String idProducto = ventaTemporal.get(i).getIdProducto();
                String producto = ventaTemporal.get(i).getProducto();
                Integer cantidad = ventaTemporal.get(i).getCantidad();
                Double precio = ventaTemporal.get(i).getPrecio();
                agregarVenta(cliente, idProducto, producto, cantidad, precio);
            }

            //Quita todo en la vista y limpiar
            ventaTemporal.clear();
            ventasVista.clear();
            this.tablaVenta.refresh();
            this.textID.clear();
            this.textCantidad.clear();


            //Actualizar las listas de producto
            medicamentosVistaVenta.clear();
            medicamentosVistaVenta.setAll(listMedicamentos);
            this.tablaMedicamentos.setItems(medicamentosVistaVenta);
            alimentosVistaVenta.clear();
            alimentosVistaVenta.setAll(alimentos);
            this.tablaAlimento.setItems(alimentosVistaVenta);
        }
    }

    @FXML
    private void cancelarVenta(ActionEvent event) {
        ventaTemporal.clear();
        ventasVista.clear();
        this.tablaVenta.setItems(ventasVista);
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
