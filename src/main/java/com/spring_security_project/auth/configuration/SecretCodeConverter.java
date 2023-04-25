package com.spring_security_project.auth.configuration;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.AttributeConverter;

@Configuration
public class SecretCodeConverter implements AttributeConverter<String, String> {
	
	//// ARRAY DI CARATTERI CASUALI SCELTI DA ME
	Character[] arrChar = {'P', 'Q','A', '@', 'O','Y','P', '-', 'F', '+'};

	@Override
	public String convertToDatabaseColumn(String attribute) {
		////"385" devo codificarlo in @FY 
		String result = "";
		if(attribute == null) {
			return result;
		} 
		
		for (Character c  :attribute.toCharArray()) {
			String sc = c + ""; /*"3", "8", "5"  */ ///385 è il valore passato come parametro
			int num = Integer.parseInt(sc); /// trasmormo il valore passato da stringa a numero
			result += arrChar[num]; /// viene scelto il carattere nella posisizone dell'array già indicato
		}
		
		return result;
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		String result = "";
		if(dbData == null) {
			return result;
		} 
		
		for (Character c  :dbData.toCharArray()) {
			for (int i = 0; i < arrChar.length; i++) {
				if (arrChar[i].equals(c)) {
					result += i;
				}
			}		
		}
		
		return result;
	}
	

}
