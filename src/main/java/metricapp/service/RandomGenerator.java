package metricapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


public class RandomGenerator {
	//seed is time from epoch
	private static Random rnd = new Random(LocalDate.now().toEpochDay());
	
	static public String randomString(){
		return UUID.randomUUID().toString();
	}
	
	static public boolean randomBoolean(){
		return rnd.nextBoolean();
	}
	
	static public double randomDouble(){
		return rnd.nextDouble();
	}
	
	static public long randomLong(){
		return rnd.nextLong();
	}
	
	static public ArrayList<String> randomArrayList(){
		ArrayList<String> list = new ArrayList<String>();
		int n=rnd.nextInt(50);
		while(n>0){
			list.add(randomString());
			n--;
		}
		return list;
	}
	
	static public LocalDate randomLocalDate(){
		return LocalDate.ofEpochDay(randomLong());
	}
}
