package com.dariovarriale.utils;

import java.util.Random;

/**
 * Rappresenta il modello della <code>Spedizione</code>.
 *
 * <p>
 * La <strong>Spedizione</strong> &egrave; formata da un <strong>id</strong>
 * univoco composto dallo <strong>username</strong> dell'utente che richiede la spedizione e dal numero <i>progressivo</i>
 * delle spedizioni.
 * <br>
 * Oltre l'id, una spedizione ha una <strong>destinazione</strong>, il <strong>peso</strong> del pacco,
 * la <strong>data</strong> di immissione nel sistema, un <strong>valore assicurato</strong>
 * <i>(di default a 0)</i> e il controllo dell'<strong>assicurazione</strong> <i>(di default a "No")</i>.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class Spedizione {

    /**
     * ID della spedizione.
     */
    private final String id;
    /**
     * Destinazione della spedizione.
     */
    private final String destinazione;
    /**
     * Peso del pacco da spedire.
     */
    private final int peso;
    /**
     * Stato della spedizione.
     */
    private String stato;
    /**
     * Data di immissione nel sistema.
     */
    private final String data;
    /**
     * Valore del pacco da assicurare.
     */
    private int valoreAssicurato = 0;
    /**
     * Controllo sull'assicurazione.
     */
    private String assicurata = "No";

    /**
     * Metodo costruttore.
     *
     * @param id ID della spedizione.
     * @param peso Peso del pacco da spedire.
     * @param data Data di immissione nel sistema.
     * @param destinazione Destinazione della spedizione.
     */
    public Spedizione(String id, int peso, String data, String destinazione){
        this.id = id;
        this.peso = peso;
        this.data = data;
        this.destinazione = destinazione;
        this.stato = "IN PREPARAZIONE";
    }

    /**
     * Metodo costruttore.
     *
     * @param id ID della spedizione.
     * @param peso Peso del pacco da spedire.
     * @param data Data di immissione nel sistema.
     * @param destinazione Destinazione della spedizione.
     * @param stato Stato della spedizione.
     */
    public Spedizione(String id, int peso, String data, String destinazione, String stato){
        this.id = id;
        this.peso = peso;
        this.data = data;
        this.destinazione = destinazione;
        this.stato = stato;
    }

    /**
     * Metodo per aggiornare lo stato della spedizione in base allo stato attuale.
     */
    public void updateStato(){
        switch(this.stato){
            case "IN PREPARAZIONE":
                this.stato = "IN TRANSITO";
                break;
            case "IN TRANSITO":
                Random rand = new Random();
                int guess = rand.nextInt(3);
                if(0 == guess) {
                    this.stato = "FALLITA";
                    break;
                }
                this.stato = "RICEVUTA";
                break;
            default: break;
        }
    }

    /**
     * Metodo per aggiornare manualmente lo stato.
     *
     * @param stato Nuovo stato della spedizione.
     */
    public void updateStatoManual(String stato){
        this.stato = stato;
    }

    /**
     * Setter per il valore assicurato.
     *
     * @param valoreAssicurato Nuovo valore assicurato.
     */
    public void setValoreAssicurato(int valoreAssicurato) {
        this.valoreAssicurato = valoreAssicurato;
    }

    /**
     * Setter per il controllo sull'assicurazione.
     *
     * @param assicurata Nuovo controllo per l'assicurazione.
     */
    public void setAssicurata(String assicurata) {
        this.assicurata = assicurata;
    }

    /**
     * Getter per il controllo sull'assicurazione.
     *
     * @return String
     */
    public String getAssicurata() {
        return assicurata;
    }

    /**
     * Getter per lo stato della spedizione.
     *
     * @return String
     */
    public String getStato(){
        return this.stato;
    }

    /**
     * Getter per l'ID della spedizione.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Getter per la destinazione della spedizione.
     *
     * @return String
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Getter per il peso del pacco.
     *
     * @return String
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Getter per la data di immissione nel sistema.
     *
     * @return String
     */
    public String getData() {
        return data;
    }

    /**
     * Getter per il valore assicurato.
     *
     * @return String
     */
    public int getValoreAssicurato() {
        return valoreAssicurato;
    }

    /**
     * Ritorna l'oggetto sottoforma di stringa per il salvataggio.
     *
     * @return String
     */
    @Override
    public String toString() {
        return id + ":" + destinazione + ":" + peso + ":" + data + ":No:0:" + stato + "\n";
    }
}
