/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class Medicamentos extends Producto {

    /**
 * Constructor por defecto de la clase Medicamentos
 */  
    public Medicamentos(String ID, String tipoAnimal, String nombre, Double precio, Integer cantidad){
        super(ID, tipoAnimal, nombre, precio,cantidad);
    }

      /**
 * Constructor de la clase Medicamentos
 * @param ID
 * @param tipo
 * @param nombre
 * @param precio
 * @param cantidad

 */  
    public Medicamentos(String ID, String tipo, String nombre, Double precio, Integer cantidad, String sabor){
        super(ID, tipo, nombre, precio, cantidad);
    }

    /**
 * Método que muestra la información de un objeto de la clase Medicamentos
 * @return String con los valores de todos los atributos de un objeto de la clase Medicamentos
 */
    @Override
    public String toString() {
        return "ID=" + ID + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad;
    }
}
