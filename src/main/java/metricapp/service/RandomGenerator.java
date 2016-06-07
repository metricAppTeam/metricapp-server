package metricapp.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;



public class RandomGenerator {
	//seed is time from epoch
	private static Random rnd = new Random(LocalDate.now().toEpochDay());
	
	
	/*
	 * this function permits to fill an object 
	 * obj with random attributes (if String, Double, Int, String, ArrayList<String>, Enum<String>
	 * 
	 * */
	@SuppressWarnings("unchecked")
	static public void randomAttribute(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException {
		if (field.getType().equals(String.class)){
			field.set(obj, RandomGenerator.randomString());
		}
		if (field.getType().equals(LocalDate.class)){
			field.set(obj, RandomGenerator.randomLocalDate());
		}
		if (field.getType().equals(boolean.class)){
			field.set(obj, RandomGenerator.randomBoolean());
		}
		if (field.getType().equals(double.class)){
			field.set(obj, RandomGenerator.randomDouble());
		}
		if (field.getType().equals(List.class)){
			field.set(obj, RandomGenerator.randomArrayList());
		}
		if (field.getType().equals(int.class)){
				field.set(obj, RandomGenerator.randomInt());
		}
		if (field.getType().isEnum()){
			field.set(obj, RandomGenerator.randomEnum((Class<Enum<?>>) field.getType()));
		}
	}
	
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
	
	static public long randomInt(){
		return rnd.nextInt();
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
		return LocalDate.ofEpochDay(randomInt());
	}
	
	/*
	 * e.g. for Enum State: randomEnum(State.class) --> return State (State.getValue() = Approved)
	 * this function uses reflections to bring you a random element of a Enumeration. 
	 * As a parameter it takes the Enum.class element, T is my enumeration like State or ScaleType
	 * */
	
	
	
	public static <T extends Enum<?>> T randomEnum(Class<T> myEnumClass){
        int x = rnd.nextInt(myEnumClass.getEnumConstants().length);
        return myEnumClass.getEnumConstants()[x];
    }
}
