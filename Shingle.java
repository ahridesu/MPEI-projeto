
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Shingle {					// destinado tratamento e cria��o de shingles
	private static int ktitle = 4;
	private static int kcontent = 10;
	private static int numhash = 100;
	private static HashFunction hashfuncs[] = new HashFunction[numhash];
	
	public Shingle() {
		for(int i=0; i<hashfuncs.length; i++) {
			hashfuncs[i] = new HashFunction();
		}
	}
	
	public static String createShingleFile(String path){
		File fx = new File(path);
		String title = "";
		String content = "";
		StringBuilder sb = new StringBuilder();
		String p = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
	        while ((line = in.readLine()) != null) {
	            if (line.startsWith("Title: ")) {
	                title = removeLinePrefix(line, "Title: ");   
		            line = in.readLine();
	            }
	            if(line.startsWith("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
	            	while((line = in.readLine()) != null) {
						if(!line.startsWith("*** END OF THIS PROJECT GUTENBERG EBOOK")){
							line = in.readLine();
	            			sb.append(line);
						}
	            	}
	            }
	        }
	        content = sb.toString();
	        String[] s_title = createShingleTitle(title);
	        String[] s_content = createShingleContent(content); 
	        p = createShingleFile(fx.getName().replace(".txt", ""), s_title, s_content);
		} 
		catch(IOException e) {
	        e.printStackTrace();
	    }
		return p;
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
	
	private static String createShingleFile(String name, String[] titleset, String[] contentset) throws IOException{
		String dir = new File(".").getCanonicalPath();
		File fx = new File (dir +"\\Shingles\\"+ name + "_shingles.txt"); 
		PrintWriter pw = new PrintWriter(fx);
		for(String i: titleset)
			pw.print(i + "_");					//shingles divididos por _
		pw.println("");
		pw.println("***");
		for(String i: contentset)
			pw.print(i + "_");					//shingles divididos por _
		pw.close();
		System.out.println("SHINGLE FILE FOR "+name+" BOOK CREATED!");
		return fx.getAbsolutePath();
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
		Livro livro = ListaLivros.searchLivro(fx.getName().replace("_shingles.txt", ""));
		livro.minHashTitle = new int[numhash];
		livro.minHashContent = new int[numhash];
		if(livro == null) throw new IllegalArgumentException("PROBLEMA COM O PATH");
		Scanner read = new Scanner(fx);
		while(read.hasNextLine()) {
			String line = read.nextLine();
			if(line.equals("***")) {
				break;
			}
			Scanner readtitle = new Scanner(line).useDelimiter("_");
			while(readtitle.hasNext()) {
				String shingle = readtitle.next();
				int[] hf = getHashFunctions(shingle);
				for(int i=0; i<hf.length; i++) {
					if(livro.minHashTitle[i] > hf[i] || livro.minHashTitle[i] == 0) {
						livro.minHashTitle[i] = hf[i];
					}
				}
			}
		}
		while(read.hasNextLine()) {
			String line = read.nextLine();
			Scanner readcontent = new Scanner(line).useDelimiter("_");
			while(readcontent.hasNext()){
				String shingle = readcontent.next();
				int[] hf = getHashFunctions(shingle);
				for(int i=0; i<hf.length; i++) {
					if(livro.minHashContent[i] > hf[i] || livro.minHashContent[i] == 0) {
						livro.minHashContent[i] = hf[i];
					}
				}
			}
		}
	}
	
	private static int[] getHashFunctions(String shingle) {
		int id = shingleToHash(shingle);
		int sign[] = new int[numhash];
		for(int i=0; i<hashfuncs.length; i++) {
			sign[i] = Math.abs((hashfuncs[i].a()*id + hashfuncs[i].b()) % HashFunction.prime);
		}
		return sign;
	}
	
	// public static void main(String args[]) throws IOException {
	// 	Shingle sg = new Shingle();
	// 	Livro livro = new Livro("test");
	// 	Livro livro1 = new Livro("test_copia");
	// 	ListaLivros.lista.add(livro);
	// 	ListaLivros.lista.add(livro1);
	// 	String path1 = Shingle.createShingleFile("C:\\Users\\Ahri Gon�alves\\eclipse-workspace\\p3\\src\\mpei\\test.txt"); // pode ser qualquer path
	// 	String path2 = Shingle.createShingleFile("C:\\Users\\Ahri Gon�alves\\eclipse-workspace\\p3\\src\\mpei\\test_copia.txt");
	// 	Shingle.readShingleFile(path1);
	// 	Shingle.readShingleFile(path2);
	// 	for(int i: livro.minHashTitle) 
	// 		System.out.print(i + " ");
	// 	System.out.println("");
	// 	for(int i: livro1.minHashTitle)
	// 		System.out.print(i + " ");
	// }
}
