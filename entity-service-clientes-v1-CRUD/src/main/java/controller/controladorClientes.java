	package controller;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.clientes.modelo.Clientes;
import com.boot.clientes.persistencia.Persistencia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RestController
public class controladorClientes {
	
	private List<Clientes> clientes;

    @PostConstruct
    public void init() throws JsonProcessingException {
        clientes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String datos = getLista();
        try {
            clientes = mapper.readValue(datos, new TypeReference<List<Clientes>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    public String getLista() {
        String persistencia = new Persistencia().leerArchivo();
        return persistencia;
    }
    
    public void actualizarJson(List<Clientes> clientes) throws IOException {
        String json = new Gson().toJson(clientes);
        FileWriter file = new FileWriter("database.json");
        file.write(json);
        file.close();
    }
    
    @GetMapping(value = "listarclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clientes> getClientes() {
        return clientes;
    }


    @GetMapping(value = "clientes/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clientes> buscarClientes(@PathVariable("name") String nombreCliente) {
        List<Clientes> lista = new ArrayList<>();
        for (Clientes c : clientes) {
            if (c.getNombreCliente().contains(nombreCliente)) {
                lista.add(c);
            }
        }

        return lista;
    }

    @DeleteMapping(value = "eliminarcliente/{idCliente}")
    public List<Clientes> eliminarCliente(@PathVariable("idCliente") int idCliente) {
        clientes.removeIf(c -> c.getIdCliente() == idCliente);
        try {
			actualizarJson(clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return clientes;
    }

    @PostMapping(value = "agregarcliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clientes> altaCliente(@RequestBody Clientes cliente) throws IOException {
    	int idInsert = obtieneId(clientes);
        cliente.setIdCliente(idInsert);
        clientes.add(cliente);
        actualizarJson(clientes);
        return clientes;
    }

    @PutMapping(value = "actualizarcliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clientes> actualizaCliente(@RequestBody Clientes updateclientes) throws IOException {
        for (Clientes cliente: clientes) {
            if (cliente.getIdCliente() == updateclientes.getIdCliente()){
                cliente.setNombreCliente(updateclientes.getNombreCliente());
                cliente.setCorreoCliente(updateclientes.getCorreoCliente()); 
            }
        }
        actualizarJson(clientes);
        return clientes;
    }
    
    public int obtieneId(List<Clientes> listaClientes) {
    	int id = 0;
    	List<Integer> idlista = new ArrayList<Integer>();  
    	for (Clientes cliente : listaClientes) {
			idlista.add(cliente.getIdCliente());
		}
    	
    	 Collections.sort(idlista);
    	 int index = idlista.size() - 2;
    	 
    	 id = idlista.get(index + 1) +1;
    	 
    	
    	return id;
    }

    

}
