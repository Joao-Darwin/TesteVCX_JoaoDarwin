package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.entities.ContaDebitada;
import application.entities.Documento;
import application.entities.Pagamento;

public class Utilitarios {

	public static Documento createDocumento() {
		return new Documento("", new ContaDebitada("", "", ""), new Pagamento("", 0, ""), "10/10/2010");
	}
	
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
