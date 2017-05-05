/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Estado;
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
@ManagedBean(name = "estadoControle")
@SessionScoped
public final class EstadoControle {

    private List<Estado> filtroEstado;
    private Estado objeto = new Estado();
    private EntityManager em;
    private List<Estado> lista = new ArrayList<Estado>();

    public EstadoControle() {
        lista = new ArrayList<Estado>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Estado();
        Listar();

    }

    public String incluir() {

        objeto = new Estado();
        return "estadoForm";
    }

    public String alterar(Integer id) {
        objeto = em.find(Estado.class, id);
        return "estadoForm";
    }

    public String Listar() {
        String jpql = "from Estado order by nome";
        lista = em.createQuery(jpql).getResultList();
        return "estadoList";
    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "estadoList";
    }

    public String gravar() {
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
        em.getTransaction().begin();
        if (objeto.getId() == null) {
            em.persist(objeto);
        } else {
            em.merge(objeto);
        }
        em.getTransaction().commit();
        objeto = new Estado();

        return Listar();

    }

    public String filtrar() {
        return Listar();
    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Estado.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir o Estado | O Estado possui dependências na classe Cidades!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);

            //para exibir a mensagem de erro la no faces.
        }
        return Listar();

    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Estado> getLista() {
        return lista;
    }

    public void setLista(List<Estado> lista) {
        this.lista = lista;
    }

    public List<Estado> getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(List<Estado> filtroEstado) {
        this.filtroEstado = filtroEstado;
    }
}
