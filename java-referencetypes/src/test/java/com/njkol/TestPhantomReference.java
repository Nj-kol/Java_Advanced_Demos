package com.njkol;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.njkol.references.phantom.LargeObjectFinalizer;

public class TestPhantomReference {

	@Test
	public void testPhantomReference() throws InterruptedException {
		
		/*
		 * First, we’re initializing all necessary objects:
		 * 
		 *  referenceQueue  – to keep track of enqueued references,
		 *  references      – to perform cleaning work afterward
		 *  largeObjects    – to imitate a large data structure.
		 */
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
		
		List<LargeObjectFinalizer> references = new ArrayList<LargeObjectFinalizer>();
		
		List<Object> largeObjects = new ArrayList<Object>();
		
		/*
		 * Next, we’re creating these objects using the Object 
		 * and LargeObjectFinalizer classes
		 */
		for (int i = 0; i < 10; ++i) {
		    Object largeObject = new Object();
		    largeObjects.add(largeObject);
		    references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
		}
		
		/*
		 * Before we call the Garbage Collector, we manually free up 
		 * a large piece of data by dereferencing the largeObjects list. 
		 * Note that we used a shortcut for the Runtime.getRuntime().gc() 
		 * statement to invoke the Garbage Collector.
		 */
		largeObjects = null;
		System.gc();
		 
		Reference<?> referenceFromQueue;
		
       /*
        * The for loop demonstrates how to make sure that all references 
        * are enqueued – it will print out true for each reference
        */
		for (PhantomReference<Object> reference : references) {
		    System.out.println(reference.isEnqueued());
		}
		 
        /*
         * Finally, we used a while loop to poll out the enqueued
         * references and do cleaning work for each of them.
         */
		while ((referenceFromQueue = referenceQueue.poll()) != null) {
		    ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
		    referenceFromQueue.clear();
		}
	}
}
