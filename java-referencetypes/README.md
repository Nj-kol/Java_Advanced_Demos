# Reference in Java

Java objects in the memory are classified as:

1. Strong References
2. Soft References
3. Weak References
4. Phantom References

* The difference between the types of references is that the objects on the heap they refer to are eligible for garbage collecting under the different criteria.
  
* All references are present in `java.lang.ref` package
* Present since 1.2
* A reference object encapsulates a reference to some  other object so that the reference itself may be examined and manipulated like any other object
* Three types of reference objects are provided,   each weaker than the last: 
1. Soft
2. Weak
3.Phantom

* Each reference-object type is implemented by a subclass of the abstract base `Reference` class
* An instance of one of these subclasses encapsulates  a single reference to a particular object, called  the ***referent***
* Every reference object provides methods for getting  and clearing the reference
* Aside from the clearing operation reference objects   are otherwise immutable, so no set operation is provided
* A program may further subclass these subclasses, adding   whatever fields and methods are required for its purposes,  or it may use these subclasses without change.

# Notes

* A weak reference is unlikely to survive GC 
* In comparison to weak references, soft references can have longer lifetimes since they continue to exist until extra memory is required. Therefore, they're a better choice if we need to hold objects in memory as long as possible

References
===========
http://learningviacode.blogspot.com/2014/02/weak-references-in-java.html