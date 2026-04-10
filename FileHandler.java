import java.io.*;

public class FileHandler {
    static String file = "students.dat";

    // ADD
    static void add(Student s) {
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos;

            if (new File(file).length() == 0)
                oos = new ObjectOutputStream(fos);
            else
                oos = new ObjectOutputStream(fos) {
                    protected void writeStreamHeader() throws IOException {}
                };

            oos.writeObject(s);
            oos.close();

            System.out.println("Added!");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // VIEW
    static void view() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                Student s = (Student) ois.readObject();
                System.out.println(s);
            }

        } catch (EOFException e) {
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // SEARCH
    static void search(int id) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() == id) {
                    System.out.println("Found: " + s);
                    return;
                }
            }

        } catch (EOFException e) {
            System.out.println("Not found!");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // DELETE
    static void delete(int id) {
        boolean found = false;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.dat"));

            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() != id) {
                    oos.writeObject(s);
                } else {
                    found = true;
                }
            }

        } catch (EOFException e) {
            new File(file).delete();
            new File("temp.dat").renameTo(new File(file));

            if (found)
                System.out.println("Deleted!");
            else
                System.out.println("Not found!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // UPDATE
    static void update(int id, String name, double marks) {
        boolean found = false;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.dat"));

            while (true) {
                Student s = (Student) ois.readObject();

                if (s.getId() == id) {
                    s.setName(name);
                    s.setMarks(marks);
                    found = true;
                }

                oos.writeObject(s);
            }

        } catch (EOFException e) {
            new File(file).delete();
            new File("temp.dat").renameTo(new File(file));

            if (found)
                System.out.println("Updated!");
            else
                System.out.println("Not found!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // TOPPER
    static void showTopper() {
        Student top = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                Student s = (Student) ois.readObject();

                if (top == null || s.marks > top.marks) {
                    top = s;
                }
            }

        } catch (EOFException e) {
            if (top != null)
                System.out.println("Topper: " + top);
            else
                System.out.println("No data!");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // COUNT
    static void count() {
        int count = 0;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                ois.readObject();
                count++;
            }

        } catch (EOFException e) {
            System.out.println("Total Students: " + count);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    // EXPORT
    static void exportToText() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

            while (true) {
                Student s = (Student) ois.readObject();
                bw.write(s.toString());
                bw.newLine();
            }

        } catch (EOFException e) {
            System.out.println("Exported to students.txt");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}