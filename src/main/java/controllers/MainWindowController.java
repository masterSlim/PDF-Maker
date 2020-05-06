package controllers;

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Checker;
import logic.IncomingInfo;
import logic.MyPDFWriter;

import java.io.FileNotFoundException;

public class MainWindowController {
    MyPDFWriter myWriter;

    @FXML
    TextField phoneField;
    @FXML
    TextField emailField;
    @FXML
    TextField nameField;
    @FXML
    TextField surnameField;
    @FXML
    TextField positionField;
    @FXML
    TextField skypeField;
    @FXML
    TextField icqField;
    @FXML
    Label finalButton;
    private String name;
    private String surname;
    private String position;
    private int[] phone;
    private String email;
    private String skype;
    private int[] icq;

    public MainWindowController() throws FileNotFoundException {
        //TODO заменить тестовый конструктор на реальный
        myWriter = new MyPDFWriter();
    }

    public void sendInfo() throws FileNotFoundException, DocumentException {
        //TODO не забыть добавить +7 к номеру телефона перед печатью в файл
        if (checkRequired()) {
            myWriter.write(IncomingInfo.getIncomingInfo(name, surname, position, phone, email, skype, icq));
        }
    }

    boolean checkName() {
        String name = nameField.getText();
        if (Checker.checkName(name)) {
            this.name = name;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkSurname() {
        String surname = surnameField.getText();
        if (Checker.checkSurname(surname)) {
            this.surname = surname;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkPosition() {
        String position = positionField.getText();
        if (Checker.checkPosition(position)) {
            this.position = position;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkPhone() {
        // телефон изначально записывается без +7 или 8, их добавляет метод sendInfo
        //перед передачей массива цифр на проверку методу checkPhone, идёт проверка являются ли введённые символы цифрами

        char[] phoneChars = phoneField.getText().toCharArray();
        int[] phone = new int[10];
        //проверка каждого символа цифра он или нет
        //TODO запретить вводить буквы
        if (phoneChars.length == 10) {
            for (int i = 0; i <= phoneChars.length; i++) {
                char ch = phoneChars[i];
                //TODO вывести на экран причину ошибки
                if (!Character.isDigit(ch)) return false;
                phone[i] = Character.getNumericValue(ch);
            }
        }
        //TODO вывести ошибку пользователю
        else return false;
        if (Checker.checkPhone(phone)) {
            this.phone = phone;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkEmail() {
        String email = emailField.getText();
        if (Checker.checkEmail(email)) {
            this.email = email;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkSkype() {
        String skype = skypeField.getText();
        if (Checker.checkSkype(skype)) {
            this.skype = skype;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    boolean checkIcq() {
        //размер номера аськи может быть разный, так что проверять количество цифр в нём не следует
        char[] icqChars = phoneField.getText().toCharArray();
        int[] icq = new int[icqChars.length];
        //проверка каждого символа цифра он или нет
        //TODO запретить вводить буквы
        for (int i = 0; i <= icqChars.length; i++) {
            char ch = icqChars[i];
            //TODO вывести на экран причину ошибки
            if (!Character.isDigit(ch)) return false;
            icq[i] = Character.getNumericValue(ch);
        }
        //TODO вывести на экран причину ошибки
        if (Checker.checkIcq(icq)) {
            this.icq = icq;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    private boolean checkRequired() {
        return checkName() && checkSurname() && checkPosition() && checkPhone() && checkEmail();
    }

    //TODO  разделить логику заполнено/не заполнено и правильно/неправильно
    private boolean checkExtra() {
        String skype = skypeField.getText().trim();
        boolean skypeBool;
        String icq = icqField.getText().trim();
        boolean icqBool;
        //не пустая ли строка Скайп
        if (skype.equals("")) {
            skypeBool = true;
        } else skypeBool = checkSkype();

        //не пустая ли строка icq
        if (icq.equals("")) {
            icqBool = true;
        } else icqBool = checkIcq();

        return skypeBool && icqBool;
    }


}
