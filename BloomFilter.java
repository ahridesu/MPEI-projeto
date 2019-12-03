package mpei;

public class BloomFilter {
	// grupo de nomes de livros no acervo em bloomfilter
    private int size;
    private int[] bitarray;                          										// array bloomfilter modificado a cada adição do nome
    private final double nFP = 0.01;                     									// probabilidade de falsos positivos 1/100
    private int k;                                      									// numero de hashfunctions 

    public BloomFilter(int n){																// n numero de livros
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
    
    private int stringToHash(String nome, int k) {
    	int hash = 0;
    	char[] palavra = nome.toCharArray();
    	for(char c: palavra) {
    		hash = Math.abs(37 * hash + charToASCII(c)); 
    	}
    	return (int)(hash % k);
    }

    public void insert(String nome){                                   // insere nome do livro no bloomfilter
    	for(int i=1; i<=k; i++) {
    		bitarray[stringToHash(nome, i)] = 1;
    	}
    }

    public boolean query(String nome){                                 // verifica se o nome de um livro pertence ao acervo    
    	for(int i=1; i<=k; i++) {
    		if(!(bitarray[stringToHash(nome, i)] == 1)) {
    			return false;
    		}
    	}
    	return true;                                                // retorna true, mas pode incluir um falso positivo           															
    }																// retorna false, temos a certeza que não pertence

}
