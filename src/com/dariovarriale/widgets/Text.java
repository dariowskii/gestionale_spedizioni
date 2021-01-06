package com.dariovarriale.widgets;

import com.dariovarriale.utils.Constants;

import javax.swing.*;
import java.awt.*;

/**
 *  Rappresenta l'oggetto Testo con font di default size a 15.
 *
 *  @author Dario Varriale - 145622
 *  @version 1.0
 */
public class Text extends JLabel {

    /**
     * Metodo costruttore.
     *
     * @param testo Stringa del testo da visualizzare.
     */
    public Text(String testo){
        super(testo);
        setFont(Constants.fontText15);
    }

    /**
     * Metodo costruttore con font custom.
     *
     * @param testo Stringa del testo da visualizzare.
     * @param font  Font da impostare.
     */
    public Text(String testo, Font font){
        super(testo);
        setFont(font);
    }
}
