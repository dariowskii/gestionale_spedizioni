package com.dariovarriale.widgets;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta un <code>JPanel</code> con un allineamento <code>FlowLayout</code> custom.
 *
 * @author Davrio Varriale - 145622
 * @version 1.0
 */
public class PannelloFlow extends JPanel {

    /**
     * Metodo costruttore con allineamento custom.
     *
     * @param align Costante FlowLayout.
     */
    public PannelloFlow(int align){
        super();
        setLayout(new FlowLayout(align));
    }

}
