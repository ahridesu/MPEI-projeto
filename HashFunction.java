package mpei;

import java.util.Random;

public class HashFunction {
	private int a;
	private int b;
	private int signature;
	public int prime = 999983;
	
	public HashFunction(int id) {
		Random rand = new Random();
		this.a = rand.nextInt(prime-1)+1;
		this.b = rand.nextInt(prime-1)+1;
		this.signature = Math.abs((a*id+b)%prime);
	}
	
	public int a() {
		return a;
	}
	
	public int b() {
		return b;
	}
	
	public int signature() {
		return signature;
	}
	
}
