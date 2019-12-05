
import java.io.IOException;

public class Similaridade{
	
	public static void printPivotTitleSim(String nome) {			// printa a similaridade a partir de um determinado TITULO por ordem decrescente
		Livro livro = ListaLivros.searchLivro(nome);
		if(livro == null) throw new IllegalArgumentException("LIVRO NULO");
		System.out.println("SIMILARITY OF ALL BOOK TITLES COMPARING TO " + nome + ":");
		for(Livro l: ListaLivros.lista) {
			int counter = 0;
			for(int i=0; i<l.minHashTitle.length; i++) {
				if(livro.minHashTitle[i] == l.minHashTitle[i]) {
					counter++;
				}
			}
			if(!l.equals(livro)) {
				double jaccard = ((double)counter/((double)livro.minHashTitle.length*2 - counter));
				System.out.printf("%50.50s = %.3f%%\n", l.getTitulo(), jaccard*100);
			}
		}
	}
	
	public static void printPivotContentSim(String nome) {		// printa a similaridade a partir de um determinado CONTEUDO por ordem decrescente
		Livro livro = ListaLivros.searchLivro(nome);
		if(livro == null) throw new IllegalArgumentException("PROBLEMA COM PATH");
		System.out.println("SIMILARITY OF ALL BOOKS CONTENT COMPARING TO " + nome + ":");
		for(Livro l: ListaLivros.lista) {
			int counter = 0;
			for(int i=0; i<l.minHashContent.length; i++) {
				if(livro.minHashContent[i] == l.minHashContent[i]) {
					counter++;
				}
			}
			if(!l.equals(livro)) {
				double jaccard = ((double)counter/((double)livro.minHashContent.length*2 - counter));
				System.out.printf("%50.50s = %.3f%%\n", l.getTitulo(), jaccard*100);
			}
		}
	}
	
	
	public static void printTitleCompare(String nome1, String nome2) {	//printa a similaridade dos titulos entre dois livros
		Livro livro1 = ListaLivros.searchLivro(nome1);
		Livro livro2 = ListaLivros.searchLivro(nome2);
		if(livro1 == null || livro2 == null) throw new IllegalArgumentException("PROBLEMA COM PATH");
		System.out.println("SIMILARITY BETWEEN 2 BOOKS:  " + nome1 + " <> " + nome2 + ":");
		int counter = 0;
		for(int i=0; i<livro1.minHashTitle.length; i++) {
			if(livro1.minHashTitle[i] == livro2.minHashTitle[i]) {
				counter++;
			}
		}
		double jaccard = ((double)counter/((double)livro1.minHashTitle.length*2 - counter));
		System.out.printf("=\t%.3f%%\n", jaccard*100);
	}
	
	public static void printContentCompare(String nome1, String nome2) {	//printa a similaridade dos conteudos entre dois livros
		Livro livro1 = ListaLivros.searchLivro(nome1);
		Livro livro2 = ListaLivros.searchLivro(nome2);
		if(livro1 == null || livro2 == null) throw new IllegalArgumentException("PROBLEMA COM PATH");
		System.out.println("SIMILARITY BETWEEN 2 BOOKS:  " + nome1 + " <> " + nome2 + ":");
		int counter = 0;
		for(int i=0; i<livro1.minHashContent.length; i++) {
			if(livro1.minHashContent[i] == livro2.minHashContent[i]) {
				counter++;
			}
		}
		double jaccard = ((double)counter/((double)livro1.minHashContent.length*2 - counter));
		System.out.printf("=\t%.3f%%\n", jaccard*100);
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
	// 	Similaridade.printPivotTitleSim("test");
	// 	Similaridade.printPivotContentSim("test");
	// 	Similaridade.printTitleCompare("test", "test_copia");
	// 	Similaridade.printContentCompare("test", "test_copia");
	// }
	
}