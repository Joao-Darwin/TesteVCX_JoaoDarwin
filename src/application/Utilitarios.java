package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.entities.ContaDebitada;
import application.entities.Documento;
import application.entities.Pagamento;

/**
* <h1>Utilitarios</h1>
* Contem métodos importantes para o funcionamento do projeto
* <p>
*
* @author  João darwin
*/
public class Utilitarios {

	/**
	   * Este método é usado para limpar a tela do terminal.
	   * @return void, a tela é limpada
	   */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	/**
	   * Este método é usado para imprimir na tela do terminal um menu com opções de execução que o programa pode fazer.
	   * @return void, imprime na tela do terminal uma menu
	   */
	public static void imprimirMenu() {
		System.out.println("[0] - Mostrar todas as informações");
		System.out.println("[1] - Info. Conta Debitada");
		System.out.println("[2] - Info. Pagamento");
		System.out.println("[3] - Encerrar Programa\n");
		System.out.print("Opção: ");
	}
	
	/**
	   * Este método cria uma Documento vazio, usado no começo do programa.
	   * @return Documento, documento vazio
	   */
	public static Documento createDocumento() {
		return new Documento("", new ContaDebitada("", "", ""), new Pagamento("", 0, ""), "10/10/2010");
	}
	
	/**
	   * Este método é usado para alterar informaçoes no documento que acabou de ser criado. Essas informações foram baseadas em uma array de strings que ele recebe
	   * O foreach dentro desse array a cada nova volta verifica se a linha contem determinada informação, caso sim, usamos o método find para pegar o valor dessa informação e atualizar no Documento doc.
	   * @param linhas, é uma array de Strings
	   * @param doc, Documento onde serão processadas as informações.
	   * @return void, o Documento doc é alterado em memória usando os sets do obj
	   */
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
	
	/**
	   * Este método é usado para encontrar determinada valor de uma determinada sentença.
	   * @param text, é a string completa onde temos vários valores
	   * @param find, é o valor que queremos encontrar
	   * Ex: text == "Conta: xxxx Agencia: yyy" e find == "Conta:" o retorno deve ser "xxxx"
	   * @return String, o valor retornado é o valor da sentença que passamos no find.
	   */
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
