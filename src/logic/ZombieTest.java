package logic;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

public class ZombieTest {
	
	// Testing constructors
		@Test
		public void test_Constructor_EmptyStringAnd0() throws FileNotFoundException {
			Zombie c = new Zombie("", 0);
			assertEquals("Created zombie with empty type and row 0 - testing type", "Zombie", c.getType());
			assertEquals("Created zombie with empty type and row 0 - testing row", 3, c.getRow());
		}

		@Test
		public void test_Constructor_NegativeRow() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", -1);
			assertEquals("Created zombie with 'Flag Zombie' type and row -1 - testing type", "Flag Zombie", c.getType());
			assertEquals("Created zombie with 'Flag Zombie' type and row -1 - testing row", 3, c.getRow());
		}

		@Test
		public void test_Constructor_InvalidRow() throws FileNotFoundException {
			Zombie c = new Zombie("Cone Zombie", 11);
			assertEquals("Created zombie with 'Cone Zombie' type and row 11 - testing type", "Cone Zombie", c.getType());
			assertEquals("Created zombie with 'Cone Zombie' type and row 11 - testing row", 3, c.getRow());
		}
		
		@Test 
		public void test_CopyConstructor() throws FileNotFoundException {
			Zombie c = new Zombie("Football Zombie", 5);
			Zombie c2 = new Zombie(c);
			assertEquals("Testing Copy Constructor, copying 'Football Zombie' type and row 5 - testing type", "Football Zombie", c2.getType());
			assertEquals("Testing Copy Constructor, copying 'Football Zombie' Copy Constructor' type and row 5 - testing row", 5, c2.getRow());
		}

		@Test 
		public void test_CopyConstructor2() throws FileNotFoundException {
			Zombie c = new Zombie("Cone Zombie", 2);
			Zombie c2 = new Zombie(c);
			assertEquals("Testing Copy Constructor, copying 'Cone Zombie' title and row 2 - testing type", "Cone Zombie", c2.getType());
			assertEquals("Testing Copy Constructor, copying 'Cone Zombie' title and row 2 - testing row", 2, c2.getRow());
		}

	// Testing setter and getters
		
		
		@Test
		public void test_setter_and_getter_health() throws FileNotFoundException{
			Zombie c = new Zombie("FootBall Zombie", 3);
			c.setHealth(2000);
			assertEquals("Set health to 2000", 2000, c.getHealth());
		}
				
		@Test
		public void test_setter_and_getter_speed_positiveNumber() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setSpeed(1000);
			assertEquals("Set speed to 1000.", 1000, c.getSpeed(), 0.000001);
		}
		
		@Test
		public void test_setter_and_getter_speed_negativeNumber() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setSpeed(-5);
			assertEquals("Set speed to -5.", 0.2, c.getSpeed(), 0.000001);
		}
		
		@Test
		public void test_setter_and_getter_speed_zero() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setSpeed(0);
			assertEquals("Set speed to 0.", 0, c.getSpeed(), 0.000001);
		}
		
		@Test
		public void test_setter_and_getter_attack_positiveNumber() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setAttack(100);
			assertEquals("Set attack to 100.", 100, c.getAttack());
		}
		
		@Test
		public void test_setter_and_getter_attack_negativeNumber() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setAttack(-5);
			assertEquals("Set attack to -5.", 10, c.getAttack());
		}
		
		@Test
		public void test_setter_and_getter_attack_zero() throws FileNotFoundException {
			Zombie c = new Zombie("Flag Zombie", 5);
			c.setAttack(0);
			assertEquals("Set attack to 0.", 0, c.getAttack());
		}

}
