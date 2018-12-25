package dao;

public interface LoginDao {
    /**
     *
     * @param user
     * @param pw
     * @return
     *  返回值为 1，用户通过登录验证
     *  返回值为 0，用户密码错误
     *  返回值为 －1，用户不存在
     */
    public int Login_in(String user,String pw);


    public boolean deductFare(String account,double fare);

}
