package ui;
import java.util.Scanner;
import model.Market;
import Exceptions.InvalidDayException;
import Exceptions.InvalidIDException;

public class Main {
	
	//public static ArrayList<Client> clients = new ArrayList<Client>();
	public static Scanner sc = new Scanner(System.in);
	
	public static Market market = new Market("Mi barrio te quiere");
	
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
				market.totalClients++;
				askData();
			}else if(ans.equals("2")) {
				market.showInfo();
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
		String id = "";
		String type = "";
		boolean clientIn;
		
		System.out.println("Seleccione el tipo de identificación\n (1) TI -Tarjeta de Identidad\n"
				+ " (2) CC - Cédula de Ciudadanía\n (3) PP -Pasaporte-\n (4) CE -Cédula de Extranjería-");
		
		type = sc.nextLine();
		
		System.out.println("Inserte el número de identidad");
		id = sc.nextLine();
		
		clientIn = market.addClient(id,type);
		
		if(clientIn == false) {
			throw new Exception("Por favor inserte un valor valido");
		}else {
			System.out.println("Cliente ingresado con exito");
		}
		
	}
	
	
}