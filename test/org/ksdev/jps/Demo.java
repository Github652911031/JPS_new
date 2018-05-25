package org.ksdev.jps;

import org.junit.Test;

public class Demo {
    @Test
    public void test1(){
        A b = new B();
        b.s();
    }
}

class A{
    public A(){

    }
    public void s(){
        System.out.println("调用A");
    }
}

class B extends A{
    public B(){
        super();
    }

    public void s(){
        System.out.println("调用B");
    }
}
