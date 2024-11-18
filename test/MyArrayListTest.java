import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<Integer> list;
    private MyArrayList<Animal> animalList;

    @BeforeEach
    void setUp() {
        // Инициализация нового пустого списка перед каждым тестом
        list = new MyArrayList<>();
    }

    @Test
    void testAddElement() {
        // Добавляем элемент и проверяем его наличие в списке
        list.add(10);
        assertEquals(1, list.size(), "Размер списка должен быть 1");
        assertEquals(10, list.get(0), "Элемент в списке должен быть 10");
    }

    @Test
    void testAddElementAtIndex() {
        // Добавляем элементы и вставляем элемент по индексу
        list.add(10);
        list.add(20);
        list.add(1, 15);

        assertEquals(3, list.size(), "Размер списка должен быть 3");
        assertEquals(10, list.get(0), "Первый элемент должен быть 10");
        assertEquals(15, list.get(1), "Второй элемент должен быть 15");
        assertEquals(20, list.get(2), "Третий элемент должен быть 20");
    }

    @Test
    void testAddElementAtInvalidIndex() {
        // Тестируем добавление по неверному индексу
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 20));
    }

    @Test
    void testGetFirstElement() {
        // Тестируем получение первого элемента
        list.add(10);
        list.add(20);
        assertEquals(10, list.getFirst(), "Первый элемент должен быть 10");
    }

    @Test
    void testGetFirstElementFromEmptyList() {
        // Проверяем исключение для получения первого элемента из пустого списка
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void testGetLastElement() {
        // Тестируем получение последнего элемента
        list.add(10);
        list.add(20);
        assertEquals(20, list.getLast(), "Последний элемент должен быть 20");
    }

    @Test
    void testGetLastElementFromEmptyList() {
        // Проверяем исключение для получения последнего элемента из пустого списка
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void testRemoveElement() {
        // Тестируем удаление элемента
        list.add(10);
        list.add(20);
        list.add(30);
        list.remove(1);

        assertEquals(2, list.size(), "Размер списка должен быть 2");
        assertEquals(10, list.get(0), "Первый элемент должен быть 10");
        assertEquals(30, list.get(1), "Второй элемент должен быть 30");
    }

    @Test
    void testRemoveElementFromEmptyList() {
        // Проверяем удаление элемента из пустого списка
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testClearList() {
        // Тестируем очистку списка
        list.add(10);
        list.add(20);
        list.clear();

        assertEquals(0, list.size(), "Размер списка после очистки должен быть 0");
        assertTrue(list.isEmpty(), "Список должен быть пустым после очистки");
    }

    @Test
    void testSortList() {
        // Тестируем сортировку списка
        list.add(30);
        list.add(10);
        list.add(20);
        list.sort((Integer::compareTo));

        assertEquals(10, list.get(0), "Первый элемент после сортировки должен быть 10");
        assertEquals(20, list.get(1), "Второй элемент после сортировки должен быть 20");
        assertEquals(30, list.get(2), "Третий элемент после сортировки должен быть 30");
    }

    @Test
    void testIsEmpty() {
        // Тестируем метод isEmpty
        assertTrue(list.isEmpty(), "Список должен быть пустым при создании");
        list.add(10);
        assertFalse(list.isEmpty(), "Список не должен быть пустым после добавления элемента");
    }

    @Test
    void testSize() {
        // Тестируем метод size
        assertEquals(0, list.size(), "Размер списка должен быть 0 при его создании");
        list.add(10);
        assertEquals(1, list.size(), "Размер списка должен быть 1 после добавления элемента");
    }

    @Test
    void testAddElementAndExpandCapacity() {
        // Тестируем добавление элементов, что приведет к расширению массива
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        assertEquals(20, list.size(), "Размер списка должен быть 20");
        assertEquals(19, list.get(19), "Последний добавленный элемент должен быть 19");
    }

    // Тест на сортировку животных по имени
    @Test
    public void testSortAnimalsByName() {
        animalList = new MyArrayList<>();
        animalList.add(new Animal("Lion"));
        animalList.add(new Animal("Zebra"));
        animalList.add(new Animal("Elephant"));
        animalList.add(new Animal("Cat"));

        // Сортируем животных по имени
        AnimalNameComparator comparator = new AnimalNameComparator();
        animalList.sort(comparator);

        // Проверяем порядок после сортировки
        assertEquals("Cat", animalList.get(0).getName(), "First animal should be Cat");
        assertEquals("Elephant", animalList.get(1).getName(), "Second animal should be Elephant");
        assertEquals("Lion", animalList.get(2).getName(), "Third animal should be Lion");
        assertEquals("Zebra", animalList.get(3).getName(), "Fourth animal should be Zebra");
    }

    // Тест на сортировку в обратном порядке (по убыванию имени)
    @Test
    public void testSortAnimalsByNameDescending() {
        animalList = new MyArrayList<>();
        animalList.add(new Animal("Lion"));
        animalList.add(new Animal("Zebra"));
        animalList.add(new Animal("Elephant"));
        animalList.add(new Animal("Cat"));

        // Сортируем животных по имени в обратном порядке
        animalList.sort((a1, a2) -> a2.getName().compareTo(a1.getName()));

        // Проверяем порядок после сортировки в обратном порядке
        assertEquals("Zebra", animalList.get(0).getName(), "First animal should be Zebra");
        assertEquals("Lion", animalList.get(1).getName(), "Second animal should be Lion");
        assertEquals("Elephant", animalList.get(2).getName(), "Third animal should be Elephant");
        assertEquals("Cat", animalList.get(3).getName(), "Fourth animal should be Cat");
    }

    // Тест на пустой список животных
    @Test
    public void testSortEmptyList() {
        animalList = new MyArrayList<>();
        animalList.sort(new AnimalNameComparator());  // Применяем сортировку к пустому списку
        assertTrue(animalList.isEmpty(), "List should remain empty after sorting");
    }

    // Тест на сортировку с одинаковыми именами
    @Test
    public void testSortAnimalsWithEqualNames() {
        animalList = new MyArrayList<>();
        animalList.add(new Animal("Lion"));
        animalList.add(new Animal("Lion"));
        animalList.add(new Animal("Lion"));

        // Сортируем животных с одинаковыми именами
        animalList.sort(new AnimalNameComparator());

        // Проверяем, что все элементы одинаковые и порядок не изменился
        assertEquals("Lion", animalList.get(0).getName(), "All animals should have the name Lion");
        assertEquals("Lion", animalList.get(1).getName(), "All animals should have the name Lion");
        assertEquals("Lion", animalList.get(2).getName(), "All animals should have the name Lion");
    }
}
