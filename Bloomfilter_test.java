package mpei;

public class Bloomfilter_test {
	
    public static void main(String[] args) {
		BloomFilter bloomfilter = new BloomFilter(5);
		
		System.out.print(bloomfilter.getNhashes() + " ");				// number of hashes
		System.out.println(bloomfilter.getSize() + " ");				// bloom filter array size
		
		bloomfilter.insert("The Wind Blows");
		System.out.println(bloomfilter.query("The Wind Blows"));		// must give true
		
		bloomfilter.insert("Dead or Alive");
		System.out.println(bloomfilter.query("Dead or Alive"));			//must give true
		
		bloomfilter.insert("Friends with Benefits");
		System.out.println(bloomfilter.query("Friends with Benefits"));	//must give true
	
		System.out.println(bloomfilter.query("I love you"));			// might give false
		System.out.println(bloomfilter.query("Guide to love"));			// might give false
		System.out.println(bloomfilter.query("Dog Days"));				// caso de falso positivo?? 0.01
		
		for(int i: bloomfilter.getBitArray())
			System.out.print(i+ " ");
	}


}
