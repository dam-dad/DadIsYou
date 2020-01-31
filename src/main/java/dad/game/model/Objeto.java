package dad.game.model;

import javafx.scene.image.Image;

public class Objeto {

	private String nombreTipoObjeto;
	private Image imagen;
	private Posicion posicion;
	private String estado;
	
	public Objeto() {
		// TODO Auto-generated constructor stub
	}

	public String getNombreTipoObjeto() {
		return nombreTipoObjeto;
	}

	public void setNombreTipoObjeto(String nombreTipoObjeto) {
		this.nombreTipoObjeto = nombreTipoObjeto;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
