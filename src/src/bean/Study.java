package bean;

public class Study {
    private String student;
    private String course;

    public Study(String student, String course) {
        this.student = student;
        this.course = course;
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
}
