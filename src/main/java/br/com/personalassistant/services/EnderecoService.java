package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.EnderecoDAO;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class EnderecoService implements Serializable {

	private static final long serialVersionUID = -9005147697634584210L;

	@Inject
	private EnderecoDAO enderecoDAO;
	
	@Transacional
	public void save(Endereco endereco) throws ServiceException{
		try{
			this.enderecoDAO.save(endereco);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Endereco endereco) throws ServiceException{
		try{
			this.enderecoDAO.delete(endereco);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Endereco update(Endereco endereco) throws ServiceException{
		try{
			return this.enderecoDAO.update(endereco);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Endereco getById(PK id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.enderecoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Endereco> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.enderecoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Long generateId() throws ServiceException{

		Long id = this.enderecoDAO.generateId();
		
		this.enderecoDAO.updateSequenceId();
		
		return id;

	}
}
