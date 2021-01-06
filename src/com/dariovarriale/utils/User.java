package com.dariovarriale.utils;

/**
 * Rappresenta il modello cliente con username, password e indirizzo.
 *
 * @author Dario Varriale - 145622.
 * @version 1.0
 */
public class User {
    /**
     * Username.
     */
    private final String username;
    /**
     * Password.
     */
    private final String password;
    /**
     * Indirizzo.
     */
    private final String indirizzo;

    /**
     * Metodo costruttore per lo <code>User</code> con l'indirizzo.
     *
     * @param username  Username dell'utente.
     * @param password  Password dell'utente.
     * @param indirizzo Indirizzo dell'utente.
     */
    public User(String username, String password, String indirizzo){
        this.username = username;
        this.password = password;
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce lo username.
     *
     * @return String username.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Restituisce la password.
     *
     * @return String password.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Restituisce l'oggetto sottoforma di Stringa.
     *
     * @return String
     */
    @Override
    public String toString() {
        return username + ":" + password + ":" + indirizzo;
    }
}
