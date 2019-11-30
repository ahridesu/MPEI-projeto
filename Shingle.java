package mpei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Shingle {
	private static int ktitle = 4;
	private static int kcontent = 10;
	
	
	public static void createShingleFile(String path){
		//Analisar o ficheiro, leitura 
		File fx = new File(path);
		String title = "";
		String content = "";
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
	        while ((line = in.readLine()) != null) {
	            if (line.startsWith("Title: ")) {
	                title = removeLinePrefix(line, "Title: ");   
		            line = in.readLine();
	            }
	            if(line.startsWith("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
	            	while(!((line = in.readLine()).startsWith("*** END OF THIS PROJECT GUTENBERG EBOOK"))) {
	            		sb.append(line);
	            		line = in.readLine();
	            	}
	            }
	        }
	        content = sb.toString();
	        String[] s_title = createShingleTitle(title);
	        String[] s_content = createShingleContent(content);
	        createShingleFile(title, s_title, s_content);
		} 
		catch(IOException e) {
	        e.printStackTrace();
	    }
	}

	private static String removeLinePrefix(String line, String prefix) {
	    return line.substring(prefix.length());
	}
			
	private static String[] createShingleTitle(String titulo) {
		String nospaces = titulo.replaceAll("\\s+|\\.|\\,|\\;|\\:", "");
		List<String> shingles = new ArrayList<>();
		for(int j=0; j<nospaces.length()-ktitle+1; j++) {
			String word = "";
			word = nospaces.substring(j, j+ktitle).toLowerCase();
			if(!shingles.contains(word)) {
				shingles.add(word);
				//System.out.print(word + " ");
			}
		}
		return shingles.toArray(new String[0]);
	}
	
	private static String[] createShingleContent(String content){
		String nospaces = content.replaceAll("\\s+|\\.|\\,|\\;|\\:", "");
		List<String> shingles = new ArrayList<>();
		for(int j=0; j<nospaces.length()-kcontent+1; j++) {
			String word = "";
			word = nospaces.substring(j, j+kcontent).toLowerCase();
			if(!shingles.contains(word)) {
				shingles.add(word);
				//System.out.println(word + " ");
			}
		}
		return shingles.toArray(new String[0]);
	}
	
	private static void createShingleFile(String title, String[] titleset, String[] contentset) throws IOException{
		File fx = new File("C:\\Users\\Ahri Gonçalves\\eclipse-workspace\\p3\\src\\mpei\\" + title + "_shingles.txt"); 
		PrintWriter pw = new PrintWriter(fx);
		for(String i: titleset)
			pw.print(i + " ");
		pw.println("");
		pw.println("");
		for(String i: contentset)
			pw.print(i + " ");
		pw.close();
		System.out.print("SHINGLE FILE FOR "+title+" BOOK CREATED!");
	}
	
	public static void main(String args[]) throws IOException {
		Shingle.createShingleFile("C:\\Users\\Ahri Gonçalves\\eclipse-workspace\\p3\\src\\mpei\\test.txt"); // pode ser qualquer path
	}
}
