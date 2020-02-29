package dad.game.model;

import dad.game.model.enums.AccionEnum;
import dad.game.model.enums.SujetoEnum;
import dad.game.model.enums.TipoEnum;
import dad.game.model.enums.VerboEnum;

/**
 * Almacén de niveles
 */
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
				"fiw·····················",
				"diy·····················",
				"························",
				"·············UUUUUUUUU··",
				"·············U·······U··",
				"··rie········U·u·····U··",
				"·············U·i·····U··",
				"·············U·m·····U··",
				"·····U·U·····U·······U··",
				"·····URU·····U·····F·U··",
				"···UUURUUU···U·······U··",
				"···U··R··U···UUUUUUUUU··",
				"···U·····U··············",
				"···U··D··U··············",
				"···U·····U··············"
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
	
	public static Objeto<?>[][] cinco() {
		String[] nivel= {
				"························",
				"························",
				"························",
				"························",
				"························",
				"··········diy·····WWWWWW",
				"··················W···Wp",
				"····D·············W·F·Wi",
				"··················W···Ws",
				"··········fiw·····WWWWWW",
				"························",
				"························",
				"························",
				"························",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] seis() {
		String[] nivel= {
				"pisLL··W·····W·········L",
				"LLLL···W··D··W········LL",
				"LL·····W·····W·······LLL",
				"L······W·diy·W······LLL·",
				"·······WW···WW·····LLL··",
				"·······R·····W····LLL···",
				"·······WWWWWWW····LLL···",
				"·········WrW·····LLL····",
				"··········i·····LLL·····",
				"··········e·····LLL·····",
				"···············LLL······",
				"····l·········LLL····F··",
				"··············LLL·······",
				"·············LLL····fiw·",
				"lim·········LLL·········"
		};
		return parsear(nivel);
	}

	
	public static Objeto<?>[][] siete() {
		String[] nivel= {
				"d·p·a···················",
				"i·i·i····WWWWWWWW·······",
				"y·s·k····W······W·······",
				"·········W·D··R·W·······",
				"·········W····R·W·······",
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
	
	public static Objeto<?>[][] ocho() {
		String[] nivel= {
				"ris·R···················",
				"····R···················",
				"uid·R···················",
				"····R···················",
				"biw·R···················",
				"····R···················",
				"RRRRR···················",
				"························",
				"························",
				"·d······················",
				"·i······················",
				"·y······················",
				"························",
				"························",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] nueve() {
		String[] nivel= {
				"························",
				"····uimWWWWWWWWWWW······",
				"···WWWWW·········W······",
				"···W···W····tik··W······",
				"···W···WWW·······Wpis···",
				"·Q·W·q·U·T·······WWWWWW·",
				"···W···WWW········R·W·W·",
				"···W···W····rie··WW·D·W·",
				"···WWdWW·········WW·W·W·",
				"···WWiWW····R·&··WWWWWW·",
				"·F·WWyWW·········W······",
				"···WWWWWWWWWWWWWWW······",
				"························",
				"·fiw····················",
				"························"
		};
		return parsear(nivel);
	}
	
	@SuppressWarnings("unused")
	private static Objeto<?>[][] empty() {
		String[] nivel= {
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························",
				"························"
		};
		return parsear(nivel);
	}
	
	/**
	 * Carga un nivel
	 * @param id Id del nivel
	 * @return Nivel
	 */
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
		case 5:
			nivel = cinco();
			break;
		case 6:
			nivel = seis();
			break;
		case 7:
			nivel = siete();
			break;
		case 8:
			nivel = ocho();
			break;
		case 9:
			nivel = nueve();
			break;
		}
		return nivel;
	}
	
	/**
	 * Carga la imagen de fondo de un nivel
	 * @param id Id del nivel
	 * @return nombre de la imagen sin extensión
	 */
	public static String getBackground(int id) {
		String bg = "";
		switch (id) {
		case 0:
			bg = "uno";
			break;
		case 1:
			bg = "uno";
			break;
		case 2:
			bg = "uno";
			break;
		case 3:
			bg = "uno";
			break;
		case 4:
			bg = "uno";
			break;
		case 5:
			bg = "uno";
			break;
		case 6:
			bg = "uno";
			break;
		case 7:
			bg = "uno";
			break;
		case 8:
			bg = "uno";
			break;
		case 9:
			bg = "uno";
			break;
		}
		return bg;
	}

	/**
	 * Convierte el mapa String en un mapa Objeto
	 * @param nivel Mapa de Strings con el nivel
	 * @return Mapa de Objeto con el nivel
	 */
	private static Objeto<?>[][] parsear(String[] nivel) {
		Objeto<?>[][] mapa = new Objeto[nivel.length][nivel[0].length()];
		for (int i = 0; i < nivel.length; i++) {
			for (int j = 0; j < nivel[i].length(); j++) {
				char letra = nivel[i].charAt(j);
				Posicion posicion = new Posicion(j, i);
				// Se convierte la letra del mapa en un objeto
				if(letra == '-') { // Aire ·
					mapa[i][j] = null;
				} else if (letra == 'i') {
					mapa[i][j] = new Objeto<VerboEnum>(VerboEnum.IS, TipoEnum.VERBO, letra, posicion);
				} else if (letra == '&') {
					mapa[i][j] = new Objeto<VerboEnum>(VerboEnum.AND, TipoEnum.VERBO, letra, posicion);
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
				} else if (letra == 'U') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.SKULL, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'Q') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.CRAB, TipoEnum.ELEMENTO, letra, posicion);
				} else if (letra == 'T') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.STAR, TipoEnum.ELEMENTO, letra, posicion);
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
				} else if (letra == 'u') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.SKULL, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 'q') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.CRAB, TipoEnum.SUJETO, letra, posicion);
				} else if (letra == 't') {
					mapa[i][j] = new Objeto<SujetoEnum>(SujetoEnum.STAR, TipoEnum.SUJETO, letra, posicion);
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
				// Añadir posición inicial cuando no sea aire
				if(mapa[i][j] != null) {
					mapa[i][j].setPosicion(posicion);
				}
			}
		}
		return mapa;
	}
	
}
