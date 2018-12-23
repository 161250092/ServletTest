package listener;
import javax.jws.soap.SOAPBinding;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.UserList;
@WebListener
public class OnlineListener implements HttpSessionListener,HttpSessionAttributeListener{
    //监听Http会话中的属性添加
    public void attributeAdded(HttpSessionBindingEvent se) {
        if(se.getName().equals("account")) {
            UserList.addUser(String.valueOf(se.getValue()));//增加一个用户
            System.out.println("session(" + se.getSession().getId() + ")增加属性" + se.getName() + ",值为" + se.getValue());
        }
    }
    //监听Http会话中的属性移除
    public void attributeRemoved(HttpSessionBindingEvent se) {
        UserList.removeUser(String.valueOf(se.getValue()));
        System.out.println(se.getValue()+" 帐号已经登出");
        System.out.println("剩余用户:"+UserList.getUserCount());
    }
    //监听Http会话中的属性更改操作
    public void attributeReplaced(HttpSessionBindingEvent se) {
        String oldValue=String.valueOf(se.getValue());//旧的属性
        String newValue=String.valueOf(se.getSession().getAttribute(se.getName()));//新的属性
        if(se.getName().equals("account")) {
             //已经登陆的不用新增
             if(UserList.getUserList().indexOf(newValue)==-1) {
                 UserList.addUser(newValue);//增加新的属性
                 System.out.println(oldValue + "属性已更改为" + newValue);
             }
        }
    }

    public void sessionCreated(HttpSessionEvent se) {

        System.out.println("会话已创建！");
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        System.out.println("会话已销毁！");

    }

}
