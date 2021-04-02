package ui;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import model.Type;
import model.Client;
import model.InvalidDayException;
import model.InvalidIDException;

public class Main {
	
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static Scanner sc = new Scanner(System.in);
	public static int totalClients = 0;
	
	public static void main (String[]args) {
		int terminar = 0;
		while(terminar != 3) {
			terminar = menu();
		}
	}
	
	public static int menu() {
		System.out.println("Seleccione una accion");
		System.out.println(" (1) Ingresar un cliente\n (2) Mostrar clientes\n (3) Salir");
		String ans = sc.nextLine();
		try {
			if(ans.equals("1")) {
				totalClients++;
				askData();
			}else if(ans.equals("2")) {
				showInfo();
			}else if(ans.equals("3")) {
				return Integer.parseInt(ans);
			}else {
				System.out.println("Por favor inserte un valor valido");
				return 0;
			}
		} catch (InvalidIDException ie) {
			System.out.println(ie.getMessage());
		} catch (InvalidDayException id) {
			System.out.println(id.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Integer.parseInt(ans);
	}
	
	public static void askData () throws Exception  {
		/*Calendar c = Calendar.getInstance();
		int dia = Integer.parseInt(Integer.toString(c.get(Calendar.DATE)));*/
		boolean perm;
		String id = "";
		String type = "";
		int testNum;
		Type clientType;
		
		System.out.println("Seleccione el tipo de identificación\n (1) TI -Tarjeta de Identidad\n"
				+ " (2) CC - Cédula de Ciudadanía\n (3) PP -Pasaporte-\n (4) CE -Cédula de Extranjería-");
		
		type = sc.nextLine();
		clientType = validateIDType(type);
		if(clientType == null) {
			throw new Exception("Por favor inserte un valor valido");
		}
		
		System.out.println("Inserte el número de identidad");
		id = sc.nextLine();
		validateID(id);
		validateDayID(id);
		clients.add(new Client(id,clientType));
		System.out.println("Cliente ingresado con exito");
	}
	
	public static Type validateIDType (String type) throws InvalidIDException{
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
	
	/**
	 * las personas cuyo documento tiene el penúltimo número par, pueden salir si el día
	 * del mes es un número impar, y viceversa
	 * @param id
	 * @throws InvalidDayException
	 */
	public static void validateDayID(String id) throws InvalidDayException {
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
	
	public static void validateID(String id) throws Exception {		
		if(id.length() < 2) {
			throw new Exception ("El numero de identificacion no es valido");
		}
	}
	
	public static void showInfo() {
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