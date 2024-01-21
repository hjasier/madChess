package junitTest;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.GestorDB;
import juego.DatosPartida;
import juego.modoJuego;
import juego.partidaTipo;
import objetos.Jugador;
import objetos.Movimiento;
import objetos.Usuario;
import utils.Session;

public class GestorDBTest {



    @Test
    public void testIniciarSesion() {
        assertTrue(GestorDB.iniciarSesion("Giova", "test"));
    }

    @Test
    public void testInsertarYEliminarUsuario() {
        String result = GestorDB.insertarUsuario("Ramon", "soyRamon");
        assertEquals("Usuario insertado correctamente.", result);

        assertTrue(GestorDB.existeUsuario("Ramon"));

        GestorDB.eliminarUsuario("Ramon");
        assertFalse(GestorDB.existeUsuario("Ramon"));
    }
    
    @Test
    public void testInsertarYEliminarPartida() {
        DatosPartida datosPartida = new DatosPartida(modoJuego.LOCAL);
        datosPartida.setTipoPartida(partidaTipo.CLASICA);
        datosPartida.setGameId("AAAAA");
 

        GestorDB.insertarPartida(datosPartida);
        assertTrue(GestorDB.existePartida("AAAAA"));

        GestorDB.eliminarPartida("AAAAA");
        assertFalse(GestorDB.existePartida("AAAAA"));
        
    }
    

}

