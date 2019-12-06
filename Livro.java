
public class Livro {
	private String titulo;
	public int minHashTitle[]; // array de minhash do titulo do livro
	public int minHashContent[];
	
	//___________CONSTRUTORES______________
	
	public Livro(String titulo) {
		this.titulo = titulo.replace(".txt", "");
	}
	
	//________________GETS_________________
	
	public String getTitulo() {
		return titulo;
	}
	
	public boolean equals(Livro l) {
		if(this.titulo.equalsIgnoreCase(l.titulo)) {
			return true;
		}else return false;
	}
}