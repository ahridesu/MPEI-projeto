package mpei;

import java.util.HashSet;
import java.util.Set;

public class ListaLivros {
	
	public static Set<Livro> lista = new HashSet<>();			// set de livros, que contem o indice do livro, titulo e se está requisitado
	
	public Livro[] getLista() {
		return lista.toArray(new Livro[0]);
	}
	
	public static int getIndex(String titulo) {
		Livro livro = null;
		for(Livro l: lista) {
			if(l.getTitulo().equals(titulo)) {
				return l.getIndice();
			}
		}
		return -1;
	}
	
}
