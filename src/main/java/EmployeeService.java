public class EmployeeService implements  EmployeeDaoInterface{
    private static EmployeeDao employeeDao;

    public EmployeeService(){
        employeeDao = new EmployeeDao();
    }

    @Override
    public void login() {
        
    }

    @Override
    public void submitRequest() {

    }

    @Override
    public void viewPendingRequests() {

    }

    @Override
    public void viewPastRequests() {

    }
}
