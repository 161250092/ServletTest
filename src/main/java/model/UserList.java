package model;

import java.util.ArrayList;

public class UserList {
    private static ArrayList<String> onlineUser = new ArrayList<String>();
    private static int visitor = 0;

    public static void addUser(String account){
        onlineUser.add(account);
    }

    public static void removeUser(String account){
        onlineUser.remove(account);
    }

    public static int getUserCount(){
        return onlineUser.size();
    }

    public static ArrayList<String> getUserList(){
        return onlineUser;
    }


    public static int getVisitor(){return visitor;}

    public  static void addVisitor(){visitor++;}

    public static void removeVisitor(){visitor--;}

    public static int getAllNum(){
        return visitor+onlineUser.size();
    }
}
