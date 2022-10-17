// Dmitry Borisov

import java.util.Scanner;


public class Calculator_KATA {

    public static void main(String[] args) throws myException {

        char myoperand = '*';
        String[] romenumbermain = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabicmain = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        Scanner input = new Scanner(System.in); // Создаем ввод
        System.out.println("Введите вычисляемое выражение вида a + b, a - b, a * b, a / b"); // Вводим предложение о введении выражения
        String input1 = input.nextLine(); // Читаем данные от пользователя
        char[] char1 = new char[input1.length()]; // создаем массив из данных от пользователя
        for (int i = 0; i < input1.length(); i++) { /////инициализация массива
            char1[i] = input1.charAt(i);
            if (char1[i] == '+') { // поиск знака операции
                myoperand = '+';
            }
            if (char1[i] == '-') {
                myoperand = '-';
            }
            if (char1[i] == '/') {
                myoperand = '/';
            }
            if (char1[i] == '*') {
                myoperand = '*';
            }
        }
        String char1String = String.valueOf(char1);
        String[] parts = char1String.split("[+-/*]");
        if (parts.length<2){
            throw new myException("Ошибка: Введенное значение не является магической операцией");
        }
        if (parts.length>2){
            throw new myException("Ошибка: Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String block3 = parts[0].trim();
        String block4 = parts[1].trim();

        RomeNumException ch_all1= new RomeNumException();
        boolean chall_2=ch_all1.checkall(block3, block4);
        if (!chall_2){
            throw new myException("ОШИБКА: Введены неподходящие значения операндов");
        }
        RomeNumException ch1 = new RomeNumException();
        boolean ch2 = ch1.checksystem (block3, block4);
        if (!ch2) {
            throw new myException("Цифры в разных системах счисления");
        }
        for (int i = 0; i < romenumbermain.length; i++) {
            if (romenumbermain[i].equals(block3)) {
                TransformRometoArab rome = new TransformRometoArab();
                int rome1 = rome.transform(block3);
                int rome2 = rome.transform(block4);
                Calculate resultrome = new Calculate();
                int rr = resultrome.calculate2(rome1, rome2, myoperand);
                TransformRometoArab romeres = new TransformRometoArab();
                romeres.back(rr);

            } else if (arabicmain[i].equals(block3)) {
                int num1 = Integer.parseInt(block3); // формирование цифры 1 арабская
                int num2 = Integer.parseInt(block4); // формирование цифры 2 арабская
                Calculate result = new Calculate();
                System.out.println(result.calculate2(num1, num2, myoperand));
            }
        }
    }
    static class Calculate  {
        int calculate2(int num1_calculate, int num2_calculate, char myoperand_calculate) throws myException {
            int result=0;
            switch (myoperand_calculate)
            {
                case '*':
                    result=num1_calculate * num2_calculate;
                break;
                case '+':
                    result=num1_calculate + num2_calculate;
                    break;
                case '-':
                    result=num1_calculate - num2_calculate;
                    break;
                case '/':
                    if (num2_calculate==0){
                        throw new myException("Ошибка: Деление на ноль невозможно");
                    }
                    result=num1_calculate / num2_calculate;
                    break;
                            }
            return (result);
        }
    }
    public static class TransformRometoArab {
        int yyyy;
        int transform(String rometr)  {
            String[] romenumber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            for (int i = 0; i < romenumber.length; i++) {
                if (romenumber[i].equals(rometr)) {
                    yyyy = i + 1;
                    break;
                }
            }
            return yyyy;
        }
        void back(int resutl) throws myException {
            int x;
            x = resutl;
            String[] allrome = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                    "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                    "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                    "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                    "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                    "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI",
                    "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
                    "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                    "XCVIII", "XCIX", "C"};
            if (x <= 0) {
                throw new myException("ОШИБКА: В римской системе нет отрицательных чисел и числа ноль");
            }
            System.out.println(allrome[x - 1]);
        }
    }
    public static class RomeNumException  { // сопоставление систем счисления
        boolean checksystem(String num1, String num2) {
            String [] romenumbermain = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String [] arabicmain = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            boolean num1IsRome= false, num2IsRome= false, num2arabic= false, num1arabic= false, isContains = false;
            for (String s : romenumbermain) {
                if (num1.equals(s)){
                    num1IsRome = true;
                }
                if(num2.equals(s)) {
                    num2IsRome = true;
                }
                            }
            for (String a : arabicmain) {
                if (num1.equals(a)) {
                    num1arabic = true;
                }
                if (num2.equals(a)) {
                    num2arabic = true;
                }
            }
           if (num1IsRome && num2IsRome || num1arabic && num2arabic) {
               isContains = true;
           }
            return (isContains);
           }
        boolean checkall (String num1, String num2){
            String [] all = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            boolean checking=false, checking1=false, checking2=false;
            for (String a: all) {
                if (num1.equals(a)){
                    checking1= true;
                }
                if (num2.equals(a)){
                    checking2= true;
                }
            }
            if (checking1 && checking2) {
                checking= true;
            }
        return (checking);}
    }
    public static class myException extends Exception {
        public myException(String desc){
            super(desc);
        }
    }
}  // финальное закрытие всей программы)