package controlador;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorMedicamentosTest {

    @Test
    public void buscar() {
        System.out.println("Buscar Medicamento");
        String ID= "21216893";
        ControladorMedicamentos instance = new ControladorMedicamentos();
        int expResult= -1;
        int result = instance.buscar(ID);
        assertEquals(expResult, result);
    }
}