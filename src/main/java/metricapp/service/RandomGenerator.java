package metricapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


public class RandomGenerator {
	//seed is time from epoch
	Random rnd = new Random(LocalDate.now().toEpochDay());
	
	public String randomString(){
		return UUID.randomUUID().toString();
	}
	
	public boolean randomBoolean(){
		return rnd.nextBoolean();
	}
	
	public double randomDouble(){
		return rnd.nextDouble();
	}
	
	public long randomLong(){
		return rnd.nextLong();
	}
	
	public ArrayList<String> randomArrayList(){
		ArrayList<String> list = new ArrayList<String>();
		int n=rnd.nextInt(50);
		while(n>0){
			list.add(randomString());
			n--;
		}
		return list;
	}
	
	public LocalDate randomLocalDate(){
		return LocalDate.ofEpochDay(randomLong());
	}
}
