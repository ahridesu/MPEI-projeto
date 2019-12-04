
public class Livro {
	private String titulo;
	private boolean req = false; //Requisitado==true 
	private Data data; //Data de requisição, a do dia atual por default
	private int nReq = 0; //contador de numero de vezes que foi req.
	public int minHashTitle[]; // array de minhash do titulo do livro
	public int minHashContent[];
	
	//___________CONSTRUTORES______________
	
	public Livro(String titulo) {
		this.titulo = titulo.replace(".txt", "");
		this.data = Data.today();
	}
	
	public Livro(String titulo, String sinopse, Data data) { // Para livros requisitados, se adicionada data ent�o fica requisitado
		this(titulo);
		req = true;
		nReq++;
		this.data = data;
	}
	
	//________________GETS_________________
	
	public String getTitulo() {
		return titulo;
	}
	
	public boolean getRequisitado() {
		return req;
	}
	
	//_____________SETS______________________
	
	public void setRequisitado(boolean req) {
		this.req = req;
		
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