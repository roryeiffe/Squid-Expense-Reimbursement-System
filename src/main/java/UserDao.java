import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;

public class UserDao{

    private Session currentSession;
    private Transaction currentTransaction;

    public UserDao(){
    }

    //open session
    public Session openCurrentSession(){
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    //open session with transaction
    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    //close current session
    public void closeCurrentSession(){
        currentSession.close();
    }

    //close current session and commit
    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    // return factory
    private static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration();

        cfg.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        return sessionFactory;
    }

    public Session getCurrentSession(){
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction(){
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void saveUser(User user){
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            //start a transaction
            transaction = session.beginTransaction();
            //save the object
            session.save(user);
            //commit transaction
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean validate(String email, String password){
        Transaction transaction = null;
        User user = null;
        try(Session session = getSessionFactory().openSession()){
            //start a transaction
            transaction = session.beginTransaction();
            //get user object
            user = (User) session.createQuery("FROM User u WHERE u.email LIKE :useremail").setParameter("useremail", email).getSingleResult();
            //List users = session.createQuery("FROM User u").getResultList();
            //System.out.println(users);
            //Query qry2 = qry.setParameter("useremail", email);
            //user = (User) qry.setParameter("useremail", email);
            //user = (User) qry2.uniqueResult();
            //user = (User) getCurrentSession().get(User.class, email);

            if (user != null && user.getPassword().equals(password)){
                return true;
            }
            //commit transaction
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                //System.out.println("You have issues..");
            }
            e.printStackTrace();
        }
        return false;
    }

}
