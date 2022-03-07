public class Deco extends Animal{
    private Animal animal;
    public Deco(Animal animal){
        this.animal = animal;
    }

    @Override
    void speak() {
        this.animal.speak();
    }
}
