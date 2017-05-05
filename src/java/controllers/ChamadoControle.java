/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Chamado;
import beans.Equipamento;
import beans.Funcionario;
import beans.Historico;
import beans.Peca;
import beans.Pessoa;
import beans.Servico;
import converters.ConverterEquipamento;
import converters.ConverterFuncionario;
import converters.ConverterPeca;
import converters.ConverterPessoa;
import converters.ConverterServico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import persistence.GerarEntityManager;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "chamadoControle")
@SessionScoped
public final class ChamadoControle {

    private Chamado objeto = new Chamado();
    private Historico objetohistorico = new Historico();
    private List<Chamado> filtroChamado;
    private EntityManager em;
    private List<Chamado> lista = new ArrayList<Chamado>();
    private List<Chamado> listaencerrados = new ArrayList<Chamado>();
    private ConverterEquipamento converterEquipamento = new ConverterEquipamento();
    private ConverterPeca converterPeca = new ConverterPeca();
    private ConverterServico converterServico = new ConverterServico();
    private ConverterPessoa converterPessoa = new ConverterPessoa();
    private ConverterFuncionario converterFuncionario = new ConverterFuncionario();

    public ChamadoControle() {
        lista = new ArrayList<Chamado>();
        listaencerrados = new ArrayList<Chamado>();
        em = GerarEntityManager.getInstance().getEntityManager();
        Listar();
        ListarEncerrados();
        objeto = new Chamado();
    }

    public String incluir() {

        objeto = new Chamado();
        objeto.setDataAbertura(new Date());
        return "chamadoForm";
    }

    public String alterar(Integer id) {

        objeto = em.find(Chamado.class, id);
        objeto.setDataAlteracao(new Date());
        return "chamadoEditar";
    }

    public String os(Integer id) {

        objeto = em.find(Chamado.class, id);
        return "chamadoOS";
    }

    public String Listar() {

        String jpql = "from Chamado where status <> 'Encerrado' order by dataMarcada";
        lista = em.createQuery(jpql).getResultList();
        return "chamadoList";

    }

    public String ListarEncerrados() {
        String jpql = "from Chamado where status = 'Encerrado'";
        listaencerrados = em.createQuery(jpql).getResultList();

        return "encerradoList";
    }

    public String cancelar() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
        Listar();
        return "chamadoList";
    }

    public String gravar() {
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
        em.getTransaction().begin();
        if (objeto.getId() == null) {
            em.persist(objeto);

        } else {
            em.merge(objeto);

            objetohistorico.setObservacoes(objeto.getObservacoes());
            objetohistorico.setDataAbertura(objeto.getDataAbertura());
            objetohistorico.setDataAlteracao(objeto.getDataAlteracao());
            objetohistorico.setDataFechamento(objeto.getDataFechamento());
            objetohistorico.setDataMarcada(objeto.getDataMarcada());
            objetohistorico.setDataOrcamento(objeto.getDataOrcamento());
            objetohistorico.setEquipamento(objeto.getEquipamento());
            objetohistorico.setFuncionario(objeto.getFuncionario());
            objetohistorico.setId(objeto.getId());
            objetohistorico.setObspeca(objeto.getObspeca());
            objetohistorico.setOrcamento(objeto.getOrcamento());
            objetohistorico.setPeca(objeto.getPeca());
            objetohistorico.setPessoa(objeto.getPessoa());
            objetohistorico.setPrioridade(objeto.getPrioridade());
            objetohistorico.setProblema(objeto.getProblema());
            objetohistorico.setResolucao(objeto.getResolucao());
            objetohistorico.setServico(objeto.getServico());
            objetohistorico.setStatus(objeto.getStatus());

            em.persist(objetohistorico);

        }
        em.getTransaction().commit();
        objeto = new Chamado();
        objetohistorico = new Historico();
        objeto.setDataAlteracao(new Date());
        ListarEncerrados();
        return Listar();

    }

 

    public String encerrar(Integer id) {
        objeto = em.find(Chamado.class, id);
        objeto.setDataFechamento(new Date());
        return "chamadoFormEncerrar";

    }

    public String filtrar() {
        return Listar();
    }

    public Chamado getObjeto() {
        return objeto;
    }

    public void setObjeto(Chamado objeto) {
        this.objeto = objeto;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Chamado> getLista() {
        return lista;
    }

    public void setLista(List<Chamado> lista) {
        this.lista = lista;
    }

    public List<Chamado> getFiltroChamado() {
        return filtroChamado;
    }

    public void setFiltroChamado(List<Chamado> filtroChamado) {
        this.filtroChamado = filtroChamado;
    }

    public List<Chamado> getListaencerrados() {
        return listaencerrados;
    }

    public void setListaencerrados(List<Chamado> listaencerrados) {
        this.listaencerrados = listaencerrados;
    }

    public ConverterPeca getConverterPeca() {
        return converterPeca;
    }

    public void setConverterPeca(ConverterPeca converterPeca) {
        this.converterPeca = converterPeca;
    }

    public ConverterServico getConverterServico() {
        return converterServico;
    }

    public void setConverterServico(ConverterServico converterServico) {
        this.converterServico = converterServico;
    }

    public ConverterEquipamento getConverterEquipamento() {
        return converterEquipamento;
    }

    public void setConverterEquipamento(ConverterEquipamento converterEquipamento) {
        this.converterEquipamento = converterEquipamento;
    }

    public ConverterPessoa getConverterPessoa() {
        return converterPessoa;
    }

    public void setConverterPessoa(ConverterPessoa converterPessoa) {
        this.converterPessoa = converterPessoa;
    }

    public ConverterFuncionario getConverterFuncionario() {
        return converterFuncionario;
    }

    public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
        this.converterFuncionario = converterFuncionario;
    }

    public Historico getObjetohistorico() {
        return objetohistorico;
    }

    public void setObjetohistorico(Historico objetohistorico) {
        this.objetohistorico = objetohistorico;
    }

    public List<Equipamento> completeEquipamento(String query) {
        List<Equipamento> results = em.createQuery("from Equipamento where upper(tipo) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by tipo").getResultList();
        return results;
    }

    public List<Servico> completeServico(String query) {
        List<Servico> results = em.createQuery("from Servico where upper(descricao) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by descricao").getResultList();
        return results;
    }

    public List<Peca> completePeca(String query) {
        List<Peca> results = em.createQuery("from Peca where upper(descricao) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by descricao").getResultList();
        return results;
    }

    public List<Pessoa> completePessoa(String query) {
        List<Pessoa> results = em.createQuery("from Pessoa where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }

    public List<Funcionario> completeFuncionario(String query) {
        List<Funcionario> results = em.createQuery("from Funcionario where upper(nome) like "
                + "'" + query.trim().toUpperCase() + "%' "
                + "order by nome").getResultList();
        return results;
    }
}
