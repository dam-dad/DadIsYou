package dad.game.controller;

/**
 * Interfaz que es implementada por los controladores JavaFX
 * Sirve para agrupar los controladores en una lista
 */
public interface Controller {
	
	/**
	 * Método que recoge la tecla pulsada en la escena
	 * @param key Clave de la tecla pulsada
	 */
	public default void evento(String key) { }
    
}