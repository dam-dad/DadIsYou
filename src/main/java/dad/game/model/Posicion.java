package dad.game.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Posicion {
	private int x;
	private int y;

	public Posicion() {
	}

	public Posicion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void mover(DireccionEnum direccion, int paso) {
		if (direccion == DireccionEnum.ARRIBA) {
			y -= paso;
		} else if (direccion == DireccionEnum.ABAJO) {
			y += paso;
		} else if (direccion == DireccionEnum.DERECHA) {
			x += paso;
		} else if (direccion == DireccionEnum.IZQUIERDA) {
			x -= paso;
		}
	}
}
