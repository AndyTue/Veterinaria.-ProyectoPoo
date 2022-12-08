/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import modelo.AlimentosVet;

/**
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorAlimento {
     public static ArrayList<AlimentosVet> alimentos = new ArrayList<>();
    
    public int buscar(String ID){
        for(int i = 0; i< alimentos.size(); i++){
            if(ID.equals(alimentos.get(i).getID())){
                return i;
            }
        }
        return -1;
    }
    
    public boolean existe(String ID){
        if(buscar(ID) != -1){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * @param ID 
     * @param tipo
     * @param nombre
     * @param precio
     * @param cantidad
      */
    public void agregar(String ID, String tipo, String nombre, Double precio, Integer cantidad){
        try{
            if(!existe(ID)){
                alimentos.add(new AlimentosVet(ID, tipo, nombre, precio, cantidad));
            }
        }catch(NumberFormatException e){
            System.out.println("Datos ingresados en el campo 'ID' deben ser números.");
        }
    }
    
    public void eliminar(String ID){
        if(existe(ID)){
            alimentos.remove(alimentos.get(buscar(ID)));
        }
    }
}