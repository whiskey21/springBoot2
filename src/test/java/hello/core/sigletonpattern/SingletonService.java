package hello.core.sigletonpattern;

public class SingletonService {

    //final선언으로 객체는 단 한번밖에 생성 못함, static변수로 속성값과 관계없이 참조변수로써 이용가능
    private static final SingletonService instance = new SingletonService();

    //private생성자로 외부에서 객체생성 방지
    private SingletonService(){ };

    //단일객체의 속성 생성 방지를 위한 정적메서드 선언
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic(){
        System.out.println("객체의 진짜 로직은 여기에!");
    }

}
