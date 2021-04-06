package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import Exceptions.InvalidDayException;
import Exceptions.InvalidIDException;

class MarketTest {
	public Market market;
	
	public void setUpScenary1() {
	}
	
	public void setUpScenary2() {
		market = new Market("Market");
	}
	
	@Test
	void testMarket() {
		setUpScenary1();
		
		String n = "Market";
		
		market = new Market(n);
		
		assertTrue(market.getClients().isEmpty());
		assertEquals(n,market.getName());
		assertEquals(0, market.getTotalClients());
	}
	
	@Test
	void testAddClient1() throws Exception {
		setUpScenary2();
		
		String id = "9234892" + (Calendar.getInstance().get(Calendar.DATE) + 1) + 4;
		String t = "2";
		
			market.addClient(id,t);
			assertTrue(market.getClients().size() == 1);
			
	}
	
	@Test
	void testAddClient2() throws Exception {
		setUpScenary2();
		
		String id = "9234892" + (Calendar.getInstance().get(Calendar.DATE) + 1) + 4;
		String t = "1";
		try {
			market.addClient(id,t);
		}catch (InvalidIDException ide){
			assertEquals(ide.getMessage(),("No puede ingresar porque es menor de edad"));
		}		
	}
	
	@Test
	void testAddClient3() throws Exception {
		setUpScenary2();
		
		String id = "9234892" + (Calendar.getInstance().get(Calendar.DATE)) + 4;
		String t = "2";
		try {
			market.addClient(id,t);
		}catch (InvalidDayException iie){
			assertEquals(iie.getMessage(),("No puede ingresar porque tiene pico y cedula"));
		}	
	}
	//dia
	@Test
	void testValidateDayID() {
		setUpScenary2();
		
		String id = "9234892" + (Calendar.getInstance().get(Calendar.DATE)) + 4;
		try {
			market.validateDayID(id);
		}
		catch (InvalidDayException iie){
			assertEquals(iie.getMessage(),("No puede ingresar porque tiene pico y cedula"));
		}	
	}
	//tipo
	@Test
	void testValidateIDType1() throws InvalidIDException {
		setUpScenary2();
		
		String t = "2";
		
		Type ty = market.validateIDType(t);
		assertEquals(ty, Type.CC);
	}
	
	@Test
	void testValidateIDType2(){
		setUpScenary2();
		
		String t = "1";
		
		try {
			market.validateIDType(t);
		}catch (InvalidIDException ide){
			assertEquals(ide.getMessage(),("No puede ingresar porque es menor de edad"));
		}		
	}
	
	@Test
	void testValidateID() {
		setUpScenary2();
		
		String id = "9";
		try {
			market.validateID(id);
		}
		catch (Exception e){
			assertEquals(e.getMessage(),("El numero de identificacion no es valido"));
		}	
	}
	
}
