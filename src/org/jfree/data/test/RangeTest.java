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

}
