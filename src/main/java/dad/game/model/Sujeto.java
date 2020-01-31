package dad.game.model;

public class Sujeto extends Objeto {
	
	private SujetoEnum nombre;

	public Sujeto() {
		super();
	}
	
	public Sujeto(SujetoEnum nombre, String imagen, Posicion posicion) {
		super(posicion);
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}

	public SujetoEnum getNombre() {
		return nombre;
	}

	public void setNombre(SujetoEnum nombre) {
		this.nombre = nombre;
		this.setImagen(nombre.toString().toLowerCase());
	}
	
}
