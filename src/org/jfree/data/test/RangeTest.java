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
	
	//Nominal value for getCentralValue
	@Test
	public void getCentralValueNominalTest() {
		double centralResult = rangeObject.getCentralValue();
		assertEquals(0, centralResult, 0);		
	}
	
	//Central value where a range difference is 0
	@Test
	public void getCentralValueNoRangeTest() {
		rangeObject = new Range(-1,-1);
		double centralResult = rangeObject.getCentralValue();
		assertEquals(-1, centralResult, 0);		
	}
	
	//Central value for a range of 0 to the largest possible double value
	@Test
	public void getCentralValueMaximumTest() {
		rangeObject = new Range(0, maxValDouble);
		assertEquals(maxValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	//Central value for a range of the smallest possible double to 0
	@Test
	public void getCentralValueMinimumTest() {
		rangeObject = new Range(minValDouble, 0);
		assertEquals(minValDouble/2, rangeObject.getCentralValue(), 0);
	}
	
	//Central value for a range from the smallest to the largest possible double
	@Test
	public void getCentralValueMaximumRangeTest() {
		double middleValue = (maxValDouble + minValDouble) / 2;
		rangeObject = new Range(minValDouble, maxValDouble);
		assertEquals(middleValue, rangeObject.getCentralValue(), 0);
	}
	
	/*
	 * CONTAINS()
	 */
	
	//Checks if a value is contained in a nominal range
	@Test
	public void ContainsNominalTest(){
		boolean isContained = rangeObject.contains(3);
		assertEquals(true, isContained);
	}
	
	//checks if a values is not contained in a nominal range
	@Test
	public void DoesNotContainNominalTest(){
		boolean isContained = rangeObject.contains(101);
		assertEquals(false, isContained);
	}
	
	//checks if a value is contained from 0 to the maximum possible double
	@Test
	public void ContainsMaximumTest(){
		rangeObject = new Range(0, maxValDouble);
		assertEquals(true, rangeObject.contains(maxValDouble));
	}
	
	//checks if a value is not contained in a range from 0 to the maximum possible double
	@Test
	public void DoesNotContainMaximumTest(){
		rangeObject = new Range(0, maxValDouble);
		assertEquals(false, rangeObject.contains(-1));
	}
	
	//Contains for a range of the smallest possible double to 0
	@Test
	public void ContainsMinimumTest(){
		rangeObject = new Range(minValDouble, 0);
		assertEquals(true, rangeObject.contains(minValDouble));
	}
	
	//Does not contain for a range of the smallest possible double to 0
	@Test
	public void DoesNotContainMinimumTest(){
		rangeObject = new Range(minValDouble, 0);
		assertEquals(false, rangeObject.contains(1));
	}
	
	/*
	 * EQUALS()
	 */

	//Two nominal ranges are equal
	@Test
	public void EqualsNominalTest(){
		Range rangeObjectTwo = new Range(-100, 100);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	//nominal ranges are not equal, differing on the lower end
	@Test
	public void DoesNotEqualNominalTestLower(){
		Range rangeObjectTwo = new Range(-101, 100);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	//nominal ranges are equal differing on the upper end
	@Test
	public void DoesNotEqualNominalTestUpper(){
		Range rangeObjectTwo = new Range(-100, 101);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	//Largest possible ranges are equal
	@Test
	public void EqualsMaximumTest(){
		rangeObject = new Range(minValDouble, maxValDouble);
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	//Largest possible ranges are not equal
	@Test
	public void DoesNotEqualMaximumTest(){
		Range rangeObjectTwo = new Range(minValDouble, maxValDouble);
		assertEquals(false, rangeObject.equals(rangeObjectTwo));
	}
	
	//smallest possible difference in ranges are equal
	@Test
	public void EqualsMinimumTest(){
		rangeObject = new Range(0, 0);
		Range rangeObjectTwo = new Range(0, 0);
		assertEquals(true, rangeObject.equals(rangeObjectTwo));
	}
	
	/*
	 * INTERSECTS()
	 */
	
	//Two nominal ranges intersect
	@Test
	public void IntersectsNominalTrueTest(){
		assertEquals(true, rangeObject.intersects(-100, 100));
	}
	
	//Nominal ranges intersect on bottom
	@Test
	public void IntersectsBottomTest(){
		assertEquals(true, rangeObject.intersects(-100, 0));
	}
	
	//Nominal ranges intersect on top
	@Test
	public void IntersectsTopTest(){
		assertEquals(true, rangeObject.intersects(0, 99));
	}
	
	//Nominal ranges intersect with a difference of 0
	@Test
	public void IntersectsMinimumRangeTest(){
		rangeObject = new Range(0,0);	
		assertEquals(true, rangeObject.intersects(0, 0));
	}
	
	//nominal ranges intersect of maximum possible size
	@Test
	public void IntersectsMaximumRangeTest(){
		Range rangeObject = new Range(minValDouble, maxValDouble);
		assertEquals(true, rangeObject.intersects(minValDouble, maxValDouble));
	}
	
	/*
	 * SHIFT()
	 */
	
	//Nominal shift test, shifting values by 5
	@Test
	public void ShiftNominalTest(){
		double shiftValue = 5;
		Range rangeObjectTwo = new Range(-95, 105);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	//Shifting by 0
	@Test
	public void ShiftMinimumShiftTest(){
		double shiftValue = 0;
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(0, 0);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	//Shifting by maximum possible double
	@Test
	public void ShiftMaximumShiftTest(){
		double shiftValue = maxValDouble;
		
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(maxValDouble, maxValDouble);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	//shift near maximum possible double
	@Test
	public void ShiftNearMaximumShiftTest(){
		double shiftValue = maxValDouble -1;
		
		rangeObject = new Range(0,0);
		Range rangeObjectTwo = new Range(maxValDouble-1, maxValDouble-1);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
	
	//shift a negative (left) amount (by -1)
	@Test
	public void ShiftNegativeShiftTest(){
		double shiftValue = -1;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-101, 99);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectShifted));
	}
}
