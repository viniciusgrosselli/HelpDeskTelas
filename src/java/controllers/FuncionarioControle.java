/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Cidade;
import beans.Funcionario;
import converters.ConverterCidade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "funcionarioControle")
@SessionScoped
public final class FuncionarioControle {

    private Funcionario objeto = new Funcionario();
    private List<Funcionario> filtroFuncionario;
    private EntityManager em;
    private Map<String, String> listaOrdem = new HashMap<String, String>();
    private List<Funcionario> lista = new ArrayList<Funcionario>();
    private ConverterCidade converterCidade = new ConverterCidade();

    public FuncionarioControle() {
        lista = new ArrayList<Funcionario>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Funcionario();
        Listar();
    }

    public String incluir() {

        objeto = new Funcionario();
        return "funcionarioForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(Funcionario.class, id);
        return "funcionarioForm";
    }

    public String Listar() {

        String jpql = "from Funcionario order by nome";

        lista = em.createQuery(jpql).getResultList();

        return "funcionarioList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "funcionarioList";
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
        objeto = new Funcionario();
        return Listar();

    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Funcionario.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir o Funcionário | O Funcionário possui dependências nas classe Chamado e Usuário!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public String filtrar() {
        return Listar();
    }

    public Funcionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Funcionario objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

    public Map<String, String> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(Map<String, String> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    public List<Funcionario> getFiltroFuncionario() {
        return filtroFuncionario;
    }

    public void setFiltroFuncionario(List<Funcionario> filtroFuncionario) {
        this.filtroFuncionario = filtroFuncionario;
    }

    public ConverterCidade getConverterCidade() {
        return converterCidade;
    }

    public void setConverterCidade(ConverterCidade converterCidade) {
        this.converterCidade = converterCidade;
    }

    public List<Cidade> completeCidade(String query) {
        List<Cidade> results = em.createQuery("from Cidade where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}
