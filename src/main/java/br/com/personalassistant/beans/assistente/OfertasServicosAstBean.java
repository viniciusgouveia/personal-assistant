package br.com.personalassistant.beans.assistente;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.LanceService;
import br.com.personalassistant.services.OfertaServicoService;

@Named
@ViewScoped
public class OfertasServicosAstBean extends AbstractBean {

	private static final long serialVersionUID = -3648717368496249778L;
	
	@Inject private OfertaServicoService ofertaServicoService;
	@Inject private AssistenteService assistenteService;
	@Inject private LanceService lanceService;
	private List<OfertaServico> ofertasServicos;
	private OfertaServico ofertaServico;
	private Lance lance;
	
	public void preRenderView(){
		
		try {
			this.lance = new Lance();
			
			Assistente assistente = this.assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			Long idCategoriaServico = assistente.getCategoriaServico().getPk().getId();
			this.ofertasServicos = ofertaServicoService.getAll();
		
			this.lance.setAssistente(assistente);
		}
		catch (ServiceException | ObjetoNaoExisteException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
		
	}

	public void fazerLance(OfertaServico oferta){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {
			this.lance.setOfertaServico(oferta);
			
			this.lanceService.save(this.lance);
			
			this.ofertaServicoService.update(oferta);
			
			msg = "Lance feito com sucesso, aguarde a resposta do contratante";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch (ServiceException e) {
			msg = "Ocorreu um erro ao tentar fazer o lance, recarregue a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;
			
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));
		
	}
	
	public List<OfertaServico> getOfertasServicos() {
		return ofertasServicos;
	}

	public void setOfertasServicos(List<OfertaServico> ofertasServicos) {
		this.ofertasServicos = ofertasServicos;
	}

	public Lance getLance() {
		return lance;
	}

	public void setLance(Lance lance) {
		this.lance = lance;
	}

	public OfertaServicoService getOfertaServicoService() {
		return ofertaServicoService;
	}

	public void setOfertaServicoService(OfertaServicoService ofertaServicoService) {
		this.ofertaServicoService = ofertaServicoService;
	}
	
}
