package application.entities;

import java.util.Objects;

/**
* <h1>ContaDebitada</h1>
* A classe conta debitada armazena informações extraídas do pdf relacionadas a conta, como nome, agencia e numero da conta
* <p>
*
* @author  João darwin
*/
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nome da Conta: " + nome + "\n");
		sb.append("Agência: " + agencia + "\n");
		sb.append("Conta: " + conta + "\n\n");
		return sb.toString();
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
