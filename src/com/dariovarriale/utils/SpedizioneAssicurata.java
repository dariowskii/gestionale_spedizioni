package com.dariovarriale.utils;

/**
 * Rappresenta il modello della <code>Spedizione</code> assicurata.
 * La spedizione assicurata è caratterizzata dal fatto che può chiedere un
 * <strong>rimborso</strong> se la spedizione non va a buon fine, poichè è <strong>assicurata</strong>.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class SpedizioneAssicurata extends Spedizione{

    /**
     * Metodo costruttore semplice.
     *
     * @param username Username del cliente che sta facendo la richiesta.
     * @param peso Peso del pacco da spedire.
     * @param data Data di immissione nel sistema.
     * @param destinazione Destinazione del pacco.
     * @param valoreAssicurato Valore del pacco che viene restituito
     *                         dall'assicurazione in caso di fallimento.
     */
    public SpedizioneAssicurata(String username, int peso, String data, String destinazione, int valoreAssicurato) {
        super(username, peso, data, destinazione);
        setValoreAssicurato(valoreAssicurato);
        setAssicurata("Si");
    }

    /**
     * Metodo costruttore con stato custom.
     *
     * @param username Username del cliente che sta facendo la richiesta.
     * @param peso Peso del pacco da spedire.
     * @param data Data di immissione nel sistema.
     * @param destinazione Destinazione del pacco.
     * @param valoreAssicurato Valore del pacco che viene restituito
     *                         dall'assicurazione in caso di fallimento.
     * @param stato Stato della spedizione.
     */
    public SpedizioneAssicurata(String username, int peso, String data, String destinazione, int valoreAssicurato, String stato) {
        super(username, peso, data, destinazione, stato);
        setValoreAssicurato(valoreAssicurato);
        setAssicurata("Si");
    }

    /**
     * Aggiorna lo stato della spedizione.
     */
    @Override
    public void updateStato() {
        super.updateStato();
        if ("RIMBORSO RICHIESTO".equals(getStato())) {
            updateStatoManual("RIMBORSO EROGATO");
        }
    }

    /**
     * Ritorna l'oggetto sottoforma di stringa per il salvataggio.
     *
     * @return String
     */
    @Override
    public String toString() {
        return getId() + ":" + getDestinazione() + ":" + getPeso() + ":" + getData() + ":Si:" + getValoreAssicurato() + ":" + getStato() + "\n";
    }
}
