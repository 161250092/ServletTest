package controller;

import model.Product;
import model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ProductManageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Controller
public class ProductController {

    @RequestMapping("/AddOrderItemServlet")
    public void addOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

System.out.println(req.getParameter("num"));
System.out.println(req.getParameter("pid"));
System.out.println(req.getSession().getAttribute("account"));
        int num=Integer.parseInt(req.getParameter("num"));
        int pid=Integer.parseInt(req.getParameter("pid"));

        List<ProductItem> orderItemList=(List<ProductItem>)req.getSession().getAttribute("orderItemList");

        if(orderItemList==null)
            System.out.println("fuck!!!");

        ProductItem  product = this.getProductDetail(req,pid,num);
        orderItemList.add(product);

        System.out.println("现在有商品:"+orderItemList.size());
        req.getRequestDispatcher("order/listProduct.jsp").forward(req, resp);
    }


    public ProductItem  getProductDetail(HttpServletRequest req,int pid,int num){
        ProductItem detail = null;
        ArrayList<Product> products =( ArrayList<Product>)req.getSession().getAttribute("products");
        for(Product p:products){
            if(p.getProductId()==pid) {
                boolean oos = false;
                if(num>=p.getRepertory())
                    oos = true;
                detail = new ProductItem(pid, p.getProductName(), num, p.getPrice(), num * p.getPrice(), oos);
            }
        }
        return detail;
    }
}


