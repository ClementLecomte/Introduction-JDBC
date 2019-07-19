package fr.starwars.quest.controller;

import fr.starwars.quest.dbdriver.DbDriver;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DbTestServlet extends HttpServlet  {

   //static final String messages = "messages";
   static final String VUE     = "/WEB-INF/test_jdbc.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Initialisation de l'objet Java et récupération des messages */
        DbDriver test = new DbDriver();
        List<String> jedi = test.executerTests( request );

        /* Enregistrement de la liste des messages dans l'objet requête */
        request.setAttribute("jedis", jedi);

        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
