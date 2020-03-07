/**
 * @author Khachatryan Haroutyun
 *
 * Task 1:
 * Задан массив целых чисел. Необходимо переставить наименьшее из этих чисел в начало массива, а наибольшее - в конец.
 */


public class InArrayMovements {

    public static void main(String[] args) {

        int[] array = {2, 7, 3, 4, 5, 1, 6};

        int max = array[0];
        int min = array[0];

        int tmp;

        int index_max = 0;  // Индекс максимального элемента
        int index_min = 0;  // Индекс минимального элемента


        System.out.print("\n\n");

        // Вывод исходного массива
        System.out.print("Исходный массив: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.print("\n\n");


        // Поиск максимального значения
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index_max = i;
            }
        }

        System.out.println("Максимальный элемент массива:   " + max);

        // Перестановка максимального элемента
        for (int i = index_max; i < array.length - 1; i++) {
            tmp = array[i + 1];
            array[i + 1] = array[i];
            array[i] = tmp;
        }

        // Поиск минимального значения
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index_min = i;
            }
        }

        System.out.println("Минимальный элемент массива:    " + min);

        // Перестановка минимального элемента
        for (int i = index_min; i > 0; i--) {
            tmp = array[i - 1];
            array[i - 1] = array[i];
            array[i] = tmp;
        }

        System.out.print("\n");


        // Вывод результата
        System.out.print("Результат: ");
        for (int element : array) {
            System.out.print(element + " ");
        }

        System.out.print("\n\n");
    }
}