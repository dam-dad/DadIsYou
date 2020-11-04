# Dad Is You

### Miembros
Cristian Trujillo Trujillo <br>
Beatriz García Hernández


### Instalación
El juego está disponible para **Windows** en versión *instalable* y *portable*.

Descarga y ejecuta alguna de las siguientes versiones:

* [Dad Is You v1.0.0](https://github.com/dam-dad/DadIsYou/releases/tag/1.0.0)


### Capturas de pantalla

![alt text](https://raw.githubusercontent.com/dam-dad/DadIsYou/master/screenshots/1.png)
![alt text](https://raw.githubusercontent.com/dam-dad/DadIsYou/master/screenshots/2.png)
![alt text](https://raw.githubusercontent.com/dam-dad/DadIsYou/master/screenshots/3.png)
![alt text](https://raw.githubusercontent.com/dam-dad/DadIsYou/master/screenshots/4.png)


### Documentación

El proyecto tiene cinco paquetes principales:

* `dad`: Se encuentran los archivos principales de la aplicación **JavaFX**.
* `dad.game.controller`: Aquí están los controladores de las vistas del juego.
  * `MenuPrincipalController`: Controlador del menú principal.
  * `MenuNivelController`: Controlador del mapa de los niveles.
  * `GameController`: Controlador del nivel del juego.
  * `AjustesController`: Controlador de la pantalla de ajustes.
* `dad.game.model`: En este paquete están los modelos del juego.
  * `Estadistica`: Modelo utilizado para almacenar estadísticas del juego.
  * `Tablero`: Contiene la lógica de cada nivel.
  * `Nivel`: Almacena los distintos niveles del juego.
  * `Posicion`: Modelo utilizado para almacenar las coordenadas de los objetos en el tablero.
  * `Objeto`: Modelo de los objetos del mapa. Pueden ser: sujetos, operadores, propiedades o elementos.
* `dad.game.model.enums`: Enumerados utilizados en el modelo.
* `dad.game.utils`: Aquí se encuentra la clase que genera los informes de **JasperReport**.









