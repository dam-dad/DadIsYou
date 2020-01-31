package dad.game.model;

public class Accion extends Objeto {
	
	private AccionEnum nombre;

	public Accion() {
		super();
	}
	
	public Accion(AccionEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public AccionEnum getNombre() {
		return nombre;
	}

	public void setNombre(AccionEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
}
