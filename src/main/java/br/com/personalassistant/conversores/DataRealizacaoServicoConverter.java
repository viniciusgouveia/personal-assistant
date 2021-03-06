//package br.com.personalassistant.conversores;
//
//import javax.enterprise.context.RequestScoped;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import br.com.personalassistant.services.DataRealizacaoServicoService;
//import br.com.personalassistant.entidades.DataRealizacaoServico;
//import br.com.personalassistant.excecoes.ServiceException;
//
//@Named
//@RequestScoped
//@FacesConverter(forClass = DataRealizacaoServico.class)
//public class DataRealizacaoServicoConverter implements Converter {
//
//	@Inject
//	private DataRealizacaoServicoService dataRealizacaoServicoService;
//	
//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		
//		if (value == null || (value.trim().isEmpty())) {
//			return null;
//		}
//
//		try {
//			DataRealizacaoServico dataRealizacaoServico = dataRealizacaoServicoService.getById(Long.valueOf(value));
//			return dataRealizacaoServico;
//		} 
//		catch (NumberFormatException | ServiceException ex) {
//			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
//					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
//			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
//			throw new ConverterException(msgErro);
//		}
//	}
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		
//		if (!(value instanceof DataRealizacaoServico)) {
//			return null;
//		}
//
//		DataRealizacaoServico dataRealizacaoServico = (DataRealizacaoServico) value;
//		return String.valueOf(dataRealizacaoServico.getId());
//	}
//
//}
