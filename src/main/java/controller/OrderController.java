package controller;

import factory.ServiceFactory;
import model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderManageService;
import service.ProductManageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@Controller
public class OrderController {
    @Autowired
    OrderManageService orderManageService;

    @Autowired
    ProductManageService productManageService;


    @RequestMapping(value = "/createOrderServlet")
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取session中的user
        String user = "";
        user=(String)req.getSession().getAttribute("account");
        //判断，如果user==null，则尚未登录，直接跳转到登录界面
        if(user.equals(""))
        {
            req.getRequestDispatcher("user/login.jsp").forward(req,resp);
            //返回
            return;
        }
        ArrayList<ProductItem> productList = (ArrayList<ProductItem>)(req.getSession().getAttribute("orderItemList"));

        if(!ableToPlaceOrder(productList))
        {
            String message="库存不足，订单提交失败";
            System.out.println(message);
            req.setAttribute("message",message);
            req.getRequestDispatcher("order/orderFailMessage.jsp").forward(req,resp);
            return;
        }

//添加订单
        double total = ServiceFactory.getOrderManageService().addOrder(user,productList);

        //折扣策略
        String discount="";
        if(total>=200) {
            total *= 0.9;
            discount="已经折扣至"+total+"元";
        }
//扣除账户余额
        ServiceFactory.getLoginManageService().deductFare(user,total);
//出库

        for(ProductItem item:productList)
            productManageService.stockOut(item.getProductId(),item.getQuantity());

        productList.clear();

System.out.println("购物车清空:"+productList.size());

        String message=discount+"    订单提交成功!已经为您跳转到商品列表页！";
        req.setAttribute("message",message);
        req.getRequestDispatcher("order/listProduct.jsp").forward(req,resp);

    }


    public boolean  ableToPlaceOrder( ArrayList<ProductItem>  productList){
        for(ProductItem item:productList)
            if(item.isOos())
                return false;
        return true;
    }

}
