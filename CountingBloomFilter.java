
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
    public int getNumElems() {
    	return numInserts;
    }

    private int charToASCII(char c){          // char para ascii
        return (int)(c);
    }
	
	
	public boolean insert(String nome){
		if(!query(nome)){
			for (int i=0; i<k; i++) 
        	{
        		bitarray[stringToHash(nome)] = 1;
			}
			numInserts++;
			return true;
		}else return false;
        
	}
    
	public boolean query(String nome) {                                 // verifica se o nome de um livro pertence ao acervo    
    	for(int i=0; i<k; i++) {
    		if(!(bitarray[stringToHash(nome)] == 1)) {
    			return false;
    		}
    	}
    	return true;                                                // retorna true, mas pode incluir um falso positivo           															
    }	
	
	public boolean remove(String nome) {
		if(query(nome)){
			for (int i=0; i<k; i++) 
        	{
        		if (bitarray[stringToHash(nome)] > 0)  
            	{
        			bitarray[stringToHash(nome)]--;
				}
			}
			numInserts--;
			return true;
		}else return false;
        
	}
   
	private int stringToHash(String nome) {
    	int hash = 0;
    	char[] palavra = nome.toCharArray();
    	for(char c: palavra) {
    		hash = 37 * hash + charToASCII(c); 
    	}
    	return (int)Math.abs((hash % size));
    }
   
	
}