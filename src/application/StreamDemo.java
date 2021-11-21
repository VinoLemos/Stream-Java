package application;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main(String[] args) {
	
		List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
		
		//Cria uma stream a partir da lista, multiplicando cada valor por 10
		Stream<Integer> st1 = list.stream().map(x -> x*10);
		System.out.println(Arrays.toString(st1.toArray()));
		
		//Cria uma stream aplicando valores como parametros
		Stream<String> st2 = Stream.of("Maria", "Alex", "Bob");		
		System.out.println(Arrays.toString(st2.toArray()));
		
		//Criando uma stream com função de iteração
		//Recebe um primeiro elemento, e uma função de iteração para gerar os próximos elementos
		//Ira gerar numeros de 2 em 2 infinitamente
		Stream<Integer> st3 = Stream.iterate(0, x -> x + 2);
		//Limita para um máximo de 10 elementos, e os imprime
		System.out.println(Arrays.toString(st3.limit(10).toArray()));
		
		//Formando a sequencia de Fubonacci
		Stream<Long> st4 = Stream.iterate(new Long[] {0L, 1L}, p -> new Long[] {p[1], p[0]+p[1]}).map(p -> p[0]);
		System.out.println(Arrays.toString(st4.limit(20).toArray()));
		
	}

}
