package dad.game.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Posicion {
	private IntegerProperty x = new SimpleIntegerProperty();
	private IntegerProperty y = new SimpleIntegerProperty();
	
	public Posicion() {}

	public Posicion(int x, int y) {
		super();
		this.x.set(x);
		this.y.set(y);
	}

	public IntegerProperty xProperty() {
		return this.x;
	}
	

	public int getX() {
		return this.xProperty().get();
	}
	

	public void setX(final int x) {
		this.xProperty().set(x);
	}
	

	public IntegerProperty yProperty() {
		return this.y;
	}
	

	public int getY() {
		return this.yProperty().get();
	}
	

	public void setY(final int y) {
		this.yProperty().set(y);
	}
	



	

	
	
	
}
