package mpei;

import java.util.Random;

public class HashFunction {
	private int a;
	private int b;
	public final static int prime = 999983;
	
	public HashFunction() {
		Random rand = new Random();
		this.a = rand.nextInt(prime-1)+1;
		this.b = rand.nextInt(prime-1)+1;
	}
	
	public int a() {
		return a;
	}
	
	public int b() {
		return b;
	}
	
}
