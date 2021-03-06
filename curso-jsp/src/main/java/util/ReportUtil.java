package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({"rawtypes","unchecked"})
public class ReportUtil implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public byte[] gerarRelatorioPDF( List listaDados,String nomeRelatorio, HashMap<String, Object> params ,ServletContext ServletContext ) throws Exception {
		
		//cria lista de dados da consulta Sql feita
		JRBeanCollectionDataSource  jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = ServletContext.getRealPath("relatorio")  + File.separator + nomeRelatorio + ".jasper";
		

		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params,jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}
	
	
	public byte[] gerarRelatorioPDF( List listaDados,String nomeRelatorio,ServletContext ServletContext ) throws Exception {
	
		//cria lista de dados da consulta Sql feita
		JRBeanCollectionDataSource  jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = ServletContext.getRealPath("relatorio")  + File.separator + nomeRelatorio + ".jasper";
		

		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashedMap(),jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

}
