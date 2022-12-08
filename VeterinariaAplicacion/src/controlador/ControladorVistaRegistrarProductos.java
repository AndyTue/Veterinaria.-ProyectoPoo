/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorVistaRegistrarProductos {
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnRegistrarMedicamentos;
    @FXML
    private Button btnRegistrarAlimentos;
    
    public void initialize(URL url, ResourceBundle rb) {

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
    
    @FXML
    private void irARegistrarMedicamentos(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/RegistrarMedicamentos.fxml"));
            
            ControladorVistaRegistrarMedicamentos controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setTitle("Medicamentos");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnRegistrarMedicamentos.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void irARegistrarAlimento(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/vista/RegistrarAlimento.fxml"));
            
            ControladorVistaRegistrarAlimentos controlador = loader.getController();
            
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage = new Stage();
            
            stage.setTitle("Postres y Delicias");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnRegistrarAlimentos.getScene().getWindow();
            myStage.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
