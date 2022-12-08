package controlador;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorAdministradorTest {

    @Test
    public void buscar() {
        System.out.println("Buscar Administrador");
        String ID= "21216893";
        ControladorAdministrador instance = new ControladorAdministrador();
        int expResult=-1;
        int result =instance.buscar(ID);
        assertEquals(expResult, result);
    }
}