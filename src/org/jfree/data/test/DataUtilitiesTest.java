package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Values2D;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {

	private Mockery mockingContext;
	private Values2D values;
	
	@Before
	public void setUp() throws Exception {
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();     
				will(returnValue(2));      
				one(values).getValue(0, 0);     
				will(returnValue(7.5));     
				one(values).getValue(1, 0);  
				will(returnValue(2.5));  
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		mockingContext = null;
		values = null;
	}

	@Test
	public void columnTotalTest() {
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(result, 10.0, .000000001d);
	}
}
