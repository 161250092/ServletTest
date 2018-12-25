package listeners;

import model.UserList;

import javax.servlet.*;


public class CounterListener implements ServletContextListener,ServletContextAttributeListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
            application.setAttribute("userList", UserList.getUserList());

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("服务器关闭");
    }



    public void attributeAdded(ServletContextAttributeEvent scae) {
            String account =(String)scae.getValue();
            if(UserList.getUserList().indexOf(account)==-1){
                UserList.addUser(account);
                System.out.println("在线用户："+UserList.getUserCount());
            }
    }

    public  void attributeRemoved(ServletContextAttributeEvent scae) {
            UserList.removeUser((String)scae.getValue());
            System.out.println((String)scae.getValue()+"登出");

    }

    public void attributeReplaced(ServletContextAttributeEvent scae) {
    }

}
