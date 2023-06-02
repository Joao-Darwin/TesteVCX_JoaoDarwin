package application;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Main {
    public static void main(String[] args) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        document.addPage(page);

        try {
            document.save("/home/joao-darwin/arquivo.pdf");
            System.out.println("PDF criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

