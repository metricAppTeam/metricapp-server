package metricapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelMapperUtility {
	
	private static Converter<String, LocalDate> stringToDateConverter;
	private static Converter<LocalDate, String> localDateToStringConverter;
	
	public static Converter<String, LocalDate> stringToLocalDate(){

		if(stringToDateConverter == null){
			stringToDateConverter = new AbstractConverter<String, LocalDate>() {
					@Override
					protected LocalDate convert(String arg0) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						
						System.out.println(arg0 == null);
						return arg0 == null || arg0 == "" ? null : LocalDate.parse(arg0, formatter);
						
					}
				};
				return stringToDateConverter;

		}
		else{
			return stringToDateConverter;
		}
	}
	
	public static Converter<LocalDate,String> localDateToString(){

		if(localDateToStringConverter == null){
			localDateToStringConverter = new AbstractConverter<LocalDate,String>() {
					@Override
					protected String convert(LocalDate arg0) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						return arg0 == null ? null : arg0.format(formatter);
						
					}
				};
				return localDateToStringConverter;

		}
		else{
			return localDateToStringConverter;
		}
	}

}
