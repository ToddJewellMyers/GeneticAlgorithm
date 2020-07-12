package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
// used for random number generation
	private static Random rng;
	
	public Chromosome()
	{
		
	}
	//adds a copy oif each of the items pased in to this chromosome. uses a random number to decide whether each 
	//items included field is set to true or false
	public Chromosome(ArrayList<Item> items)
	{
		int number;
		rng = new Random();
		//This for loop will go through every item in the passed in ArrayList
		for (int counter = 0; counter < items.size(); counter++) {
			number = rng.nextInt(2);
			//If rng generates a 1, the item inclusion is set to true and it is added to the Chromosome list
			//If it generates a 0, its set to false and added to the Chromosome list
			if(number == 1) {
				items.get(counter).setIncluded(true);
			}else {
				items.get(counter).setIncluded(false);
			}
			Chromosome.super.add(items.get(counter));
		}
	}
	//Creates and returns a	new	child chromosome by	performing the crossover	
	//operation	on this chromosome	and	the	other one that is passed in
	public Chromosome crossover(Chromosome other) 
	{
		Chromosome child = new Chromosome();
		int number;
		rng = new Random();
		//This for loop will go through every item in the chromosome
		for (int counter = 0; counter < Chromosome.super.size(); counter++) 
		{
			number = rng.nextInt(2);
			//If a 1 is generated the current chromosome value will be added to the child
			if(number == 1) {
				child.add(Chromosome.super.get(counter));
			//If a 0 is generated the other chromosome value will be added to the child
			}else {
				child.add(other.get(counter));
			}
		}
		return child;
	}
	//performs the mutation operation on this chromosome (each item in this chromosome use a random number to decide 
	//whether or not to flip its included field from true or false or vice versa
	public void mutate() 
	{
		int number;
		rng = new Random();
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < Chromosome.super.size(); counter++)
		{
			//Will generate a 0 or a 1 in the number variable
			number = rng.nextInt(2);
			if(number == 1) {
				//If rng generates a 1, this flips the current items inclusion
				Chromosome.super.get(counter).setIncluded(!Chromosome.super.get(counter).isIncluded());
			}
		}
	}
	// returns the fitness of this chromosome. if the sum of all the included items weights are greater than 10, the fitness
	// is zero. otherwise the fitness is equal to the sum of all of the included items values.
	public int getFitness()
	{
		double totalweight = 0;
		int totalvalue = 0;
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < Chromosome.super.size(); counter++)
		{
			//Checks if the current value is included, if not it is skipped
			if(Chromosome.super.get(counter).isIncluded() == true) {
				totalweight = totalweight + Chromosome.super.get(counter).getWeight();
				totalvalue = totalvalue + Chromosome.super.get(counter).getValue();
			}
		}
		if(totalweight > 10.0)
		{
			return 0;
		}else {
			return totalvalue;
		}
	}
	//returns -1 if this chromosomes fitness is greater than the other fitness, +1 if this chromosome fitness is less than the other ones,
	// and 0, if their fitness is the same.
	public int compareTo(Chromosome other)
	{
		if (getFitness() > other.getFitness()) {
			return -1;
		}else if(getFitness() < other.getFitness()) {
			return 1;
		}else {
			return 0;
		}
	}
	// Displays the name, weight and value of all items in this chromsome whose included value iis true, followed byu the fitness of this 
	//chromosome.
	public String toString() 
	{
		//Initialization of returnstring
		String returnstring = "";
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < Chromosome.super.size(); counter++) {
			//Checks if the current value is included, if not it is skipped
			if (Chromosome.super.get(counter).isIncluded() == true) {
				//Appends the included items values on to the returnstring
				returnstring = returnstring + Chromosome.super.get(counter).getName() + ", " +
				Chromosome.super.get(counter).getWeight() + ", " + Chromosome.super.get(counter).getValue() + "\n";
			}
		}
		returnstring = returnstring + getFitness() + "\n";
		return returnstring;
	}
}
