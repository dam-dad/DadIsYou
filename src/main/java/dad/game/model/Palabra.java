package dad.game.model;

public class Palabra {
	
	private String imagen;
	public Posicion posicion;
	
	public Palabra() {}
	
	public Palabra(Posicion posicion) {
		this.posicion = posicion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
}
