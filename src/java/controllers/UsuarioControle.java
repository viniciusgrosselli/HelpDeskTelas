/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Funcionario;
import beans.Usuario;
import converters.ConverterFuncionario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import persistence.GerarEntityManager;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "usuarioControle")
@SessionScoped
public final class UsuarioControle {

    private Usuario objeto = new Usuario();
    private List<Usuario> filtroUsuario;
    private EntityManager em;
    private List<Usuario> lista = new ArrayList<Usuario>();
    private ConverterFuncionario converterFuncionario = new ConverterFuncionario();

    public ConverterFuncionario getConverterFuncionario() {
        return converterFuncionario;
    }

    public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
        this.converterFuncionario = converterFuncionario;
    }

    public UsuarioControle() {
        lista = new ArrayList<Usuario>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Usuario();
        Listar();
    }

    public String incluir() {

        objeto = new Usuario();
        return "usuarioForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(Usuario.class, id);
        return "usuarioForm";
    }

    public String Listar() {

        String jpql = "from Usuario";

        lista = em.createQuery(jpql).getResultList();

        return "usuarioList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "usuarioList";
    }

    public String gravar() {
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
        em.getTransaction().begin();
        try {
            if (objeto.getId() == null) {
                em.persist(objeto);
            } else {
                em.merge(objeto);
            }
            em.getTransaction().commit();
            objeto = new Usuario();
            return Listar();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O login que você digitou já esta cadastrado no sistema!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";

    }

    public String excluir(Integer id) {
        objeto = em.find(Usuario.class, id);
        if (objeto != null) {
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
        }
        return Listar();
    }

    public String filtrar() {
        return Listar();
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public List<Usuario> getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(List<Usuario> filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }

    public List<Funcionario> completeFuncionario(String query) {
        List<Funcionario> results = em.createQuery("from Funcionario where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}