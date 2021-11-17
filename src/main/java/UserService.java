public class UserService implements  UserDaoInterface{
    private static UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    @Override
    public void login() {

    }
}
