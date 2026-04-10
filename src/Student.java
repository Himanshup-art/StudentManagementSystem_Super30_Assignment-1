import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    int getId() {
        return id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setMarks(double marks) {
        this.marks = marks;
    }

    public String toString() {
        return id + " | " + name + " | " + marks;
    }
}