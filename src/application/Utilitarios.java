package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.entities.ContaDebitada;
import application.entities.Documento;
import application.entities.Pagamento;

public class Utilitarios {

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void imprimirMenu() {
		System.out.println("[0] - Mostrar todas as informações");
		System.out.println("[1] - Info. Conta Debitada");
		System.out.println("[2] - Info. Pagamento");
		System.out.println("[3] - Encerrar Programa\n");
		System.out.print("Opção: ");
	}
	
	// Cria uma obj Documento vazio.
	public static Documento createDocumento() {
		return new Documento("", new ContaDebitada("", "", ""), new Pagamento("", 0, ""), "10/10/2010");
	}
	
	// Método que recebe uma linha de um documento pdf, verifica se a linha tem informações importantes, se tiver altera no Obj Doc.
	public static void loadInformations(String[] linhas, Documento doc) {
		for(String linha : linhas) {
			if(linha.contains("Identificação no extrato:")) {
				String identificacaoExtrato = Utilitarios.find(linha, "Identificação no extrato:");
				doc.setIdentificacaoExtrato(identificacaoExtrato);
			} else if(linha.contains("Nome:")) {
				String nome = Utilitarios.find(linha, "Nome:");
				doc.getContaDebitada().setNome(nome);
			} else if(linha.contains("Agência:")) {
				String agencia = Utilitarios.find(linha, "Agência:");
				doc.getContaDebitada().setAgencia(agencia);
				String conta = Utilitarios.find(linha, "Conta:");
				doc.getContaDebitada().setConta(conta);
			} else if(linha.contains("Código de barras:")) {
				String codigoDeBarras = Utilitarios.find(linha, "Código de barras:");
				doc.getPagamento().setCodigoBarras(codigoDeBarras);
			} else if(linha.contains("Valor do documento:")) {
				String valorDocumento = Utilitarios.find(linha, "Valor do documento:").split(" ")[1].replace(",", ".");
				double documentoFormat = Double.parseDouble(valorDocumento);
				doc.getPagamento().setValor(documentoFormat);
			} else if(linha.contains("pagador:")) {
				String infoPagador = Utilitarios.find(linha, "pagador:");
				doc.getPagamento().setInfoPagador(infoPagador);
			} else if(linha.contains("Operação efetuada em ")) {
				String data = Utilitarios.find(linha, "efetuada em ");
				doc.setDataOperacao(data);
			}
		}
	}
	
	// Método que recebe um linha e o parametro que queremos o valor, retorna o valor do parametro informado.
	// Usando expressões regulares para achar
	public static String find(String text, String find) {
		String regex = null;
		if(find.equals("Agência:")) {
			regex = find+"\\s*(\\w+)";
		} else if(find.equals("Conta:")) {
			regex = find+"\\s*(\\d+\\s*-\\s*\\d+)";
		} else if(find.equals("pagador:")) {
			regex = find+"\\s*([A-Za-z]+\\s*\\d+)";
		} else if(find.equals("efetuada em ")) {
			regex = find+"\\s*(\\d{2}/\\d{2}/\\d{4})";
		}
		else {
			regex = find+"\\s*(.+)";
		}
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }
        
        return null;
	}
	
}
