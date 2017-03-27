package org.stepik.kushnirenko;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepik.kushnirenko.servlet.*;


public class RunApp {

    public static final Logger log = Logger.getLogger(RunApp.class);

    public static void main(String[] args) throws Exception {
        WebSocketChatServlet chatServlet = new WebSocketChatServlet();

        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.addServlet(new ServletHolder(chatServlet), "/chat");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, servletHandler});

        Server server = new Server(8080);
        server.setHandler(servletHandler);

        server.start();
        log.info("Server started");
        server.join();
    }
}
