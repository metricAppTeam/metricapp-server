package metricapp.utility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;



import metricapp.entity.external.PointerBus;



public class RandomGenerator {
	//seed is time from epoch	
	private static Random rnd = new Random(LocalTime.now().getNano());
	
	/*
	 * this function permits to fill an object 
	 * obj with random attributes (if String, Double, Int, String, ArrayList<String>, Enum<String>
	 * 
	 * */
	@SuppressWarnings("unchecked")
	static public void randomAttribute(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
		
		if (field.getType().equals(String.class)){
			//System.out.println("set"+toCamelCase(field.getName()));
			//TODO replace with something that use reflection
			//try {
				//if (field.getClass().getMethod("set"+toCamelCase(field.getName())).getParameterTypes()[0].equals(LocalDate.class)){
				if(field.getName().equals("creationDate") || field.getName().equals("lastVersionDate")){
					field.set(obj, RandomGenerator.randomLocalDate().toString());
				}
				else{
					field.set(obj, RandomGenerator.randomString());
				}
			//} catch (NoSuchMethodException e) {
				//System.out.println("Warning, this method doesn't exists : "+field.getDeclaringClass()+".set"+toCamelCase(field.getName()));
				//e.printStackTrace();
				
			//}
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
			try{
				field.set(obj, RandomGenerator.randomGenericArrayList( (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]  ));
			} catch(Exception e){
				
			}
		}
		if (field.getType().equals(int.class)){
				field.set(obj, RandomGenerator.randomInt());
		}
		if (field.getType().equals(PointerBus.class)){
			PointerBus pb = new PointerBus();
			pb.randomAttributes();
			field.set(obj,  pb);
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
		return rnd.nextInt(1024*128);
	}
	
	static public PointerBus randomPointerBus(){
		PointerBus pb = new PointerBus();
		try {
			pb.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		}
		return pb;
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
	
	static public <T> ArrayList<T> randomGenericArrayList(Class<T> clazz) throws Exception{
		ArrayList<T> list = new ArrayList<T>();
		int n=rnd.nextInt(10);
		while(n>0){
			if(clazz.equals(String.class)){
				list.add(clazz.cast(randomString()));
			}
			if(clazz.equals(PointerBus.class)){
				PointerBus pb =new PointerBus();
				pb.randomAttributes();
				list.add(clazz.cast(pb));
			}
			n--;
		}
		return list;
	}
	
	//random date from 1970 and 2300
	static public LocalDate randomLocalDate(){
		return LocalDate.ofEpochDay(randomInt()%(2300 * 360));
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
