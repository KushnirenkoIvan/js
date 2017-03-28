package org.stepik.kushnirenko.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stepik.kushnirenko.server.AccountServer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    static final Logger log = LogManager.getLogger(AdminServlet.class);

    public static final String PAGE_URL = "/admin";
    private final AccountServer accountServer;

    public AdminServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getParameter("remove") != null) {
            accountServer.removeUser();
            response.getWriter().println("Hasta la vista!");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        log.info("Limit: {}. Count {}", limit, count);

        if (count < limit) {
            log.info("User pass");
            accountServer.addNewUser();
            response.getWriter().println(accountServer.getUsersLimit());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            log.info("User were rejected");
            response.getWriter().println("Server is closed for maintenance!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
