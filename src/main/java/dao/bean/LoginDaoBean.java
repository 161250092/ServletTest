package dao.bean;

import utils.HibernateUtil;
import dao.LoginDao;
import model.User;
import model.UserList;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDaoBean extends BaseDaoImpl implements LoginDao{
    private static LoginDaoBean loginDao = new LoginDaoBean();

    public static LoginDaoBean getInstance(){return loginDao;}


    @Override
    public int Login_in(String user, String pw) {

        User u  = (User) super.load(User.class,user);
        if(u!=null&&u.getPw().equals(pw)) {
            UserList.addUser(user);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean deductFare(String account, double fare) {

        User user = (User)super.load(User.class,account);
        double balance = user.getMoney();
        if(balance-fare>=0) {
            user.setMoney(balance - fare);
            super.save(user);
            return true;
        }
        return false;
    }
}
