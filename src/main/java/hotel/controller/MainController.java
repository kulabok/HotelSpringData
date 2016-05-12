package hotel.controller;

import hotel.service.Service;
import hotel.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/controller.MainController")
public class MainController extends HttpServlet {

    protected void process(HttpServletRequest request, HttpServletResponse response) {
        Service service = ServiceFactory.getService(request);
        String path = service.execute(request, response);
        RequestDispatcher rd=request.getRequestDispatcher(path);
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            //LOGGER
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
    }

}

