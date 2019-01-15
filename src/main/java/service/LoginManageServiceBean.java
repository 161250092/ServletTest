package service;

import dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.impl.LoginManageServiceImpl;

@Service
public class LoginManageServiceBean implements LoginManageService {
    @Autowired
    private LoginDao loginDao;


    @Override
    public int Login_in(String user, String pw) {
        return loginDao.Login_in(user,pw);
    }

    @Override
    public boolean deductFare(String account, double fare) {
        return loginDao.deductFare(account,fare);
    }
}
