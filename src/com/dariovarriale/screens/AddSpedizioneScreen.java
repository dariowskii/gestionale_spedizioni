package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Button;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Schermata per aggiungere una spedizione all'interno del sistema.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public class AddSpedizioneScreen extends JFrame implements ActionListener {

    /**
     * Codice che avrà la spedizione se viene immessa nel sistema.
     */
    private final LabelTextField codice;
    /**
     * Destinazione della spedizione.
     */
    private final LabelTextField destinazione;
    /**
     * Peso del pacco da spedire.
     */
    private final LabelTextField peso;
    /**
     * Valore del pacco in caso sia assicurato.
     */
    private final LabelTextField valore;
    /**
     * Checkbox per sapere se il pacco è assicurato o meno.
     */
    private final JCheckBox assicurata;
    /**
     * Bottone per la richiesta di aggiunta della spedizione nel sistema.
     */
    private final Button submitBtn;
    /**
     * Tabella delle spedizioni di riferimento.
     */
    private final JTable table;
    /**
     * <code>UserDashboard</code> di riferimento.
     */
    private final UserDashboard dashboard;

    /**
     * Metodo costruttore.
     *
     * @param dashboard UserDashboard di riferimento.
     * @param username Username dell'utente che fa la richiesta.
     * @param table Tabella di riferimento.
     */
    public AddSpedizioneScreen(UserDashboard dashboard, String username, JTable table){
        super("Aggiungi una spedizione");
        ContatoreSpedizioni cont = new ContatoreSpedizioni();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.table = table;
        this.dashboard = dashboard;

        //Creo
        assicurata = new JCheckBox("Spedizione Assicurata", false);
        submitBtn = new Button(this, "Aggiungi spedizione", "add");

        assicurata.setActionCommand("isAssicurata");

        //UI Setting
        submitBtn.setEnabled(false);

        //ActionListener
        KeyListener onPressKey = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(assicurata.isSelected()){
                    submitBtn.setEnabled(!destinazione.getTextField().isBlank() && !peso.getTextField().isBlank() && !valore.getTextField().isBlank());
                } else submitBtn.setEnabled(!destinazione.getTextField().isBlank() && !peso.getTextField().isBlank());
            }
        };

        assicurata.addActionListener(this);

        //Pannelli
        PannelloBorder pannelloCheckbox = new PannelloBorder();
        PannelloBorder pannelloBtn = new PannelloBorder();

        codice = new LabelTextField(this, "Codice",username + "-" + cont.getNum(), null);
        destinazione = new LabelTextField(this, "Destinazione", onPressKey, "add");
        peso = new LabelTextField(this, "Peso", onPressKey, "add");
        valore = new LabelTextField(this, "Valore", onPressKey, "add");
        codice.setEnabledField(false);

        codice.setBorder(Constants.emptyBottom10);
        destinazione.setBorder(Constants.emptyBottom10);
        peso.setBorder(Constants.emptyBottom10);
        valore.setBorder(Constants.emptyBottom10);
        valore.setVisible(false);

        pannelloCheckbox.add(assicurata, BorderLayout.CENTER);
        pannelloCheckbox.setBorder(Constants.emptyBottom10);

        pannelloBtn.add(submitBtn);

        //Container
        Container contentView = new Container();

        contentView.add(codice);
        contentView.add(destinazione);
        contentView.add(peso);
        contentView.add(pannelloCheckbox);
        contentView.add(valore);
        contentView.add(pannelloBtn);

        this.add(contentView);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo per intercettare la richiesta di aggiunta della spedizione all'interno del sistema,
     * inoltre intercetta il click della checkbox per sapere se la spedizione è assicurata o meno.
     *
     * @param e <code>ActionEvent</code> dei bottoni.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case "add":
                String inputDestinazione = destinazione.getTextField();
                String inputPeso = peso.getTextField();
                //Controllo che non ci siano campi vuoti
                if(inputDestinazione.isBlank() && inputPeso.isBlank()){
                    return;
                }
                int intPeso;
                //Controllo che il peso sia effettivamente un numero
                try{
                    intPeso = Integer.parseInt(inputPeso);
                }catch (NumberFormatException ex){
                    peso.setTextField("");
                    peso.getFieldFocus();
                    JOptionPane.showMessageDialog(this,"Inserire un peso di soli numeri!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Controllo che il peso non sia <= 0
                if(intPeso <= 0){
                    peso.setTextField("");
                    peso.getFieldFocus();
                    JOptionPane.showMessageDialog(this,"Il peso deve essere maggiore di 0!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Acquisisco la data per registrarla nel sistema
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdf.format(new Date());
                String codice = this.codice.getTextField();

                if(assicurata.isSelected()){
                    String inputValore = valore.getTextField();
                    if(inputValore.isBlank()) return;

                    int intValore;
                    //Controllo che il valore sia effettivamente un numero
                    try{
                        intValore = Integer.parseInt(inputValore);
                    }catch (NumberFormatException ex){
                        valore.setTextField("");
                        valore.getFieldFocus();
                        JOptionPane.showMessageDialog(this,"Inserire un valore di soli numeri!", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //Controllo che il valore non sia <= 0
                    if(intValore <= 0){
                        valore.setTextField("");
                        valore.requestFocus();
                        JOptionPane.showMessageDialog(this,"Il valore del pacco deve essere maggiore di 0!", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //Passati tutti i controlli inserisco la spedizione nel sistema
                    SpedizioneAssicurata spedizioneAssicurata = new SpedizioneAssicurata(codice, intPeso, data, inputDestinazione, intValore);
                    if(dashboard.addSpedizione(spedizioneAssicurata)){
                        SpedizioniModelTable model = (SpedizioniModelTable) table.getModel();
                        model.addRow(spedizioneAssicurata);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,"Errore nell'inserimento della spedizione: dashboard.addSpedizione() = false", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Spedizione spedizione = new Spedizione(codice, intPeso, data, inputDestinazione);
                    if(dashboard.addSpedizione(spedizione)){
                        SpedizioniModelTable model = (SpedizioniModelTable) table.getModel();
                        model.addRow(spedizione);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,"Errore nell'inserimento della spedizione: dashboard.addSpedizione() = false", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //Aggiorno il contatore delle spedizioni
                ContatoreSpedizioni cont = new ContatoreSpedizioni();
                cont.inc();
                cont.saveCont();

                break;
            case "isAssicurata":
                valore.setVisible(assicurata.isSelected());
                if(assicurata.isSelected()){
                    submitBtn.setEnabled(!valore.getTextField().isBlank());
                } else submitBtn.setEnabled(!destinazione.getTextField().isBlank() && !peso.getTextField().isBlank());
                pack();

                break;
            default: break;
        }
    }
}
