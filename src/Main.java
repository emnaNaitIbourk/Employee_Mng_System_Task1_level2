//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        EmployeeManagement manager=new EmployeeManagement();

    int choice;
    do{
        System.out.println("-----------------Employee Management System-----------------");
        System.out.println("1. Add  employee");
        System.out.println("2. View  employee");
        System.out.println("3. Update  employee");
        System.out.println("4. Delete employee");
        System.out.println("5. Exit");
        System.out.println("Enter your choice:");
        choice=Integer.parseInt(sc.nextLine());
        switch(choice){
            case 1:
                manager.addEmployeeMenu();
                break;
            case 2:
                manager.viewEmployee();
                break;
            case 3:
                manager.updateEmployeeMenu();
                break;
            case 4:
                manager.deleteEmployeeMenu();
                break;
            case 5:
                System.out.println(" Exiting...");
                break;
            default:
                System.out.println("Invalid choice ! Please try again!");
        }


    }while(choice!=5);
    sc.close();




    }
}