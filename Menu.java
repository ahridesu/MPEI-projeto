
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) throws IOException{
		Shingle sg = new Shingle();
		Scanner read = new Scanner(System.in);
		String path = new File(".").getCanonicalPath() + "\\Acervo";				// current path
		File pasta = new File(path);
		File[] listOfFiles = pasta.listFiles();
		for(File fx: listOfFiles) {
			Livro liv = new Livro(fx.getName());
			ListaLivros.lista.add(liv);
			String p = Shingle.createShingleFile(path + "\\" + fx.getName()); 
			Shingle.readShingleFile(p);
		}
		do {
			System.out.println("****ACERVO DE LIVROS****");
			System.out.println("");
			System.out.println("Manual de Instru��es: ");
			System.out.println("> Na pasta 'Acervo' existe um conjunto de 50 livros, no formato: 'NomeLivro.txt'");
			System.out.println("> Na pasta 'Shingles' existe um conjunto de 50 ficheiros, no formato: 'NomeLivro_shingles.txt', correspondentes a cada livro.");
			System.out.println("  Estes s�o os shingles gerados de cada livro.");
			System.out.println("> Esta pasta serve como exemplo para testar as funcionalidades do programa.");
			System.out.println("> Para adicionar um novo livro para teste ter� de coloc�-lo na pasta com o formato indicado a cima.");
			System.out.println("  E escolher a op��o de 'Gerar ficheiro shingles.txt'");
			System.out.println("Aqui est�o todos os ficheiros disponiveis: ");
			for(File fx: listOfFiles) {
				System.out.println(fx.getName());
			}
			System.out.println("");
			System.out.println("Para continuar digite '0'");
		}while(read.nextInt() != 0);
		int option;
		do {
			System.out.println("");
			System.out.println("Op��es:");
			System.out.println("(1)\tGerar ficheiro shingles.txt para novo livro");
			System.out.println("(2)\tVerificar se um livro existe no Acervo");
			System.out.println("(3)\tVerificar se um livro est� disponivel");
			System.out.println("(4)\tVerificar a similaridade entre 2 livros");
			System.out.println("(5)\tVerificar a similaridade entre livros com 1 livro de referencia");
			System.out.println("(0)\tSair");
			option = read.nextInt();
			switch(option) {
				case 1:
					System.out.println("Indique o nome do ficheiro (existente na pasta, com '.txt') para o qual deseja obter os shingles: ");
					String name = read.next();
					Livro book = new Livro(name);
					ListaLivros.lista.add(book);
					String p = Shingle.createShingleFile(path + "\\" + name); 
					Shingle.readShingleFile(p);
					break;
				case 2:

					break;
				case 3:

					break;
				case 4:
					System.out.println("Opções: ");
					System.out.println("(1)\tComparar titulos ");
					System.out.println("(2)\tComparar conteudos ");
					int opt = read.nextInt();
					switch(opt){
						case 1:
							System.out.println("Indique o 1º livro (existente na pasta, com '.txt' para o qual quer comparar o titulo: ");
							String n1 = read.next();
							String s_n1 = n1.replace(".txt", "");
							System.out.println("Indique o 2º livro (existente na pasta, com '.txt' para o qual quer comparar o titulo: ");
							String n2 = read.next();
							String s_n2 = n2.replace(".txt", "");
							Similaridade.printTitleCompare(s_n1, s_n2);
							break;
						case 2:
							System.out.println("Indique o 1º livro (existente na pasta, com '.txt' para o qual quer comparar o conteudo: ");
							String n3 = read.next();
							String s_n3 = n3.replace(".txt", "");
							System.out.println("Indique o 2º livro (existente na pasta, com '.txt' para o qual quer comparar o conteudo: ");
							String n4 = read.next();
							String s_n4 = n4.replace(".txt", "");
							Similaridade.printContentCompare(s_n3, s_n4);
							break;
					}
					break;
				case 5:
					System.out.println("Opções: ");
					System.out.println("(1)\tComparar titulos ");
					System.out.println("(2)\tComparar conteudos ");
					int op = read.nextInt();
					switch(op){
						case 1:
							System.out.println("Indique o livro de referencia (existente na pasta, com '.txt' para o qual quer comparar os titulos: ");
							String nome = read.next();
							String s_nome = nome.replace(".txt", "");
							Similaridade.printPivotTitleSim(s_nome);
							break;
						case 2:
							System.out.println("Indique o livro de referencia (existente na pasta, com '.txt' para o qual quer comparar os titulos: ");
							String n = read.next();
							String s_n = n.replace(".txt", "");
							Similaridade.printPivotContentSim(s_n);
							break;
					}
					break;
				case 0: 
					System.exit(0);
					break;
			}
		}while(option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 0);
	}
	
}
