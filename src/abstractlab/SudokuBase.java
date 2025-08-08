package abstractlab;

import javax.swing.JTextField;

public abstract class SudokuBase {
    public abstract boolean solucionValida(JTextField casillas[][]);
    public abstract boolean cajaValida(int tablero [][], int caja);
    public abstract boolean columnaValida(int tablero [][], int indexC, boolean columna);
    public abstract boolean filaValida(int tablero [][], int indexF, boolean fila);
    public abstract void generarTablero();
}
