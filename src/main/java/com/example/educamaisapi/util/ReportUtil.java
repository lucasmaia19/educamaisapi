package com.example.educamaisapi.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import com.sun.istack.ByteArrayDataSource;

//import br.com.n4w3b.gestaoassociados.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Slf4j
public class ReportUtil {

	// Property

    static ConnectionFactory con = BeanUtil.getBean(ConnectionFactory.class);


	// Method

	// Retorna nome do arquivo do relatório
	public static String reportName(String path) {

		String reportName = "relatorio";
		Pattern pattern = Pattern.compile("([\\w\\d-_]+).jrxml");
		Matcher matcher = pattern.matcher(path);
		while (matcher.find()) {
			reportName = matcher.group(1).replaceAll("-xlsx|-pdf", "");
		}

		return reportName;
	}

	// Compila jrxml
	public static JasperPrint reportCompile(String path, Map<String, Object> parametros) {

		ClassPathResource cpr = null;
		InputStream inputStream = null;
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;

		try {

			cpr = new ClassPathResource(path);
			inputStream = cpr.getInputStream();
			jasperReport = JasperCompileManager.compileReport(inputStream);

			// Exibe log da query compilada com parametros.
			reportShowQuery(jasperReport, parametros);

			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.getConexao());

			// Valida se relatório está vazio
			if (jasperPrint.getPages().isEmpty())
				throw new BusinessException("Relatório sem registros :(");

		} catch (BusinessException ex) {
			throw ex;

		} catch (Exception ex) {
			throw new BusinessException("Erro ao tentar compilar relatório :(", ex);
		}

		return jasperPrint;
	}

	// Gera relatório conforme tipo
	public static DataSource reportMake(String tipo, String path, Map<String, Object> params, HttpServletResponse response) {

		try {
			params.put("REPORT_LOCALE", new Locale("pt", "BR"));

			// Identifica se é para gerar "pdf" para navegador ou como anexo do email.
			if (tipo.equals("pdf")) {
				if (response != null)
					return reportMakePdf(path, params, response);
				else
					return reportMakePdf(path, params);

			// Identifica se é para gerar "xlsx" para navegador ou como anexo do email.
			} else if (tipo.equals("xlsx")) {
				if (response != null)
					return reportMakeXlsx(path, params, response);
				else
					return reportMakeXlsx(path, params);

			// Identifica se é para gerar "docx" para navegador ou como anexo do email.
			} else if (tipo.equals("docx")) {
				if (response != null)
					return reportMakeDocx(path, params, response);
				else
					return reportMakeDocx(path, params);

			} else {
				return null;
			}

		} catch (BusinessException ex) {
			throw ex;

		} catch (Exception ex) {
			throw new BusinessException("Erro ao tentar gerar relatório :(", ex);
		}

	}


	// Gera relatório em "pdf" para navegador.
	public static DataSource reportMakePdf(String path, Map<String, Object> params, HttpServletResponse response) throws Exception {

		JasperPrint jasperPrint = reportCompile(path, params);

		String reportName = reportName(path);
		String nomeRelatorioComOrdenacao = reportName.replaceAll("-pdf", "") + ".pdf";

		JRPdfExporter exporter = new JRPdfExporter();
		response.addHeader("Content-Type", "application/pdf");
		response.addHeader("Content-Disposition", "inline; filename=" + nomeRelatorioComOrdenacao);
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();

		return null;
	}

	// Gera relatório em "pdf" para usar como anexo em email.
	public static DataSource reportMakePdf(String path, Map<String, Object> params) throws Exception {

		JasperPrint jasperPrint = reportCompile(path, params);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();

		return new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/pdf");
	}


	// Gera relatório em "xlsx" para navegador.
	public static DataSource reportMakeXlsx(String path, Map<String, Object> params, HttpServletResponse response) 
			throws Exception, BusinessException {

		JasperPrint jasperPrint = reportCompile(path, params);
		
		String reportName = reportName(path);
		String nomeRelatorioComOrdenacao = reportName.replaceAll("-xlsx", "") + ".xlsx";

		JRXlsxExporter exporter = new JRXlsxExporter();
		response.addHeader("Content-Type", "application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "inline; filename=" + nomeRelatorioComOrdenacao);
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
		reportConfig.setSheetNames(new String[] { reportName });
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();

		return null;
	}

	//	Gera relatório em "xlsx" para usar como anexo em email.
	public static DataSource reportMakeXlsx(String path, Map<String, Object> params) throws Exception {

		JasperPrint jasperPrint = reportCompile(path, params);

 		String reportName = reportName(path);
 		
 		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
 		JRXlsxExporter exporter = new JRXlsxExporter();
 		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
 		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

 		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
 		reportConfig.setSheetNames(new String[] { reportName });
 		exporter.setConfiguration(reportConfig);
 		exporter.exportReport();

	    return new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/vnd.ms-excel");
	}


	// Gera relatório em "docx" para navegador.
	public static DataSource reportMakeDocx(String path, Map<String, Object> params, HttpServletResponse response) throws Exception {

		JasperPrint jasperPrint = reportCompile(path, params);

		String reportName = reportName(path);
		String nomeRelatorioComOrdenacao = reportName.replaceAll("-docx", "") + ".docx";

		JRDocxExporter exporter = new JRDocxExporter();
		response.addHeader("Content-Type", "application/msword");
		response.addHeader("Content-Disposition", "inline; filename=" + nomeRelatorioComOrdenacao);
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		SimpleDocxReportConfiguration reportConfig = new SimpleDocxReportConfiguration();
		exporter.setConfiguration(reportConfig);
		exporter.exportReport();

		return null;
	}


	//	Gera relatório em "docx" para usar como anexo em email.
	public static DataSource reportMakeDocx(String path, Map<String, Object> params) throws Exception {

		JasperPrint jasperPrint = reportCompile(path, params);

 		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
 		JRDocxExporter exporter = new JRDocxExporter();
 		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
 		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

 		SimpleDocxReportConfiguration reportConfig = new SimpleDocxReportConfiguration();
 		exporter.setConfiguration(reportConfig);
 		exporter.exportReport();

	    return new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/msword");
	}


	/**
	 * Return DataSource from file in resource
	 * <p>Used for attach file to mail. Don't support deploy jar</p>
	 * 
	 * @author	celesitnocortez@gmail.com
	 * @version	1.0
	 * @since	2019-10-21
	 * @param 	path path file relative in resource.
	 * @return 	DataSource from file path.
	 */
	public static DataSource getDataSource(String path) {

		DataSource dataSource = null;
		try {
			ClassPathResource resouce = new ClassPathResource(path);
			dataSource = new FileDataSource(resouce.getFile());

		} catch (Exception e) {
			throw new BusinessException("Erro ao tentar anexar arquivo :(", e);
			
		}
		return dataSource;
	}

	/**
	 * Return InputStream from file in resource
	 * <p>Used for attach file to mail. With suppport deploy jar.</p>
	 * 
	 * @author	celesitnocortez@gmail.com
	 * @version	1.0
	 * @since	2019-11-01
	 * @param 	path path file relative in resource.
	 * @return 	InputStream from file path.
	 */
	public static InputStream getInputStream(String path) {

		InputStream inputStream = null;
		try {
			ClassPathResource resouce = new ClassPathResource(path);
			inputStream = resouce.getInputStream();

		} catch (Exception e) {
			throw new BusinessException("Erro ao tentar anexar arquivo :(", e);

		}
		return inputStream;
	}


	// Debug
	
	/**
	 * Exibe no log texto do elemento que possui a key fornecida, usado para debug report.
	 * 
	 * Jaspersoft > Relatorio > Elemento > Properties > Appearance > Style and Print Details > key <parametro_localizado>
	 */
	public static void reportElementText(String key, String path, Map<String, Object> parametros)
			throws Exception {

		JasperPrint jasperPrint = reportCompile(path, parametros);

		jasperPrint.getPages().get(0).getElements().forEach(e -> {
			if (e.getKey() != null && e.getKey().equals(key)) {
				JRPrintText d = (JRPrintText) e;
				log.info("report element {}", d);
			}
		});
	}

	/**
	 * Exibe em log query do relatório com parametros atribuídos
	 * 
	 * 	jparam.getName() > nome do parametro
	 * 	jparam.getValueClassName() > classe do parametro
	 */
	public static void reportShowQuery(JasperReport jasperReport, Map<String, Object> parametros) throws Exception {

		try {

			// Exibe nome do relatório no log.
			log.debug(":: jasperReport -> name: {}", jasperReport.getName());

			String query = jasperReport.getMainDataset().getQuery().getText();

			JRParameter[] jParameter = jasperReport.getParameters();

			Iterator<Entry<String, Object>> iterator = parametros.entrySet().iterator();
			while (iterator.hasNext()) {

				Entry<String, Object> element = iterator.next();
				if (element.getKey().equals("REPORT_LOCALE"))
					continue;

				// Exibe nome e tipo do parâmetro em log.
				for (JRParameter jparam : jParameter)
					if (element.getKey().equals(jparam.getName()))
						log.debug("::: jasperReport -> param: {}, value: {}, type: {}", 
								jparam.getName(), element.getValue(), jparam.getValueClassName());

				query = query.replaceAll("\\$P!?\\{"+ element.getKey() + "\\}", "'" + element.getValue().toString() + "'");
			}

			// Exibe query do relatório com parâmetros no log.
			log.debug(":: jasperReport -> query with param: {}", query);

		} catch (Exception ex) {
			throw new BusinessException("Erro ao tentar gerar debug do relatório -> " + ex.getMessage());
		}

	}

}
