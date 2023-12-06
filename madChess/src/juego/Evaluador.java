package juego;

import java.util.ArrayList;

import objetos.Casilla;
import objetos.Pieza;
import piezas.Peon;
import piezas.Rey;

public class Evaluador {

    public static int evaluarTablero(ArrayList<Casilla> estadoActual) {
        int evaluacionTotal = 0;

        // Importancia de cada factor
        int pesoEstructuraPeones = 1;
        int pesoSeguridadRey = 2;
        int pesoControlCentro = 3;

        evaluacionTotal += pesoEstructuraPeones * evaluarEstructuraPeones(estadoActual);
        evaluacionTotal += pesoSeguridadRey * evaluarSeguridadRey(estadoActual);
        evaluacionTotal += pesoControlCentro * evaluarControlCentro(estadoActual);

        return evaluacionTotal;
    }

    private static int evaluarEstructuraPeones(ArrayList<Casilla> estadoActual) {
        int valorEstructuraPeones = 0;

        for (Casilla casilla : estadoActual) {
            Pieza pieza = casilla.getPieza();
            if (pieza != null && pieza.getIsWhite() && pieza instanceof Peon) {
                // Aumentar la puntuación si los peones blancos están en filas avanzadas
                valorEstructuraPeones += casilla.getFila() - 1;
            } else if (pieza != null && !pieza.getIsWhite() && pieza instanceof Peon) {
                // Aumentar la puntuación si los peones negros están en filas avanzadas
                valorEstructuraPeones += 7 - casilla.getFila();
            }
        }

        return valorEstructuraPeones;
    }


    private static int evaluarSeguridadRey(ArrayList<Casilla> estadoActual) {
        int valorSeguridadRey = 0;

        for (Casilla casilla : estadoActual) {
            Pieza pieza = casilla.getPieza();
            if (pieza != null && pieza.getIsWhite() && pieza instanceof Rey) {
                // Aumentar la puntuación si el rey blanco está en una posición segura
                valorSeguridadRey += evaluarSeguridadReyEnCasilla((Rey) pieza, casilla,estadoActual);
            } 
        }
        return valorSeguridadRey;

    }

    private static int evaluarSeguridadReyEnCasilla(Rey rey, Casilla casillaRey, ArrayList<Casilla> estadoActual) {
        // Utiliza tu función existente para verificar si el rey está amenazado
        boolean isAmenazado = rey.reCheckJaqueStatus(casillaRey, estadoActual);
        
        if (isAmenazado && !rey.getIsWhite()) {
            // Si el rey está amenazado y es del bot
            return -50;
        } else if (isAmenazado && rey.getIsWhite()) {
            // Si el rey está amenazado y es del jugador
            return 50;
        } else {
            // Ninguno de los reyes está amenazado
            return 0;
        }
    }

    private static int evaluarControlCentro(ArrayList<Casilla> estadoActual) {
        int valorControlCentro = 0;

        for (Casilla casilla : estadoActual) {
            // Aumentar la puntuación si la casilla está en el centro del tablero
            if (casilla.getFila() >= 3 && casilla.getFila() <= 5 &&
                casilla.getColumna() >= 3 && casilla.getColumna() <= 5) {
                valorControlCentro += 1;
            }
        }

        return valorControlCentro;
    }
}
