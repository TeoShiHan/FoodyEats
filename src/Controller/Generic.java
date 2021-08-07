package Controller;
import Classes.*;  //invoke the all the class in the Classes package

public class Generic<T> {    
    // An object of type T is declared
    T obj;
    Generic(T obj) {  this.obj = obj;  }  // constructor
    public T getObject()  { return this.obj; }

}
