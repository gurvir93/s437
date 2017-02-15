package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.Double;

public class RangeTest {

	private Range rangeObject;
	private double maxValDouble = Double.MAX_VALUE;
	private double minValDouble = Double.MIN_VALUE;
	
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
		rangeObject = new Range(0, maxValDouble);
		assertEquals(maxValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	@Test
	public void getCentralValueMinimumTest() {
		rangeObject = new Range(minValDouble, 0);
		assertEquals(minValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	@Test
	public void getCentralValueMaximumRangeTest() {
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
		rangeObject = new Range(0, maxValDouble);
		assertEquals(true, rangeObject.contains(maxValDouble));
	}
	
	@Test
	public void DoesNotContainMaximumTest(){
		rangeObject = new Range(0, maxValDouble);
		assertEquals(false, rangeObject.contains(-1));
	}
	
	@Test
	public void ContainsMinimumTest(){
		rangeObject = new Range(minValDouble, 0);
		assertEquals(true, rangeObject.contains(minValDouble));
	}
	
	@Test
	public void DoesNotContainMinimumTest(){
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
		rangeObject = new Range(minValDouble, maxValDouble);
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void DoesNotEqualMaximumTest(){
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	@Test
	public void EqualsMinimumTest(){
		rangeObject = new Range(0, 0);
		Range rangeObjectTwo = new Range(0, 0);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	/*
	 * INTERSECTS()
	 */
	
	@Test
	public void IntersectsNominalTrueTest(){
		assertEquals(true, rangeObject.intersects(-100, 100));
	}
	
	@Test
	public void IntersectsBottomTest(){
		assertEquals(true, rangeObject.intersects(-100, 0));
	}
	
	@Test
	public void IntersectsTopTest(){
		assertEquals(true, rangeObject.intersects(0, 99));
	}
	
	@Test
	public void IntersectsMinimumRangeTest(){
		rangeObject = new Range(0,0);	
		assertEquals(true, rangeObject.intersects(0, 0));
	}
	
	@Test
	public void IntersectsMaximumRangeTest(){
		Range rangeObject = new Range(minValDouble, maxValDouble);
		assertEquals(true, rangeObject.intersects(minValDouble, maxValDouble));
	}
	
	/*
	 * SHIFT()
	 */
	
	@Test
	public void ShiftNominalTest(){
		double shiftValue = 5;
		Range rangeObjectTwo = new Range(-95, 105);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	@Test
	public void ShiftMinimumShiftTest(){
		double shiftValue = 0;
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(0, 0);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	
	@Test
	public void ShiftMaximumShiftTest(){
		double shiftValue = maxValDouble;
		
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(maxValDouble, maxValDouble);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	@Test
	public void ShiftNearMaximumShiftTest(){
		double shiftValue = maxValDouble -1;
		
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(maxValDouble-1, maxValDouble-1);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	@Test
	public void ShiftNegativeShiftTest(){
		double shiftValue = -1;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-101, 99);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
}
