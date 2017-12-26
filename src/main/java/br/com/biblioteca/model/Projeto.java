package br.com.biblioteca.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "projeto")
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome do projeto é obrigatório")
	private String nome;
	
	@NotNull(message = "Data de de inicio é obrigatório")
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@NotNull(message = "Data de privisão é obrigatório")
	@Column(name = "data_previsao_fim")
	private LocalDate dataPrevisaoFim;
	
	@NotNull(message = "Data de fim é obrigatório")
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@NotBlank(message = "Descrição do projeto é obrigatória")
	private String descricao;
	
	@NotNull(message = "Status é obrigatório")
	@Enumerated(EnumType.STRING)
	private StatusProjeto status;
	
	@NotNull(message = "Orçameto é obrigatório")
	private BigDecimal orcamento;
	
	@NotNull(message = "Classificação de risco é obrigatório")
	@Enumerated(EnumType.STRING)
	private RiscoProjeto risco;
	
	@NotNull(message = "Nome do gerente do projeto é obrigatório")
	@ManyToOne
	@JoinColumn(name = "idgerente")
	private Pessoa gerente;
	
	@ManyToMany
	@JoinTable(name = "membros", joinColumns = @JoinColumn(name = "idprojeto")
				, inverseJoinColumns = @JoinColumn(name = "idpessoa"))	
	private List<Pessoa> pessoas = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Pessoa getGerente() {
		return gerente;
	}

	public void setGerente(Pessoa gerente) {
		this.gerente = gerente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusProjeto getStatus() {
		return status;
	}

	public void setStatus(StatusProjeto status) {
		this.status = status;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public RiscoProjeto getRisco() {
		return risco;
	}

	public void setRisco(RiscoProjeto risco) {
		this.risco = risco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
