
import java.util.HashSet;
import java.util.Set;

public class ListaLivros {
	
	public static Set<Livro> lista = new HashSet<>();			// set de livros, que contem o indice do livro, titulo e se est� requisitado
	
	public Livro[] getLista() {
		return lista.toArray(new Livro[0]);
	}
	
	public static Livro searchLivro(String titulo) {
		for(Livro l: lista) {
			if(l.getTitulo().equals(titulo)) {
				return l;
			}
		}
		return null;
	}
	
	public static void printLivros() {
		for(Livro l: lista) {
			System.out.println(l);
		}
	}
	
}
