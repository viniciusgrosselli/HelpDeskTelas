package controllers;

import beans.Cidade;
import beans.Estado;
import converters.ConverterEstado;
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
@ManagedBean(name = "cidadeControle")
@SessionScoped
public final class CidadeControle {

    private Cidade objeto = new Cidade();
    private List<Cidade> filtroCidade;
    private EntityManager em;
    private List<Cidade> lista = new ArrayList<Cidade>();
    private ConverterEstado converterEstado = new ConverterEstado();

    public ConverterEstado getConverterEstado() {
        return converterEstado;
    }

    public void setConverterEstado(ConverterEstado converterEstado) {
        this.converterEstado = converterEstado;
    }

    public CidadeControle() {
        lista = new ArrayList<Cidade>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Cidade();
        Listar();
    }

    public String incluir() {

        objeto = new Cidade();
        return "cidadeForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(Cidade.class, id);
        return "cidadeForm";
    }

    public String Listar() {

        String jpql = "from Cidade order by nome";
        lista = em.createQuery(jpql).getResultList();

        return "cidadeList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "cidadeList";
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
        objeto = new Cidade();
        return Listar();

    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Cidade.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir a Cidade | A Cidade possui dependências na classe Pessoa!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public String filtrar() {
        return Listar();
    }

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Cidade> getLista() {
        return lista;
    }

    public void setLista(List<Cidade> lista) {
        this.lista = lista;
    }

    public List<Cidade> getFiltroCidade() {
        return filtroCidade;
    }

    public void setFiltroCidade(List<Cidade> filtroCidade) {
        this.filtroCidade = filtroCidade;
    }

    public List<Estado> completeEstado(String query) {
        List<Estado> results = em.createQuery("from Estado where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}
