/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Cidade;
import beans.PessoaJuridica;
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
@ManagedBean(name = "pessoajurControle")
@SessionScoped
public final class PessoajurControle {

    private PessoaJuridica objeto = new PessoaJuridica();
    private List<PessoaJuridica> filtroPjur;
    private EntityManager em;
    private List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
    private ConverterCidade converterCidade = new ConverterCidade();

    public ConverterCidade getConverterCidade() {
        return converterCidade;
    }

    public void setConverterCidade(ConverterCidade converterCidade) {
        this.converterCidade = converterCidade;
    }

    public PessoajurControle() {
        lista = new ArrayList<PessoaJuridica>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new PessoaJuridica();
        Listar();
    }

    public String incluir() {

        objeto = new PessoaJuridica();
        return "pessoajurForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(PessoaJuridica.class, id);
        return "pessoajurForm";
    }

    public String Listar() {

        String jpql = "from PessoaJuridica order by nome";

        lista = em.createQuery(jpql).getResultList();

        return "pessoajurList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "pessoajurList";
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
            objeto = new PessoaJuridica();
            return Listar();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O CNPJ digitado já está cadastrado no Sistema!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return "";

    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(PessoaJuridica.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir a Pessoa Jurídica | A Pessoa possui dependência na classe Chamado!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);
        }
        return Listar();
    }

    public String filtrar() {
        return Listar();
    }

    public PessoaJuridica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaJuridica objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<PessoaJuridica> getLista() {
        return lista;
    }

    public void setLista(List<PessoaJuridica> lista) {
        this.lista = lista;
    }

    public List<PessoaJuridica> getFiltroPjur() {
        return filtroPjur;
    }

    public void setFiltroPjur(List<PessoaJuridica> filtroPjur) {
        this.filtroPjur = filtroPjur;
    }

    public List<Cidade> completeCidade(String query) {
        List<Cidade> results = em.createQuery("from Cidade where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}
