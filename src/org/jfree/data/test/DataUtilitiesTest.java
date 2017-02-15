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
	private double maxValDouble = Double.MAX_VALUE;
	private double minValDouble = Double.MIN_VALUE;

	
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
				
				one(values).getValue(0,1);
				will(returnValue(maxValDouble));
				one(values).getValue(1,1);
				will(returnValue(0));
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		mockingContext = null;
		values = null;
	}

	/*
	 * CALCULATECOLUMNTOTAL()
	 */
	
	@Test
	public void calculateColumnTotalNominalTest() {
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void calculateColumnTotalMaximumTest() {		
		double result = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(result, maxValDouble, .000000001d);
	}
}
