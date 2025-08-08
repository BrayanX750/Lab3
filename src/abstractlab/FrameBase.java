/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstractlab;

import javax.swing.JFrame;

/**
 *
 * @author edwin
 */
public abstract class FrameBase extends JFrame {

    public FrameBase(String titulo, int width, int height) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
    }
    
    protected abstract void initComponents();
}
