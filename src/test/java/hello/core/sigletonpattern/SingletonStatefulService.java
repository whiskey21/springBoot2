package hello.core.sigletonpattern;

public class SingletonStatefulService {

    //private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
        //this.price = price;
    }


}
