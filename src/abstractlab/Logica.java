/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstractlab;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Logica extends SudokuBase {

    @Override
    public void generarTablero(JTextField[][] casillas, String dificultad) {
        int[][][] faciles = {
            {
                {0, 2, 0, 6, 0, 8, 0, 0, 0},
                {5, 8, 0, 0, 0, 9, 7, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 0},
                {3, 7, 0, 0, 0, 0, 5, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 4},
                {0, 0, 8, 0, 0, 0, 0, 1, 3},
                {0, 0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 9, 8, 0, 0, 0, 3, 6},
                {0, 0, 0, 3, 0, 6, 0, 9, 0}
            }
        };

        int[][][] medios = {
            {
                {0, 0, 0, 0, 0, 7, 0, 9, 0},
                {1, 0, 0, 4, 0, 0, 2, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0, 5},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 0, 7, 0},
                {5, 0, 0, 0, 0, 0, 6, 0, 0},
                {0, 0, 3, 0, 0, 1, 0, 0, 9},
                {0, 4, 0, 6, 0, 0, 0, 0, 0}
            }
        };

        int[][][] dificiles = {
            {
                {0, 0, 0, 0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 9, 0, 0, 0, 7, 0, 0},
                {0, 0, 0, 6, 0, 0, 0, 0, 0},
                {8, 6, 0, 0, 0, 0, 0, 0, 0}
            }
        };

        int[][][] seleccion;

        if (dificultad.equalsIgnoreCase("facil")) {
            seleccion = faciles;
        } else if (dificultad.equalsIgnoreCase("medio")) {
            seleccion = medios;
        } else {
            seleccion = dificiles;
        }

        int index = (int) (Math.random() * seleccion.length);
        int[][] tablero = seleccion[index];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tablero[i][j] != 0) {
                    casillas[i][j].setText(String.valueOf(tablero[i][j]));
                } else {
                    casillas[i][j].setText("");
                }
            }
        }

    }

    @Override
    public boolean solucionValida(JTextField casillas[][]) {
        int[][] tablero = new int[9][9];

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                try {
                    String texto = casillas[fila][col].getText();
                    if (!texto.isEmpty()) {
                        tablero[fila][col] = Integer.parseInt(texto);
                    } else {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!filaValida(tablero, i, true)) {
                return false;
            }
            if (!columnaValida(tablero, i, true)) {
                return false;
            }
            if (!cajaValida(tablero, i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean filaValida(int[][] tablero, int indexF, boolean fila) {
        boolean[] vistos = new boolean[10];

        for (int col = 0; col < 9; col++) {
            int num = tablero[indexF][col];
            if (num != 0) {
                if (vistos[num]) {
                    return false;
                }
                vistos[num] = true;
            }
        }
        return true;
    }

    @Override
    public boolean columnaValida(int[][] tablero, int indexC, boolean columna) {
        boolean[] vistos = new boolean[10];

        for (int fila = 0; fila < 9; fila++) {
            int num = tablero[fila][indexC];
            if (num != 0) {
                if (vistos[num]) {
                    return false;
                }
                vistos[num] = true;
            }
        }
        return true;
    }

    @Override
    public boolean cajaValida(int[][] tablero, int caja) {
        boolean[] vistos = new boolean[10];

        int filaInicio = (caja / 3) * 3;
        int columnaInicio = (caja % 3) * 3;

        for (int i = filaInicio; i < filaInicio + 3; i++) {
            for (int j = columnaInicio; j < columnaInicio + 3; j++) {
                int num = tablero[i][j];
                if (num != 0) {
                    if (vistos[num]) {
                        return false;
                    }
                    vistos[num] = true;
                }
            }
        }
        return true;
    }

    private boolean sePuedeColocar(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (tablero[i][col] == num) {
                return false;
            }
        }

        int filaInicio = (fila / 3) * 3;
        int colInicio = (col / 3) * 3;
        for (int i = filaInicio; i < filaInicio + 3; i++) {
            for (int j = colInicio; j < colInicio + 3; j++) {
                if (tablero[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void resolverSudoku(JTextField[][] casillas) {
        int[][] tablero = new int[9][9];

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                String texto = casillas[fila][col].getText();
                if (!texto.isEmpty()) {
                    try {
                        tablero[fila][col] = Integer.parseInt(texto);
                    } catch (NumberFormatException e) {
                        tablero[fila][col] = 0;
                    }
                } else {
                    tablero[fila][col] = 0;
                }
            }
        }

        if (resolverPasoAPaso(tablero)) {
            for (int fila = 0; fila < 9; fila++) {
                for (int col = 0; col < 9; col++) {
                    casillas[fila][col].setText(String.valueOf(tablero[fila][col]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo resolver el sudoku :(");
        }
    }

    private boolean resolverPasoAPaso(int[][] tablero) {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tablero[fila][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (sePuedeColocar(tablero, fila, col, num)) {
                            tablero[fila][col] = num;
                            if (resolverPasoAPaso(tablero)) {
                                return true;
                            }
                            tablero[fila][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
