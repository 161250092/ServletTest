package service;

public interface LoginManageService {

    public int Login_in(String user,String pw);


    public boolean deductFare(String account,double fare);
}
