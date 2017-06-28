package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.personalassistant.dao.PropostaDAO;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.PersistenciaException;

public class PropostaConverter implements Converter {

	private PropostaDAO propostaDAO = new PropostaDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			Proposta proposta = propostaDAO.getById(Long.valueOf(value));
			return proposta;
		} 
		catch (NumberFormatException | PersistenciaException ex) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof Proposta)) {
			return null;
		}

		Proposta proposta = (Proposta) value;
		return String.valueOf(proposta.getId());
	}

}
