package com.njkol.references.phantom;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import com.njkol.references.weak.Person;

/*
 * we need a subclass of the PhantomReference class to 
 * define a method for clearing resources
 * 
 */
public class PersonFinalizer extends PhantomReference<Person> {
	 
    public PersonFinalizer(Person referent, ReferenceQueue<? super Person> q) {
        super(referent, q);
 
    }
 
    public void finalizeResources() {
        System.out.println("clearing ... ");
    }
}