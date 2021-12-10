package bean;

public class Course {
    private String code;
    private String name;
    private String summary;
    private String profession;
    private String semester;
    private String number;
    private String qualifications;

    public Course(String code, String name, String summary, String profession, String semester, String number, String qualifications) {
        this.code = code;
        this.name = name;
        this.summary = summary;
        this.profession = profession;
        this.semester = semester;
        this.number = number;
        this.qualifications = qualifications;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
