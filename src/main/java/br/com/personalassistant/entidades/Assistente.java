package br.com.personalassistant.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.personalassistant.enums.TIPO_USUARIO;

@Table(name = "TB_ASSISTENTE")
@Entity(name = "Assistente")
@DiscriminatorValue("assist")
public class Assistente extends Usuario{
	
	private static final long serialVersionUID = 5159659883981518233L;
		
	@Column(nullable = false)
	private Double precoFixo;
	
	@Column(nullable = false)
	private Double precoHora;
	
	@Transient
	private Long quantServicosPrestados;
	
	@Transient
	private String nivelExperiencia;
	
	@Transient
	private int reputacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "endereco_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoEndereco_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="endereco_FK", nullable=false)
	private Endereco endereco; // unidirecional

	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "categoriaServico_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoCategoriaServico_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="categoriaServico_FK", nullable=false)
	private CategoriaServico categoriaServico; // unidirecional

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "assistente")
	@Columns(columns = {
		@Column(name = "assistente_FK"),
		@Column(name = "ultimaAtualizacaoAssistente_FK")})
	//@Column(name="assistente_FK")
	private List<Lance> lances = new ArrayList<Lance>(); // bidirecional 
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "assistente_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoAssistente_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="assistente_FK")
	private List<Capacidade> capacidades; // unidirecional
	
	public Assistente() {
		super();
		this.setTipoUsuario(TIPO_USUARIO.ASSISTENTE);
	}

	public Assistente(Double precoFixo, Double precoHora, Endereco endereco, CategoriaServico categoriaServico,
			List<Capacidade> capacidades) {
		super();
		this.precoFixo = precoFixo;
		this.precoHora = precoHora;
		this.endereco = endereco;
		this.categoriaServico = categoriaServico;
		this.capacidades = capacidades;
		this.setTipoUsuario(TIPO_USUARIO.ASSISTENTE);
	}

	public Double getPrecoFixo() {
		return precoFixo;
	}

	public void setPrecoFixo(Double precoFixo) {
		this.precoFixo = precoFixo;
	}
	
	public Double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(Double precoHora) {
		this.precoHora = precoHora;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Capacidade> getCapacidades(){
		return capacidades;
	}

	public void setCapacidades(List<Capacidade> capacidades){
		this.capacidades = capacidades;
	}
	
	public Long getQuantServicosPrestados() {
		return quantServicosPrestados;
	}

	public void setQuantServicosPrestados(Long quantServicosPrestados) {
		this.quantServicosPrestados = quantServicosPrestados;
	}

	public String getNivelExperiencia() {
		return nivelExperiencia;
	}

	public void setNivelExperiencia(String nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}

	public int getReputacao() {
		return reputacao;
	}

	public void setReputacao(int reputacao) {
		this.reputacao = reputacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capacidades == null) ? 0 : capacidades.hashCode());
		result = prime * result + ((categoriaServico == null) ? 0 : categoriaServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		result = prime * result + ((nivelExperiencia == null) ? 0 : nivelExperiencia.hashCode());
		result = prime * result + ((precoFixo == null) ? 0 : precoFixo.hashCode());
		result = prime * result + ((precoHora == null) ? 0 : precoHora.hashCode());
		result = prime * result + ((quantServicosPrestados == null) ? 0 : quantServicosPrestados.hashCode());
		result = prime * result + reputacao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assistente other = (Assistente) obj;
		if (capacidades == null) {
			if (other.capacidades != null)
				return false;
		} else if (!capacidades.equals(other.capacidades))
			return false;
		if (categoriaServico == null) {
			if (other.categoriaServico != null)
				return false;
		} else if (!categoriaServico.equals(other.categoriaServico))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (lances == null) {
			if (other.lances != null)
				return false;
		} else if (!lances.equals(other.lances))
			return false;
		if (nivelExperiencia == null) {
			if (other.nivelExperiencia != null)
				return false;
		} else if (!nivelExperiencia.equals(other.nivelExperiencia))
			return false;
		if (precoFixo == null) {
			if (other.precoFixo != null)
				return false;
		} else if (!precoFixo.equals(other.precoFixo))
			return false;
		if (precoHora == null) {
			if (other.precoHora != null)
				return false;
		} else if (!precoHora.equals(other.precoHora))
			return false;
		if (quantServicosPrestados == null) {
			if (other.quantServicosPrestados != null)
				return false;
		} else if (!quantServicosPrestados.equals(other.quantServicosPrestados))
			return false;
		if (reputacao != other.reputacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assistente [precoFixo=" + precoFixo + ", precoHora=" + precoHora + ", quantServicosPrestados="
				+ quantServicosPrestados + ", nivelExperiencia=" + nivelExperiencia + ", reputacao=" + reputacao
				+ ", endereco=" + endereco + ", categoriaServico=" + categoriaServico + ", lances=" + lances
				+ ", capacidades=" + capacidades + ", getPk()=" + getPk() + ", getNome()=" + getNome() + ", getEmail()="
				+ getEmail() + ", getSenha()=" + getSenha() + ", getNumTelefonico()=" + getNumTelefonico()
				+ ", getTipoUsuario()=" + getTipoUsuario() + "]";
	}
	
}
