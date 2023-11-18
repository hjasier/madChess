package juego;

import objetos.Jugador;

public class Session {
    private static Jugador currentUser;

    public static void setCurrentUser(Jugador user) {
        currentUser = user;
    }

    public static Jugador getCurrentUser() {
        return currentUser;
    }
}
