import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.StringListImplementation;

import java.util.NoSuchElementException;

public class StringListTest {
    StringListImplementation list;

    @BeforeEach
    public void init(){
        list = new StringListImplementation(5);
        list.add("Апельсин");
        list.add("Яблоко");
        list.add("Апельсин");
        list.add("Груша");
    }

    // Добавление элемента.Вернуть добавленный элемент в качестве результата выполнения
    @Test
    public void addTest1(){
        String result = list.add("Банан");
        String[] expectedArray = {"Апельсин", "Яблоко", "Апельсин", "Груша", "Банан"};
        Assertions.assertEquals("Банан", result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void addTest2(){
        //тест расширения массива
        list.add("Банан");
        list.add("Морковь");
        list.add("Тыква");
        list.add("Огурец");
        String[] expectedArray = {"Апельсин", "Яблоко", "Апельсин", "Груша", "Банан", "Морковь", "Тыква", "Огурец"};
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void addTest3(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.add(null)
        );
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    @Test
    public void addTest4(){
        String result = list.add(1, "Банан");
        String[] expectedArray = {"Апельсин", "Банан", "Яблоко", "Апельсин", "Груша"};
        Assertions.assertEquals("Банан", result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void addTest5(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.add(1, null)
        );
    }
    @Test
    public void addTest6(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.add(4, "Банан")
        );
    }
    @Test
    public void addTest7(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.add(5, "Банан")
        );
    }


    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение,если индекс большe фактического количества элементов или выходит за пределы массива.
    @Test
    public void setTest1(){
        String result = list.set(1, "Банан");
        String[] expectedArray = {"Апельсин", "Банан", "Апельсин", "Груша"};
        Assertions.assertEquals("Банан", result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void setTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.set(1, null)
        );
    }
    @Test
    public void setTest3(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.set(4, "Банан")
        );
    }
    @Test
    public void setTest4(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.set(5, "Банан")
        );
    }


    // Удаление элемента. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Test
    public void removeTest1(){
        String result = list.remove("Яблоко");
        String[] expectedArray = {"Апельсин", "Апельсин", "Груша"};
        Assertions.assertEquals("Яблоко", result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void removeTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.remove(null)
        );
    }
    @Test
    public void removeTest3(){
        Assertions.assertThrows(
                NoSuchElementException.class,
                ()->list.remove("Банан")
        );
    }

    // Удаление элемента по индексу. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Test
    public void removeTest4(){
        String result = list.remove(1);
        String[] expectedArray = {"Апельсин", "Апельсин", "Груша"};
        Assertions.assertEquals("Яблоко", result);
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }
    @Test
    public void removeTest5(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.remove(4)
        );
    }
    @Test
    public void setTest6(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.remove(5)
        );
    }


    // Проверка на существование элемента. Вернуть true/false;
    @Test
    public void containsTest1(){
        Assertions.assertTrue(list.contains("Груша"));
        Assertions.assertFalse(list.contains("Банан"));
    }
    @Test
    public void containsTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.contains(null)
        );
    }

    // Поиск элемента.Вернуть индекс элемента или -1 в случае отсутствия.
    @Test
    public void indexOfTest1(){
        Assertions.assertEquals(3,list.indexOf("Груша"));
        Assertions.assertEquals(-1, list.indexOf("Банан"));
    }
    @Test
    public void indexOfTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.indexOf(null)
        );
    }


    // Поиск элемента с конца.Вернуть индекс элемента или -1 в случае отсутствия.
    @Test
    public void lastIndexOfTest1(){
        Assertions.assertEquals(2,list.lastIndexOf("Апельсин"));
        Assertions.assertEquals(-1, list.lastIndexOf("Банан"));
    }
    @Test
    public void lastIndexOfTest2(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.lastIndexOf(null)
        );
    }


    // Получить элемент по индексу. Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    @Test
    public void getTest1(){
        Assertions.assertEquals("Апельсин", list.get(2));
    }
    @Test
    public void getTest2(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.get(4)
        );
    }
    @Test
    public void getTest3(){
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                ()->list.get(5)
        );
    }


    // Сравнить текущий список с другим. Вернуть true/false или исключение, если передан null.
    @Test
    public void equalsTest1() {
        StringListImplementation otherList = new StringListImplementation(4);
        otherList.add("Апельсин");
        otherList.add("Яблоко");
        otherList.add("Апельсин");
        otherList.add("Груша");
        Assertions.assertTrue(list.equals(otherList));
    }
    @Test
    public void equalsTest2() {
        StringListImplementation otherList = new StringListImplementation(7);
        otherList.add("Апельсин");
        otherList.add("Яблоко");
        otherList.add("Апельсин");
        otherList.add("Груша");
        otherList.add("Банан");
        Assertions.assertFalse(list.equals(otherList));
    }
    @Test
    public void equalsTest3() {
        StringListImplementation otherList = new StringListImplementation(5);
        otherList.add("Апельсин");
        otherList.add("Тыква");
        otherList.add("Апельсин");
        otherList.add("Груша");
        Assertions.assertFalse(list.equals(otherList));
    }
    @Test
    public void  equalsTest4(){
        Assertions.assertThrows(
                NullPointerException.class,
                ()->list.equals(null)
        );
    }

    // Вернуть фактическое количество элементов.
    @Test
    public void sizeTest() {
        Assertions.assertEquals(4, list.size());
    }

    // Вернуть true,если элементов в списке нет,иначе false.
    @Test
    public void isEmptyTest() {
        StringListImplementation list1 = new StringListImplementation(0);
        StringListImplementation list2 = new StringListImplementation(5);
        StringListImplementation list3 = new StringListImplementation(3);
        list3.add("Банан");
        Assertions.assertTrue(list1.isEmpty());
        Assertions.assertTrue(list2.isEmpty());
        Assertions.assertFalse(list3.isEmpty());
    }


    // Удалить все элементы из списка.
    @Test
    public void clearTest() {
        list.clear();
        Assertions.assertTrue(list.isEmpty());
    }

    // Создать новый массив из строк в списке и вернуть его.
    @Test
    public void toArrayTest() {
        String[] expectedArray = {"Апельсин", "Яблоко", "Апельсин", "Груша"};
        Assertions.assertArrayEquals(expectedArray, list.toArray());
    }

}
