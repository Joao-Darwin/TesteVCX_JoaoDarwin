package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import application.entities.Documento;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		PDDocument documento = null;
		
		// Lógica para quando o PDF não é encontrado 
		boolean controler = true;
		do {
			try {
				Utilitarios.clearScreen();
				System.out.print("Informe o caminho e o nome do arquivo em PDF (/home/exemplo.pdf): ");
				String caminho = scanner.next();
				File oldFile = new File(caminho);
				documento = PDDocument.load(oldFile);
				controler = false;
			} catch (FileNotFoundException e) {
				System.out.println("PDF não encontrado! Informe novamente.");
				scanner.nextLine();
				scanner.nextLine();
			}
		} while(controler);

		Documento doc = Utilitarios.createDocumento();
		
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(documento);
	
		// Array com cada linha do documento
		// \\r?\\n == quebra de linha
		String[] linhas = text.split("\\r?\\n");
		
		// Carregar informações do pdf
		Utilitarios.loadInformations(linhas, doc);
		
		// Lógica para renderização do menu em loop
		boolean controleMenu = true;
		do {
			Utilitarios.clearScreen();
			Utilitarios.imprimirMenu();
			int opcaoEscannerolhida = scanner.nextInt();
			scanner.nextLine();
			
			if(opcaoEscannerolhida == 0) {
				System.out.println(doc.toString());
				scanner.nextLine();
			} else if(opcaoEscannerolhida == 1) {
				System.out.println(doc.getContaDebitada().toString());
				scanner.nextLine();
			} else if(opcaoEscannerolhida == 2) {
				System.out.println(doc.getPagamento().toString());
				scanner.nextLine();
			} else if(opcaoEscannerolhida == 3) {
				System.out.println("Até logo!");
				controleMenu = false;
			} else {
				System.out.println("Opção inválida!");
				scanner.nextLine();
			}
		} while(controleMenu);
				
		documento.close();
		scanner.close();
	}
}
