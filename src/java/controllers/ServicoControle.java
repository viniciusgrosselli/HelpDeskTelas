package controllers;

import beans.Equipamento;
import beans.Servico;
import converters.ConverterEquipamento;
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
@ManagedBean(name = "servicoControle")
@SessionScoped
public final class ServicoControle {

    private Servico objeto = new Servico();
    private List<Servico> filtroServico;
    private EntityManager em;
    private List<Servico> lista = new ArrayList<Servico>();
    private ConverterEquipamento converterEquipamento = new ConverterEquipamento();

    public ConverterEquipamento getConverterEquipamento() {
        return converterEquipamento;
    }

    public void setConverterEquipamento(ConverterEquipamento converterEquipamento) {
        this.converterEquipamento = converterEquipamento;
    }

    public ServicoControle() {
        lista = new ArrayList<Servico>();
        em = GerarEntityManager.getInstance().getEntityManager();
        objeto = new Servico();
        Listar();
    }

    public String incluir() {

        objeto = new Servico();
        return "servicoForm";
    }

    public String alterar(Integer id) {
        objeto = em.find(Servico.class, id);
        return "servicoForm";
    }

    public String Listar() {

        String jpql = "from Servico order by descricao";

        lista = em.createQuery(jpql).getResultList();

        return "servicoList";


    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "servicoList";
    }

    public String ultimo() {
        return Listar();

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
        objeto = new Servico();

        return Listar();

    }

    public String filtrar() {
        return Listar();
    }

    public String excluir(Integer id) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Servico.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluir o Serviço | O Serviço possui dependências na classe Chamado!", "");
            FacesContext.getCurrentInstance().addMessage(null, mensagem);


        }
        return Listar();
    }

    public Servico getObjeto() {
        return objeto;
    }

    public void setObjeto(Servico objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Servico> getLista() {
        return lista;
    }

    public void setLista(List<Servico> lista) {
        this.lista = lista;
    }

    public List<Servico> getFiltroServico() {
        return filtroServico;
    }

    public void setFiltroServico(List<Servico> filtroServico) {
        this.filtroServico = filtroServico;
    }

    public List<Equipamento> completeEquipamento(String query) {
        List<Equipamento> results = em.createQuery("from Equipamento where upper(tipo) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by tipo").getResultList();
        return results;
    }
}
