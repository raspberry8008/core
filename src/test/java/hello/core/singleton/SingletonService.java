package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체 instance 생성해서 올려둔다.
    private static final SingletonService instance = new SingletonService();

    // 이 객체가 필요하면 getInstance() 메서드를 통해서만 조회할 수 있다.
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
