
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
	
	//______________PRINTS_____________________
	
	@Override public String toString() { 
		String s = "Titulo: '"+titulo+"', Requisitado: "+req+", Data de Requisi��o: " +data.toString();
		String n = "Titulo: '"+titulo+"'" + ", Requisitado: "+req;
		if(req == true) return s; else return n;
	}
	
	public boolean equals(Livro l) {
		if(this.titulo.equalsIgnoreCase(l.titulo) && this.req == l.req && this.nReq == l.nReq) {
			return true;
		}else return false;
	}
}