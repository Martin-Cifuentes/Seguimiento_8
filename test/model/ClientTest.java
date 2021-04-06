package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ClientTest {
	
	public void setUpScenary1() {
	}
	
	@Test
	void testClient() {
		setUpScenary1();
		
		String id = "9234892874";
		Type t = Type.CC;
		
		Client c = new Client(id,t);
		
		assertEquals(id, c.getId());
		assertEquals(t, c.getType());
		
	}

}
