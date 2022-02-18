package com.boot.clientes.persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.springframework.stereotype.Service;


import com.google.gson.Gson;


@Service
public class Persistencia {
	
	Gson gson = new Gson();
	public String leerArchivo() {
		
		String fichero = "";
		try (BufferedReader br = new BufferedReader(new FileReader("database.json"))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        fichero += linea;
		    }
		 
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}
		
        return fichero;
	}
	


}
