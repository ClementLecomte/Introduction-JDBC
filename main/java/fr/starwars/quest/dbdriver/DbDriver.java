package fr.starwars.quest.dbdriver;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DbDriver {
    /* La liste qui contiendra tous les résultats de nos essais */


    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {
            /* Chargement du driver JDBC pour MySQL */
            try {
                messages.add( "Chargement du driver..." );
                Class.forName( "com.mysql.jdbc.Driver" );
                messages.add( "Driver chargé !" );
            } catch ( ClassNotFoundException e ) {
                messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                        + e.getMessage() );
            }

            /* Connexion à la base de données */
            String url = "jdbc:mysql://localhost:3306/starwars";
            String utilisateur = "";
            String motDePasse = "";
            Connection connexion = null;
            Statement statement = null;
            ResultSet resultat = null;
            try {
                messages.add( "Connexion à la base de données..." );
                connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
                messages.add( "Connexion réussie !" );

                /* Création de l'objet gérant les requêtes */
                statement = connexion.createStatement();

                /* Exécution d'une requête de lecture */
                resultat = statement.executeQuery( "SELECT id, name, surname  FROM jedi_masters;" );

                /* Récupération des données du résultat de la requête de lecture */
                while ( resultat.next() ) {
                    int idJedi = resultat.getInt( "id" );
                    String nameJedi = resultat.getString( "name" );
                    String surnameJedi = resultat.getString( "surname" );


                    /* Formatage des données pour affichage dans la JSP finale. */
                    messages.add( " id = " + idJedi + ", Nom = " + nameJedi
                            + ", Prénom = "
                            + surnameJedi );
                }
            } catch ( SQLException e ) {
                messages.add( "Erreur lors de la connexion : <br/>"
                        + e.getMessage() );
            } finally {
                messages.add( "Fermeture de l'objet ResultSet." );
                if ( resultat != null ) {
                    try {
                        resultat.close();
                    } catch ( SQLException ignore ) {
                    }
                }
                messages.add( "Fermeture de l'objet Statement." );
                if ( statement != null ) {
                    try {
                        statement.close();
                    } catch ( SQLException ignore ) {
                    }
                }
                messages.add( "Fermeture de l'objet Connection." );
                if ( connexion != null ) {
                    try {
                        connexion.close();
                    } catch ( SQLException ignore ) {
                    }
                }
            }

            return messages;
        }
}
