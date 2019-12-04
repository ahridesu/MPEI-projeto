package mpei;

public class CountingBloomFilter {
	private int[] bitarray;
	private int numInserts = 0; //contador
	private int k;              // number of hashFunctions
	private int size;              // Bloom bitarray positions
	private double nFP = 0.01;    // false positive probability
	
	public CountingBloomFilter(int n) {
		size = (int)(Math.ceil((n*Math.log(nFP))/Math.log(1/Math.pow(2, Math.log(2)))));
        k = (int)(Math.ceil((size/n)*Math.log(2)));
        bitarray = new int[size];
	}
	
	 public int[] getBitArray(){
	        return bitarray;
	    }
	    
    public int getNhashes() {
    	return k;
    }
    
    public int getSize() {
    	return size;
    }

    private int charToASCII(char c){          // char para ascii
        return (int)(c);
    }
	
	
	public void insert(String nome){
        for (int i=1; i < k; i++) 
        {
        	bitarray[stringToHash(nome, i)] = 1;
		}
		numInserts++;
	}
    
	public boolean query(String nome) {                                 // verifica se o nome de um livro pertence ao acervo    
    	for(int i=1; i<=k; i++) {
    		if(!(bitarray[stringToHash(nome, i)] == 1)) {
    			return false;
    		}
    	}
    	return true;                                                // retorna true, mas pode incluir um falso positivo           															
    }	
	
	public boolean exists(String nome) {
		int count = k;
        for (int i = 0; i < k; i++) 
        {
			if (bitarray[Math.abs(stringToHash(nome,k))] > 0) 
			{
				count--;
			}
		}
        return count == 0;
        // if present at every HashCoded index position is probable that has been inserted
	}
    
	public void remove(String nome) {
        for (int i  = 0; i < k; i++) 
        {
        	if (bitarray[Math.abs(stringToHash(nome,k))] > 0)  
            {
        		bitarray[Math.abs(stringToHash(nome,k))]--;
			}
		}
		numInserts--;
	}
   
	private int stringToHash(String nome, int k) {
    	int hash = 0;
    	char[] palavra = nome.toCharArray();
    	for(char c: palavra) {
    		hash = Math.abs(37 * hash + charToASCII(c)); 
    	}
    	return (int)(hash % k);
    }
   
	
}