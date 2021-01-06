package com.dariovarriale.screens;

import com.dariovarriale.utils.*;
import com.dariovarriale.widgets.*;
import com.dariovarriale.widgets.Container;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

/**
 * Modello astratto per una schermata dashboard.
 * <p>
 * In questa schermata l'utente gestisce le sue spedizioni.
 *
 * @author Dario Varriale - 145622
 * @version 1.0
 */
public abstract class Dashboard extends JFrame implements ActionListener, WindowListener {

    /**
     * Tabella delle spedizioni.
     */
    private final JTable tableSpedizioni;
    /**
     * Vettore delle spedizioni di riferimento.
     */
    private final Vector<Spedizione> spedizioni = new Vector<>();
    /**
     * Pannello contenitore del benvenuto.
     */
    private final PannelloBorder pannelloBenvenuto;
    /**
     * Container per gestire il benvenuto.
     */
    private final PannelloBorder containerBenvenuto;
    /**
     * Modello per la tabella spedizioni.
     */
    private SpedizioniModelTable tableModel;

    /**
     * Metodo costruttore.
     *
     * @param titoloSreen Titolo del <code> JFrame</code>.
     */
    public Dashboard(String titoloSreen){
        super(titoloSreen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //Inizializzo le spedizioni per questa dashboard
        initSpedizioni();

        //Creo
        DashboardTitle titleScreen = new DashboardTitle();
        tableModel = new SpedizioniModelTable(spedizioni, false);
        tableSpedizioni = new JTable(tableModel);
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menù");
        JMenuItem exit = new JMenuItem("Salva ed esci");
        menu.add(exit);
        menubar.add(menu);
        setJMenuBar(menubar);

        exit.setActionCommand("exit");

        //UI Setting
        menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tableSpedizioni.setRowHeight(30);
        tableSpedizioni.getTableHeader().setReorderingAllowed(false);
        //Imposto il colore della cella in base allo stato della spedizione
        tableSpedizioni.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) table.getModel().getValueAt(row, 6);
                if(status == null) return this;
                setBackground(status.equals("IN TRANSITO") || status.equals("RIMBORSO RICHIESTO") ? Constants.senapeColor : status.equals("FALLITA") ? Constants.redAccent : status.equals("RICEVUTA") || status.equals("RIMBORSO EROGATO") ? Constants.greenAccent : Color.white);
                setForeground(status.equals("IN PREPARAZIONE") || status.equals("RICEVUTA") || status.equals("RIMBORSO EROGATO") ? Color.black : Color.white );
                if(value.equals("FALLITA") || value.equals("RIMBORSO RICHIESTO")) setText(value.toString());
                return this;
            }
        });

        //ActionListener
        exit.addActionListener(this);
        addWindowListener(this);

        //Pannelli
        PannelloBorder pannelloTitle = new PannelloBorder();
        pannelloBenvenuto = new PannelloBorder();
        containerBenvenuto = new PannelloBorder();
        JScrollPane pannelloTable = new JScrollPane(tableSpedizioni);

        pannelloTitle.add(titleScreen, BorderLayout.WEST);

        pannelloBenvenuto.add(containerBenvenuto, BorderLayout.WEST);
        pannelloBenvenuto.setBorder(Constants.emptyBottom20);

        pannelloTable.setPreferredSize(new Dimension(pannelloTable.getWidth(), 200));
        tableSpedizioni.setPreferredScrollableViewportSize(pannelloTable.getPreferredSize());
        tableSpedizioni.setFillsViewportHeight(true);

        //Container
        Container contentView = new Container();

        contentView.add(pannelloTitle);
        contentView.add(pannelloBenvenuto);
        contentView.add(pannelloTable);

        this.add(contentView);
    }

    /**
     * Metodo per inizializzare le spedizioni della dashboard.
     */
    private void initSpedizioni(){
        InputStreamReader in;
        BufferedReader b;
        File f;
        //Recupero le spedizioni
        try{
            f = new File("com/dariovarriale/data/spedizioni.txt");
            if(!f.exists()) return;

            in = new InputStreamReader(new FileInputStream(f));
            b = new BufferedReader(in);

            String s;
            String[] buf;

            do {
                s = b.readLine();
                if(s == null) return;
                buf = s.split(":");
                int peso = 0, valore = 0;
                try{
                    peso = Integer.parseInt(buf[2]);
                    valore = Integer.parseInt(buf[5]);
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if(buf[4].equals("Si")){
                    addSpedizione(new SpedizioneAssicurata(buf[0], peso , buf[3], buf[1], valore, buf[6]));
                } else {
                    addSpedizione(new Spedizione(buf[0], peso , buf[3], buf[1], buf[6]));
                }
            } while (b.ready());

        } catch(IOException e){
            System.err.println("Errore nell'apertura del file");
            e.printStackTrace();
        }
    }

    /**
     * Metodo per aggiungere una spedizione all'interno del sistema.
     *
     * @param spedizione <code>Spedizione</code> da aggiungere nel sistema
     * @return boolean
     */
    public boolean addSpedizione(Spedizione spedizione){
        spedizioni.add(spedizione);
        return true;
    }

    /**
     * Metodo per recuperare il modello della tabella.
     *
     * @return SpedizioniModelTable
     */
    public SpedizioniModelTable getTableModel() {
        return tableModel;
    }

    /**
     * Metodo per recuperare le spedizioni.
     *
     * @return Vector
     */
    public Vector<Spedizione> getSpedizioni() {
        return spedizioni;
    }

    /**
     * Metodo per recuperare la tabella delle spedizioni.
     *
     * @return JTable
     */
    public JTable getTableSpedizioni() {
        return tableSpedizioni;
    }

    /**
     * Metodo per recuperare il pannello del benvenuto.
     *
     * @return PannelloBorder
     */
    public PannelloBorder getPannelloBenvenuto() {
        return pannelloBenvenuto;
    }

    /**
     * Metodo per recuperare il container del benvenuto.
     *
     * @return PannelloBorder
     */
    public PannelloBorder getContainerBenvenuto() {
        return containerBenvenuto;
    }

    /**
     * Metodo per cambiare il modello della tabella all'interno della dashboard.
     *
     * @param tableModel Nuovo modello per la tabella.
     */
    public void setTableModel(SpedizioniModelTable tableModel) {
        this.tableModel = tableModel;
        tableSpedizioni.setModel(this.tableModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
