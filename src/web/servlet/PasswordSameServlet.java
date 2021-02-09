package web.servlet;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordSameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pw1 = req.getParameter("pw1");
        String pw2 = req.getParameter("pw2");
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if (pw1.equals(pw2)) {
            out.print("Same");
        } else {
            out.print("Not Same");
        }
    }
}
