import  javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java .awt.*;

import java.awt.event.*;
public class EmployeeMng_GUI extends JFrame {
    private EmployeeManagement employeeManagement = new EmployeeManagement();
    private JTextField idField, nameField, salaryField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    Font myFont=new Font("Comic Sans MS",Font.PLAIN,14);

    public EmployeeMng_GUI() {
        setTitle("Employee Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createInputPanel();
        createButtonPanel();
        createTablePanel();
        setVisible(true);


    }

    private void createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));


        TitledBorder border=BorderFactory.createTitledBorder("Employee Details");
        border.setTitleFont(myFont);
        border.setTitleColor(Color.BLUE);
        inputPanel.setBorder(border);
        EmptyBorder emptyBorder=new EmptyBorder(10,10,10,10);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(border,emptyBorder));

        idField = new JTextField();
        nameField = new JTextField();
        salaryField = new JTextField();
        idField.setFont(myFont);
        nameField.setFont(myFont);
        salaryField.setFont(myFont);

        JLabel idLabel=new JLabel("Employee ID: ");
        idLabel.setFont(myFont);
        JLabel nameLabel=new JLabel("Name: ");
        nameLabel.setFont(myFont);
        JLabel salaryLabel=new JLabel("Salary:");
        salaryLabel.setFont(myFont);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(salaryLabel);
        inputPanel.add(salaryField);
        add(inputPanel, BorderLayout.NORTH);



    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add employee");
        addBtn.addActionListener(e -> addEmployee());
        JButton updBtn = new JButton("Update employee");
        updBtn.addActionListener(e -> updateEmployee());
        JButton dltBtn = new JButton("Delete employee");
        dltBtn.addActionListener(e -> deleteEmployee());
        addBtn.setFont(myFont);
        updBtn.setFont(myFont);
        dltBtn.setFont(myFont);
        buttonPanel.add(addBtn);
        buttonPanel.add(updBtn);
        buttonPanel.add(dltBtn);
        add(buttonPanel, BorderLayout.CENTER);
        addBtn.setFocusPainted(false);
        updBtn.setFocusPainted(false);
        dltBtn.setFocusPainted(false);
    }

    private void createTablePanel() {
        tableModel = new DefaultTableModel(new String[]{"Id", "Name", "Salary"}, 0);
        employeeTable = new JTable(tableModel);

        employeeTable.setFont(myFont);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    salaryField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });
        JScrollPane scroll = new JScrollPane(employeeTable);
        add(scroll, BorderLayout.SOUTH);
    }
    private void addEmployee() {
       try {
           int id = Integer.parseInt(idField.getText());
           String name = nameField.getText();
           double salary = Double.parseDouble(salaryField.getText());
           employeeManagement.addEmployee(id, name, salary);
           tableModel.addRow(new Object[]{id, name, salary});
           JLabel addLabel=new JLabel("Employee added successfully :)");
           addLabel.setFont(myFont);
           addLabel.setForeground(Color.BLUE);
           JButton okAdd=new JButton("Ok");
           okAdd.setFocusPainted(false);
           okAdd.setFont(myFont);
           Object[] addOptions={okAdd};
           final JOptionPane addPane=new JOptionPane(addLabel,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,addOptions,okAdd);
           final JDialog dial=addPane.createDialog("Add Success");
           okAdd.addActionListener(ad->dial.dispose());
           dial.setVisible(true);
           idField.setText("");
           nameField.setText("");
           salaryField.setText("");
       }catch(NumberFormatException ex){
           Toolkit.getDefaultToolkit().beep();
           JLabel catchLabel=new JLabel("Please enter valid numeric values for Id or salary!");
           catchLabel.setFont(myFont);
           catchLabel.setForeground(Color.red);
           JButton catchOk=new JButton("Ok");
           catchOk.setFocusPainted(false);
           catchOk.setFont(myFont);
           Object[] catchOptions={catchOk};
            final JOptionPane catchPane=new JOptionPane(catchLabel,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,catchOptions,catchOk);
            final JDialog d=catchPane.createDialog("Invalid Input");
            catchOk.addActionListener(c->d.dispose());
            d.setVisible(true);
       }



    }
    private void updateEmployee(){

        try{
            int id=Integer.parseInt(idField.getText());
            String name=nameField.getText();
            double salary=Double.parseDouble(salaryField.getText());
            if(employeeManagement.updateEmployee(id,name,salary)==true) {
                refreshTable();
                JLabel updateLabel = new JLabel("Employee updated successfully:)");
                updateLabel.setForeground(Color.BLUE);
                updateLabel.setFont(myFont);
                JButton updtOk = new JButton("Ok");
                updtOk.setFocusPainted(false);
                updtOk.setFont(myFont);
                Object[] updoptions = {updtOk};
                final JOptionPane updatePane = new JOptionPane(updateLabel, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, updoptions, updtOk);
                final JDialog dl = updatePane.createDialog("Update Success");
                updtOk.addActionListener(up->dl.dispose());
                dl.setVisible(true);
                idField.setText("");
                nameField.setText("");
                salaryField.setText("");
            }

            else{
                Toolkit.getDefaultToolkit().beep();
                JLabel updtFail=new JLabel("Employee not found! Update is impossible!");
                updtFail.setFont(myFont);
                updtFail.setForeground(Color.red);
                JButton failUpOk=new JButton("Ok");
                failUpOk.setFocusPainted(false);
                failUpOk.setFont(myFont);
                Object[] failOptions={failUpOk};
                final JOptionPane updateFail_pane=new JOptionPane(updtFail,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,failOptions,failUpOk);
                final JDialog  d=updateFail_pane.createDialog("Update Failure");
                failUpOk.addActionListener(u->d.dispose());
                d.setVisible(true);
                idField.setText("");
                nameField.setText("");
                salaryField.setText("");
            }
        }catch(NumberFormatException exep){
            Toolkit.getDefaultToolkit().beep();
            JLabel  invalidLabel=new JLabel("Please enter a valid Id or salary numbers!");
            invalidLabel.setFont(myFont);
            invalidLabel.setForeground(Color.RED);
            JButton invalidOk=new JButton("Ok");
            invalidOk.setFocusPainted(false);
            invalidOk.setFont(myFont);
            Object[] invalidOptions={invalidOk};
            final JOptionPane invalidPane=new JOptionPane(invalidLabel,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,invalidOptions,invalidOk);
            final JDialog dl= invalidPane.createDialog("Invalid Input");
            invalidOk.addActionListener(i->dl.dispose());
            dl.setVisible(true);
        }

    }
    private  void deleteEmployee(){
        if(tableModel.getRowCount()==0){
            Toolkit.getDefaultToolkit().beep();
            JLabel warningmsg=new JLabel("No employees available to delete!");
            warningmsg.setFont(myFont);
            warningmsg.setForeground(Color.RED);
            JButton wrnOk=new JButton("Ok");
            wrnOk.setFocusPainted(false);
            wrnOk.setFont(myFont);
            Object [] wrnOptions={wrnOk};
            final JOptionPane wrnPane= new JOptionPane(warningmsg,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,wrnOptions,wrnOk);
            final JDialog dl=wrnPane.createDialog("Warning");
            wrnOk.addActionListener(w->dl.dispose());
            dl.setVisible(true);
            return;

        }
        int selectedRow=employeeTable.getSelectedRow();
        if(selectedRow<0){
            Toolkit.getDefaultToolkit().beep();
            JLabel selectLabel=new JLabel("Please select a row from the table! first");
            selectLabel.setForeground(Color.BLUE);
            selectLabel.setFont(myFont);
            JButton selectOk=new JButton("Ok");
            selectOk.setFont(myFont);
            selectOk.setFocusPainted(false);
            Object[] selectOptions={selectOk};
            final  JOptionPane selectPane=new JOptionPane(selectLabel,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,selectOptions,selectOk);
            final JDialog dl=selectPane.createDialog("Warning for selection");
            selectOk.addActionListener(s->dl.dispose());
            dl.setVisible(true);
            return;

        }
        int id=(int) tableModel.getValueAt(selectedRow,0);
        JLabel confirmLabel=new JLabel("Are you sure you want to  delete  employee #"+id+"?");
        confirmLabel.setFont(myFont);
        confirmLabel.setForeground(Color.black);
        JButton confirmYes=new JButton("Yes");
        confirmYes.setFocusPainted(false);
        confirmYes.setFont(myFont);
        JButton noBtn=new JButton("No");
        noBtn.setFocusPainted(false);
        noBtn.setFont(myFont);
        Object[] yes_noOptions={confirmYes,noBtn};
        final JOptionPane confirmPane=new JOptionPane(confirmLabel,JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,null,yes_noOptions,confirmYes);
        final JDialog dl=confirmPane.createDialog("Confirm Deletion");
        confirmYes.addActionListener(y-> {
            confirmPane.setValue(JOptionPane.YES_OPTION);
            dl.dispose();
        });
        noBtn.addActionListener(no->{
            confirmPane.setValue(JOptionPane.NO_OPTION);
            dl.dispose();
        });
        dl.setVisible(true);
        Object result=confirmPane.getValue();
        int confirm=(result instanceof  Integer &&((Integer) result )==JOptionPane.YES_OPTION)?JOptionPane.YES_OPTION:JOptionPane.NO_OPTION;
        if(confirm==JOptionPane.YES_OPTION){
            if(employeeManagement.deleteEmployee(id)==true){
                refreshTable();
                idField.setText("");
                nameField.setText("");
                salaryField.setText("");
                employeeTable.clearSelection();
                JLabel deletsucess=new JLabel("Employee deleted successfully:)");
                deletsucess.setFont(myFont);
                deletsucess.setForeground(Color.BLUE);
                JButton dleOk=new JButton("Ok");
                dleOk.setFont(myFont);
                dleOk.setFocusPainted(false);
                Object[] dloption={dleOk};
                final JOptionPane optionpane=new JOptionPane(deletsucess,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,dloption,dleOk);
                final JDialog dialog=optionpane.createDialog("Successful Deletion");
                dleOk.addActionListener(d->dialog.dispose());
                dialog.setVisible(true);
            }
            else{
                Toolkit.getDefaultToolkit().beep();
                JLabel dlFailure=new JLabel("Employee deletion failed X Employee not found!");
                dlFailure.setForeground(Color.RED);
                dlFailure.setFont(myFont);
                JButton dlOkFail=new JButton("Ok");
                dlOkFail.setFocusPainted(false);
                dlOkFail.setFont(myFont);
                Object[] dloptionFail={dlOkFail};
                final  JOptionPane opt=new JOptionPane(dlFailure,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,dloptionFail,dlOkFail);
                final JDialog dial=opt.createDialog("Deletion  Failure");
                dlOkFail.addActionListener(ac->dial.dispose());
                dial.setVisible(true);
            }

        }
        else{
            employeeTable.clearSelection();
            idField.setText("");
            nameField.setText("");
            salaryField.setText("");

                Toolkit.getDefaultToolkit().beep();
                JLabel dlFailure=new JLabel("Employee deletion  declined!");
                dlFailure.setForeground(Color.BLUE);
                dlFailure.setFont(myFont);
                JButton dlOkFail=new JButton("Ok");
                dlOkFail.setFocusPainted(false);
                dlOkFail.setFont(myFont);
                Object[] dloptionFail={dlOkFail};
                final  JOptionPane opt=new JOptionPane(dlFailure,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,dloptionFail,dlOkFail);
                final JDialog dial=opt.createDialog("Deletion  Failure");
                dlOkFail.addActionListener(ac->dial.dispose());
                dial.setVisible(true);

        }



    }
    private void refreshTable(){
         tableModel.setRowCount(0);
         for(Employee emp:employeeManagement.getEmployees()){
             tableModel.addRow(new Object[]{emp.getId(),emp.getName(),emp.getSalary()});
         }
    }
    public static void main (String[] args) {
        new EmployeeMng_GUI();
    }
}
