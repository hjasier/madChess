
![Git Banner](https://media.discordapp.net/attachments/1158758866297815202/1173696242715476039/macChessLogo.png?ex=6564e4ce&is=65526fce&hm=8e19e36dce15b75b7395e9e97b9c56842c18bfa89ad41e28b01fe65bce93d202&=&width=976&height=246)

Información relevante para la ejecución:





#### 1- Desactivar modo DEBUG

En la clase Configuración cambiar la variable **``DEBUG_MODE``**  y **``LOGIN_DEBUG``**  a false
```
    Configuración.java
```
#### Mientras el modo debug este activado:
- Las piezas se podrán mover a **cualquier posición** independientemente de si es un movimiento permitido

- **No** se tendrá en cuenta los **turnos** de movimiento (se puede mover 2 veces un mismo color por ejemplo)

- El panel chat se activa para poder debugearlo

- Las partidas no se guardarán en las analíticas ni estadísticas de usuario (cuando se hagan)
- 
#### Mientras ``LOGIN_DEBUG`` este activado:
- Se iniciará sesión automaticamente con una cuenta demo


## Jugar en modo local (Jugador contra jugador)




#### 1 - Ejecutar


```
    main.java
```
#### Seleccionar el modo de juego ``partida local `` en el menú de inicio.





## Jugar en modo online (En progreso)

#### 1 - Cambios necesarios

En la clase **``Configuración.java``** descomentar la variable host de url y comentar localhost
```
	public static final String HOST = "madchess.tabernagogorra.eu"; 
	//public static final String HOST = "localhost"; 
    
```

#### 2- Ejecutar


```
    main.java
```
Seleccionar el modo de juego ``Crear  partida online`` en el menú de inicio.


#### El segundo jugador selecciona el modo ``Join  partida online`` en el menú de inicio.

