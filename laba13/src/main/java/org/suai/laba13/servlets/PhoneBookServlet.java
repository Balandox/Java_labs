package org.suai.laba13.servlets;

import org.suai.laba13.dao.PhoneBookDAO;
import org.suai.laba13.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

public class PhoneBookServlet extends HttpServlet {
    private PhoneBookDAO phoneBookDAO;

    @Override
    public void init(){
        this.phoneBookDAO = new PhoneBookDAO();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        StringTokenizer stringTokenizer = new StringTokenizer(uri, "/");
        int id = 0;

        if(stringTokenizer.countTokens() == 2) { //
            stringTokenizer.nextToken(); // "phonebook"
            try {
                id = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (NumberFormatException e){

            }
        }


        if(Objects.equals(uri, "/phonebook/" + id)){
            this.phoneBookDAO.addTelephoneNumber(this.phoneBookDAO.show(id), request.getParameter("telephone"));
            response.sendRedirect("/phonebook");
        }

        if(Objects.equals(uri, "/phonebook")){
            this.phoneBookDAO.addPerson(request.getParameter("firstName"),
                    request.getParameter("lastName"), request.getParameter("telephone"));
            response.sendRedirect("/phonebook");
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        StringTokenizer stringTokenizer = new StringTokenizer(uri, "/");

        int id = 0;

        if(stringTokenizer.countTokens() == 2) { // show.jsp
            stringTokenizer.nextToken(); // "phonebook"
            try {
                id = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (NumberFormatException e){

            }
        }
        else if(stringTokenizer.countTokens() == 3){ // addTelephone.jsp
            stringTokenizer.nextToken(); // "phonebook"
            id = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer.nextToken();
            // "addTelephone"
        }

        if(Objects.equals(uri, "/phonebook")){
            request.setAttribute("personList", this.phoneBookDAO.index());
            request.getRequestDispatcher("/WEB-INF/views/phonebook.jsp").forward(request, response);
        }
        else if(Objects.equals(uri, "/phonebook/" + id)){
            request.setAttribute("person", this.phoneBookDAO.show(id));
            request.getRequestDispatcher("/WEB-INF/views/show.jsp").forward(request, response);
        }

        else if(Objects.equals(uri, "/phonebook/" + id + "/addTelephone")){
            request.setAttribute("person", this.phoneBookDAO.show(id));
            request.getRequestDispatcher("/WEB-INF/views/addTelephone.jsp").forward(request, response);
        }

        else if(uri.equals("/phonebook/all")){
            request.setAttribute("personList", this.phoneBookDAO.index());
            request.getRequestDispatcher("/WEB-INF/views/phonebook.jsp").forward(request, response);
        }

        else if(uri.equals("/phonebook/new")){
            request.getRequestDispatcher("/WEB-INF/views/newPerson.jsp").forward(request, response);
        }

        else if(uri.equals("/phonebook/search")){
            Person person = this.phoneBookDAO.search(request.getParameter("searchPerson"));
            if(person != null) {
                request.setAttribute("person", person);
                request.getRequestDispatcher("/WEB-INF/views/show.jsp").forward(request, response);
            }
            else{
                request.setAttribute("errorString", "No matches found!");
                request.setAttribute("personList", this.phoneBookDAO.index());
                request.getRequestDispatcher("/WEB-INF/views/phonebook.jsp").forward(request, response);
            }

        }

    }

}
