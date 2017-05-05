/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Historico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import persistence.GerarEntityManager;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "historicoControle")
@SessionScoped
public final class HistoricoControle {

    private List<Historico> filtroHistorico;
    private Historico objeto = new Historico();
    private EntityManager em;
    private List<Historico> lista = new ArrayList<Historico>();

    public HistoricoControle() {
        lista = new ArrayList<Historico>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Historico();
        Listar(objeto.getId());

    }

    public String Listar(Integer id) {

        String jpql = "from Historico where id = " + id;
        lista = em.createQuery(jpql).getResultList();
        return "historicoList";


    }

    public String Voltar() {

        return "chamadoList";
    }

    public Historico getObjeto() {
        return objeto;
    }

    public void setObjeto(Historico objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Historico> getLista() {
        return lista;
    }

    public void setLista(List<Historico> lista) {
        this.lista = lista;
    }

    public List<Historico> getFiltroHistorico() {
        return filtroHistorico;
    }

    public void setFiltroHistorico(List<Historico> filtroHistorico) {
        this.filtroHistorico = filtroHistorico;
    }
}
