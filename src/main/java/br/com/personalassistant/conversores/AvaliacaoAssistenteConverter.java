package br.com.personalassistant.conversores;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.services.AvaliacaoAssistenteService;
import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;

@Named
@RequestScoped
@FacesConverter(value="conversorAvaliacaoAssistente", forClass = AvaliacaoAssistente.class)
public class AvaliacaoAssistenteConverter implements Converter {

	@Inject
	private AvaliacaoAssistenteService avaliacaoAssistenteService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			AvaliacaoAssistente avaliacaoAssistente = avaliacaoAssistenteService.getById(Long.valueOf(value));
			return avaliacaoAssistente;
		} 
		catch (NumberFormatException | ServiceException | ObjetoNaoExisteException ex) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			
			ex.printStackTrace();
			
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof AvaliacaoAssistente)) {
			return null;
		}

		AvaliacaoAssistente avaliacaoAssistente = (AvaliacaoAssistente) value;
		return String.valueOf(avaliacaoAssistente.getId());
	}

}
