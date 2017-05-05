/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Cidade;
import beans.PessoaFisica;
import converters.ConverterCidade;
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
@ManagedBean(name = "pessoafisControle")
@SessionScoped
public final class PessoafisControle {

    private PessoaFisica objeto = new PessoaFisica();
    private List<PessoaFisica> filtroPfis;
    private EntityManager em;
    private List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
    private ConverterCidade converterCidade = new ConverterCidade();

    public ConverterCidade getConverterCidade() {
        return converterCidade;
    }

    public void setConverterCidade(ConverterCidade converterCidade) {
        this.converterCidade = converterCidade;
    }

    public PessoafisControle() {
        lista = new ArrayList<PessoaFisica>();
        em = GerarEntityManager.getInstance().getEntityManager();

        objeto = new PessoaFisica();
        Listar();
    }

    public String incluir() {

        objeto = new PessoaFisica();
        return "pessoafisForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(PessoaFisica.class, id);
        return "pessoafisForm";
    }

    public String Listar() {



        String jpql = "from PessoaFisica order by nome";

        lista = em.createQuery(jpql).getResultList();

        return "pessoafisList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "pessoafisList";
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
            objeto = new PessoaFisica();
            return Listar();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O CPF ou RG digitados já estão cadastrados no Sistema!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";

    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(PessoaFisica.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir a Pessoa Física | A Pessoa possui dependência na classe Chamado!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public String filtrar() {
        return Listar();
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaFisica> getLista() {
        return lista;
    }

    public void setLista(List<PessoaFisica> lista) {
        this.lista = lista;
    }

    public List<PessoaFisica> getFiltroPfis() {
        return filtroPfis;
    }

    public void setFiltroPfis(List<PessoaFisica> filtroPfis) {
        this.filtroPfis = filtroPfis;
    }

    public List<Cidade> completeCidade(String query) {
        List<Cidade> results = em.createQuery("from Cidade where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}
