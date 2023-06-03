package application.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
* <h1>Documento</h1>
* Classe que representa o documento onde será colocadas as informações extraidas do PDF
* Ele possui relacionamentos com a classes ContaDebitada e Pagamento
* <p>
*
* @author  João darwin
*/
public class Documento {
	
	private String identificacaoExtrato;
	private ContaDebitada contaDebitada;
	private Pagamento pagamento;
	private Date dataOperacao;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public Documento() {
	}
	
	// No caso da data da operação eu recebo uma string e converto para uma Date já passando uam máscara
	public Documento(String identificacaoExtrato, ContaDebitada contaDebitada, Pagamento pagamento, String dataOperacao) {
		this.identificacaoExtrato = identificacaoExtrato;
		this.contaDebitada = contaDebitada;
		this.pagamento = pagamento;
		try {
			this.dataOperacao = dateFormat.parse(dataOperacao.toString());
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getIdentificacaoExtrato() {
		return identificacaoExtrato;
	}
	
	public void setIdentificacaoExtrato(String identificacaoExtrato) {
		this.identificacaoExtrato = identificacaoExtrato;
	}
	
	public ContaDebitada getContaDebitada() {
		return contaDebitada;
	}
	
	public void setContaDebitada(ContaDebitada contaDebitada) {
		this.contaDebitada = contaDebitada;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Date getDataOperacao() {
		return dataOperacao;
	}
	
	public void setDataOperacao(String dataOperacao) {
		try {
			this.dataOperacao = dateFormat.parse(dataOperacao);
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nIdentificação do Extrato: " + identificacaoExtrato + "\n\n");
		sb.append(contaDebitada.toString());
		sb.append(pagamento.toString());
		sb.append("Data da Operação: " + dateFormat.format(dataOperacao) + "\n");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(identificacaoExtrato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		return Objects.equals(identificacaoExtrato, other.identificacaoExtrato);
	}
}
