package dad.game.model;

public class Verbo extends Objeto {
	
	private VerboEnum nombre;

	public Verbo() {
		super();
	}
	
	public Verbo(VerboEnum nombre) {
		setNombre(nombre);
	}

	public Verbo(VerboEnum nombre, char codigo) {
		super(codigo);
		setNombre(nombre);
	}

	public Verbo(VerboEnum nombre, Posicion posicion) {
		super(posicion);
		setNombre(nombre);
	}

	public Verbo(VerboEnum nombre, char codigo, Posicion posicion) {
		super(codigo, posicion);
		setNombre(nombre);
	}

	public VerboEnum getNombre() {
		return nombre;
	}

	public void setNombre(VerboEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
}
