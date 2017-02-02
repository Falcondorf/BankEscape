/*
 * DBManager.java
 */
package be.esi.devir5.db;

import be.esi.devir5.exception.BankEscapeDbException;
import java.sql.*;

/**
 * Offre les outils de connexion et de gestion de transaction. 
 */
public class DBManager {

    private static Connection connection;
    //private static MesSettingsDeConnexion dbChoisie;


    /**
     * Retourne la connexion établie ou à défaut, l'établit
     * @return la connexion établie.
     * @throws be.esi.devir5.exception.BankEscapeDbException la connexion est impossible
     */
    public static Connection getConnection() throws BankEscapeDbException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/BankEscapeDB", "julax", "julax");
            } catch (SQLException ex) {
                throw new BankEscapeDbException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * débute une transaction
     * @throws be.esi.devir5.exception.BankEscapeDbException le démarrage de la transaction est impossible
     */
    public static void startTransaction() throws BankEscapeDbException {
        try {

            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new BankEscapeDbException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * débute une transaction en spécifiant son niveau d'isolation
     * Attention, ceci n'est pas implémenté sous Access!
     * (cette notion sera abordée ultérieurement dans le cours de bd)
     * @param isolationLevel le niveau d'isolation
     * @throws be.esi.devir5.exception.BankEscapeDbException le degré d'isolation est inexistant
     */
    public static void startTransaction(int isolationLevel) throws BankEscapeDbException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new BankEscapeDbException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new BankEscapeDbException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * valide la transaction courante
     * @throws be.esi.devir5.exception.BankEscapeDbException la transaction n'est pas validable
     */
    public static void validateTransaction() throws BankEscapeDbException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new BankEscapeDbException("Impossible de valider la transaction: "+ex.getMessage());
        }
    }

    /**
     * annule la transaction courante
     * @throws be.esi.devir5.exception.BankEscapeDbException la transaction ne peut pas etre annulée
     */
    public static void cancelTransaction() throws BankEscapeDbException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new BankEscapeDbException("Impossible d'annuler la transaction: "+ex.getMessage());
        }
    }
}
