package mpei;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shingle {
	private static int ktitle = 5;
	private static int kcontent = 10;
	
	
	public static String[] createShingleTitle(String titulo){
		String nospaces = titulo.replaceAll("\\s+|\\.|\\,|\\;|\\:", "");
		List<String> shingles = new ArrayList<>();
		for(int j=0; j<nospaces.length()-ktitle+1; j++) {
			String word = "";
			word = nospaces.substring(j, j+ktitle).toLowerCase();
			if(!shingles.contains(word)) {
				shingles.add(word);
				System.out.print(word + " ");
			}
		}
		return shingles.toArray(new String[0]);
	}
	
	public static String[] createShingleContent(String content){
		String nospaces = content.replaceAll("\\s+|\\.|\\,|\\;|\\:", "");
		List<String> shingles = new ArrayList<>();
		for(int j=0; j<nospaces.length()-kcontent+1; j++) {
			String word = "";
			word = nospaces.substring(j, j+kcontent).toLowerCase();
			if(!shingles.contains(word)) {
				shingles.add(word);
				System.out.print(word + " ");
			}
		}
		return shingles.toArray(new String[0]);
	}
	
	public static boolean writeShingleFile(String path) throws IOException {
		File shingles = new File("shingles.txt");
		String title = "";
		String content = "";
		shingles.createNewFile();
		Scanner readfx;
		try {
			readfx = new Scanner(new File(path));
			while(readfx.hasNextLine()) {
				String line = readfx.nextLine();
				if(line.startsWith("Title: ")) {
					title = line.substring(7);
				}
				if(line.startsWith("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
					while(readfx.hasNextLine()) {
						content = content + " " + readfx.nextLine();
					}
				}
				readfx.close();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		createShingleContent(content);
		createShingleTitle(title);
		return true;	
	}

	public static void main(String args[]) throws IOException {
		Shingle.createShingleTitle("Alabama");
		Shingle.createShingleContent("Amor � fogo que arde sem se ver\r\n" + 
				"\r\n" + 
				"Amor � fogo que arde sem se ver;\r\n" + 
				"� ferida que d�i e n�o se sente;\r\n" + 
				"� um contentamento descontente;\r\n" + 
				"� dor que desatina sem doer;\r\n" + 
				"\r\n" + 
				"� um n�o querer mais que bem querer;\r\n" + 
				"� solit�rio andar por entre a gente;\r\n" + 
				"� nunca contentar-se de contente;\r\n" + 
				"� cuidar que se ganha em se perder;\r\n" + 
				"\r\n" + 
				"� querer estar preso por vontade;\r\n" + 
				"� servir a quem vence, o vencedor;\r\n" + 
				"� ter com quem nos mata lealdade.\r\n" + 
				"\r\n" + 
				"Mas como causar pode seu favor\r\n" + 
				"Nos cora��es humanos amizade,\r\n" + 
				"Se t�o contr�rio a si � o mesmo Amor?");
//		Shingle.writeShingleFile("C:\\Users\\Ahri Gon�alves\\Desktop");
		
	}
}
