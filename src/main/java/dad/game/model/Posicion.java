package dad.game.model;

public class Posicion {
	private int x;
	private int y;

	public Posicion() {}

	public Posicion(int x, int y) {
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
	
	public boolean compararPosicion(Posicion posicion2) {
		return x == posicion2.getX() && y == posicion2.getY();
	}
}
