package com.njkol;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.njkol.references.phantom.PersonFinalizer;
import com.njkol.references.weak.Person;

public class TestPhantomReference {

	private ReferenceQueue<Person> referenceQueue;
	private List<PersonFinalizer> personReferences;
	List<Person> persons;

	@BeforeEach
	public void init() {

		/*
		 * First, we’re initializing all necessary objects:
		 * 
		 * referenceQueue – to keep track of enqueued references, references – to
		 * perform cleaning work afterward largeObjects – to imitate a large data
		 * structure.
		 */
		referenceQueue = new ReferenceQueue<Person>();
		personReferences = new ArrayList<PersonFinalizer>();

		persons = new ArrayList<Person>();

		/*
		 * Next, we’re creating these objects using the Object and LargeObjectFinalizer
		 * classes
		 */
		for (int i = 0; i < 10; ++i) {
			Person aPerson = new Person("Person "+i);
			persons.add(aPerson);
			personReferences.add(new PersonFinalizer(aPerson, referenceQueue));
		}
	}

	@Test
	public void testPhantomReference() throws InterruptedException {

		/*
		 * Before we call the Garbage Collector, we manually free up a large piece of
		 * data by dereferencing the largeObjects list. Note that we used a shortcut for
		 * the Runtime.getRuntime().gc() statement to invoke the Garbage Collector.
		 */
		persons = null;
		System.gc();

		/*
		 * The for loop demonstrates how to make sure that all references are enqueued –
		 * it will print out true for each reference
		 */
		for (PhantomReference<Person> reference : personReferences) {
			System.out.println(reference.isEnqueued());
		}

		/*
		 * Finally, we used a while loop to poll out the enqueued references and do
		 * cleaning work for each of them.
		 */
		Reference<?> referenceFromQueue;

		while ((referenceFromQueue = referenceQueue.poll()) != null) {
			((PersonFinalizer) referenceFromQueue).finalizeResources();
			referenceFromQueue.clear();
		}
	}
}
