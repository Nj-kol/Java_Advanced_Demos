package com.njkol.references.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/*
 * we need a subclass of the PhantomReference class to 
 * define a method for clearing resources
 * 
 */
public class LargeObjectFinalizer extends PhantomReference<Object> {
	 
    public LargeObjectFinalizer(Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }
 
    public void finalizeResources() {
        // free resources
        System.out.println("clearing ...");
    }
}