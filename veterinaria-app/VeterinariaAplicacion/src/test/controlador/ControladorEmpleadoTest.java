package controlador;

import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorEmpleadoTest {

    @Test
    public void buscar() {
        System.out.println("Buscar Empleado");
        String ID= "21216893";
        ControladorEmpleado instance = new ControladorEmpleado();
        int expResult= -1;
        int result= instance.buscar(ID);
        assertEquals(expResult, result);
    }


    @Test
    public void existe() {
        System.out.println("Existe Empleado");
        String ID= "21216893";
        ControladorEmpleado instance= new ControladorEmpleado();
        boolean expResult= false;
        boolean result= instance.existe(ID);
        assertEquals(expResult, result);
    }
}