package logic;
public class IncomingInfo {
    private String name;
    private String surname;
    private String position;
    private int[] phone;
    private String email;
    private String skype;
    private int[] icq;

    public static IncomingInfo getIncomingInfo(String name, String surname, String position, int[] phone, String email, String skype, int[] icq) {
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int[] getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSkype() {
        return skype;
    }

    public int[] getIcq() {
        return icq;
    }
}
