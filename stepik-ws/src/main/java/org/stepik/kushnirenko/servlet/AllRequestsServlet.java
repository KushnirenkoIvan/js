package org.stepik.kushnirenko.servlet;

import org.stepik.kushnirenko.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AllRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> pathVariables = createpageVariablesMap(request);

        pathVariables.put("message", "");

        response.getWriter().print(PageGenerator.instance().getPage("page.html", pathVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createpageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        pageVariables.put("message", message);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }

    public static Map<String, Object> createpageVariablesMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        map.put("method", request.getMethod());
        map.put("URL", request.getRequestURL().toString());
        map.put("pathInfo", request.getPathInfo() != null ? request.getPathInfo(): "");
        map.put("sessionId", request.getSession().getId());
        map.put("parameters", request.getParameterMap().toString());

        return map;
    }
}
