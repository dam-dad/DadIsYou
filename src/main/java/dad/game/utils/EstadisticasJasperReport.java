package dad.game.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dad.App;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class EstadisticasJasperReport {
	
	private static final String JRXML_FILE = "/informes/estadisticas.jrxml";
	private static final String PDF_FILE = "src/main/resources/informes/estadisticas.pdf";

	public static void generarVistaPrevia() {
		try {
			// Compila el informe
			JasperReport report = JasperCompileManager
					.compileReport(App.class.getResourceAsStream(EstadisticasJasperReport.JRXML_FILE));

			// Crea el mapa de parámetros para el informe (en este caso vacío)
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Generamos el informe (combinamos informe + datos)
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
					new JRBeanArrayDataSource(App.getEstadisticasProvider()));

			// Visualiza el informe generado
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void generarPdf() {
		try {
			// Compila el informe
			JasperReport report = JasperCompileManager
					.compileReport(App.class.getResourceAsStream(EstadisticasJasperReport.JRXML_FILE));

			// Mapa de parámetros para el informe
			Map<String, Object> parameters = new HashMap<String, Object>();

			// Generamos el informe (combinamos el informe compilado con los datos)
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
					new JRBeanArrayDataSource(App.getEstadisticasProvider()));

			// Exporta el informe a un fichero PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint, PDF_FILE);

			// Abre el archivo PDF generado con el programa predeterminado del sistema
			Desktop.getDesktop().open(new File(PDF_FILE));
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}
}
