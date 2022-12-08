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
public class AlimentosVet extends Producto{

    /**
 * Constructor por defecto
 */  
    public AlimentosVet(){}

    /**
 * Constructor
 * @param ID
 * @param tipo
 * @param nombre
 * @param precio
 * @param cantidad
 */ 
    public AlimentosVet(String ID, String tipo, String nombre, Double precio, Integer cantidad){
        super(ID, tipo, nombre, precio, cantidad);
    }

    @Override
    public String toString() {
        return "ID=" + ID + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad ;
    }
}