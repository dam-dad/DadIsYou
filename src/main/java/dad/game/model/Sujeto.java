package dad.game.model;

public class Sujeto extends Objeto {
	
	private SujetoEnum nombre;

	public Sujeto() {
		super();
	}
	
	public Sujeto(SujetoEnum nombre) {
		setNombre(nombre);
	}

	public Sujeto(SujetoEnum nombre, char codigo) {
		super(codigo);
		setNombre(nombre);
	}

	public Sujeto(SujetoEnum nombre, Posicion posicion) {
		super(posicion);
		setNombre(nombre);
	}

	public Sujeto(SujetoEnum nombre, char codigo, Posicion posicion) {
		super(codigo, posicion);
		setNombre(nombre);
	}

	public SujetoEnum getNombre() {
		return nombre;
	}

	public void setNombre(SujetoEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
	
}
