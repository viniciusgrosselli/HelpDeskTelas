package controllers;

import beans.Equipamento;
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
@ManagedBean(name = "equipamentoControle")
@SessionScoped
public final class EquipamentoControle {

    private Equipamento objeto = new Equipamento();
    private List<Equipamento> filtroEquipamento;
    private EntityManager em;
    private List<Equipamento> lista = new ArrayList<Equipamento>();

    public EquipamentoControle() {
        lista = new ArrayList<Equipamento>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Equipamento();
        Listar();
    }

    public String incluir() {

        objeto = new Equipamento();
        return "equipamentoForm";
    }

    public String alterar(Integer id) {
        objeto = em.find(Equipamento.class, id);
        return "equipamentoForm";
    }

    public String Listar() {

        String jpql = "from Equipamento order by descricao";

        lista = em.createQuery(jpql).getResultList();

        return "equipamentoList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "equipamentoList";
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
        objeto = new Equipamento();

        return Listar();

    }

    public String filtrar() {
        return Listar();
    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Equipamento.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir o Equipamento | O Equipamento possui dependências na classe Serviços!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public Equipamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Equipamento objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Equipamento> getLista() {
        return lista;
    }

    public void setLista(List<Equipamento> lista) {
        this.lista = lista;
    }

    public List<Equipamento> getFiltroEquipamento() {
        return filtroEquipamento;
    }

    public void setFiltroEquipamento(List<Equipamento> filtroEquipamento) {
        this.filtroEquipamento = filtroEquipamento;
    }
}
