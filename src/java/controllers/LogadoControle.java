/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "logadoControle")
@SessionScoped
public class LogadoControle {

    private String usuariologado;
    UsuarioControle c = new UsuarioControle();

    public LogadoControle() {
    }

    public String Template() {
        if (c.getObjeto().getTipo().equalsIgnoreCase("Atendente")) {
            usuariologado = "template_atendente.xhtml";
        }

        return usuariologado;
    }

    public String getUsuariologado() {
        return usuariologado;
    }

    public void setUsuariologado(String usuariologado) {
        this.usuariologado = usuariologado;
    }
}
