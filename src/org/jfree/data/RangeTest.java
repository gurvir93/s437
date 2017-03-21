package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.Double;

public class RangeTest {

	private Range rangeObject;
	private double maxValDouble = Double.MAX_VALUE;
	private double minValDouble = -Double.MIN_VALUE;
	
	@Before
	public void setUp() throws Exception {
		rangeObject = new Range(-100, 100);
	}

	@After
	public void tearDown() throws Exception {
		rangeObject = null;
	}

	@Test (expected = IllegalArgumentException.class)
	public void rangeObjectTest() {
		
		// Test for exception message
		try {
			rangeObject = new Range(100, 0);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Range(double, double): require lower (100.0) <= upper (0.0).", e.getMessage());
		}
		
		// Will get caught by expected
		rangeObject = new Range(100, 0);
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
		assertEquals(false, rangeObject.contains(1));
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
		assertEquals(false, rangeObject.intersects(0,0));
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
	
	/*
	 * For Code Coverage increase
	 */
	
	
	@Test
	public void ExpandRangeTest(){

		rangeObject = new Range(2, 6);
		Range rangeObjectTwo = new Range(1, 8);
		
		Range rangeObjectExpanded = Range.expand(rangeObject, 0.25, 0.5);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));
	}
	
	@Test
	public void ExpandToIncludeRangeUpperTest(){
		double expandValue = 5;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-100, 105);
		
		Range rangeObjectExpanded = Range.expandToInclude(rangeObject, expandValue);
		
		assertEquals(false, rangeObjectTwo.equals(rangeObjectExpanded));
		rangeObjectTwo = new Range(-100, 100);
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));

	}
	
	@Test
	public void expandToIncludeRangeIncreaseUpperTest(){
		double expandValue = 110;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-100, 110);
		
		Range rangeObjectExpanded = Range.expandToInclude(rangeObject, expandValue);
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));

	}
	
	@Test
	public void ExpandToIncludeRangeLowerTest(){
		double expandValue = -5;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-100, 100);
		
		Range rangeObjectExpanded = Range.expandToInclude(rangeObject, expandValue);
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));
	}
	
	@Test
	public void expandToIncludeRangeIncreaseLowerTest(){
		double expandValue = -105;
		
		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-105, 100);
		
		Range rangeObjectExpanded = Range.expandToInclude(rangeObject, expandValue);
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));

	}
	
	@Test
	public void ExpandToIncludeRangeNullTest(){
		double expandValue = 5;
		
		rangeObject = null;
		Range rangeObjectTwo = new Range(5, 5);
		
		Range rangeObjectExpanded = Range.expandToInclude(rangeObject, expandValue);
		
		assertEquals(true, rangeObjectTwo.equals(rangeObjectExpanded));
	}
	
	@Test
	public void CombineTest(){

		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = new Range(-200, -99);
		
		Range rangeObjectCombined = Range.combine(rangeObject, rangeObjectTwo);
		
		Range comparedRangeObject = new Range(-200, 100);
		
		assertEquals(true, rangeObjectCombined.equals(comparedRangeObject));
	}
	
	@Test
	public void CombineNullOneTest(){

		rangeObject = null;
		Range rangeObjectTwo = new Range(-200, -99);
		
		Range rangeObjectCombined = Range.combine(rangeObject, rangeObjectTwo);
		
		assertEquals(true, rangeObjectCombined.equals(rangeObjectTwo));
	}
	
	@Test
	public void CombineNullTwoTest(){

		rangeObject = new Range(-100, 100);
		Range rangeObjectTwo = null;
		
		Range rangeObjectCombined = Range.combine(rangeObject, rangeObjectTwo);
		assertEquals(true, rangeObjectCombined.equals(rangeObject));
	}
	
	@Test
	public void constrainInRangeTest() {
		rangeObject = new Range(0, 100);
		double constrainValue = 42;
		
		double returnedConstrained = rangeObject.constrain(constrainValue);
		
		assertEquals(42, returnedConstrained, 0);
	}
	
	@Test
	public void ConstrainUpperTest(){

		rangeObject = new Range(-100, 100);
		double constrainValue = 102;
		
		double returnedConstrained = rangeObject.constrain(constrainValue);
		
		assertEquals(100, returnedConstrained, 0);
	}
	
	@Test
	public void ConstrainLowerTest(){

		rangeObject = new Range(-100, 100);
		double constrainValue = -102;
		
		double returnedConstrained = rangeObject.constrain(constrainValue);
		
		assertEquals(-100, returnedConstrained, 0);
	}
	
	@Test
	public void HashCodeTest(){
		rangeObject = new Range(-100, 100);

        assertEquals(174981120, rangeObject.hashCode());
	}
	
	@Test
	public void ToStringTest(){
		rangeObject = new Range(-100,100);
		
		String rangeString = "Range[-100.0,100.0]";
		assertEquals(true, rangeObject.toString().equals(rangeString));
	}
	
	@Test
	public void notEqualsTest(){
		rangeObject = new Range(-100,100);
		
		String rangeString = "Range[-100,100]";
		
		assertEquals(false, rangeObject.equals(rangeString));
	}
	
	/*
	 * For PiTest increase
	 */
	
	@Test(expected=IllegalArgumentException.class)
	public void errorTest(){
		rangeObject = new Range(100, -100);
	}
	
	@Test
	public void ShiftDownNoZeroCrossingTest(){
		double shiftValue = -80;
		rangeObject = new Range(10, 100);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue, false);
		
		Range trueRangeObject = new Range(0, 20);
		
		assertEquals(true, rangeObjectShifted.equals(trueRangeObject));
	}
	
	@Test
	public void ShiftUpNoZeroCrossingTest(){
		double shiftValue = 80;
		rangeObject = new Range(-100, -20);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue, false);
		
		Range trueRangeObject = new Range(-20, 0);
		
		assertEquals(true, rangeObjectShifted.equals(trueRangeObject));
	}

	@Test
	public void instersectsOneValueInsideTest() {
		assertEquals(true, rangeObject.intersects(-99, -99));
	}
	
	@Test
	public void IntersectsLessThanLowerTest(){
		assertEquals(false, rangeObject.intersects(-99, 100));
	}
	
	@Test
	public void IntersectLowerGreaterThanHigherTest(){
		assertEquals(false, rangeObject.intersects(-50, -101));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected = IllegalArgumentException.class)
	public void falseRangeObjectTest() throws IllegalArgumentException {
		rangeObject = new Range(100,-100);
	}
	
	@Test
	public void TestShiftAllowZero(){
		
		double shiftValue = 80;
		rangeObject = new Range(-100, -20);
		
		Range rangeObjectShifted = Range.shift(rangeObject, shiftValue, true);
		
		Range trueRangeObject = new Range(-20, 60);
		
		assertEquals(true, rangeObjectShifted.equals(trueRangeObject));
	}


}
