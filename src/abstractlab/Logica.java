/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstractlab;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Logica extends SudokuBase {

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
                        tablero[fila][col] = 0;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!filaValida(tablero, i, true)) return false;
            if (!columnaValida(tablero, i, true)) return false;
            if (!cajaValida(tablero, i)) return false;
        }

        return true;
    }

    @Override
    public boolean filaValida(int[][] tablero, int indexF, boolean fila) {
        boolean[] vistos = new boolean[10];

        for (int col = 0; col < 9; col++) {
            int num = tablero[indexF][col];
            if (num != 0) {
                if (vistos[num]) return false;
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
                if (vistos[num]) return false;
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
                    if (vistos[num]) return false;
                    vistos[num] = true;
                }
            }
        }
        return true;
    }

    @Override
    public void generarTablero() {
    }

    public void escaneoSimple(JTextField[][] casillas) {
        int[][] tablero = new int[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                String texto = casillas[fila][columna].getText();
                if (!texto.isEmpty()) {
                    try {
                        tablero[fila][columna] = Integer.parseInt(texto);
                    } catch (NumberFormatException e) {
                        tablero[fila][columna] = 0;
                    }
                } else {
                    tablero[fila][columna] = 0;
                }
            }
        }

        for (int num = 1; num <= 9; num++) {
            for (int region = 0; region < 9; region++) {
                int filaInicio = (region / 3) * 3;
                int columnaInicio = (region % 3) * 3;

                boolean yaEsta = false;

                for (int i = filaInicio; i < filaInicio + 3; i++) {
                    for (int j = columnaInicio; j < columnaInicio + 3; j++) {
                        if (tablero[i][j] == num) {
                            yaEsta = true;
                            break;
                        }
                    }
                }

                if (yaEsta) continue;

                int posibles = 0;
                int filaGuardada = -1;
                int colGuardada = -1;

                for (int i = filaInicio; i < filaInicio + 3; i++) {
                    for (int j = columnaInicio; j < columnaInicio + 3; j++) {
                        if (tablero[i][j] == 0 && sePuedeColocar(tablero, i, j, num)) {
                            posibles++;
                            filaGuardada = i;
                            colGuardada = j;
                        }
                    }
                }

                if (posibles == 1) {
                    tablero[filaGuardada][colGuardada] = num;
                    casillas[filaGuardada][colGuardada].setText(String.valueOf(num));
                }
            }
        }
    }

    private boolean sePuedeColocar(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (tablero[i][col] == num) return false;
        }

        int filaInicio = (fila / 3) * 3;
        int colInicio = (col / 3) * 3;
        for (int i = filaInicio; i < filaInicio + 3; i++) {
            for (int j = colInicio; j < colInicio + 3; j++) {
                if (tablero[i][j] == num) return false;
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

