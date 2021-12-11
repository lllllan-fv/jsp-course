package bean;

public class Study {
    private String student;
    private String course;
    private String left;

    public Study(String student, String course, String left) {
        this.student = student;
        this.course = course;
        this.left = left;
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

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
