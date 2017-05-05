/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistence.GerarEntityManager;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "loginControle")
@SessionScoped
public class LoginControle {

    private String login;
    private String senha;
    private Usuario usuarioLogado = null;

    public LoginControle() {
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
    }

    public String entrar() {
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
        Query qry = em.createQuery("from Usuario where login = :login and senha = :senha");
        qry.setParameter("login", login);
        qry.setParameter("senha", senha);
        List<Usuario> list = qry.getResultList();
        if (list.size() <= 0) {
            usuarioLogado = null;
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login ou senha inválida!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
            return "";
        } else {
            usuarioLogado = list.get(0);
            if (usuarioLogado.getTipo().equalsIgnoreCase("Tecnico")) {
                return "/template/template_Tecnico.xhtml?faces-redirect=true";
            } else if (usuarioLogado.getTipo().equalsIgnoreCase("Atendente")) {
                return "/template/template_Atendente.xhtml?faces-redirect=true";
            } else {
                return "/template/template_Administrador.xhtml?faces-redirect=true";
            }
        }
    }

    public String sair() {
        usuarioLogado = null;
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuário Desconectado!", "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
        return "/faces/LoginForm.xhtml";
    }

    public boolean controle() {
        if (usuarioLogado.getTipo().toString().equalsIgnoreCase("Administrador")) {
            return false;
        } else if (usuarioLogado.getTipo().toString().equalsIgnoreCase("Técnico")) {
            return false;
        } else {
            return true;
        }
    }

    public void verificarLogin() throws Exception {
        if (usuarioLogado == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/LoginForm.xhtml");
            
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
