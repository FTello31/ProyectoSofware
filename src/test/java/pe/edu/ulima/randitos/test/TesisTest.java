/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.ulima.randitos.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pe.edu.ulima.randitos.blogapp.ConexionMongo;

/**
 *
 * @author Administrator
 */
public class TesisTest {
    
    public TesisTest() {
    }
    
    /*
    @Test
    public void testFecha(){
        ConexionMongo gestor = new ConexionMongo();
        assertEquals("fecha errada", "24/2/2017",gestor.obtenerFecha());
    }
    */
    
    @Test   
    public void testLenght(){
        ConexionMongo gestor = new ConexionMongo();
        gestor.obtenerFeedback().size();
        assertEquals("Cantidad de registros incorrectos", 0,gestor.obtenerTemas().size());
    }
    
    
    
    
}
