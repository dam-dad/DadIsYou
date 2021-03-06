package dad.game.model;

import dad.game.model.enums.PropiedadEnum;
import dad.game.model.enums.SujetoEnum;
import dad.game.model.enums.TipoEnum;
import dad.game.model.enums.OperadorEnum;

/**
 * Almacén de niveles
 */
public class Nivel {
	
	public static Objeto<?>[][] test() {
		String[] nivel = {
				"························",
				"···R·······rie··········",
				"···W····················",
				"·······R··p·ie··i···s···",
				"···W····················",
				"·······R·····t··i···e···",
				"···W····················",
				"·······T·····t··i···s···",
				"···W····················",
				"·······T·····p··i···s···",
				"························",
				"················RRAAAAA·",
				"···················AAAA·",
				"····D········diy···AAAA·",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] uno() {
		String[] nivel = {
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
		String[] nivel = {
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
		String[] nivel = {
				"························",
				"···········FFFFFFFFF····",
				"···········F·······F····",
				"···········F··i····F····",
				"···········F·······F····",
				"·····FFFFFFF·····w·F····",
				"·····F·············F····",
				"·····F·d·····D·····F····",
				"·····F·············F····",
				"·····FFFFFFFFFFFFFFF····",
				"···········F·f·····F····",
				"····p······F·i··W··F····",
				"····i······F·s·····F····",
				"····y······FFFFFFFFF····",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] cuatro() {
		String[] nivel = {
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

	
	public static Objeto<?>[][] cinco() {
		String[] nivel = {
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
	
	public static Objeto<?>[][] seis() {
		String[] nivel = {
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
		String[] nivel = {
				"ris·R·········R·········",
				"····R·········R·········",
				"biw·R····WWWWWRWWWWWW···",
				"····R····W····R·····W···",
				"RRRRR····W····R··B··W···",
				"·········W····R·····W···",
				"·········W····RRRRRRRRRR",
				"·········W··········W···",
				"·········W····D·····W···",
				"·d·······W··········W···",
				"·i·······W···pis····W···",
				"·y·······W··········W···",
				"·········WWWWWWWWWWWW···",
				"························",
				"························"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] ocho() {
		String[] nivel = {
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
	
	private static Objeto<?>[][] nueve() {
		String[] nivel = {
				"fiw········AAAA······tik",
				"···········AAAA·········",
				"···WWWWW····AAAA···TTT··",
				"···W···W····AAAAA·TT·TT·",
				"···W·D·W····AAAAA·T·F·T·",
				"···W···W····AAAAAATT·TT·",
				"···WW·WW····AAA·AA·TTT··",
				"············AA·q·AA·····",
				"·············AA·AAAA····",
				"··············AAAAAAA···",
				"······WWWW······AAAAA···",
				"···W··diy&kW······AAAA··",
				"···WWWWWWW·········AAAA·",
				"···················AAAAA",
				"··················AAApis"
		};
		return parsear(nivel);
	}
	
	public static Objeto<?>[][] diez() {
		String[] nivel = {
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
	
	public static Objeto<?>[][] once() {
		String[] nivel = {
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
	
	private static Objeto<?>[][] doce() {
		String[] nivel = {
				"························",
				"························",
				"························",
				"······WWWWWWWWWWWW······",
				"······WD·········W······",
				"······W··T·····p·W······",
				"·fiw··WTTT·····i·W······",
				"······W········s·W······",
				"·diy··W··········W······",
				"······W·tis······W······",
				"······W··········W······",
				"······WWWWWWWWWWWW······",
				"························",
				"························",
				"pim·····················"
		};
		return parsear(nivel);
	}
	
	private static Objeto<?>[][] trece() {
		String[] nivel = {
				"························",
				"··········rir···········",
				"························",
				"························",
				"········D·····F·········",
				"························",
				"························",
				"··········fir···········",
				"························",
				"························",
				"························",
				"························",
				"························",
				"WWWW·····WWWWW······WWWW",
				"diyW·····WpisW······Wfiw"
		};
		return parsear(nivel);
	}
	
	private static Objeto<?>[][] catorce() {
		String[] nivel = {
				"························",
				"·····fif········pis·····",
				"························",
				"WWWWWWWWWWWWWWWWWWWWWWWW",
				"··············W·········",
				"·····fie······W·········",
				"··············W·········",
				"·····t········F·W·······",
				"··············W·········",
				"·········D····W···fis···",
				"··············W·········",
				"··············W····T····",
				"··············W·········",
				"··W···········W···tiw···",
				"diy···········W·········"
		};
		return parsear(nivel);
	}
	
	@SuppressWarnings("unused")
	private static Objeto<?>[][] empty() {
		String[] nivel = {
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
		case 10:
			nivel = diez();
			break;
		case 11:
			nivel = once();
			break;
		case 12:
			nivel = doce();
			break;
		case 13:
			nivel = trece();
			break;
		case 14:
			nivel = catorce();
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
		case 10:
			bg = "uno";
			break;
		case 11:
			bg = "uno";
			break;
		case 12:
			bg = "uno";
			break;
		case 13:
			bg = "uno";
			break;
		case 14:
			bg = "uno";
			break;
		case 15:
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
					mapa[i][j] = new Objeto<OperadorEnum>(OperadorEnum.IS, TipoEnum.OPERADOR, letra, posicion);
				} else if (letra == '&') {
					mapa[i][j] = new Objeto<OperadorEnum>(OperadorEnum.AND, TipoEnum.OPERADOR, letra, posicion);
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
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.YOU, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'w') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.WIN, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 's') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.STOP, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'm') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.DEFEAT, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'e') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.PUSH, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'v') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.FLOAT, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'k') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.SINK, TipoEnum.PROPIEDAD, letra, posicion);
				} else if (letra == 'c') {
					mapa[i][j] = new Objeto<PropiedadEnum>(PropiedadEnum.MOVE, TipoEnum.PROPIEDAD, letra, posicion);
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
