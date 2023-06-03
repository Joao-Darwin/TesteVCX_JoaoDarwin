package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import application.entities.Documento;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Informe o caminha e o nome do arquivo em PDF: ");
		String caminho = sc.next();
		File oldFile = new File(caminho);
		PDDocument documento = PDDocument.load(oldFile);

		Documento doc = Utilitarios.createDocumento();
		
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(documento);
	
		String[] linhas = text.split("\\r?\\n");
		
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
		
		
		documento.close();
		sc.close();
	}
}
