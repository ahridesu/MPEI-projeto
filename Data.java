package mpei;

import java.util.Calendar;

public class Data {
	private int dia;
	private int mes;
	private int ano;
	
	public Data(int dia, int mes, int ano){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public int dia() {
		return dia;
	}
	
	public int mes() {
		return mes;
	}
	
	public int ano() {
		return ano;
	}
	
	public static Data today() {
		Calendar calendar = Calendar.getInstance();
		return new Data(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
	}
	
	@Override public String toString() {
		return dia + "-"+ mes +"-"+ ano;
	}
	
	public boolean equals(Data d) {
		if(d.dia == this.dia && d.mes == this.mes && d.ano == this.ano) {
			return true;
		}else return false;
	}
	
	public boolean isValid() {
		if(this.dia >0 && this.dia <= 31 && this.mes > 0 && this.mes <= 12 && this.ano >= 0) {
			return true;
		}else return false;
	}
}
