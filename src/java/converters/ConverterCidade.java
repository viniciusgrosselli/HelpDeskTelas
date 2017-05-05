/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import beans.Cidade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import persistence.EntityManagerUtil;

/**
 *
 * @author Vinicius
 */
public class ConverterCidade implements Serializable, Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(Cidade.class, Integer.parseInt(string));

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Cidade obj = (Cidade) o;
        return obj.getId().toString();
    }
}