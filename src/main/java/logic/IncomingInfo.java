package logic;

public class IncomingInfo {
    private String name;
    private String surname;
    private String position;
    private byte[] phone;
    private String email;
    private String skype;
    private byte[] icq;

    /**
     * Формирует по заданным значениям и возвращает объект IncomingInfo
     *
     * @return String
     */

    public static IncomingInfo getIncomingInfo(String name, String surname, String position, byte[] phone, String email, String skype, byte[] icq) {
        IncomingInfo info = new IncomingInfo();
        info.name = name;
        info.surname = surname;
        info.position = position;
        info.phone = phone;
        info.email = email;
        info.skype = skype;
        info.icq = icq;
        return info;
    }

    /**
     * Форматирует и возвращает имя с заглавной буквы в виде строки
     *
     * @return String
     */

    public String getName() {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    /**
     * Форматирует и возвращает фамилию с заглавной буквы в виде строки
     *
     * @return String
     */

    public String getSurname() {
        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        return surname;
    }

    /**
     * Форматирует и возвращает должность с заглавной буквы в виде строки
     *
     * @return String
     */
    public String getPosition() {
        position = position.substring(0, 1).toUpperCase() + position.substring(1);
        return position;
    }

    /**
     * Форматирует и возвращает номер телефона в виде массива byte
     *
     * @return byte[]
     */

    public byte[] getPhoneByte() {
        return phone;
    }

    /**
     * Форматирует и возвращает номер телефона в виде строки +7 987 654 32 10
     *
     * @return String
     */

    public String getPhone() {
        StringBuilder phoneString = new StringBuilder("+7 ");

        //форматирование номера телефона, добавление пробелов между группами цифр
        for (int i = 0; i < this.phone.length; i++) {
            phoneString.append(this.phone[i]);
            switch (i) {
                case 2:
                case 5:
                case 7:
                case 9:
                    phoneString.append(" ");
                    break;
            }
        }
        return phoneString.toString();
    }

    /**
     * Форматирует и возвращает почту в виде строки
     *
     * @return String
     */

    public String getEmail() {
        return email;
    }

    /**
     * Форматирует и возвращает Skype в виде строки или null объекта
     *
     * @return String
     */

    public String getSkype() {
        if (skype == null) return null;
        return skype;
    }

    /**
     * Форматирует и возвращает номер ICQ в виде массива byte[]
     *
     * @return String
     */

    public byte[] getIcqByte() {
        return icq;
    }

    /**
     * Форматирует и возвращает номер ICQ в виде строки или null объекта
     *
     * @return String
     */

    public String getIcq() {

        //если поле ICQ не заполнено ничего не обрабатываем
        if (icq == null) return null;


        StringBuilder icqString = new StringBuilder();

        //форматирование номера icq, добавление пробелов между группами цифр
        for (int i = 0; i < this.icq.length; i++) {
            icqString.append(this.icq[i]);
            if ((this.icq.length % 3 == 0) && ((i + 1) % 3 == 0)) icqString.append(" ");
            if ((this.icq.length % 3 != 0) && ((i + 1) % 4 == 0)) icqString.append(" ");
        }

        return icqString.toString();
    }
}

