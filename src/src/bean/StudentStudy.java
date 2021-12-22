package bean;

public class StudentStudy {
    private String student;
    private String course;
    private String finish;

    public StudentStudy() {
    }

    public StudentStudy(String student, String course, String finish) {
        this.student = student;
        this.course = course;
        this.finish = finish;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
