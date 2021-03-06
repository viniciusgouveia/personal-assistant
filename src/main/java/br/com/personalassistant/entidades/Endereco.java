package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Endereco")
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable{

	private static final long serialVersionUID = -4023551797287042535L;
	
	@EmbeddedId
	private PK pk;
	
	@Column(nullable = false)
	private Integer numero;
	
	@Column(nullable = false)
	private String rua;
	
	private String referencia;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String estado;
	
	public Endereco() {
		super();
	}

	public Endereco(int numero, String rua, String referencia, String cep, String bairro, 
			String cidade, String estado) {
		super();
		this.numero = numero;
		this.rua = rua;
		this.referencia = referencia;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk){
		this.pk = pk;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Endereco [pk=" + pk + ", numero=" + numero + ", rua=" + rua + ", referencia=" + referencia + ", cep="
				+ cep + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + "]";
	}

	public String getEnderecoFormatado(){
		return "Rua: "+rua+", Nº: "+numero+", CEP: "+cep+", referência: "+referencia
				+", bairro: "+bairro+", cidade: "+cidade + ", estado: "+estado;
	}
	
	public String getEnderecoFormatadoReduzido(){
		return "Cidade: "+cidade + ", Estado: "+estado;
	}
	
}
