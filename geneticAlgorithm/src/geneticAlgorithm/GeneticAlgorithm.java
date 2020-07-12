/*
 Todd J Myers
 5/23/20
  CIS-1181 
  Project1
  Genetic Algorithm
 */


// This is a change in the code for github for extra credit my first project in CIS 1181  Computer Science 2 
package geneticAlgorithm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm 
{
// reads in data file with specific format and creates and returns an ArrayList of the Item objects.
	public static ArrayList<Item> readData(String filename) throws FileNotFoundException
	{
		ArrayList<Item> items = new ArrayList<>();
		
		String name;
		double weight;
		int value;
		
		String holder;
		 
		Scanner in = new Scanner(new FileReader(filename));
		while(in.hasNext()) {
			holder = in.nextLine();
			String[] array1 = holder.split(", ", 3);
			name = array1[0];
			weight = Double.parseDouble(array1[1]);
			value = Integer.parseInt(array1[2]);
			Item placeholderItem = new Item(name, weight, value);
			items.add(placeholderItem);
		}
		return items;
	}
	//(**** i think my Error is here somewhere****) my outcome varies on each run and should not do so?
	//creates and returns an ArrayList of poplutionSize Chromosome objects that each contain the items, with their
	// included field randomly set to true and false 
	public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize)
	{
		ArrayList<Chromosome> population = new ArrayList<>();
		ArrayList<Item> itemlist = items;
		int popsize = populationSize;
		Chromosome chrom = new Chromosome(itemlist);
		Random rng = new Random();
		int number;
		int counter = 0;
		
		while(counter < popsize) {
			chrom = new Chromosome(itemlist);
			for(int i = 0; i < chrom.size(); i++) {
				number = rng.nextInt(2);
				if (number == 1) {
					chrom.get(i).setIncluded(true);
				}else {
					chrom.get(i).setIncluded(false);
				}
			}
			population.add(chrom);
			counter++;
		}
		
		return population;
	}
	
	public static ArrayList<Integer> arrayTest(int num)
	{
		ArrayList<Integer> test = new ArrayList<>();
		test.add(num);
		num = 2;
		test.add(num);
		num = 3;
		test.add(num);
		return test;
	}
	// main that reads in data about the items in from a file called items.txt and then performs the steps described to complete this 
	public static void main(String[] args) throws FileNotFoundException
	{
		
		Random rng = new Random();
		int num;
		int maincounter = 0;
		
		ArrayList<Item> items = readData("items.txt");
		int popsize = 10;
		//Create set of 10 random individuals
		//Add individuals to next generation
		ArrayList<Chromosome> population = initializePopulation(items, popsize);
		
		while(maincounter < 20) {
			//Pair off and perform crossovers making parents and producing children 
			int counter = 0;
			int numchild = 0;
			while(numchild < 10) {
				Chromosome parent1 = population.get(counter);
				Chromosome parent2 = population.get(counter+1);
				Chromosome child = parent1.crossover(parent2);
				population.add(child);
				numchild++;
				counter++;
			}
			//Mutate 10% of population
			int mutatecounter = 0;
			while(mutatecounter < 2) {
				num = rng.nextInt(20);
				population.get(num).mutate();
				mutatecounter++;
			}
			//Sort individuals by fitness
			Collections.sort(population);
			
			//Clear bottom half of population
			ArrayList<Chromosome> top10 = new ArrayList<>();
			
			for(int i = 0; i < 10; i++) {
				top10.add(population.get(i));
			}
			population = new ArrayList<Chromosome>(top10);
			maincounter++;
		}
		System.out.println("The best chromosome is: ");   // outcome is different each run sometimes presenting 0 and sometimes the correct 
		System.out.println(population.get(0)); // answer i believe its going over the weight allowed 
	}

}
