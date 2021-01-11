package edu.ncsu.csc216.travel.list_utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests SimpleArrayList.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class SimpleArrayListTest {

	/**
	 * Tests the SimpleArrayList constructor.
	 */
	@Test
	public void testSimpleArrayList() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertNotNull(list);
	}

	/**
	 * Tests the SimpleArrayList constructor with a given size.
	 */
	@Test
	public void testSimpleArrayListInt() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(10);
		assertNotNull(list);
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(10);
		assertEquals(0, list.size());
		list.add("apple");
		assertEquals(1, list.size());
		list.add("banana");
		assertEquals(2, list.size());
	}

	/**
	 * Tests isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(10);
		assertTrue(list.isEmpty());
		list.add("apple");
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains().
	 */
	@Test
	public void testContains() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(10);
		assertFalse(list.contains("apple"));
		list.add("apple");
		assertTrue(list.contains("apple"));
	}

	/**
	 * Tests add() with a given element.
	 */
	@Test
	public void testAddE() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(5);
		list.add("alpha");
		list.add("beta");
		assertEquals(2, list.size());
		try {
			list.add("alpha");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, list.size());
		}
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
		assertEquals(2, list.size());
		list.add("gamma");
		list.add("delta");
		list.add("epsilon");
		assertTrue(list.add("zeta"));
		assertEquals(6, list.size());

	}

	/**
	 * Tests add() with a given index and element.
	 */
	@Test
	public void testAddIntE() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(5);
		list.add(0, "alpha");
		try {
			list.add(-1, "beta");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		try {
			list.add(2, "beta");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		try {
			list.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, list.size());
		}
		try {
			list.add(0, "alpha");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, list.size());
		}
		list.add(1, "beta");
		assertEquals(2, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("beta", list.get(1));

		list.add(1, "gamma");
		assertEquals(3, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("gamma", list.get(1));
		assertEquals("beta", list.get(2));

		list.add(0, "delta");
		assertEquals(4, list.size());
		assertEquals("delta", list.get(0));
		assertEquals("alpha", list.get(1));
		assertEquals("gamma", list.get(2));
		assertEquals("beta", list.get(3));
	}

	/**
	 * Tests remove().
	 */
	@Test
	public void testRemove() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(5);
		list.add("alpha");
		list.add("beta");
		list.add("gamma");
		list.add("delta");
		assertEquals(4, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("beta", list.get(1));
		assertEquals("gamma", list.get(2));
		assertEquals("delta", list.get(3));

		assertEquals("beta", list.remove(1));
		assertEquals("delta", list.remove(2));
		assertEquals("alpha", list.remove(0));
		assertEquals(1, list.size());
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		try {
			list.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}

	}

	/**
	 * Tests get().
	 */
	@Test
	public void testGet() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(5);
		list.add("alpha");
		list.add("beta");
		list.add("gamma");
		list.add("delta");
		assertEquals(4, list.size());
		assertEquals("alpha", list.get(0));
		assertEquals("beta", list.get(1));
		assertEquals("gamma", list.get(2));
		assertEquals("delta", list.get(3));
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		try {
			list.get(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * Tests indexOf().
	 */
	@Test
	public void testIndexOf() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(5);
		assertEquals(-1, list.indexOf("alpha"));
		list.add("alpha");
		list.add("beta");
		list.add("gamma");
		list.add("delta");
		assertEquals(4, list.size());
		assertEquals(0, list.indexOf("alpha"));
		assertEquals(1, list.indexOf("beta"));
		assertEquals(2, list.indexOf("gamma"));
		assertEquals(3, list.indexOf("delta"));
	}
}