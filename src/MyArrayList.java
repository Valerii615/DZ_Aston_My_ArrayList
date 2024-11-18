import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Собственная реализация динамического списка ArrayList.
 * Реализованы методы согласно ТЗ: добавить элемент, добавить элемент по индексу, получить элемент, удалить элемент,
 * очистить всю коллекцию, отсортировать.
 *
 * @param <T> тип элементов в этом списке.
 */

public class MyArrayList<T> {
    /**
     * Константа для стандартного размера списка
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * Массив для хранения данных списка
     */
    private Object[] array;
    /**
     * Длинна списка
     */
    private int size;
    /**
     * Создание списка без длинны
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * Конструктор без параметров создает пустой список со стандартным размером
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструктор с параметром создает пустой список с указанным размером
     *
     * @param capacity параметр размена списка
     * @throws IllegalArgumentException в случае получения отрицательного параметра,
     *                                  * пробрасывается исключение
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
            this.size = 0;
        } else if (capacity == 0) {
            this.array = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /**
     * Метод добавление элемента в конец списка, в случае если список заполнен, происходит предварительно расширение
     * списка, в 2 раза, с копированием данных
     *
     * @param element элемент добавляемый в список
     */
    public void add(T element) {
        expandingArray();
        array[size++] = element;
    }

    /**
     * Метод добавление элемента по индексу, в случае если список заполнен, происходит предварительно расширение
     * списка, в 2 раза, с копированием данных, а затем происходит вставка элемента по индексу со смещением всех последующих
     * элементов
     *
     * @param index   индекс куда надо вставить элемент
     * @param element элемент необходимый вставить
     * @throws IndexOutOfBoundsException возникает если индекс выходит за пределы списка
     */
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        expandingArray();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Метод получение элемента по индексу
     *
     * @param index индекс по которому необходимо получить элемент
     * @throws IndexOutOfBoundsException возникает если индекс выходит за пределы списка
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    /**
     * Метод получения первого элемента списка
     *
     * @throws NoSuchElementException возникает в случае пустого списка
     */
    public Object getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return array[0];
        }
    }

    /**
     * Метод получения последнего элемента списка
     *
     * @throws NoSuchElementException возникает в случае пустого списка
     */
    public Object getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return array[size - 1];
        }
    }

    /**
     * Метод удаление элемента по индексу, со смещением последующих элементов
     *
     * @param index индекс по которому необходимо удалить элемент
     * @throws IndexOutOfBoundsException возникает если индекс выходит за пределы списка
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    /**
     * Метод очистки коллекции, отчищает коллекцию без изменения ее размера
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Метод для сортировки списка с компаратором (быстрая сортировка)
     */
    public void sort(Comparator<? super T> comparator) {
        quickSort(array, 0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод быстрой сортировки
     * @param array текущий список
     * @param low нижний индекс
     * @param high верхний индекс
     * @param comparator класс сравнения
     */
    private void quickSort(Object[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);

            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Разделение массива на две части и нахождение опорного элемента
     * @param array текущий список
     * @param low нижний индекс
     * @param high верхний индекс
     * @param comparator класс сравнения
     */
    private int partition(Object[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare((T) array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Метод для обмена элементов массива
     * @param array текущий список
     * @param i индекс, который меняем
     * @param j индекс, на который меняем
     */
    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Метод получения длинны списка
     */
    public int size() {
        return this.size;
    }

    /**
     * Метод проверки пустой ли список, возвращает true если список пуст
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void expandingArray() {
        if (this.size == this.array.length) {
            array = Arrays.copyOf(this.array, this.array.length * 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(array[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
