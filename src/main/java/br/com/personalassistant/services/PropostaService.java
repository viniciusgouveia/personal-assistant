package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.PropostaDAO;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class PropostaService implements Serializable {

	private static final long serialVersionUID = 6845558854773354022L;

	@Inject
	private PropostaDAO propostaDAO;
	
	@Transacional
	public void save(Proposta proposta) throws ServiceException{
		try{
			this.propostaDAO.save(proposta);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Proposta proposta) throws ServiceException{
		try{
			this.propostaDAO.delete(proposta);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Proposta update(Proposta proposta) throws ServiceException{
		try{
			return this.propostaDAO.update(proposta);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Proposta getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.propostaDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Proposta> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.propostaDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public Proposta refresh(Proposta proposta) throws ServiceException{
		try{
			return this.propostaDAO.refresh(proposta);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}

}
