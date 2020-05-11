package logic;

/**
 * Првоеряет различную поступающую ему информацию на соответствие требованиям
 */

public class Checker {
    //TODO добавить реальные проверки
    //TODO возвращать причину ошибки вместо boolean

    public static boolean checkName(String name) {
        return true;
    }

    public static boolean checkSurname(String surname) {
        return true;
    }

    public static boolean checkPosition(String position) {
        return true;
    }

    public static boolean checkPhone(String phoneString) {

        phoneString = phoneString.replace(" ", "");
        char[] phoneChars = phoneString.replace(" ", "").toCharArray();
        for (Character c : phoneString.toCharArray()) {
            if (!(c >= '0' && c <= '9')) {
                System.out.println("Phone has no digits");
                return false;
            }
        }

        //предпроверка количества цифр. Меньше 10 - возвращаем false
        byte[] phone;
        if (phoneChars.length == 10) {
            phone = new byte[10];
            for (int i = 0; i < phoneChars.length; i++) {
                char ch = phoneChars[i];
                phone[i] = (byte) Character.getNumericValue(ch);
            }
        } else {System.out.println("Phone have less than 10 characters"); return false;}

        //если номер начинается не с 9 то возвращаем false
        switch (phone[0]) {
            case (byte) 1:
            case (byte) 2:
            case (byte) 3:
            case (byte) 4:
            case (byte) 5:
            case (byte) 6:
            case (byte) 7:
            case (byte) 8:
                System.out.println("first character not equal 9");
                return false;
            default:
                return true;
        }
    }

    public static boolean checkEmail(String email) {
        //относится ли указанная почта к корпоративному домену
        int atIndex = email.indexOf("@");
        //если знак @ отсуствует (atIndex = -1) или стоит на первом месте в строке (atIndex = 0) возвращатеся false;
        if (atIndex < 1) return false;
        String domain = email.substring(atIndex + 1).toLowerCase();
        return domain.equals("del-ko.ru");
    }

    public static boolean checkSkype(String skype) {
        return true;
    }

    public static boolean checkIcq(String icqInput) {
        //размер номера аськи может варьироваться, так что проверять количество цифр в нём не следует
        icqInput = icqInput.replace(" ", "");
        for (Character c : icqInput.toCharArray()) {
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
}
