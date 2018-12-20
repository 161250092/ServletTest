
import model.Product;
import model.ProductItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddOrderItemServlet")
public class AddOrderItemServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取出商品的数量和pid

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
        req.getRequestDispatcher("listProduct.jsp").forward(req, resp);

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

