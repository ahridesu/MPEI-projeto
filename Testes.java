import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Testes {
	public static void main(String args[]) throws IOException {
		//Criação dos BFs
		BloomFilter bf= new BloomFilter(20);
		CountingBloomFilter cbf = new CountingBloomFilter(20);
		
		
		/*INICIO DO TESTE DO PROGRAMA*/
		System.out.println("----------PROGRAMA DE TESTES-----------");
		
		System.out.println("--------VERIFICAÇÃO DOS SHINGLES--------");
		Shingle sg = new Shingle();
		Scanner read = new Scanner(System.in);
		String path = new File(".").getCanonicalPath() + "\\Livros_teste";				// current path
		File pasta = new File(path);
		File[] listOfFiles = pasta.listFiles();
		for(File fx: listOfFiles) {
			bf.insert(fx.getName());
			cbf.insert(fx.getName());
			Livro liv = new Livro(fx.getName());
			ListaLivros.lista.add(liv);
	        String p = Shingle.createShingleFile(path + "\\" + fx.getName());  		//JA FORAM CRIADOS OS FICHEIROS, ESTAS LINHAS NÃO SAO MAIS NECESSARIAS
		 	Shingle.readShingleFile(p);
			Shingle.readShingleFile(new File(".").getCanonicalPath() + "\\Shingles\\" + fx.getName().replace(".txt", "_shingles.txt"));
			System.out.println("ANALISED SHINGLE FILE " + fx.getName().replace(".txt", "_shingles.txt" + " SUCESSFULLY!"));
		}
		System.out.println(" ");
		System.out.println(" ");	
		System.out.println("------A INICIAR TESTES-----------");
		System.out.println("------A CRIAR SHINGLE FILES------");
		System.out.println("A testar BloomFilter, verificando se os livros pertencem: ");
		System.out.println("teste 1: "+bf.query("teste1.txt")+" --- deve dar 'true'");
		System.out.println("teste 2: "+bf.query("teste2.txt")+" --- deve dar 'true'");
		System.out.println("teste 3: "+bf.query("teste3.txt")+" --- deve dar 'true'");
		System.out.println("teste 4: "+bf.query("teste4.txt")+" --- deve dar 'true'");
		System.out.println("teste 5: "+bf.query("teste5.txt")+" --- deve dar 'false'");
		System.out.println("teste 6: "+bf.query("tewfmkonfow.txt")+" --- deve dar 'false'");
		System.out.println("teste 7: "+bf.query("randomtext.txt")+" --- deve dar 'false'");
		System.out.println("");
		for(int i: bf.getBitArray())
			System.out.print(i+ " ");
		System.out.println(" ");
		System.out.println("--------------------------------");
		System.out.println("A testar CountingBloomFilter, verificando se pertencem e removendo alguns:");
		System.out.println("teste 1: "+cbf.query("teste1.txt")+" --- deve dar 'true'");
		System.out.println("teste 2: "+cbf.query("teste2.txt")+" --- deve dar 'true'");
		System.out.println("teste 3: "+cbf.query("teste3.txt")+" --- deve dar 'true'");
		System.out.println("teste 4: "+cbf.query("teste9.txt")+" --- deve dar 'false'");
		System.out.println(" ");
		System.out.println("A remover teste 1 do CountingBloomFilter");
		cbf.remove("teste1.txt");
		System.out.println("A remover teste 2 do CountingBloomFilter");
		cbf.remove("teste2.txt");
		System.out.println("A verificar novamente se pertencem");
		System.out.println(" ");
		System.out.println("teste 1: "+cbf.query("teste1.txt")+" --- deve dar 'false'");
		System.out.println("teste 2: "+cbf.query("teste2.txt")+" --- deve dar 'false'");
		System.out.println("teste 3: "+cbf.query("teste3.txt")+" --- deve dar 'true'");
		System.out.println("teste 4: "+cbf.query("teste4.txt")+" --- deve dar 'false'");
		System.out.println(" ");
		System.out.println("--------------------------------");
		System.out.println("Testar calculo de similaridade pela distancia de Jacard");
		System.out.println("");
		Similaridade.printContentCompare("teste1","teste2");
		Similaridade.printContentCompare("teste3","teste4");
		Similaridade.printContentCompare("teste1","teste1");
		System.out.println("Resultado esperado: 100%");
		System.out.println(" ");
		System.out.println("--------------------------------");
	
	}
}
