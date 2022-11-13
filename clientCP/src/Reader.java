public class Reader extends User {
    private String name;
    private String passwordID;
    private String phone;
    private String birthDay;


    public Reader(String login, String password) {
        super(login, password);
    }

    public Reader(String login, String password, String name, String passwordID, String phone, String birthDay) {
        super(login, password);
        this.name = name;
        this.passwordID = passwordID;
        this.phone = phone;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordID() {
        return passwordID;
    }

    public void setPasswordID(String passwordID) {
        this.passwordID = passwordID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
