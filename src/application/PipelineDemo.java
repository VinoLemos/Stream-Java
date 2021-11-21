package application;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PipelineDemo {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
		
		//Inicia uma Stream de Integers, com o primeiro
		//elemento x, e os elementos subsequentes sendo gerados
		//ao multiplicar x por 10s
		Stream<Integer> st1 = list.stream().map(x -> x* 10);
		
		System.out.println(Arrays.toString(st1.toArray()));
		
		//Variável recebe a soma de todos os elementos da stream
		/*A operação reduce recebe um elemento inicial neutro,
		  uma função recebendo dois argumentos ( (x, y) -> x+y ) */
		int sum = list.stream().reduce(0, (x,y ) -> x+y);
		System.out.println("Total sum: " + sum);
		
	//Cria uma nova lista de inteiros
		List<Integer> newList = list.stream()
	//Filtra os elementos da lista anterior, encontrando apenas os números pares
	//Gera uma nova stream contendo apenas os números pares
				.filter(x -> x % 2 == 0)
	//Aplica o mapeamento na stream filtrada de numeros pares, multiplicando cada elemento por 10			
				.map(x -> x * 10)
	//Transforma a stream mapeada em uma lista			
				.collect(Collectors.toList());
	System.out.println(Arrays.toString(newList.toArray()));
	}	

}
