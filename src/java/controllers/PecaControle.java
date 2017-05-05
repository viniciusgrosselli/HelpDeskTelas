package controllers;

import beans.Equipamento;
import beans.Peca;
import converters.ConverterEquipamento;
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
@ManagedBean(name = "pecaControle")
@SessionScoped
public final class PecaControle {

    private Peca objeto = new Peca();
    private List<Peca> filtroPeca;
    private EntityManager em;
    private Map<String, String> listaOrdem = new HashMap<String, String>();
    private List<Peca> lista = new ArrayList<Peca>();
    private ConverterEquipamento converterEquipamento = new ConverterEquipamento();

    public ConverterEquipamento getConverterEquipamento() {
        return converterEquipamento;
    }

    public void setConverterEquipamento(ConverterEquipamento converterEquipamento) {
        this.converterEquipamento = converterEquipamento;
    }

    public PecaControle() {
        lista = new ArrayList<Peca>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Peca();
        Listar();
    }

    public String incluir() {

        objeto = new Peca();
        return "pecaForm";
    }

    public String alterar(Integer id) {
        objeto = em.find(Peca.class, id);
        return "pecaForm";
    }

    public String Listar() {

        String jpql = "from Peca order by descricao";

        lista = em.createQuery(jpql).getResultList();

        return "pecaList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "pecaList";
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
        objeto = new Peca();

        return Listar();

    }

    public String filtrar() {
        return Listar();
    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Peca.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir a Peça | A Peça possui dependências na classe Chamado!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public Peca getObjeto() {
        return objeto;
    }

    public void setObjeto(Peca objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Peca> getLista() {
        return lista;
    }

    public void setLista(List<Peca> lista) {
        this.lista = lista;
    }

    public Map<String, String> getListaOrdem() {
        return listaOrdem;
    }

    public void setListaOrdem(Map<String, String> listaOrdem) {
        this.listaOrdem = listaOrdem;
    }

    public List<Peca> getFiltroPeca() {
        return filtroPeca;
    }

    public void setFiltroPeca(List<Peca> filtroPeca) {
        this.filtroPeca = filtroPeca;
    }

    public List<Equipamento> completeEquipamento(String query) {
        List<Equipamento> results = em.createQuery("from Equipamento where upper(tipo) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by tipo").getResultList();
        return results;
    }
}
