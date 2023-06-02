package application.entities;

import java.util.Objects;

public class ContaDebitada {
	
	private String nome;
	private String agencia;
	private String conta;
	
	public ContaDebitada(String nome, String agencia, String conta) {
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDebitada other = (ContaDebitada) obj;
		return Objects.equals(conta, other.conta);
	}
}
