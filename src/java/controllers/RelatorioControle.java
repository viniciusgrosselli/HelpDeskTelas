/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import java.sql.Connection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import outro.ConexaoRelatorio;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "relatorioControle")
@SessionScoped
public class RelatorioControle {

    

    public RelatorioControle() {
    }
    //RELATÓRIO TODAS CIDADES

    public void relCidade() {
        try {
            HashMap parameters = new HashMap();
            //parameters.put("idCidade", "varialel dinamica");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relCidade.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    //RELATÓRIO ESTADOS
    public void relEstado() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relEstado.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //RELATÓRIO PEÇAS
    public void relPeca() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relPeca.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    // SERVIÇOS
    public void relServico() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relServico.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // USUÁRIOS
    public void relUser() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relUser.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cliente
    public void relCliente() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relCliente.jasper"),
                    parameters, con);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf"); //Abrir em nova página
            //res.setHeader("Content-disposition","attachment;filename=arquivo.pdf");// baixar arquivo PDF
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


 



    
