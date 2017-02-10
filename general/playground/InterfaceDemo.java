interface baseInterface{
    void methodA();
    void methodB();
}


abstract class derivedA implements baseInterface {
    public abstract void methodA();
    public void methodB(){
    }
}

abstract class derivedB implements baseInterface {
    public abstract void methodB();
    public void methodA(){
    }
}



public class InterfaceDemo extends derivedA {
    public void methodA() {
        System.out.println("Implemented Methodd");
    }
}

