import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Cat());
        animals.add(new Dog());

        animals.forEach(a -> {a.speak(); System.out.println("");});

        ////////////////////////////////////////////////
        System.out.println("-- Decorator를 이용해 Smail, Heart를 붙여서 실행해보자! --");

        animals = new ArrayList<>();
        animals.add(new WithSmile(new Cat()));
        animals.add(new WithHeart(new Cat()));
        animals.add(new WithSmile(new WithHeart(new Dog())));

        animals.forEach(a -> {a.speak(); System.out.println("");});


    }
}
