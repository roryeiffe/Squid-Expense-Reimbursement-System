package User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

    public User validate(String email, String password){
        Transaction transaction = null;
        User user = null;
        try(Session session = getSessionFactory().openSession()){
            //start a transaction
            transaction = session.beginTransaction();
            //get user object
            user = (User) session.createQuery("FROM User.User u WHERE u.email LIKE :useremail").setParameter("useremail", email).getSingleResult();

            if (user != null && user.getPassword().equals(password)){
                return user;
                //return true;
            }
            //commit transaction
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

}
