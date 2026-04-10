import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Show Topper");
            System.out.println("7. Count Students");
            System.out.println("8. Export to Text File");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    int id;
                    while (true) {
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();
                        if (id > 0) break;
                        System.out.println("Invalid ID!");
                    }

                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    double marks;
                    while (true) {
                        System.out.print("Enter Marks (0-100): ");
                        marks = sc.nextDouble();
                        if (marks >= 0 && marks <= 100) break;
                        System.out.println("Invalid Marks!");
                    }

                    FileHandler.add(new Student(id, name, marks));
                    break;

                case 2:
                    FileHandler.view();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    FileHandler.search(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();

                    System.out.print("Enter New Marks: ");
                    double umarks = sc.nextDouble();

                    FileHandler.update(uid, uname, umarks);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    FileHandler.delete(sc.nextInt());
                    break;

                case 6:
                    FileHandler.showTopper();
                    break;

                case 7:
                    FileHandler.count();
                    break;

                case 8:
                    FileHandler.exportToText();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 9);

        sc.close();
    }
}