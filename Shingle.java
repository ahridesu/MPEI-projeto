package mpei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Shingle {
	private static int ktitle = 4;
	private static int kcontent = 10;
	private static int numhash = 10;
	public static int matrixTitle[][] = new int[50][numhash];
	public static int matrixContent[][] = new int[50][numhash];
	
	public static void createShingleFile(String path){
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
	            		line = in.readLine();
	            		sb.append(line);
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
		String s = titulo.replaceAll("[^a-zA-Z0-9 ]", "");
		Set<String> shingles = new HashSet<>();
		for(int j=0; j<s.length()-ktitle+1; j++) {
			String word = "";
			word = s.substring(j, j+ktitle).toLowerCase();
			shingles.add(word);
		}
		return shingles.toArray(new String[0]);
	}
	
	private static String[] createShingleContent(String content){
		String s = content.replaceAll("[^a-zA-Z0-9 ]", "");
		Set<String> shingles = new HashSet<>();
		for(int j=0; j<s.length()-kcontent+1; j++) {
			String word = "";
			word = s.substring(j, j+kcontent).toLowerCase();
			shingles.add(word);
		}
		return shingles.toArray(new String[0]);
	}
	
	private static void createShingleFile(String title, String[] titleset, String[] contentset) throws IOException{
		File fx = new File("C:\\Users\\Ahri Gonçalves\\eclipse-workspace\\p3\\src\\mpei\\" + title + "_shingles.txt"); 
		PrintWriter pw = new PrintWriter(fx);
		for(String i: titleset)
			pw.print(i + "_");					//shingles divididos por _
		pw.println("");
		pw.println("***");
		for(String i: contentset)
			pw.print(i + "_");					//shingles divididos por _
		pw.close();
		System.out.println("SHINGLE FILE FOR "+title+" BOOK CREATED!");
	}
	
	//hashing dos shingles
	private static int shingleToHash(String shingle) {
		int hash = 0;
		for (int i = 0; i < shingle.length(); i++) {
			hash = shingle.charAt(i) + ((hash << 5) - hash);
		}
		return hash;
	}
	
	public static void readShingleFile(String path) throws IOException {
		File fx = new File(path);
		int index = ListaLivros.getIndex(fx.getName().replace("_shingles.txt", ""));
		if(index == -1) {
			throw new IllegalArgumentException("PROBLEMA COM INDEX DO LIVRO");
		}
		Scanner read = new Scanner(fx);
		while(read.hasNextLine()) {
			String line = read.nextLine();
			if(line.equals("***")) {
				break;
			}
			Scanner readtitle = new Scanner(line).useDelimiter("_");
			while(readtitle.hasNext()) {
				String shingle = readtitle.next();
				HashFunction[] hf = getHashFunctions(shingle);
				for(int i=0; i<hf.length; i++) {
					if(matrixTitle[0][i] == 0 || matrixTitle[0][i] > hf[i].signature()) {
						matrixTitle[0][i] = hf[i].signature();
					}
				}
			}
		}
		while(read.hasNextLine()) {
			String line = read.nextLine();
			Scanner readcontent = new Scanner(line).useDelimiter("_");
			while(readcontent.hasNext()){
				String shingle = readcontent.next();
				HashFunction[] hf = getHashFunctions(shingle);
				for(int i=0; i<hf.length; i++) {
					if(matrixContent[0][i] == 0 || matrixContent[0][i] > hf[i].signature()) {
						matrixContent[0][i] = hf[i].signature();
					}
				}
			}
		}
	}
	
	private static HashFunction[] getHashFunctions(String shingle) {
		int id = shingleToHash(shingle);
		HashFunction array[] = new HashFunction[numhash];
		for(int i=0; i<array.length; i++) {
			array[i] = new HashFunction(id);
		}
		return array;
	}
	
	public static void main(String args[]) throws IOException {
		Shingle.createShingleFile("C:\\Users\\Ahri Gonçalves\\eclipse-workspace\\p3\\src\\mpei\\test.txt"); // pode ser qualquer path
		Livro livro = new Livro("Dracula");
		ListaLivros.lista.add(livro);
		Shingle.readShingleFile("C:\\Users\\Ahri Gonçalves\\eclipse-workspace\\p3\\src\\mpei\\Dracula_shingles.txt");
		for (int[] row : matrixTitle) 
            System.out.println(Arrays.toString(row)); 
//		for (int[] row : matrixContent) 
//            System.out.println(Arrays.toString(row)); 
	}
}
