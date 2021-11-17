package com.example.SpringRest;

import com.example.SpringRest.dto.ClienteDTO;
import com.example.SpringRest.dto.Cobranza;
import com.example.SpringRest.dto.Inmueble;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{

		return  args -> {
			Inmueble inmueble = new Inmueble();
			inmueble.setCodigo(1234);
			inmueble.setDomicilio("Lopez Mateos #109");
			inmueble.setImporte(35000.50F);

			Inmueble inmueble2 = new Inmueble();
			inmueble2.setCodigo(123);
			inmueble2.setDomicilio("Cuauhtemoc #209");
			inmueble2.setImporte(25000.50F);

			ClienteDTO cliente1 = new ClienteDTO();
			cliente1.setNombre("Alisson");
			cliente1.setDni("1616");

			ClienteDTO cliente2 = new ClienteDTO();
			cliente2.setNombre("Evelin");
			cliente2.setDni("0906");

			ClienteDTO cliente3 = new ClienteDTO();
			cliente3.setNombre("Elvira");
			cliente3.setDni("2905");

			ClienteDTO cliente1M = new ClienteDTO();
			cliente1M.setNombre("Alisson Brandi");
			cliente1M.setDni("1616");


			Cobranza cobranza = new Cobranza();
			cobranza.setNumero(145);
			cobranza.setFecha("17/11/2021");
			cobranza.setCliente(cliente1);
			cobranza.setInmueble(inmueble2);



			agregarInmueble(restTemplate,inmueble );
			agregarInmueble(restTemplate,inmueble2 );

			agregarCliente(restTemplate, cliente1);
			agregarCliente(restTemplate, cliente2);
			agregarCliente(restTemplate, cliente3);

			agregarCobranza(restTemplate, cobranza);

			//eliminarCliente(restTemplate);
			//eliminarInmueble(restTemplate);
			//actualizarCliente(restTemplate,clienteM);




		};
	}

	private void actualizarCliente(RestTemplate restTemplate, ClienteDTO cliente) {
		try {
			restTemplate.put("http://localhost:8080/actualizarCliente", cliente, ClienteDTO.class);
			System.out.println("");
			System.out.println("Se actualizo con exito la información del cliente.");
		}catch (Exception e){

		}

	}

	private void eliminarCliente(RestTemplate restTemplate) {

		try {
			restTemplate.delete("http://localhost:8080/deletCliente/"+1616);
			System.out.println("");
			System.out.println("Se elimino con exito el cliente.");
		}catch (Exception e){

		}

	}

	private void eliminarInmueble(RestTemplate restTemplate) {

		try {
			restTemplate.delete("http://localhost:8080/deletInmueble/"+123);
			System.out.printf("Se elimino con exitó el inmueble.");
		}catch (Exception e){

		}

	}


	private void getClientes(RestTemplate restTemplate) {


	}

	private void agregarCobranza(RestTemplate restTemplate, Cobranza cobranza) {

		ResponseEntity<Cobranza> CobranzaResponseAdd= restTemplate.postForEntity("http://localhost:8080/addCobranza",cobranza,Cobranza.class);
		System.out.println("");
		System.out.println("Respuesta agregar cobranza: " +CobranzaResponseAdd.toString());
	}


	private void agregarInmueble(RestTemplate restTemplate, Inmueble inmueble) {


		ResponseEntity<Inmueble> InmuebleResponseAdd= restTemplate.postForEntity("http://localhost:8080/agregarInmueble",inmueble,Inmueble.class);
		System.out.println("");
		System.out.println("Respuesta agregar el inmueble: "+ inmueble.getCodigo()+ " " +InmuebleResponseAdd.toString());
	}

	private void agregarCliente(RestTemplate restTemplate, ClienteDTO cliente) {


		ResponseEntity<ClienteDTO> clienteResponseAdd= restTemplate.postForEntity("http://localhost:8080/agregarCliente",cliente,ClienteDTO.class);
		System.out.println("");
		System.out.println("Respuesta agregar cliente: "+ cliente.getNombre()+ " " +clienteResponseAdd.toString());

	}


}
