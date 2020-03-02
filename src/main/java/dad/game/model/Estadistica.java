package dad.game.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Estadistica {

	private List<String> nivelesCompletados;
	private Date horaInicioDelJuego;
	private String tiempoJugado;
	private int numeroDeVictorias, numeroDeDerrotas, numeroDeNiveles;
	
	public Estadistica() {
		nivelesCompletados = new ArrayList<String>();
		horaInicioDelJuego = new Date();
		numeroDeVictorias = 0;
		numeroDeDerrotas = 0;
		tiempoJugado = "";
		numeroDeNiveles = 14;
	}
	
	public void nivelCompletado(String nivel) {
		boolean yaGuardado = false;
		for (String nivelGuardado : nivelesCompletados) {
			if (nivelGuardado.equals(nivel)) {
				yaGuardado = true;
			}
		}
		if (!yaGuardado) {
			nivelesCompletados.add(nivel);
		}
	}

	public List<String> getNivelesCompletados() {
		return nivelesCompletados;
	}
	
	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

	public static void main(String[] args) throws ParseException {
	    
	}

	public static Date getDifferenceBetwenDates(Date dateInicio, Date dateFinal) {
	    long milliseconds = dateFinal.getTime() - dateInicio.getTime();
	    int seconds = (int) (milliseconds / 1000) % 60;
	    int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
	    int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.SECOND, seconds);
	    c.set(Calendar.MINUTE, minutes);
	    c.set(Calendar.HOUR_OF_DAY, hours);
	    return c.getTime();
	}

	public String getTiempoJugado() {
		Date difference = getDifferenceBetwenDates(horaInicioDelJuego, new Date());
	    tiempoJugado = sdfResult.format(difference);
		return tiempoJugado;
	}

	public int getNumeroDeVictorias() {
		return numeroDeVictorias;
	}

	public void agregarVictoria() {
		numeroDeVictorias++;
	}

	public int getNumeroDeDerrotas() {
		return numeroDeDerrotas;
	}

	public void agregarDerrota() {
		numeroDeDerrotas++;
	}

	public int getNumeroDeNiveles() {
		return numeroDeNiveles;
	}
}
