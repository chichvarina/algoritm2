package pro.sky.java.course2;


import java.util.NoSuchElementException;

public class StringListImplementation implements StringList {

    private String[] array;

    public StringListImplementation(int length) {
        array = new String[length];
    }

    // Добавление элемента.Вернуть добавленный элемент в качестве результата выполнения.
    @Override
    public String add(String item) {
        if(item==null){
            throw new NullPointerException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1){
            //массив заполнен - нужно расширить
            String [] newArray = new String[array.length+1];
            //копируем массив в новый массив
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            //записываем новый элемент в последнюю ячейку нового массива
            newArray[newArray.length-1]=item;
            //запоминаем новый массив
            array=newArray;
        }else{
            //массив не заполнен, заменяем первый нулевой элемент
            array[nullIndex]=item;
        }
        return item;
    }

    private int getNullIndex(){
        int index = -1;
        //ищем первый нулевой элемент
        for (int i = 0; i < array.length; i++) {
            if(array[i]==null){
                index = i;
                break;
            }
        }
        return index;
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    @Override
    public String add(int index, String item) {
        if(item==null){
            throw new NullPointerException();
        }
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        //расширяем массив
        String [] newArray = new String[array.length+1];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = item;
        for (int i = index; i < array.length; i++) {
            newArray[i+1] = array[i];
        }
        array=newArray;
        return item;
    }

    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение,если индекс большe фактического количества элементов или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        if(item==null){
            throw new NullPointerException();
        }
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
        return item;
    }

    // Удаление элемента. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        if(item==null){
            throw new NullPointerException();
        }
        int index = indexOf(item);
        if(index==-1){
            throw new NoSuchElementException();
        }
        //может быть несколько item в в массиве, нужно удалить все
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1) {
            //массив заполнен, нулевых элементов нет
            nullIndex = array.length;
        }

        String [] newArray = new String[array.length];
        int j=0;//индекс нового массива
        for (int i = 0; i < nullIndex; i++) {
            if(! array[i].equals(item)){ //совпадения не записываем в новый массив
                newArray[j]=array[i];
                j++;
            }
        }
        array=newArray;
        return item;
    }

    // Удаление элемента по индексу. Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }

        String deletedItem = array[index];

        String [] newArray = new String[array.length];
        int j=0;//индекс нового массива
        for (int i = 0; i < nullIndex; i++) {
            if(i!=index){
                newArray[j]=array[i];
                j++;
            }
        }
        array=newArray;
        return deletedItem;

    }

    // Проверка на существование элемента. Вернуть true/false;
    @Override
    public boolean contains(String item) {
        if(item==null){
            throw new NullPointerException();
        }
        return indexOf(item)>-1;
    }

    // Поиск элемента.Вернуть индекс элемента или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        if(item==null){
            throw new NullPointerException();
        }
        int index = -1;
        //ищем первый индекс элемента
        for (int i = 0; i < array.length; i++) {
            if(array[i] !=null && array[i].equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }

    // Поиск элемента с конца.Вернуть индекс элемента или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        if(item==null){
            throw new NullPointerException();
        }
        int index = -1;
        for (int i = array.length-1; i >=0 ; i--) {
            if(array[i] !=null && array[i].equals(item)){
                index=i;
                break;
            }
        }
        return index;
    }

    // Получить элемент по индексу. Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    @Override
    public String get(int index) {
        if(index >= array.length ){
            //index выходит за пределы размера массива
            throw new IndexOutOfBoundsException();
        }
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex > -1 && index >= nullIndex){
            //index выходит за пределы фактического количества элементов
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    // Сравнить текущий список с другим. Вернуть true/false или исключение, если передан null.
    @Override
    public boolean equals(StringList otherList) {
        if(otherList==null){
            throw new NullPointerException();
        }
        //не совпадает фактическое количество элементов
        if(this.size() != otherList.size()){
            return false;
        }
        boolean result = true;
        for (int i = 0; i < this.size(); i++) {
            if(! this.get(i).equals(otherList.get(i))){
                result=false;
                break;
            }
        }
        return result;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        //ищем первый нулевой элемент
        int nullIndex = getNullIndex();
        if(nullIndex == -1){
            return array.length;
        }else {
            return nullIndex;
        }
    }

    // Вернуть true,если элементов в списке нет,иначе false.
    @Override
    public boolean isEmpty() {
        return (array.length==0 || array[0]==null);
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i]=null;
        }
    }

    // Создать новый массив из строк в списке и вернуть его.
    @Override
    public String[] toArray() {
        String [] newArray = new String[this.size()];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i]=array[i];
        }
        return newArray;
    }

}

