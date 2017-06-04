/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import outro.ConexaoRelatorio;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "relatorioControle")
@SessionScoped
public class RelatorioControle {

    public String uf;
    public String equiptipo;
    public String status;
    public String nome;


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

    // CIDADES POR ESTADOS
    public void relCidade2() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("ESTADO_UF", uf);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relCidade2.jasper"),
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

    // Peças por equipamentos
    public void relPeca1() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("EQUIPAMENTO_TIPO", equiptipo);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relPeca1.jasper"),
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

    public void relChamado() {
        try {
            HashMap parameters = new HashMap();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado.jasper"),
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

    public void relChamado1() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("STATUS", status);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado1.jasper"),
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

    public void relChamado2() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("NOME", nome);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado2.jasper"),
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

    public void relChamado3() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("NOME", nome);
            parameters.put("STATUS", status);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado3.jasper"),
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

    public void relChamado4() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("NOME", nome);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado4.jasper"),
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

    public void relChamado5() {
        try {
            HashMap parameters = new HashMap();
            parameters.put("NOME", nome);
            parameters.put("STATUS", status);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext
                    .getExternalContext().getContext();
            Connection con = ConexaoRelatorio.getEntityManagerJDBCConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("WEB-INF/relatorios/relChamado5.jasper"),
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEquiptipo() {
        return equiptipo;
    }

    public void setEquiptipo(String equiptipo) {
        this.equiptipo = equiptipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
