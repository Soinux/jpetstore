package web.servlet;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        AccountService accountService= new AccountService();
        Account rslt = accountService.getAccount(username);
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if (rslt == null) {
            out.print("Not Exist");
        } else {
            out.print("Exist");
        }
    }
}
