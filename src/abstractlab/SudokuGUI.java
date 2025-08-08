package abstractlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.JOptionPane;

public class SudokuGUI extends FrameBase {

    public SudokuGUI() {
        super("Sudoku", 500, 500);
        initComponents();
    }

    public JTextField espacios[][] = new JTextField[9][9];

    Random random = new Random();

    @Override
    protected void initComponents() {
        Logica logica = new Logica();

        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //otros paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelSur.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        JPanel panelOeste = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelOeste.setPreferredSize(new Dimension(50, 0));
        panelPrincipal.add(panelOeste, BorderLayout.WEST);

        JPanel panelEste = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelEste.setPreferredSize(new Dimension(50, 0));
        panelPrincipal.add(panelEste, BorderLayout.EAST);

        JLabel lblTitulo = new JLabel("SUDOKU");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));
        panelNorte.add(lblTitulo);

        //tablero
        JPanel panelTablero = new JPanel(new GridLayout(9, 9));
        panelTablero.setPreferredSize(new Dimension(20, 30));
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                JTextField numero = new JTextField();
                numero.setHorizontalAlignment(JTextField.CENTER);
                numero.setFont(numero.getFont().deriveFont(Font.PLAIN, 16f));

                boolean colorAlterno = ((fila / 3) + (columna / 3)) % 2 == 0;
                numero.setBackground(colorAlterno ? Color.decode("#CCFFFF") : Color.WHITE);
                numero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                espacios[fila][columna] = numero;
                panelTablero.add(numero);
            }
        }

        //botón verificar
        JButton btnVerificar = new JButton("Verificar solución");
        panelSur.add(btnVerificar);

        btnVerificar.addActionListener(e -> {
            if (logica.solucionValida(espacios)) {
                JOptionPane.showMessageDialog(this, "Felicidades! completaste el sudoku correctamente!");
                int opcion = random.nextInt(3);

                if (opcion == 0) {
                    logica.generarTablero(espacios, "facil");
                } else if (opcion == 1) {
                    logica.generarTablero(espacios, "medio");
                } else {
                    logica.generarTablero(espacios, "dificil");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Esta solución no es la correcta, intenta de nuevo.");
            }
        });

        //botón nueva partida
        JButton btnNuevaPartida = new JButton("Nueva partida");
        panelSur.add(btnNuevaPartida);

        btnNuevaPartida.addActionListener(e -> {
            int opcion = random.nextInt(3);

            if (opcion == 0) {
                logica.generarTablero(espacios, "facil");
            } else if (opcion == 1) {
                logica.generarTablero(espacios, "medio");
            } else {
                logica.generarTablero(espacios, "dificil");
            }
        });

        //botón salir
        JButton btnSalir = new JButton("Salir");
        panelSur.add(btnSalir);

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        //generar primer tablero
        logica.generarTablero(espacios, "dificil");
        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new SudokuGUI().setVisible(true);
    }
}
