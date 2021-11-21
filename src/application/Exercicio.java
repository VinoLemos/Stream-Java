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

import entities.Employee;

public class Exercicio {

	public static void main(String[] args) {
/*
  	Fazer um programa para ler os dados (nome, email e salário)
  	de funcionários a partir de um arquivo em formato .csv.
  	
  	Em seguida, mostrar em ordem alfabética, o email dos 
  	funcionários cujo salário seja superior a um dado valor
  	fornecido pelo usuário
  	
  	Mostrar também a soma dos salários dos funcionários cujo
  	nome começa com a letra 'M'.
 
 */	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Employee> emp = new ArrayList<>();
		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

		
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double limitSal = sc.nextDouble();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
// Lê o arquivo, e cria uma lista de Employee		
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			// Gera uma lista com os emails dos funcionários cujo salário é maior ao valor informado pelo usuário
			List<String> emails = emp.stream()
					.filter(e -> e.getSalary() > limitSal)
					.map(e -> e.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + limitSal);
			
			emails.forEach(System.out::println);
			
			double sum = emp.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x+y);
			System.out.println("Sum of salary from people whose name starts with an 'M': " + String.format("R% %.2f", sum));
					
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
