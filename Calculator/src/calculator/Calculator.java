package calculator;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Calculator {
        static Scanner scanner = new Scanner(System.in);
        static int number1, number2;
        static char operation;
        static int result;

        public static void main(String[] args) {

                System.out.println("Введите выражение [2+2] или [V+V] + Enter ");
                String userInput = scanner.nextLine();
                char[] under_char = new char[10];
                for (int i = 0; i < userInput.length(); i++) {
                        under_char[i] = userInput.charAt(i);
                        if (under_char[i] == '+') {
                                operation = '+';
                        }
                        if (under_char[i] == '-') {
                                operation = '-';
                        }
                        if (under_char[i] == '*') {
                                operation = '*';
                        }
                        if (under_char[i] == '/') {
                                operation = '/';
                        }
                }
                String under_charString =
                        String.valueOf(under_char);

                String[] blacks = under_charString.split("[+-/*]");
                try {
                        blacks[1].getBytes(StandardCharsets.UTF_8);
                } catch (RuntimeException e) {
                        throw new ArrayIndexOutOfBoundsException("Такой операции не существует");
                }
                String st00 = blacks[0];
                String st01 = blacks[1];
                String st03 = st01.trim();
                number1 = romanToNumber(st00);
                number2 = romanToNumber(st03);


                if (blacks.length > 2) {
                        throw new ArrayIndexOutOfBoundsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор!");
                }
                blacks = Arrays.copyOf(blacks, 2);
                try {
                        st00 = blacks[0].trim();
                } catch (NullPointerException e) {
                        throw new NullPointerException();
                }

                if ((number1 != -1 && number2 == -1) || (number1 == -1 && number2 != -1)) {
                        try {
                                number1 = Integer.parseInt(st00);
                                number2 = Integer.parseInt(st03);
                        } catch (RuntimeException e) {
                                throw new RuntimeException("Используется одновременно разные системы счисления");
                        }
                }

                if (number1 == -1 && number2 == -1) {
                        try {
                                number1 = Integer.parseInt(st00);
                                number2 = Integer.parseInt(st03);
                        } catch (RuntimeException e) {
                                throw new IllegalArgumentException("Число больше 10");
                        }

                        if ((number1 == -1 || number2 == -1) ||
                                ((number1 != -1 && number2 == -1) || (number1 == -1 && number2 != -1))) {
                                throw new IllegalArgumentException("Число больше 10");
                        }


                        if (number1 > 10 || number2 > 10) {
                                throw new IllegalArgumentException("Число больше 10");

                        } else {
                                result = calculated(number1, number2, operation);
                                System.out.println("Результат для арабских цифр");
                                System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
                        }


                } else {
                        result = calculated(number1, number2, operation);
                        if ((number1 != -1 && number2 == -1) || (number1 == -1 && number2 != -1)) {
                                throw new RuntimeException("Используется одновременно разные системы счисления");
                        }

                        if (number1 == -1 || number2 == -1) {
                                throw new IllegalArgumentException("Введено число больше 10");
                        }
                        System.out.println("Результат для римских цифр");
                        if (result < 0) {
                                throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                        }
                        String resultRoman = convertNumToRoman(result);

                        System.out.println(st00 + " " + operation + " " + st03 + " = " + resultRoman);
                }
        }


        private static String convertNumToRoman(int numArabian) {
                String[] roman = {"O", "I", "II", "III", "IV",
                        "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                        "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                        "XXI", "XXII", "XXIII", "XXIV", "XXV",
                        "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII",
                        "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                        "XXXIX", "XL",
                        "XLI", "XLII", "XLIII", "XLIV", "XLV",
                        "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
                        "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                        "LXI", "LXII", "LXIII", "LXIV", "LXV",
                        "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                        "LXXI", "LXXII", "LXXIII", "LXXIV",
                        "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                        "LXXXI", "LXXXII", "LXXXIII", "LXXXIV",
                        "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                        "XCI", "XCII", "XCIII", "XCIV", "XCV",
                        "XCVI", "XCVII", "XCVIII", "XCIX", "C"
                };
                return roman[numArabian];
        }

        private static int romanToNumber(String roman) {
                try {
                        switch (roman) {
                                case "I":
                                        return 1;
                                case "II":
                                        return 2;
                                case "III":
                                        return 3;
                                case "IV":
                                        return 4;
                                case "V":
                                        return 5;
                                case "VI":
                                        return 6;
                                case "VII":
                                        return 7;
                                case "VIII":
                                        return 8;
                                case "IX":
                                        return 9;
                                case "X":
                                        return 10;

                        }
                } catch (RuntimeException e) {
                        throw new RuntimeException("Неверный формат данных");
                }

                return -1;
        }

        public static int calculated(int num1, int num2, char op) {
                int result = 0;
                switch (op) {
                        case '+':
                                result = num1 + num2;
                                break;
                        case '-':
                                result = num1 - num2;
                                break;
                        case '*':
                                result = num1 * num2;
                                break;
                        case '/':
                                try {
                                        result = num1 / num2;
                                } catch (ArithmeticException | InputMismatchException e) {
                                        System.out.println("Exception : " + e);
                                        System.out.println("Допускаются только целые числа");

                                        break;
                                }
                                break;
                        default:
                                throw new IllegalArgumentException("Не верный знак операции");
                }
                return result;
        }
}
