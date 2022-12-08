package controlador;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorAlimentoTest {

    @Test
    public void buscar() {
        System.out.println("Buscar Alimento");
        String ID= "21216893";
        ControladorAlimento instance = new ControladorAlimento();
        int expResult = -1;
        int result = instance.buscar(ID);
        assertEquals(expResult, result);
    }


}