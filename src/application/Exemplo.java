package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Exemplo {

/*
 
  Fazer um programa para ler um conjunto de produtos a partir de um
  arquivo em formato .csv (suponha que exista pelo menos um produto).
  
  Em seguida mostrar o preço médio dos produtos. Depois, mostrar os
  nomes, em ordem decrescente, dos produtos que possuem preço 
  inferior ao preço médio.
 	
*/
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				list.add(new Product(name, price));
				line = br.readLine();
			}
			double avg = list.stream()
//	Gera uma nova stream com o preço de cada produto p					
					.map(p -> p.getPrice())
//	Operação reduce iniciando com valor neutro, a partir daí,
//	soma o preço de todos os produtos (x + y), e divide pelo
//	tamanho total da lista para encontrar a média
					.reduce(0.0, (x,y) -> x+y) / list.size();
			System.out.println("Average product price: " + String.format("%.2f", avg));

// Gera um comparator que irá comparar 2 Strings, independente de letras maiúsculas e minúsculas					
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
// Gera uma stream com elementos no qual o preço seja menor que o preço médio
			List<String> names = list.stream()
// A partir desta stream, gera outra stream contendo apenas os nomes desses produtos
// Utiliza o comparador dentro do sorted para organizar os nomes em ordem decrescente (reversed())					
// Utiliza collectors para converter a stream para list	
					.filter(p -> p.getPrice() < avg)
					.map(p -> p.getName())
					.sorted(comp.reversed())
					.collect(Collectors.toList());
			
			System.out.println("Names: ");
			names.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage()
			);
		}
		finally {
			sc.close();
		}
	}

}
