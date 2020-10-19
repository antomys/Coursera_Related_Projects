/*public class Funcs extends java.lang.Math {
    public int add(int x, int y) {
        return x + y;
    }
    public int sub(int x, int y) {
        return x - y;
    }
    public static void main(String[] a) {
        Funcs f = new Funcs();
        System.out.println("" + f.add(1, 2));
    }
}*/
//Пояснение: Класс java.lang.Math объявлен как final, то есть наследоваться от него нельзя.

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

interface TheInterface {
    void print();
}

         class TheClass implements TheInterface {
    public void print() {
               System.out.println("TheClass");
            }
}

         class ClassConversion {
   public static void main(String[] args) {
               TheClass c = new TheClass();
       ((TheInterface) c).print();
            }
 }

 ///Пояснение: Если при добавлении пары ключ-значение в Map оказывается, что там уже содержится такой ключ, ему в соответствие ставится новое значение.
//В классе Employee метод equals() реализован таким образом,
// что равными считаются два объекта, содержащие одну и ту же ссылку на строку
// name (this.name == emp.name). Поэтому из двух объектов-ключей,
// содержащих ссылку на строку-константу "Chandler", в Map'е остаётся только один.
// Поле count используется для подсчёта количества вызовов метода Employee.equals().
// При работе с HashMap метод equals() вызывается относительно редко, например, когда
// хэш-код вновь добавляемого ключа совпадает с хэш-кодом ранее добавленного ключа -
// чтобы достоверно убедиться, что это одинаковые объекты (равенство хэш-кодов ещё не гарантирует равенство объектов). В
// данном примере такое происходит ровно один раз, при повторном добавлении Chandler'а.

class Test1 {
    public static void main(String[] args) {
        String s1 = new String("string");
        String s2 = new String("STRINg");

        StringBuilder sb1 = new StringBuilder("test");
        StringBuilder sb2 = new StringBuilder("test");

        System.out.println(s1.equalsIgnoreCase(s2) && sb1.equals(sb2)
                && s2.charAt(s2.length()) == 'g');
    }
}
///Пояснение: Метод equalsIgnoreCase() вернёт true, так как при таком сравнении регистр не учитывается.
//Метод equals() в StringBuilder и StringBuffer не переопределён,
// поэтому не позволяет осуществлять сравнение хранящихся в них строк, а сравнивает
// только лишь адреса объектов, т.е. для разных объектов всегда возвращает false.
//Благодаря && дальнейшая проверка не выполняется, что позволяет избежать StringIndexOutOfBoundsException,
// так как последним доступным элементом в строке может быть [s2.length()-1].

class Test2 {
    static String s;

    static void go() {
        System.out.println(s);
        go();
    }

    public static void main(String[] args) {
        go();
    }
}
///Пояснение: При бесконечной рекурсии рано или поздно стек вызова методов переполнится и будет сгенерировано соответствующее исключение.

interface I_A { public void out1(); }
interface I_B { public void out2(); }

class Impl implements I_A, I_B {     //1
    public void out2() {
        System.out.print("2");
    }
    public void out1() {
        System.out.print("1");
    }
}

class Test3 {
    public static void main(String[] args) {
        Impl impl = new Impl();
        I_A a;
        a = impl;
        a.out1();
        ((I_B) a).out2();              //2
    }
}

///Пояснение: В Java возможна реализация нескольких интерфейсов.
// Данное приведение типов возможно, так как класс impl реализует оба интерфейса.

/*class Main {
    public static void main(String[] args) {
        int[] array = {1, 2};
        int x;
        for (x : array) {
            System.out.println(x);
        }
    }
}*/
///Пояснение: При использовании for-each переменная,
// использующаяся для хранения значения из массива или коллекции, должна быть объявлена внутри цикла, иначе получим ошибку компиляции.

class Test4 {
    public static boolean methodOne() {
        System.out.println("methodOne ");
        return false;
    }

    public static boolean methodTwo() {
        System.out.println("methodTwo ");
        return true;
    }

    public static boolean methodThree() {
        System.out.println("methodThree ");
        return true;
    }

    public static void main(String[] args){
        System.out.println(Test4.methodOne() || Test4.methodTwo() || Test4.methodThree());
    }
}
///Пояснение: Феномен "ускоренного вычисления" при работе с логическими выражениями.
// methodThree не будет вызван, т.к. результат уже не зависит от него.
//Строки "methodOne" и "methodTwo" выводятся при вычислении значения выражения,
// затем выводится само значение выражения - true.

///WeakHashMap, фактически, хранит не пары "ключ - значение", а пары "слабая ссылка на ключ - значение".
// Особенность слабых ссылок (WeakReference) состоит в том, что они игнорируются сборщиком мусора,
// т.е. если на объект-ключ нет других ссылок, он уничтожается.
//
//Перед любым обращением к WeakHashMap (get(), put(), size() и т.д.) анализируются невалидные ссылки и соответствующая пара удаляется.



/*public class Main1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("outstream");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            try {
                do {
                    String str = br.readLine();
                    System.out.println(str);
                } while (str != null);
            }
            finally {
                fis.close();
                isr.close();
                br.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}*/

///Пояснение: Ошибка компиляции произойдёт из-за того, что переменная str объявлена внутри цикла do.. while().
//Объявлять переменные в цикле, конечно же, разрешается.
// Только и использоваться они должны внутри тела цикла.
// А в этом примере переменная str используется за пределами блока, в котором она была объявлена - в условии продолжения цикла.


class Test7 {
    static Boolean bo1 = new Boolean("true");
    static Boolean bo2 = new Boolean(false);

    public static void main(String[] args) {
        Boolean bo3 = new Boolean(bo1);
        Boolean bo4 = new Boolean("bo2");
        System.out.println(bo1.equals(bo3));
        System.out.println(bo2.equals(bo4));
    }
}
///Пояснение: Конструктор Boolean(String) создаёт объект со значением true,
// если в него передана строка "true" (в любом регистре). В противном случае будет создан объект со значением false.
//Поэтому объекты будут иметь следующие значения:
//bo1 – true
//bo2 – false
//bo3 – true
//bo4 – false
//При сравнении объектов с одинаковым значением (bo1 и bo3, bo2 и bo4) метод equals() возвращает значение true.

class Autoboxing {
    public static void main(String[] args) {
        Integer oInt1 = null;
        Integer oInt2 = 0;
        final int int1 = oInt1;
        final int int2 = oInt2;
        System.out.println(int1 == int2);
    }
}
///Пояснение: Возникнет java.lang.NullPointerException в 5-й строке, поскольку компилятор преобразует выражение
//
//final int int1 = oInt1;
//в
//final int int1 = oInt1.intValue();

class App1 {
    public static void main(String[] args) {
        System.out.println(addToString("12345678910",','));
    }

    public static StringBuffer addToString(String s, char c) {
        StringBuffer b = new StringBuffer(s);
        int p = 0;
        for (int i = 1; i < b.length(); i++) {
            if (i%3 == 0) {
                b.insert(b.length()-i-p, c);
                p++;
            }
        }
        return b;
    }
}
///Пояснение: Следует учитывать, что строка динамически изменяется:
//при i = 3 символ вставляется в 8-ю позицию; получаем b = "12345678,910", b.length() = 12
//при i = 6 символ вставляется в 5-ю позицию; получаем b = "12345,678,910", b.length() = 13
//при i = 9 символ вставляется во 2-ю позицию; получаем b = "12,345,678,910", b.length() = 14
//при i = 12 цикл продолжает выполняться (ведь 12 < 14), происходит попытка вставить символ в -1-ю позицию - и тут выбрасывается exception