package service.impl;

import factory.DaoFactory;
import service.LoginManageService;

public class LoginManageServiceImpl implements LoginManageService {

    private static LoginManageService loginManageService = new LoginManageServiceImpl();

    public static LoginManageService getInstance(){return loginManageService;}
    @Override
    public int Login_in(String user, String pw) {
        return DaoFactory.getLoginDao().Login_in(user,pw);
    }

    @Override
    public boolean deductFare(String account, double fare) {
        return DaoFactory.getLoginDao().deductFare(account,fare);
    }
}
