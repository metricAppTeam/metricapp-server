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

/**
 * This class provides a large number of methods to generate random fields and
 * objects.
 * <p>
 * Every method can work statically, without the need of an instance of
 * RandomGenerator. The random seed is initialized when the JVM load the class,
 * the seed is the Nanosec of the moment of charge in memory.
 * 
 * User has not to initialize this class to use it
 *
 *
 */
public class RandomGenerator {
	/**
	 * seed is time from epoch
	 */
	private static Random rnd = new Random(LocalTime.now().getNano());

	/**
	 * this function permits to fill a single field of an Object. you have to
	 * pass the object entity as Object, and the field gathered by reflection.
	 * <p>
	 * This function can randomize the content of field if it is a supported
	 * type, otherwise leave the field null. Behavior of the function is not
	 * predictable with unsupported field types
	 * 
	 * This function supports a small number of ArrayList fields.
	 * 
	 * This function could throw a large number of Exception, due to internal
	 * use of reflection with unknown classes.
	 * 
	 * Due to implementation, entityType can not reflect real type of object! 
	 *
	 * @param obj
	 *            is the instance to fill. (e.g. class Car has String door, you
	 *            have to pass randomAttribute(car, doorField))
	 * @param field
	 *            is the field gathered by Reflection to fill up with random
	 *            content. (e.g. Car.getClass().getAttributes()[0])
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * TODO fix entityType generation, the object must be setted as the right type.
	 */
	@SuppressWarnings("unchecked")
	static public void randomAttribute(Object obj, Field field)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {

		
		if (field.getType().equals(String.class)) {
			//required for dto, where creationDate and LastVersionDate are Strings
			if (field.getName().equals("creationDate") || field.getName().equals("lastVersionDate")) {
				field.set(obj, RandomGenerator.randomLocalDate().toString());
			} else {
				field.set(obj, RandomGenerator.randomString());
			}
		}
		if (field.getType().equals(LocalDate.class)) {
			field.set(obj, RandomGenerator.randomLocalDate());
		}
		if (field.getType().equals(boolean.class)) {
			field.set(obj, RandomGenerator.randomBoolean());
		}
		if (field.getType().equals(double.class)) {
			field.set(obj, RandomGenerator.randomDouble());
		}
		if (field.getType().equals(List.class)) {
			try {
				field.set(obj, RandomGenerator.randomGenericArrayList(
						(Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]));
			} catch (Exception e) {

			}
		}
		if (field.getType().equals(int.class)) {
			field.set(obj, RandomGenerator.randomInt());
		}
		if (field.getType().equals(PointerBus.class)) {
			PointerBus pb = new PointerBus();
			pb.randomAttributes();
			field.set(obj, pb);
		}
		if (field.getType().isEnum()) {
			field.set(obj, RandomGenerator.randomEnum((Class<Enum<?>>) field.getType()));
		}
	}

	/**
	 * returns a randomString formatted as UUID. 
	 * @return
	 */
	static public String randomString() {
		return UUID.randomUUID().toString();
	}

	/**
	 * returns a random boolean
	 * @return
	 */
	static public boolean randomBoolean() {
		return rnd.nextBoolean();
	}

	/**
	 * returns a random double
	 * @return
	 */
	static public double randomDouble() {
		return rnd.nextDouble();
	}

	/**
	 * returns a random long
	 * @return
	 */
	static public long randomLong() {
		return rnd.nextLong();
	}
	
	/**
	 * returns a random int
	 * @return
	 */
	static public long randomInt() {
		return rnd.nextInt(1024 * 128);
	}

	/**
	 * this function returns a new randomized PointerBus element
	 * @return PointerBus randomized
	 */
	static public PointerBus randomPointerBus() {
		PointerBus pb = new PointerBus();
		try {
			pb.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
		}
		return pb;
	}

	/**
	 * returns a random ArrayList of Strings, formatted like UUID.
	 * @return
	 */
	static public ArrayList<String> randomArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		int n = rnd.nextInt(50);
		while (n > 0) {

			list.add(randomString());
			n--;
		}
		return list;
	}

	/**
	 * This is a generic method to create a randomized Array of an unknown (and supported by this module) class.
	 *
	 *     <ul>
	 * <li> Supported ArrayList:
	 * <li> String,
	 * <li> PointerBus
	 * </ul>
	 *
	 * @param clazz is the class of every element of the Array, is the parameter of the function
	 * @return an instace of a random ArrayList parameterized with clazz
	 * @throws Exception
	 */
	static public <T> ArrayList<T> randomGenericArrayList(Class<T> clazz) throws Exception {
		ArrayList<T> list = new ArrayList<T>();
		int n = rnd.nextInt(10);
		while (n > 0) {
			if (clazz.equals(String.class)) {
				list.add(clazz.cast(randomString()));
			}
			if (clazz.equals(PointerBus.class)) {
				PointerBus pb = new PointerBus();
				pb.randomAttributes();
				list.add(clazz.cast(pb));
			}
			n--;
		}
		return list;
	}

	
	/**
	 * provides a random date formatted as LocalDate between 1970 and 2300
	 * @return
	 */
	static public LocalDate randomLocalDate() {
		return LocalDate.ofEpochDay(randomInt() % (2300 * 360));
	}

	/**
	 * e.g. for Enum State: randomEnum(State.class) = return State
	 * (State.getValue() = Approved) this function uses reflections to bring you
	 * a random element of a Enumeration. As a parameter it takes the Enum.class
	 * element, T is my enumeration like State or ScaleType
	 */
	public static <T extends Enum<?>> T randomEnum(Class<T> myEnumClass) {
		int x = rnd.nextInt(myEnumClass.getEnumConstants().length);
		return myEnumClass.getEnumConstants()[x];
	}

}
