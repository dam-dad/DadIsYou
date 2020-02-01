package dad.game.model;

public class Accion extends Objeto {
	
	private AccionEnum nombre;

	public Accion() {
		super();
	}
	
	public Accion(AccionEnum nombre) {
		setNombre(nombre);
	}

	public Accion(AccionEnum nombre, char codigo) {
		super(codigo);
		setNombre(nombre);
	}

	public Accion(AccionEnum nombre, Posicion posicion) {
		super(posicion);
		setNombre(nombre);
	}

	public Accion(AccionEnum nombre, char codigo, Posicion posicion) {
		super(codigo, posicion);
		setNombre(nombre);
	}

	public AccionEnum getNombre() {
		return nombre;
	}

	public void setNombre(AccionEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
}
