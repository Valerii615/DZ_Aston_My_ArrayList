public class Main {
    public static void main(String[] args) {
        AnimalNameComparator animalNameComparator = new AnimalNameComparator();

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(new Animal("animal3"));
        animals.add(new Animal("animal2"));
        animals.add(new Animal("animal1"));

        System.out.println(animals);

        animals.sort(animalNameComparator);

        System.out.println(animals);
    }
}
