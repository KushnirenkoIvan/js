package org.stepik.kushnirenko;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepik.kushnirenko.controller.AccountServerController;
import org.stepik.kushnirenko.controller.AccountServerControllerMBean;
import org.stepik.kushnirenko.server.AccountServer;
import org.stepik.kushnirenko.server.AccountServerImpl;
import org.stepik.kushnirenko.servlet.AdminServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;


public class RunApp {

    public static final Logger log = LogManager.getLogger(RunApp.class);

    public static void main(String[] args) throws Exception {
        log.info("Starting at http://127.0.0.1:8080");

        AccountServer accountServer = new AccountServerImpl(10);
        AccountServerControllerMBean scMBean = new AccountServerController(accountServer);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mBeanServer.registerMBean(scMBean, name);

        Server server = new Server(8080);

        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.addServlet(new ServletHolder(new AdminServlet(accountServer)), AdminServlet.PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, servletHandler});

        server.setHandler(servletHandler);

        server.start();
        log.info("Server started");

        server.join();
    }
}
