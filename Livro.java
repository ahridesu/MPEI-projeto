package mpei;

public class Livro {
	private String titulo;
	private static int current_indice;
	private int indice = 0;
	private boolean req = false; //Requisitado==true 
	private Data data; //Data de requisição, a do dia atual por default
	private int nReq = 0; //contador de numero de vezes que foi req.
	
	//___________CONSTRUTORES______________
	
	public Livro(String titulo) {
		this.titulo = titulo;
		data = null;
		indice = current_indice++;
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
	
	@Override public String toString() { //toString com nome e estado de requisição
		String s = "Titulo: '"+titulo+"', Requisitado: "+req+", Data de Requisi��o: " +data.toString();
		String n = "Titulo: '"+titulo+"', Requisitado: "+req;
		if(req == true) return s; else return n;
	}
}