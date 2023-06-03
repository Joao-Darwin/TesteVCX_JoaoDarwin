package application.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Documento {
	
	private String identificacaoExtrato;
	private ContaDebitada contaDebitada;
	private Pagamento pagamento;
	private Date dataOperacao;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	
	public Documento() {
	}
	
	//No construtor eu já implemento uma máscara para a data da operação
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
