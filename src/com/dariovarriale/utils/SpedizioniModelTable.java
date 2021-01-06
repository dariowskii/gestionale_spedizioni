package com.dariovarriale.utils;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Rappresenta il <code>TableModel</code> della tabella delle spedizioni delle dashboard.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class SpedizioniModelTable extends AbstractTableModel {

    /**
     * Vettore delle spedizioni della classe.
     */
    private final Vector<Spedizione> spedizioni;
    /**
     * Nomi delle colonne.
     */
    private final String[] colName = {"ID", "Destinazione", "Peso", "Data di partenza", "Assicurata", "Valore","Stato"};
    /**
     * Valore true/false per definire se la tabella sarà in una dashboard utente o amministratore.
     */
    private final boolean isAdmin;

    /**
     * Metodo costruttore.
     *
     * @param spedizioni Vettore delle spedizioni di riferimento.
     * @param isAdmin Valore true/false per indicare se la tabella si trova
     *                all'interno di una dashboard utente o amministratore.
     */
    public SpedizioniModelTable(Vector<Spedizione> spedizioni, boolean isAdmin){
        this.spedizioni = spedizioni;
        this.isAdmin = isAdmin;
    }

    /**
     * Ritorna il numero delle colonne.
     *
     * @return int
     */
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    /**
     * Ritorna il numero delle righe.
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        return spedizioni.size();
    }

    /**
     * Ritorna l'<code>Object</code> contenuto all'interno della cella.
     *
     * @param rowIndex Indice della riga.
     * @param columnIndex Indice della colonna.
     *
     * @return Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Spedizione rowItem = spedizioni.elementAt(rowIndex);
        //Ritorna l'oggetto
        return switch (columnIndex) {
            case 0 -> rowItem.getId();
            case 1 -> rowItem.getDestinazione();
            case 2 -> rowItem.getPeso();
            case 3 -> rowItem.getData();
            case 4 -> rowItem.getAssicurata();
            case 5 -> rowItem.getValoreAssicurato();
            case 6 -> rowItem.getStato();
            default -> "";
        };
    }

    /**
     * Aggiunge una riga all'interno della tabella dell'utente.
     *
     * @param spedizione Vettore delle spedizioni di riferimento.
     */
    public void addRow(Spedizione spedizione){
        spedizioni.add(spedizione);
        fireTableChanged(new TableModelEvent(this));
    }

    /**
     * Ritorna il nome della colonna.
     *
     * @param column Indice della colonna.
     *
     * @return String
     */
    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

    /**
     * Se la tabella &egrave; nella dashboard amministratore tutte le celle
     * saranno disabilitate. Se si trova nella dashboard user, saranno abilitate
     * solo le celle per chiedere il rimborso della spedizione.
     *
     * @param row Indice della riga.
     * @param column Indice della colonna.
     *
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if(isAdmin) return false;
        Spedizione element = spedizioni.elementAt(row);
        return element.getAssicurata().equals("Si") && element.getStato().equals("FALLITA");
    }

}
