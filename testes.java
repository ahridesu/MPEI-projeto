import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class testes {
	public static void main(String args[]) throws IOException {
		//Criação dos BFs
		BloomFilter bf= new BloomFilter(20);
		CountingBloomFilter cbf = new CountingBloomFilter(20);
		
		
		/*INICIO DO TESTE DO PROGRAMA*/
		System.out.println("----------PROGRAMA DE TESTES-----------");
		
		System.out.println("--------VERIFICAÇÃO DOS LIVROS--------");
		Shingle sg = new Shingle();
		Scanner read = new Scanner(System.in);
		String path = new File(".").getCanonicalPath() + "//Testes//Livros";				// current path
		File pasta = new File(path);
		File[] listOfFiles = pasta.listFiles();
		for(File fx: listOfFiles) {
			bf.insert(fx.getName());
			Livro liv = new Livro(fx.getName());
			ListaLivros.lista.add(liv);
		 	String p = Shingle.createShingleFile(path + "//" + fx.getName());  		//JA FORAM CRIADOS OS FICHEIROS, ESTAS LINHAS NÃO SAO MAIS NECESSARIAS
		 	Shingle.readShingleFile(p);
			Shingle.readShingleFile(new File(".").getCanonicalPath() + "//Testes//Shingles//" + fx.getName().replace(".txt", "_shingles.txt"));
			System.out.println("ANALISED SHINGLE FILE " + fx.getName().replace(".txt", "_shingles.txt" + " SUCESSFULLY!"));
		
		System.out.println("");
		}
	}
}
