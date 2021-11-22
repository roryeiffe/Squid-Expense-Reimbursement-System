import User.User;
import User.UserDao;
import reimbursement.Reimbursement;
import reimbursement.ReimbursementDao;

public class App {

    public static void main(String[] args) {
//         User manager = new User("Rory","r@gmail.com","123", true);
//         User employee = new User("AJ", "a@gmail.com","1234",false);
//         UserDao dao = new UserDao();
//         dao.openCurrentSessionWithTransaction();
//         dao.saveUser(manager);
//         dao.saveUser(employee);
//         dao.closeCurrentSessionWithTransaction();
        ReimbursementDao dao = new ReimbursementDao();

        // first, create a session:
        dao.openCurrentSessionWithTransaction();

        // create some reimbursements for employee with id 1:
        Reimbursement reimbursement1 = new Reimbursement(1, "food", "lunch with client", 50, "pending");
        Reimbursement reimbursement2 = new Reimbursement(1, "gas", "driving to client", 30, "pending");
        Reimbursement reimbursement3 = new Reimbursement(1, "food", "dinner with client", 100, "pending");

        // create some reimbursements for employee with id 2:
        Reimbursement reimbursement4 = new Reimbursement(2,"test","Took Python certification test",50,  "pending");
        Reimbursement reimbursement5 = new Reimbursement(2,"food","burgers with the bros", 25, "pending");
        Reimbursement reimbursement6 = new Reimbursement(2,"food","pizza party Friday", 200, "pending");
//
////        Test insertion:
        dao.insert(reimbursement1);
        dao.insert(reimbursement2);
        dao.insert(reimbursement3);
        dao.insert(reimbursement4);
        dao.insert(reimbursement5);
        dao.insert(reimbursement6);
//
//        // Test update:
//        Reimbursement newReimbursement = new Reimbursement(2,"food","Friday pizza party", 200, "approved");
//        // update the reimbursement with id 12:
//        newReimbursement.setId(12);
//        dao.update(newReimbursement);
//
//        // Test getById:
//        Reimbursement reimbursement = dao.getById(12);
//        System.out.println(reimbursement.toString());
//
//        // Test get multiple:
//        List<Reimbursement> allPending = dao.getReimbursements(1,"approved");
//        for(Reimbursement r: allPending) {
//            System.out.println(r.toString());
//        }
//
        dao.commitAndClose();
    }
}
