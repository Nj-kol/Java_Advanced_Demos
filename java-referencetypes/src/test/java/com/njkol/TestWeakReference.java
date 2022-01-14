package com.njkol;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.njkol.references.weak.Person;

public class TestWeakReference {

	private ReferenceQueue<Object> savepointQ;

	@BeforeEach
	public void init() {
		savepointQ = new ReferenceQueue<Object>();// the ReferenceQueue
	}

	@Test
	public void testWeakReference() throws InterruptedException {

		/*
		 * 1. Creates a strong reference and adds it to a Weak reference
		 * savePointWRefernce. The object in memory is now referenced by a strong
		 * reference and a weak reference - hence strongly reachable.
		 */
		Person savePoint = new Person("Nilanjan"); // a strong object
		WeakReference<Person> savePointWRefernce = new WeakReference<Person>(savePoint, savepointQ);

		System.out.println("Person created as a weak ref " + savePointWRefernce);
		Runtime.getRuntime().gc();

		/*
		 * * 2. The first call to garbage collector will not clear our savepoint object
		 * as it is a strong reference. Hence the poll method of the referenceQ will
		 * return null. (poll method is non-blocking it checks and returns immediately.)
		 */
		System.out.println("Any weak references in Q ? " + (savepointQ.poll() != null));

		/*
		 * * 3. The savePoint reference variable is set to null. Our heap object is now
		 * referenced only by the weak reference - hence it is weakly reachable.
		 */
		savePoint = null;

		System.out.println("Now to call gc...");

		/*
		 * 4. The second gc call will now locate the object, executes its finalize
		 * method and mark this object to be freed. The object is also added to the
		 * ReferenceQ.
		 */
		Runtime.getRuntime().gc();

		System.out.println("Is the weak reference added to the ReferenceQ  ? " + (savePointWRefernce.isEnqueued()));
		/*
		 * 5. A call to the remove method of the ReferenceQ will return the object.remove is a blocking method. 
		 *    It will wait till an object has been made available in the Queue. 
		 *     (poll method might not work as the recycling process is happening on a separate thread.)
		 */
		System.out.println("Any weak references in Q ? " + (savepointQ.remove()!= null));

		System.out.println("Does the weak reference still hold the heap object ? " + (savePointWRefernce.get() != null));
	}
}
