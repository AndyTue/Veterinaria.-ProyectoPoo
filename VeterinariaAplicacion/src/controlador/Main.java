/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.ControladorAdministrador.administradores;
import static controlador.ControladorEmpleado.empleados;
import static controlador.ControladorMedicamentos.listMedicamentos;
import static controlador.ControladorAlimento.alimentos;
import static controlador.ControladorVenta.ventaTemporal;
import static controlador.ControladorVenta.ventas;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class Main extends Application{
    

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();

           loader.setLocation(Main.class.getResource("/vista/VistaMenu.fxml"));

            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setTitle("Tabla");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void stop(){
        System.out.println(administradores);
        System.out.println(empleados);
        System.out.println(listMedicamentos);
        System.out.println(alimentos);
        System.out.println(ventas);
        System.out.println(ventaTemporal);

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}