package controllers;

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Checker;
import logic.IncomingInfo;
import logic.MyPDFWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    private byte[] phone;
    private String email;
    private String skype;
    private byte[] icq;

    /**
     * Проверяются все заполняемые поля на наличие и правильность ввода. Если всё введено правильно, то создаётся объект
     * класса MyPDFWriter и данные в виде объекта IncomingInfo передаются его методу write
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void sendInfo() throws IOException {
        //TODO не забыть добавить +7 к номеру телефона перед печатью в файл
        if (checkRequired()) {
            if(checkExtra()) {
                System.out.println("do write");
                //TODO заменить тестовый конструктор на реальный
                myWriter = new MyPDFWriter();
                myWriter.write(IncomingInfo.getIncomingInfo(name, surname, position, phone, email, skype, icq));
            }
            }
    }

    /**
     * Проверка введённого имени на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkName() {
        String name = nameField.getText().replace(" ", "");
        if (Checker.checkName(name)) {
            this.name = name;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённой фамилии на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkSurname() {
        String surname = surnameField.getText().replace(" ", "");
        if (Checker.checkSurname(surname)) {
            this.surname = surname;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённой должности на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkPosition() {
        String position = positionField.getText().replace(" ", "");
        if (Checker.checkPosition(position)) {
            this.position = position;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённого номера телефона на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkPhone() {
        // телефон изначально записывается без +7 или 8, их добавляет метод sendInfo

        char[] phoneChars = phoneField.getText().replace(" ", "").toCharArray();
        byte[] phone = new byte[10];

        //предпроверка ввода: цифры это или нет и в каком количестве

        //TODO запретить вводить буквы  убрать эту проверку

        if (phoneChars.length == 10) {
            for (int i = 0; i < phoneChars.length; i++) {
                char ch = phoneChars[i];
                //TODO вывести на экран причину ошибки
                if (!Character.isDigit(ch)) {
                    System.out.println("not digit in Phone");
                    return false;
                }
                phone[i] = (byte) Character.getNumericValue(ch);
            }
        }
        //TODO вывести ошибку пользователю
        else return false;

        //постпроверка в Checker

        if (Checker.checkPhone(phone)) {
            this.phone = phone;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённой почты на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkEmail() {
        String email = emailField.getText().replace(" ", "");
        if (Checker.checkEmail(email)) {
            this.email = email;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённого скайпа на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkSkype() {
        String skype = skypeField.getText().replace(" ", "");
        if (Checker.checkSkype(skype)) {
            this.skype = skype;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка введённого номера ICQ на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkIcq() {
        //размер номера аськи может быть разный, так что проверять количество цифр в нём не следует
        char[] icqChars = icqField.getText().replace(" ", "").toCharArray();
        byte[] icq = new byte[icqChars.length];
        //проверка каждого символа цифра он или нет
        //TODO запретить вводить буквы
        for (int i = 0; i < icqChars.length; i++) {
            char ch = icqChars[i];
            //TODO вывести на экран причину ошибки
            if (!Character.isDigit(ch)) return false;
            icq[i] = (byte) Character.getNumericValue(ch);
        }
        //TODO вывести на экран причину ошибки
        if (Checker.checkIcq(icq)) {
            this.icq = icq;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    /**
     * Проверка правильности заполенения обязательных полей, без которых создание документа невозможно
     *
     * @return true если все проверки прошли успешно (вернули true) и false если одна или более проверок провалились
     */

    private boolean checkRequired() {
        System.out.println("checkName: " + checkName() + "\n checkSurname: " + checkSurname() + "\n checkPosition: " + checkPosition() + "\n checkPhone: " + checkPhone() + "\n checkEmail: " + checkEmail());
        return checkName() && checkSurname() && checkPosition() && checkPhone() && checkEmail();
    }

    /**
     * Проверка правильности заполенения необязательных полей, без которых создание документа возможно
     *
     * @return true если все проверки прошли успешно (вернули true) и false если одна или более проверок провалились
     */

    //TODO  разделить логику заполнено/не заполнено и правильно/неправильно
    private boolean checkExtra() {
        String skype = skypeField.getText().replace(" ", "");
        boolean skypeBool;
        String icq = icqField.getText().replace(" ", "");
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
