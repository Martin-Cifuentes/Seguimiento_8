package model;

import java.util.ArrayList;
import java.util.Calendar;

import Exceptions.InvalidDayException;
import Exceptions.InvalidIDException;

public class Market {
	public String name;
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public int totalClients = 0;
	
	public Market(String name) {
		this.name = name;
	}
	
	public boolean addClient(String id, String type) throws Exception {
		Type clientType;
		clientType = validateIDType(type);
		if(clientType == null) {
			return false;
		}
		
		validateID(id);
		validateDayID(id);
		clients.add(new Client(id,clientType));
		return true;
	}
	
	public Type validateIDType (String type) throws InvalidIDException{
		Type clientType = null;
		// si es TI lanza error
		if(type.equals("1")) {
			throw new InvalidIDException();
		}else if(type.equals("2")) {
			clientType = Type.CC;
		}else if(type.equals("3")) {
			clientType = Type.PP;
		}else if(type.equals("4")) {
			clientType = Type.CE;
		}
		return clientType;
	}
	
	public void validateID(String id) throws Exception {		
		if(id.length() < 2) {
			throw new Exception ("El numero de identificacion no es valido");
		}
	}
	
	/**
	 * las personas cuyo documento tiene el penúltimo número par, pueden salir si el día
	 * del mes es un número impar, y viceversa
	 * @param id
	 * @throws InvalidDayException
	 */
	public void validateDayID(String id) throws InvalidDayException {
		int testNum = id.charAt(id.length() - 2);//obtiene el penultimo caracter del id
		// si el dia es par y el doc es par, no puede ingresar
		if(Calendar.getInstance().get(Calendar.DATE) % 2 == 0) { 
			if(testNum % 2 == 0) {
				throw new InvalidDayException();
			}
		}
		// si el dia es impar y el doc es impar, no puede ingresar
		else {
			if(testNum % 2 != 0) {
				throw new InvalidDayException();
			}
		}
	}
	
	public void showInfo() {
		
		System.out.println("---------------Clientes---------------");
		if(clients.isEmpty()) {
			System.out.println("Aun no se han agregado clientes");
		}else {
			for(int i = 0; i < clients.size(); i++) {
				System.out.println(clients.get(i));
			}
			System.out.println("\nLa cantidad total de clientes que ha ingresado es: " + clients.size());
		}
		System.out.println("\nLa cantidad total de clientes que han intentado ingresar son: " + totalClients);
	}
}
