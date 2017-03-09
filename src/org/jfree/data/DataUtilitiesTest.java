package org.jfree.data;

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

	// Objects for mock objects
	private Mockery mockContextValues2D;
	private Values2D values;
	private Mockery mockContextKeyedValues;
	private KeyedValues keyValues;
	
	// Maximum and Minimum double values for testing
	private double maxValDouble = Double.MAX_VALUE;
	private double minValDouble = Double.MIN_VALUE;
	
	@Before
	public void setUp() throws Exception {
		mockContextKeyedValues = new Mockery();
		keyValues = mockContextKeyedValues.mock(KeyedValues.class);
		
		mockContextValues2D = new Mockery();
		values = mockContextValues2D.mock(Values2D.class);
		mockContextValues2D.checking(new Expectations() {
			{
				one(values).getRowCount(); 
				will(returnValue(8));      

				one(values).getColumnCount(); 
				will(returnValue(9));     
				
				/**********************************/
				/* *** UNUSED ROWS & COLUMNS *** */
				/*********************************/
				
				//These values should remain as Zero
				one(values).getValue(0, 1);     
				will(returnValue(0));     
				one(values).getValue(1, 1);  
				will(returnValue(0));
				
				
				/***********************************************/
				/* *** COLUMNS FOR COLUMN TESTING (GURVIR) *** */
				/***********************************************/

				// Zero Test (Columns)
				one(values).getValue(0, 2);
				will(returnValue(0));
				one(values).getValue(1, 2);
				will(returnValue(0));
				one(values).getValue(2, 2);     
				will(returnValue(0));     
				one(values).getValue(3, 2);  
				will(returnValue(0)); 
				one(values).getValue(4, 2);     
				will(returnValue(0));     
				one(values).getValue(5, 2);  
				will(returnValue(0)); 
				one(values).getValue(6, 2);     
				will(returnValue(0));     
				one(values).getValue(7, 2);  
				will(returnValue(0)); 
				
				// Max Values Tests (Columns)
				one(values).getValue(0, 3);
				will(returnValue(maxValDouble));
				one(values).getValue(1, 3);
				will(returnValue(0));
				one(values).getValue(2, 3);     
				will(returnValue(0));     
				one(values).getValue(3, 3);  
				will(returnValue(0)); 
				one(values).getValue(4, 3);     
				will(returnValue(0));     
				one(values).getValue(5, 3);  
				will(returnValue(0)); 
				one(values).getValue(6, 3);     
				will(returnValue(0));     
				one(values).getValue(7, 3);  
				will(returnValue(0)); 
				
				one(values).getValue(0, 4);
				will(returnValue(Math.floor(maxValDouble/2)));
				one(values).getValue(1, 4);
				will(returnValue(Math.floor(maxValDouble/2)));
				one(values).getValue(2, 4);     
				will(returnValue(0));     
				one(values).getValue(3, 4);  
				will(returnValue(0)); 
				one(values).getValue(4, 4);     
				will(returnValue(0));     
				one(values).getValue(5, 4);  
				will(returnValue(0)); 
				one(values).getValue(6, 4);     
				will(returnValue(0));     
				one(values).getValue(7, 4);  
				will(returnValue(0)); 
				
				// Min Values Tests (Columns)
				one(values).getValue(0, 5);
				will(returnValue(minValDouble));
				one(values).getValue(1, 5);
				will(returnValue(0));
				one(values).getValue(2, 5);     
				will(returnValue(0));     
				one(values).getValue(3, 5);  
				will(returnValue(0)); 
				one(values).getValue(4, 5);     
				will(returnValue(0));     
				one(values).getValue(5, 5);  
				will(returnValue(0)); 
				one(values).getValue(6, 5);     
				will(returnValue(0));     
				one(values).getValue(7, 5);  
				will(returnValue(0)); 
				
				one(values).getValue(0, 6);
				will(returnValue(Math.ceil(minValDouble/2)));
				one(values).getValue(1, 6);
				will(returnValue(Math.ceil(minValDouble/2)));
				one(values).getValue(2, 6);     
				will(returnValue(0));     
				one(values).getValue(3, 6);  
				will(returnValue(0)); 
				one(values).getValue(4, 6);     
				will(returnValue(0));     
				one(values).getValue(5, 6);  
				will(returnValue(0)); 
				one(values).getValue(6, 6);     
				will(returnValue(0));     
				one(values).getValue(7, 6);  
				will(returnValue(0)); 
				
				// Nominal Values Test (Column)
				one(values).getValue(0, 7);
				will(returnValue(-1));
				one(values).getValue(1, 7);
				will(returnValue(1));
				one(values).getValue(2, 7);     
				will(returnValue(2.5));     
				one(values).getValue(3, 7);  
				will(returnValue(7.0)); 
				one(values).getValue(4, 7);     
				will(returnValue(0));     
				one(values).getValue(5, 7);  
				will(returnValue(0.5)); 
				one(values).getValue(6, 7);     
				will(returnValue(0));     
				one(values).getValue(7, 7);  
				will(returnValue(0));
				
				one(values).getValue(0, 8);
				will(returnValue(null));
				one(values).getValue(1, 8);
				will(returnValue(null));
				one(values).getValue(2, 8);
				will(returnValue(null));
				one(values).getValue(3, 8);
				will(returnValue(null));
				one(values).getValue(4, 8);
				will(returnValue(null));
				one(values).getValue(5, 8);
				will(returnValue(null));
				one(values).getValue(6, 8);
				will(returnValue(null));
				one(values).getValue(7, 8);
				will(returnValue(null));
				
				
				
				/*****************************************/
				/* *** ROWS FOR ROW TESTING (ANGELA) *** */
				/*****************************************/
				
				one(values).getValue(2, 0); 
				will(returnValue(4.2));     
				one(values).getValue(2, 1); 
				will(returnValue(7.8));    
				
				one(values).getValue(3, 0);
				will(returnValue(0));
				one(values).getValue(3, 1);
				will(returnValue(0));
				
				one(values).getValue(4, 0);     
				will(returnValue(maxValDouble)); 
				one(values).getValue(4, 1);     
				will(returnValue(0)); 
				
				one(values).getValue(5, 0);     
				will(returnValue(maxValDouble/2)); 
				one(values).getValue(5, 1);     
				will(returnValue(maxValDouble/2)); 
				
				one(values).getValue(6, 0);     
				will(returnValue(minValDouble)); 
				one(values).getValue(6, 1);     
				will(returnValue(0)); 
				
				one(values).getValue(7, 0);     
				will(returnValue(minValDouble/2)); 
				one(values).getValue(7, 1);     
				will(returnValue(minValDouble/2));
				
				one(values).getValue(8, 0);     
				will(returnValue(0)); 
				one(values).getValue(8, 1);     
				will(returnValue(0)); 
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		mockContextValues2D = null;
		mockContextKeyedValues = null;
		values = null;
		keyValues = null;
	}

	/*
	 * CALCULATECOLUMNTOTAL()
	 */
	
	// Normal Test
	@Test
	public void calculateColumnTotalNominalTest() {
		double result = DataUtilities.calculateColumnTotal(values, 7);
		assertEquals(10.0, result, .000000001d);
	}
	
	// False Normal test, column 15 not instantiated so an error should be thrown
	@Test
	public void calculateColumnTotalFalseNominalTest() {
		try {
			DataUtilities.calculateColumnTotal(values, 15);
			fail();
		}
		catch(Error e){
			assertTrue(true);
		}
	}
	
	// Total column full of 0's
	@Test
	public void calculateColumnTotalZeroTest() {
		double result = DataUtilities.calculateColumnTotal(values, 2);
		assertEquals(0, result, .000000001d);
	}
	
	// Total Column where one row is max double value
	@Test
	public void calculateColumnTotalMaximumTest1() {		
		double result = DataUtilities.calculateColumnTotal(values, 3);
		assertEquals(maxValDouble, result, .000000001d);
	}
	
	// Total column where two rows are half of max double value
	@Test
	public void calculateColumnTotalMaximumTest2() {
		double result = DataUtilities.calculateColumnTotal(values, 4);
		assertEquals(maxValDouble, result, .000000001d);
	}
	
	// Total Column where one row is minimum double value
	@Test
	public void calculateColumnTotalMinimumTest1() {		
		double result = DataUtilities.calculateColumnTotal(values, 5);
		assertEquals(minValDouble, result, .000000001d);
	}
	
	// Total column where two rows are half of minimum double value
	@Test
	public void calculateColumnTotalMinimumTest2() {
		double result = DataUtilities.calculateColumnTotal(values, 6);
		assertEquals(minValDouble, result, .000000001d);
	}
	
	@Test
	public void calculateColumnTotalNullTest() {
		double result = DataUtilities.calculateColumnTotal(values, 8);
		assertEquals(0.0, result, .000000001d);
	}
	
	/*
	 * CALCULATEROWTOTAL()
	 * calculateRowTotal(Values2D data, int row) - Angela
	 */
	
	@Test
	public void calculateRowTotalNominalTest() {
		double result = DataUtilities.calculateRowTotal(values, 2);
		assertEquals(12.0, result, .000000001d);
	}
	
	
	@Test
	public void calculateRowTotalFalseNominalTest() {
		try {
			DataUtilities.calculateRowTotal(values, 14);
			fail();
		}
		catch(Error e){
			assertTrue(true);
		}
	}
	
	@Test
	public void calculateRowTotalZeroTest() {
		double result = DataUtilities.calculateRowTotal(values, 3);
		assertEquals(0, result, .000000001d);
	}
	
	
	@Test
	public void calculateRowTotalMaximumTest1() {		
		double result = DataUtilities.calculateRowTotal(values, 4);
		assertEquals(maxValDouble, result, .000000001d);
	}
	
	@Test
	public void calculateRowTotalMaximumTest2() {
		double result = DataUtilities.calculateRowTotal(values, 5);
		assertEquals(maxValDouble, result, .000000001d);
	}
	
	@Test
	public void calculateRowTotalMinimumTest1() {		
		double result = DataUtilities.calculateRowTotal(values, 6);
		assertEquals(minValDouble, result, .000000001d);
	}
	
	@Test
	public void calculateRowTotalMinimumTest2() {		
		double result = DataUtilities.calculateRowTotal(values, 7);
		assertEquals(minValDouble, result, .000000001d);
	}
	
	
	/*
	 * CREATENUMBERARRAY()
	 */
	
	// Test a normal array
	@Test
	public void createNumberArrayNominalTest() {
		double[] doubleArray = {0, 1, 2, 3, 4};
		Number[] numArray = DataUtilities.createNumberArray(doubleArray);
		
		assertEquals(doubleArray.length, numArray.length);
		assertEquals(doubleArray[0], numArray[0]);
		assertEquals(doubleArray[1], numArray[1]);
		assertEquals(doubleArray[2], numArray[2]);
		assertEquals(doubleArray[3], numArray[3]);
		assertEquals(doubleArray[4], numArray[4]);
	}
	
	// False normal check between 2 arrays that are not equal
	@Test
	public void createNumberArrayFalseNominalTest() {
		double[] doubleArray = {0, 1, 2, 3, 4};
		Number[] numArray = DataUtilities.createNumberArray(doubleArray);
		Number[] numFalseArray = {4.0, 3.0, 2.0, 1.0, 0.0};
		
		assertEquals(false, numFalseArray == numArray);
		
	}
	
	// Check for all zero value array
	@Test
	public void createNumberArrayZeroTest() {
		double[] doubleArray = {0.0, 0.0, 0.0, 0.0, 0.0};
		Number[] numArray = DataUtilities.createNumberArray(doubleArray);
		
		for (int i = 0; i < doubleArray.length; i++)
			assertEquals((double)0.0, numArray[i]);
	}
	
	// Check for max double value in different array elements
	@Test
	public void createNumberArrayMaximumTest() {
		double[] doubleArray = {0.0, maxValDouble/2, maxValDouble, maxValDouble};
		Number[] numArray = DataUtilities.createNumberArray(doubleArray);
		
		assertEquals(doubleArray[0], numArray[0]);
		assertEquals(doubleArray[1], numArray[1]);
		assertEquals(doubleArray[2], numArray[2]);
		assertEquals(doubleArray[3], numArray[3]);
	}
	
	// Check for minimum double value in different array elements
	@Test
	public void createNumberArrayMinimumTest() {
		double[] doubleArray = {0.0, -1.0, minValDouble/2, minValDouble, minValDouble};
		Number[] numArray = DataUtilities.createNumberArray(doubleArray);
		
		assertEquals(doubleArray[0], numArray[0]);
		assertEquals(doubleArray[1], numArray[1]);
		assertEquals(doubleArray[2], numArray[2]);
		assertEquals(doubleArray[3], numArray[3]);
		assertEquals(doubleArray[4], numArray[4]);
	}
	
	
	/*
	 * CREATENUMBERARRAY2D()
	 */
	
	// Check for normal 2d array of doubles
	@Test
	public void createNumberArray2DNominalTest() {
		double[][] doubleArray = 
			{{0, 1, 2, 3, 4},
			{4, 3, 2, 1, 0}};
		Number[][] numArray = DataUtilities.createNumberArray2D(doubleArray);
		
		assertEquals(doubleArray.length, numArray.length);
		
		for(int i=0; i < doubleArray.length; i++) {
			for(int j=0; j < doubleArray[i].length; j++)
				assertEquals(doubleArray[i][j], numArray[i][j]);
		}
	}
	
	// False normal, check equality between 2 different double arrays
	@Test
	public void createNumberArray2DFalseNominalTest() {
		double[][] doubleArray = 
			{{0, 1, 2, 3, 4},
			{4, 3, 2, 1, 0}};
		Number[][] numArray = DataUtilities.createNumberArray2D(doubleArray);
		Number[][] numFalseArray = 
			{{4.0, 3.0, 2.0, 1.0, 0.0},
			{0.0, 1.0, 2.0, 3.0, 4.0}};
		
		assertEquals(false, numFalseArray == numArray);
	}
	
	// Zero 2D array test
	@Test
	public void createNumberArray2DZeroTest() {
		double[][] doubleArray = 
			{{0.0, 0.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 0.0, 0.0}};
		Number[][] numArray = DataUtilities.createNumberArray2D(doubleArray);
		
		for(int i=0; i < doubleArray.length; i++) {
			for(int j=0; j < doubleArray[i].length; j++)
				assertEquals(0.0, numArray[i][j]);
		}
	}
	
	// Maximum test, test maximum values of double
	@Test
	public void createNumberArray2DMaximumTest() {
		double[][] doubleArray = 
			{{0.0, maxValDouble/2, maxValDouble, maxValDouble},
			{maxValDouble, maxValDouble, maxValDouble/2, 0.0}};
		Number[][] numArray = DataUtilities.createNumberArray2D(doubleArray);
		
		for(int i=0; i < doubleArray.length; i++) {
			for(int j=0; j < doubleArray[i].length; j++)
				assertEquals(doubleArray[i][j], numArray[i][j]);
		}
	}
	
	// Minimum test, test minimum values of double
	@Test
	public void createNumberArray2DMinimumTest() {
		double[][] doubleArray = 
			{{0.0, -1.0, minValDouble/2, minValDouble, minValDouble},
			{minValDouble, minValDouble, minValDouble/2, -1.0, 0.0}};
		Number[][] numArray = DataUtilities.createNumberArray2D(doubleArray);
		
		for(int i=0; i < doubleArray.length; i++) {
			for(int j=0; j < doubleArray[i].length; j++)
				assertEquals(doubleArray[i][j], numArray[i][j]);
		}
	}
	
	
	/*
	 * GETCUMULATIVEPERCENTAGES()
	 * 
	 * Note: These tests have expectations set in each test as the only way 
	 * 		 to get around this is to create several mock objects.
	 */
	
	// Normal test, check positive double values close to 0
	@Test
	public void getCumulativePercentagesNominalTest() {
		mockContextKeyedValues.checking(new Expectations() {
			{
				allowing(keyValues).getItemCount();
				will(returnValue(3));
				
				allowing(keyValues).getKey(0);
				will(returnValue(0));
				allowing(keyValues).getKey(1);
				will(returnValue(1));
				allowing(keyValues).getKey(2);
				will(returnValue(2));
				
				allowing(keyValues).getValue(0);
				will(returnValue(1));
				allowing(keyValues).getValue(1);
				will(returnValue(10));
				allowing(keyValues).getValue(2);
				will(returnValue(20));
			}			
		});
		
		KeyedValues cumVal = DataUtilities.getCumulativePercentages(keyValues);
		double cumTotal = 0;
		
		for(int i=0; i < keyValues.getItemCount(); i++)
			cumTotal += keyValues.getValue(i).doubleValue();
		
		
		// A loop that checks the cumulative total against the function (used in all tests below)
		double currentTotal = 0;
		for (int i=0; i < keyValues.getItemCount(); i++) {
			currentTotal += keyValues.getValue(i).doubleValue(); 
			assertEquals(currentTotal/cumTotal, cumVal.getValue(i).doubleValue(),.000000001d);
		}
	}
	
	// Test minimum double values as key's
	@Test
	public void getCumulativePercentagesMinimumTest() {
		mockContextKeyedValues.checking(new Expectations() {
			{				
				allowing(keyValues).getItemCount();
				will(returnValue(3));
				
				allowing(keyValues).getKey(0);
				will(returnValue(0));
				allowing(keyValues).getKey(1);
				will(returnValue(1));
				allowing(keyValues).getKey(2);
				will(returnValue(2));
				
				allowing(keyValues).getValue(0);
				will(returnValue(0));
				allowing(keyValues).getValue(1);
				will(returnValue(minValDouble/2));
				allowing(keyValues).getValue(2);
				will(returnValue(minValDouble));
			}			
		});
		
		KeyedValues cumVal = DataUtilities.getCumulativePercentages(keyValues);
		double cumTotal = 0;
		
		for(int i=0; i < keyValues.getItemCount(); i++)
			cumTotal += keyValues.getValue(i).doubleValue();
		
		double currentTotal = 0;
		for (int i=0; i < keyValues.getItemCount(); i++) {
			currentTotal += keyValues.getValue(i).doubleValue(); 
			assertEquals(currentTotal/cumTotal, cumVal.getValue(i).doubleValue(),.000000001d);
		}
	}
	
	// Test maximum double values as key;s
	@Test
	public void getCumulativePercentagesMaximumTest() {
		mockContextKeyedValues.checking(new Expectations() {
			{				
				allowing(keyValues).getItemCount();
				will(returnValue(3));
				
				allowing(keyValues).getKey(0);
				will(returnValue(0));
				allowing(keyValues).getKey(1);
				will(returnValue(1));
				allowing(keyValues).getKey(2);
				will(returnValue(2));
				
				allowing(keyValues).getValue(0);
				will(returnValue(0));
				allowing(keyValues).getValue(1);
				will(returnValue(maxValDouble/2));
				allowing(keyValues).getValue(2);
				will(returnValue(maxValDouble));
			}			
		});
		
		KeyedValues cumVal = DataUtilities.getCumulativePercentages(keyValues);
		double cumTotal = 0;
		
		for(int i=0; i < keyValues.getItemCount(); i++)
			cumTotal += keyValues.getValue(i).doubleValue();
		
		double currentTotal = 0;
		for (int i=0; i < keyValues.getItemCount(); i++) {
			currentTotal += keyValues.getValue(i).doubleValue(); 
			assertEquals(currentTotal/cumTotal, cumVal.getValue(i).doubleValue(),.000000001d);
		}
	}
	
	// Zero test, all keys are zero (this checks the dividing by zero cases)
	@Test
	public void getCumulativePercentagesZeroTest() {
		mockContextKeyedValues.checking(new Expectations() {
			{				
				allowing(keyValues).getItemCount();
				will(returnValue(3));
				
				allowing(keyValues).getKey(0);
				will(returnValue(0));
				allowing(keyValues).getKey(1);
				will(returnValue(1));
				allowing(keyValues).getKey(2);
				will(returnValue(2));
				
				allowing(keyValues).getValue(0);
				will(returnValue(0));
				allowing(keyValues).getValue(1);
				will(returnValue(0));
				allowing(keyValues).getValue(2);
				will(returnValue(0));
			}			
		});
		
		KeyedValues cumVal = DataUtilities.getCumulativePercentages(keyValues);
		double cumTotal = 0;
		
		for(int i=0; i < keyValues.getItemCount(); i++)
			cumTotal += keyValues.getValue(i).doubleValue();
		
		double currentTotal = 0;
		for (int i=0; i < keyValues.getItemCount(); i++) {
			currentTotal += keyValues.getValue(i).doubleValue(); 
			assertEquals(currentTotal/cumTotal, cumVal.getValue(i).doubleValue(),.000000001d);
		}
	}
	
	// Test where keys add to zero but some keys are non-zero (also dividing by zero test)
	@Test
	public void getCumulativePercentagesEqualToZeroTest() {
		mockContextKeyedValues.checking(new Expectations() {
			{				
				allowing(keyValues).getItemCount();
				will(returnValue(3));
				
				allowing(keyValues).getKey(0);
				will(returnValue(0));
				allowing(keyValues).getKey(1);
				will(returnValue(1));
				allowing(keyValues).getKey(2);
				will(returnValue(2));
				
				allowing(keyValues).getValue(0);
				will(returnValue(5));
				allowing(keyValues).getValue(1);
				will(returnValue(0));
				allowing(keyValues).getValue(2);
				will(returnValue(-5));
			}			
		});
		
		KeyedValues cumVal = DataUtilities.getCumulativePercentages(keyValues);
		double cumTotal = 0;
		
		for(int i=0; i < keyValues.getItemCount(); i++)
			cumTotal += keyValues.getValue(i).doubleValue();
		
		double currentTotal = 0;
		for (int i=0; i < keyValues.getItemCount(); i++) {
			currentTotal += keyValues.getValue(i).doubleValue();
			assertEquals(currentTotal/cumTotal, cumVal.getValue(i).doubleValue(), .000000001d);
		}
	}
	
}
