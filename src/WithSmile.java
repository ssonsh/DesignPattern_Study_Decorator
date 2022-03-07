public class WithSmile extends Deco{
    public WithSmile(Animal animal) {
        super(animal);
    }

    @Override
    void speak() {
        super.speak();
        System.out.print("😁");
    }
}
