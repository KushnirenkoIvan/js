package org.stepik.kushnirenko;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepik.kushnirenko.servlet.AllRequestsServlet;
import org.stepik.kushnirenko.servlet.HW01RequestServlet;


public class RunApp {

    public static final Logger log = Logger.getLogger(RunApp.class);

    public static void main(String[] args) throws Exception {
        AllRequestsServlet ars = new AllRequestsServlet();
        HW01RequestServlet hw01 = new HW01RequestServlet();

        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.addServlet(new ServletHolder(ars), "/page.html");
        servletHandler.addServlet(new ServletHolder(hw01), "/mirror");

        Server server = new Server(8080);
        server.setHandler(servletHandler);

        server.start();
        log.info("Server started");
        server.join();
    }
}
