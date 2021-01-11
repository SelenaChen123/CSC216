package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests SortedList.
 * 
 * @author Selena Chen
 * @author Travis Walter
 * @author Tyler Mathis
 */
public class SortedListTest {

	/**
	 * Tests SortedList(). Tests that the SortedList object is constructed correctly
	 * and grows correctly when adding 11 elements to an empty SortedList.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("durian");
		list.add("elderberry");
		list.add("fig");
		list.add("grape");
		list.add("honeydew melon");
		list.add("jackfruit");
		list.add("kiwi");
		list.add("lemon");

		assertEquals(11, list.size());
	}

	/**
	 * Tests add(). Tests that elements are added to the list correctly when adding
	 * elements to the front, middle, end, and adding null and duplicate elements.
	 * 
	 * @throws NullPointerException     if attemtping to add null element
	 * @throws IllegalArgumentException if inputs are invalid
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		list.add("apple");
		assertEquals(2, list.size());

		list.add("apricot");
		assertEquals(3, list.size());

		list.add("blueberry");
		assertEquals(4, list.size());

		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("apricot"));
		assertEquals(2, list.indexOf("banana"));
		assertEquals(3, list.indexOf("blueberry"));

		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}

		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}

	/**
	 * Tests get(). Tests getting an element from an empty list, adding elements to
	 * the list and getting their indexes, getting an element at an index less than
	 * 0, and getting an element at index size().
	 * 
	 * @throws IndexOutOfBoundsException if attempting to get an element not in the
	 *                                   list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

		try {
			list.get(list.indexOf("cantaloupe"));
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}

		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * Tests the removal of an index in the list by:
	 *
	 * Removing an element from an empty list. Adding at least four elements and
	 * removing an element at an index less than 0. Removing an element at index
	 * size(). Removing a middle element. Removing the last element. Removing the
	 * first element. Removing the last element.
	 * 
	 * @throws IndexOutOfBoundsException if attempting to get an element not in the
	 *                                   list
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException i) {
			assertEquals(list.isEmpty(), true);
		}

		// Add some elements to the list - at least 4
		list.add("Apple");
		list.add("Banana");
		list.add("Cantelope");
		list.add("Durian");

		// Test removing an element at an index < 0
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException i) {
			assertEquals(list.get(0), "Apple");
			assertEquals(list.get(1), "Banana");
			assertEquals(list.get(2), "Cantelope");
			assertEquals(list.get(3), "Durian");
			assertEquals(list.size(), 4);
		}

		// Test removing an element at size
		try {
			list.remove(list.size());
		} catch (IndexOutOfBoundsException i) {
			assertEquals(list.get(0), "Apple");
			assertEquals(list.get(1), "Banana");
			assertEquals(list.get(2), "Cantelope");
			assertEquals(list.get(3), "Durian");
			assertEquals(list.size(), 4);
		}

		// Test removing a middle element
		try {
			list.remove(2);
		} catch (IndexOutOfBoundsException i) {
			fail();
		}
		assertEquals(list.get(0), "Apple");
		assertEquals(list.get(1), "Banana");
		assertEquals(list.get(2), "Durian");
		assertEquals(list.size(), 3);

		// Test removing the last element
		try {
			list.remove(list.size() - 1);
		} catch (IndexOutOfBoundsException i) {
			fail();
		}
		assertEquals(list.get(0), "Apple");
		assertEquals(list.get(1), "Banana");
		assertEquals(list.size(), 2);

		// Test removing the first element
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException i) {
			fail();
		}
		assertEquals(list.get(0), "Banana");
		assertEquals(list.size(), 1);

		// Test removing the last element
		try {
			list.remove(list.size() - 1);
		} catch (IndexOutOfBoundsException i) {
			fail();
		}
		assertEquals(list.isEmpty(), true);
	}

	/**
	 * Test to see if indexOf is operating correctly by:
	 *
	 * Getting the index of an element in an empty list. Adding some elements and
	 * getting the index of elements in various positions in the list. Getting the
	 * index of elements not in the list. Getting the index of a null element.
	 * 
	 * @throws NullPointerException if attempting to get an element not in the list
	 *                              or a null element
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		try {
			list.indexOf("Apple");
		} catch (NullPointerException n) {
			fail();
		}
		assertEquals(list.indexOf("Apple"), -1);

		// Add some elements
		list.add("Apple");
		list.add("Banana");
		list.add("Cantelope");
		list.add("Durian");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		try {
			list.indexOf("Apple");
			list.indexOf("Banana");
			list.indexOf("Cantelope");
			list.indexOf("Durian");
			list.indexOf("fig");
		} catch (NullPointerException n) {
			fail();
		}
		assertEquals(list.indexOf("Apple"), 0);
		assertEquals(list.indexOf("Banana"), 1);
		assertEquals(list.indexOf("Cantelope"), 2);
		assertEquals(list.indexOf("Durian"), 3);
		assertEquals(list.indexOf("fig"), -1);

		// Test checking the index of null
		try {
			list.indexOf(null);
		} catch (NullPointerException n) {
			assertEquals(list.indexOf("Apple"), 0);
			assertEquals(list.indexOf("Banana"), 1);
			assertEquals(list.indexOf("Cantelope"), 2);
			assertEquals(list.indexOf("Durian"), 3);
		}

	}

	/**
	 * Test to see if list.clear() works together by:
	 *
	 * Checking a new list is empty. Adding an element. Checking that the list is
	 * now not empty.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("Apple");
		list.add("Banana");
		list.add("Cantelope");
		list.add("Durian");

		// Clear the list
		list.clear();

		// Test that the list is empty
		assertEquals(list.isEmpty(), true);
	}

	/**
	 * Testing the IsEmpty function by checking if the list initializes empty and
	 * checking if no longer empty.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Tests that the SortedList initializes empty
		assertTrue(list.isEmpty());

		// Adds new element to the list
		list.add("orange");

		// Tests that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Test the Contains method by checking an empty list and various true/false
	 * cases.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Tests empty list
		assertFalse(list.contains("orange"));

		// Add elements
		list.add("apple");
		list.add("banana");
		list.add("lemon");
		list.add("kiwi");

		// Tests various true/false cases
		assertTrue(list.contains("lemon"));
		assertTrue(list.contains("banana"));
		assertFalse(list.contains("elderberry"));
		assertFalse(list.contains("durian"));
	}

	/**
	 * Test the equals method by making two of the same list and one different list
	 * and using assertTrue and assertFalse to compare.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Adds elements to the three different lists
		list1.add("apple");
		list1.add("banana");
		list1.add("lemon");
		list1.add("kiwi");

		list2.add("apple");
		list2.add("banana");
		list2.add("lemon");
		list2.add("kiwi");

		list3.add("apple");
		list3.add("melon");
		list3.add("orange");
		list3.add("kiwi");

		// Tests for equality of two equal lists
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));

		// Tests equality of non-equal lists
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
	}

	/**
	 * Test the hashCode method by making two of the same list and one different
	 * list and using assertEquals and assertNotEquals to compare the hash codes.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Adds elements to the three different lists
		list1.add("apple");
		list1.add("banana");
		list1.add("lemon");
		list1.add("kiwi");

		list2.add("apple");
		list2.add("banana");
		list2.add("lemon");
		list2.add("kiwi");

		list3.add("apple");
		list3.add("melon");
		list3.add("orange");
		list3.add("kiwi");

		// Tests for equality of hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());

		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}
}