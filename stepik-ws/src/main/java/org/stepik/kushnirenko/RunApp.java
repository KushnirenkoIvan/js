package org.stepik.kushnirenko;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepik.kushnirenko.servlet.AllRequestsServlet;
import org.stepik.kushnirenko.servlet.HW01RequestServlet;
import org.stepik.kushnirenko.servlet.SignInServlet;
import org.stepik.kushnirenko.servlet.SignUpServlet;


public class RunApp {

    public static final Logger log = Logger.getLogger(RunApp.class);

    public static void main(String[] args) throws Exception {
        AllRequestsServlet ars = new AllRequestsServlet();
        HW01RequestServlet hw01 = new HW01RequestServlet();

        SignUpServlet signUpServlet = new SignUpServlet();
        SignInServlet signInServlet = new SignInServlet();

        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.addServlet(new ServletHolder(signUpServlet), "/signup");
        servletHandler.addServlet(new ServletHolder(signInServlet), "/signin");

        Server server = new Server(8080);
        server.setHandler(servletHandler);

        server.start();
        log.info("Server started");
        server.join();
    }
}
