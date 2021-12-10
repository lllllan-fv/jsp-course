package bean;

public class Account {
    private String id;
    private String pwd;
    private String name;
    private String college;
    private String profession;
    private String contact;

    public Account(String id, String pwd, String name, String college, String profession, String contact) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.college = college;
        this.profession = profession;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
