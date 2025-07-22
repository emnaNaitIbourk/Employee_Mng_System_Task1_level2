
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class EmployeeManagement {
    private ArrayList<Employee> employeeList = new ArrayList<Employee>();
    private Scanner s = new Scanner(System.in);

    public void addEmployee(int id, String name, double salary) {


        employeeList.add(new Employee(id, name, salary));


    }

    public void addEmployeeMenu() {
        System.out.println("Please enter the id of employee to add: ");
        int id = Integer.parseInt(s.nextLine());
        System.out.println("Please enter the name of employee to add:");
        String name = s.nextLine();
        System.out.println("Please enter the salary of employee to add: ");
        double salary = Double.parseDouble(s.nextLine());
        employeeList.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully");
    }

    public void viewEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("Employee list is empty! You can't view employees!");
        } else {
            for (Employee emp : employeeList) {
                emp.display();
            }
        }
    }

    public boolean updateEmployee(int id, String name, double salary) {


        for (Employee emp : employeeList) {
            if (emp.getId() == id) {

                emp.setName(name);
                emp.setSalary(salary);
                return true;

            }

        }
        return false;
    }

    public void deleteEmployeeMenu(){
        System.out.println("Enter the employee Id you want to delete: ");
        int id=s.nextInt();
        boolean  removed=employeeList.removeIf(emp-> emp.getId()==id);
        if(removed==true){
            System.out.println("Employee deleted successfully:)");
        }
        else{
            System.out.println("Employee not found ! Deletion is impossible!");
        }
    }
    public  ArrayList <Employee> getEmployees(){
        return employeeList;
    }
    public void updateEmployeeMenu(){
        System.out.println("Please enter the employee's Id to update:");
        int id=Integer.parseInt(s.nextLine());
        boolean found=false;
        for(Employee emp:employeeList){
            if(emp.getId()==id){
                System.out.println("Enter the  new name of employee for the update:");
                String name=s.nextLine();
                System.out.println("Enter the new salary  for the update: ");
                double salary=Double.parseDouble(s.nextLine());
                emp.setName(name);
                emp.setSalary(salary);
                found=true;
                break;
            }

        }
        if(found==true){
            System.out.println("Employee updated successfully:)");
        }
        else{
            System.out.println("Employee not found!Update failed!");
        }

    }
    public  boolean deleteEmployee(int id){
        return employeeList.removeIf(em->em.getId()==id);
    }
}
