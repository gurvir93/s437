package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.Double;

public class RangeTest {

	private Range rangeObject;
	
	@Before
	public void setUp() throws Exception {
		rangeObject = new Range(-100, 100);
	}

	@After
	public void tearDown() throws Exception {
		rangeObject = null;
	}

	/*
	 * GETCENTRALVALUE()
	 */
	
	@Test
	public void getCentralValueNominalTest() {
		double centralResult = rangeObject.getCentralValue();
		assertEquals(0, centralResult, 0);		
	}
	
	@Test
	public void getCentralValueNoRangeTest() {
		rangeObject = new Range(-1,-1);
		double centralResult = rangeObject.getCentralValue();
		assertEquals(-1, centralResult, 0);		
	}
	
	@Test
	public void getCentralValueMaximumTest() {
		double maxValDouble = Double.MAX_VALUE;
		rangeObject = new Range(0, maxValDouble);
		assertEquals(maxValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	@Test
	public void getCentralValueMinimumTest() {
		double minValDouble = Double.MIN_VALUE;
		rangeObject = new Range(minValDouble, 0);
		assertEquals(minValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	@Test
	public void getCentralValueMaximumRangeTest() {
		double maxValDouble = Double.MAX_VALUE;
		double minValDouble = Double.MIN_VALUE;
		
		double middleValue = (maxValDouble + minValDouble) / 2;
		rangeObject = new Range(minValDouble, maxValDouble);
		assertEquals(middleValue, rangeObject.getCentralValue(), 0);
	}
	
	/*
	 * CONTAINS()
	 */
	
	@Test
	public void ContainsNominalTest(){
		boolean isContained = rangeObject.contains(3);
		assertEquals(true, isContained);
	}
	
	@Test
	public void DoesNotContainNominalTest(){
		boolean isContained = rangeObject.contains(101);
		assertEquals(false, isContained);
	}
	
	@Test
	public void ContainsMaximumTest(){
		double maxValDouble = Double.MAX_VALUE;
		rangeObject = new Range(0, maxValDouble);
		assertEquals(true, rangeObject.contains(maxValDouble));
	}
	
	@Test
	public void DoesNotContainMaximumTest(){
		double maxValDouble = Double.MAX_VALUE;
		rangeObject = new Range(0, maxValDouble);
		assertEquals(false, rangeObject.contains(-1));
	}
	
	@Test
	public void ContainsMinimumTest(){
		double minValDouble = Double.MIN_VALUE;
		rangeObject = new Range(minValDouble, 0);
		assertEquals(true, rangeObject.contains(minValDouble));
	}
	
	@Test
	public void DoesNotContainMinimumTest(){
		double minValDouble = Double.MIN_VALUE;
		rangeObject = new Range(minValDouble, 0);
		assertEquals(false, rangeObject.contains(-1));
	}
	
	/*
	 * EQUALS()
	 */

	@Test
	public void EqualsNominalTest(){
		Range rangeObjectTwo = new Range(-100, 100);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void DoesNotEqualNominalTestLower(){
		Range rangeObjectTwo = new Range(-101, 100);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void DoesNotEqualNominalTestUpper(){
		Range rangeObjectTwo = new Range(-100, 101);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void EqualsMaximumTest(){
		double maxValDouble = Double.MAX_VALUE;
		double minValDouble = Double.MIN_VALUE;
		
		rangeObject = new Range(minValDouble, maxValDouble);
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void DoesNotEqualMaximumTest(){
		double maxValDouble = Double.MAX_VALUE;
		double minValDouble = Double.MIN_VALUE;
		
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void EqualsMinimumTest(){
		
		rangeObject = new Range(0, 0);
		Range rangeObjectTwo = new Range(0, 0);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
}
