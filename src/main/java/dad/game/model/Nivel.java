package dad.game.model;

public class Nivel {
	
	public static Objeto<?>[][] test() {
		String[] nivel= {
				"························",
				"··················f·····",
				"··········AAAA··········",
				"··AAAA····AAAA····diy···",
				"··········AAAA··········",
				"····WWWWWWWWWWWWWWWW····",
				"············L···········",
				"·····D······L·····F·····",
				"············L···········",
				"····WWWWWWWWWWWWWWWW····",
				"··········fiw···········",
				"····R···················",
				"·····R····pis···········",
				"······R···lim···········",
				"··········rie···········"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] uno() {
		String[] nivel= {
				"························",
				"························",
				"························",
				"························",
				"························",
				"····pis··········rie····",
				"························",
				"····WWWWWWWWWWWWWWWW····",
				"············R···········",
				"·····D······R·····F·····",
				"············R···········",
				"····WWWWWWWWWWWWWWWW····",
				"························",
				"····diy··········fiw····",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] dos() {
		String[] nivel= {
				"························",
				"···········WWWWWWWWW····",
				"···········W·······W····",
				"···········W··i····W····",
				"···········W·······W····",
				"·····WWWWWWW·····w·W····",
				"·····W·············W····",
				"·····W·f·····F·····W····",
				"·····W·············W····",
				"·····WWWWWWWWWWWWWWW····",
				"···········W·p·····W····",
				"····d······W·i··D··W····",
				"····i······W·s·····W····",
				"····y······WWWWWWWWW····",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] tres() {
		String[] nivel= {
				"d·m·a···················",
				"i·i·i····WWWWWWWW·······",
				"y·s·k····W······W·······",
				"·········W·D··R·W·······",
				"·········W······W·······",
				"·········W······W·······",
				"····WWWWWWAAAWWWWWWW····",
				"····W········W·····W····",
				"····W········W·rie·W····",
				"····W········W·····W····",
				"····WAAA···W·······W····",
				"····WAAA·····W·fiw·W····",
				"····WFAA·····W·····W····",
				"····WWWWWWWWWWWWWWWW····",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] cuatro() {
		String[] nivel= {
				"························",
				"························",
				"····WWWWWWWWWWWWWWWW····",
				"····W····B·········W····",
				"····W·diy········F·W····",
				"····WB·············W····",
				"····W····D····B····W···b",
				"····W··········B·B·W···i",
				"····W········B··B·BW···s",
				"····WB·······B·f·w·W····",
				"····W·B······B··B··W····",
				"····W·····B···B····W····",
				"····WWWWWWWWWWWWWWWW····",
				"························",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] load(int id) {
		Objeto<?>[][] nivel = null;
		switch (id) {
		case 0:
			nivel = test();
			break;
		case 1:
			nivel = uno();
			break;
		case 2:
			nivel = dos();
			break;
		case 3:
			nivel = tres();
			break;
		case 4:
			nivel = cuatro();
			break;
		}
		return nivel;
	}

	private static Objeto<?>[][] parsear(String[] nivel) {
		Objeto<?>[][] mapa = new Objeto[nivel.length][nivel[0].length()];
		for (int i = 0; i < nivel.length; i++) {
			for (int j = 0; j < nivel[i].length(); j++) {
				char letra = nivel[i].charAt(j);
				Posicion posicion = new Posicion(j, i);
				if(letra == '·') { // Aire
					mapa[i][j] = null;
				} else if (letra == 'i') {
					mapa[i][j] = new Objeto<VerboEnum>(VerboEnum.IS, TipoEnum.VERBO, letra, posicion);
				} else if (letra == 'W') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.WALL, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'F') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.FLAG, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'D') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.DAD, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'A') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.WATER, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'R') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.ROCK, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'L') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.LAVA, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'B') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.BOX, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'd') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.DAD, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'f') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.FLAG, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'p') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.WALL, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'r') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.ROCK, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'a') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.WATER, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'l') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.LAVA, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'b') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.BOX, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'y') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.YOU, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'w') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.WIN, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 's') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.STOP, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'm') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.DEFEAT, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'e') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.PUSH, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'v') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.FLOAT, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'k') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.SINK, TipoEnum.ACCION, letra, posicion);
				} else if (letra == 'c') {
					mapa[i][j] = new Objeto<AccionEnum>(AccionEnum.MOVE, TipoEnum.ACCION, letra, posicion);
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
