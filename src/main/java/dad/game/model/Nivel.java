package dad.game.model;

public class Nivel {
	
	public static Objeto[][] uno() {
		String[] nivel= {
				"000000000000000000000000",
				"000000000000000000000000",
				"0000000000diy00000000000",
				"000000000000000000000000",
				"0000WWWWWWWWWWWWWWWW0000",
				"000000000000000000000000",
				"00000D000000000000F00000",
				"000000000000000000000000",
				"00000WWWWWWWWWWWWWWW0000",
				"000000000000000000000000",
				"0000000000fiw00000000000",
				"000000000000000000000000",
				"000000000000000000000000",
				"000000000000000000000000",
				"000000000000000000000000"
		};
		return parsear(nivel);
	}
	
	/**
	 * 
	 * @param nivel
	 * @return
	 */
	private static Objeto[][] parsear(String[] nivel) {
		Objeto[][] mapa = new Objeto[nivel.length][nivel[0].length()];
		for (int i = 0; i < nivel.length; i++) {
			for (int j = 0; j < nivel[i].length(); j++) {
				char letra = nivel[i].charAt(j);
				Posicion posicion = new Posicion(i, j);
				if(letra == '0') { // Aire
					mapa[i][j] = null;
				} else if (letra == 'W') {
					mapa[i][j] = new Elemento(SujetoEnum.WALL); 
				} else if (letra == 'F') {
					mapa[i][j] = new Elemento(SujetoEnum.FLAG);
				} else if (letra == 'D') {
					mapa[i][j] = new Elemento(SujetoEnum.DAD);
				} else if (letra == 'd') {
					mapa[i][j] = new Sujeto(SujetoEnum.DAD);
				} else if (letra == 'i') {
					mapa[i][j] = new Verbo(VerboEnum.IS);
				} else if (letra == 'y') {
					mapa[i][j] = new Accion(AccionEnum.YOU);
				} else if (letra == 'f') {
					mapa[i][j] = new Sujeto(SujetoEnum.FLAG);
				} else if (letra == 'w') {
					mapa[i][j] = new Accion(AccionEnum.WIN);
				}
				
				//Añadir posición cuando no sea aire
				if(mapa[i][j] != null) {
					mapa[i][j].setPosicion(posicion);
				}
				
			}
		}
		
		return mapa;
	}
	
	
}
