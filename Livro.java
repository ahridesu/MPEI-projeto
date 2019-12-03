package mpei;

public class Livro {
	private String titulo;
	private static int current_indice = 0;
	private int indice;
	private boolean req = false; //Requisitado==true 
	private Data data; //Data de requisi√ß√£o, a do dia atual por default
	private int nReq = 0; //contador de numero de vezes que foi req.
	
	//___________CONSTRUTORES______________
	
	public Livro(String titulo) {
		this.titulo = titulo.replace(".txt", "");
		this.data = Data.today();
		indice = current_indice++;
	}
	
	public Livro(String titulo, String sinopse, Data data) { // Para livros requisitados, se adicionada data ent„o fica requisitado
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
	
	public int getIndice() {
		return indice;
	}
	
	//_____________SETS______________________
	
	public void setRequisitado(boolean req) {
		this.req = req;
		
	}
	
	//______________PRINTS_____________________
	
	@Override public String toString() { //toString com nome e estado de requisi√ß√£o
		String s = "Titulo: '"+titulo+"', Requisitado: "+req+", Data de RequisiÁ„o: " +data.toString();
		String n = "Titulo: '"+titulo+"'" + "Indice matrix: " + indice +", Requisitado: "+req;
		if(req == true) return s; else return n;
	}
	
	public boolean equals(Livro l) {
		if(this.titulo.equalsIgnoreCase(l.titulo) && this.indice == l.indice && this.req == l.req && this.nReq == l.nReq) {
			return true;
		}else return false;
	}
}