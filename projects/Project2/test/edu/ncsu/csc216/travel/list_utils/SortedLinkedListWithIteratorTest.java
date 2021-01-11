package edu.ncsu.csc216.travel.list_utils;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests SortedLinkedListWithIterator.
 * 
 * @author Selena Chen
 * @author Alex Snezhko
 */
public class SortedLinkedListWithIteratorTest {

	/**
	 * Tests the SortedLinkedListWithIterator constructor.
	 */
	@Test
	public void testSortedLinkedListWithIterator() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertNotNull(list);
	}

	/**
	 * Tests size().
	 */
	@Test
	public void testSize() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertEquals(0, list.size());
		list.add("alpha");
		assertEquals(1, list.size());
	}

	/**
	 * Tests isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertTrue(list.isEmpty());
		list.add("a");
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains().
	 */
	@Test
	public void testContains() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
		assertTrue(list.contains("c"));
		assertTrue(list.contains("d"));
		assertFalse(list.contains("e"));
	}

	/**
	 * Tests add().
	 */
	@Test
	public void testAdd() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		assertEquals(4, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		assertEquals("d", list.get(3));
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		try {
			list.add("a");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * Tests get().
	 */
	@Test
	public void testGet() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("c", list.get(2));
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		try {
			list.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
	}

	/**
	 * Tests remove().
	 */
	@Test
	public void testRemove() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		assertEquals("d", list.remove(3));
		assertEquals("e", list.remove(3));
		assertEquals("a", list.remove(0));
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
		try {
			list.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * Tests indexOf().
	 */
	@Test
	public void testIndexOf() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertEquals(0, list.indexOf("a"));
		assertEquals(1, list.indexOf("b"));
		assertEquals(2, list.indexOf("c"));
		assertEquals(3, list.indexOf("d"));
		assertEquals(-1, list.indexOf("e"));
	}

	/**
	 * Tests iterator().
	 */
	@Test
	public void testIterator() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		SimpleListIterator<String> iterator = list.iterator();
		assertFalse(iterator.hasNext());
		try {
			iterator.next();
			fail();
		} catch (NoSuchElementException e) {
			assertFalse(iterator.hasNext());
		}

		list.add("a");
		list.add("b");
		list.add("c");

		iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("a", iterator.next());
		assertEquals("b", iterator.next());
		assertEquals("c", iterator.next());
		try {
			iterator.next();
			fail();
		} catch (NoSuchElementException e) {
			assertFalse(iterator.hasNext());
		}
	}

	/**
	 * Tests toString().
	 */
	@Test
	public void testToString() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertEquals("[]", list.toString());
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertEquals("[a, b, c, d]", list.toString());
	}
}