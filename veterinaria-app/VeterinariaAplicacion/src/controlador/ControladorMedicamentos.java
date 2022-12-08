/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.ArrayList;

import modelo.Medicamentos;

/**
 *
 * @author Andrés de Jesús Turriza Euan
 * @author Abraham Raymundo Cruz Colli
 * @author Luis Javier Quintana OLivera
 */
public class ControladorMedicamentos {
    public static ArrayList<Medicamentos> listMedicamentos = new ArrayList<>();

    public int buscar(String ID){
        for(int i = 0; i< listMedicamentos.size(); i++){
            if(ID.equals(listMedicamentos.get(i).getID())){
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
                listMedicamentos.add(new Medicamentos(ID, tipo, nombre, precio, cantidad));
            }
        }catch(NumberFormatException e){
            System.out.println("Datos ingresados en el campo 'ID' deben ser números.");
        }
    }

    public void eliminar(String ID){
        if(existe(ID)){
            listMedicamentos.remove(listMedicamentos.get(buscar(ID)));
        }
    }
}
