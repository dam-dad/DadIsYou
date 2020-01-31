package dad.game.model;

public class Verbo extends Objeto {
	
	private VerboEnum nombre;

	public Verbo() {
		super();
	}
	
	public Verbo(VerboEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public VerboEnum getNombre() {
		return nombre;
	}

	public void setNombre(VerboEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
}
