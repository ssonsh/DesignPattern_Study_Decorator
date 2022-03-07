public class WithHeart extends Deco{
    public WithHeart(Animal animal) {
        super(animal);
    }

    @Override
    void speak() {
        super.speak();
        System.out.print("😍");
    }
}
