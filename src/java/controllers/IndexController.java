/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Chamado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.apache.commons.mail.EmailException;
import outro.EmailUtils;
import persistence.GerarEntityManager;

@ManagedBean(name = "indexController")
@SessionScoped
public class IndexController {

    private Chamado objeto = new Chamado();
    private EntityManager em;
    private String destino;
    private String titulo;
    private String mensagem;

    public IndexController() {
        em = GerarEntityManager.getInstance().getEntityManager();
    }

    public String email(Integer id) {
        objeto = em.find(Chamado.class, id);
        destino = objeto.getPessoa().getEmail();
        titulo = "Atendimento " + objeto.getId();
        mensagem = objeto.getPessoa().getNome() + " , seu chamado esta com o status de: " + objeto.getStatus();
        return "emailForm";


    }

    public void enviaEmail() {
        try {
            EmailUtils.enviaEmail(titulo, mensagem, destino);

        } catch (EmailException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Occoreu um erro ao enviar a mensagem.", "Erro"));
           Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public Chamado getObjeto() {
        return objeto;
    }

    public void setObjeto(Chamado objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}