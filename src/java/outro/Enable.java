/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vinicius
 */
@ManagedBean(name = "habilitaControle")
@SessionScoped
public final class Enable {

    private boolean habilitaorcamento = false;
    private boolean habilitafechamento = false;

    public Enable() {
    }

    public boolean isHabilitafechamento() {
        return habilitafechamento;
    }

    public void setHabilitafechamento(boolean habilitafechamento) {
        this.habilitafechamento = habilitafechamento;
    }

    public boolean isHabilitaorcamento() {
        return habilitaorcamento;
    }

    public void setHabilitaorcamento(boolean habilitaorcamento) {
        this.habilitaorcamento = habilitaorcamento;
    }

    public boolean testeorc() {
        if (habilitaorcamento == true) {
            return false;
        } else {
            return true;
        }
    }

    public boolean testefech() {
        if (habilitafechamento == true) {
            return false;
        } else {
            return true;
        }
    }
}
