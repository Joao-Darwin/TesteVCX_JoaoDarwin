package application.entities;

import java.util.Objects;

public class Pagamento {
	
	private String codigoBarras;
	private double valor;
	private String infoPagador;
	
	public Pagamento(String codigoBarras, double valor, String infoPagador) {
		this.codigoBarras = codigoBarras;
		this.valor = valor;
		this.infoPagador = infoPagador;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		if(valor <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.valor = valor;
		}
	}

	public String getInfoPagador() {
		return infoPagador;
	}

	public void setInfoPagador(String infoPagador) {
		this.infoPagador = infoPagador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarras);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(codigoBarras, other.codigoBarras);
	}
}
