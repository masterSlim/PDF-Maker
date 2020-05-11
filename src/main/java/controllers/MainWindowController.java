package controllers;

import com.itextpdf.text.DocumentException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Checker;
import logic.Information;
import logic.PDFWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainWindowController {
    PDFWriter generator;

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
    Button finalButton;
    private String name;
    private String surname;
    private String position;
    private String phone;
    private String email;
    private String skype;
    private String icq;


    /**
     * Контроллер главного экрана. Пр нажатии пользователя на finalButton проверяются все заполняемые поля на наличие
     * и правильность ввода. Если всё введено правильно, то создаётся объект класса MyPDFWriter и данные в виде объекта
     * IncomingInfo передаются его методу write
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */


    public void writeInfo() throws IOException {
        //TODO не забыть добавить +7 к номеру телефона перед печатью в файл
        if (checkRequired()) {
            if (checkExtra()) {
                System.out.println("do write");
                //TODO заменить тестовый конструктор на реальный
                generator = new PDFWriter();
                generator.write(Information.getIncomingInfo(name, surname, position, phone, email, skype, icq));
            }
        }
    }

    /**
     * Проверка введённого имени на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkName() {
        String name = nameField.getText();
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
        String surname = surnameField.getText();
        if (Checker.checkSurname(surname)) {
            this.surname = surname;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    @FXML
    public void phoneInput() {
        numInput(phoneField);
    }

    @FXML
    public void icqInput() {
        numInput(icqField);
    }

    private void numInput(TextField numField) {
        //запрет ввода чего-либо, кроме цифр
        numField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    /**
     * Проверка введённой должности на правильность ввода и соотвествие требованиям
     *
     * @return true если проверка успешна и false если проверка провалилась
     */

    boolean checkPosition() {
        String position = positionField.getText();
        if (Checker.checkPosition(position)) {
            this.position = position;
            return true;
        }
        //TODO вывести ошибку пользователю
        else return false;
    }

    @FXML
    public boolean checkPhone() {
        String phone = phoneField.getText();
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
        String email = emailField.getText();
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
        String skype = skypeField.getText();
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
        String icq = icqField.getText();
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
            this.skype = null;
        } else skypeBool = checkSkype();

        //не пустая ли строка icq
        if (icq.equals("")) {
            icqBool = true;
            this.icq = null;
        } else icqBool = checkIcq();

        return skypeBool && icqBool;
    }


}
