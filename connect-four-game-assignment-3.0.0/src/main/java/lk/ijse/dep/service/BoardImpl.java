package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardUI = boardUI;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_COLS; j++) {
                pieces[j][i] = Piece.EMPTY;
            }

        }

    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        Piece piece = Piece.EMPTY;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            piece = pieces[col][i];
            if (piece == Piece.EMPTY) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            if (findNextAvailableSpot(i) != -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int rowIndex = findNextAvailableSpot(col);
        pieces[col][rowIndex] = move;


    }

    @Override
    public Winner findWinner() {
        int col1 = 0;
        int row1 = 0;
        int col2;
        int row2;

        int vertical = 1;
        Piece piece1 = Piece.EMPTY;

        for (int i = 0; i < NUM_OF_COLS; i++) {
            if (findNextAvailableSpot(i) < 3) continue;
            Piece[] set = pieces[i];
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                Piece p = set[j];
                if (p == Piece.EMPTY || piece1 != p) {
                    piece1 = p;
                    vertical = 1;
                    row1 = j;
                    continue;
                } if (p.equals(piece1)) {
                    vertical += 1;
                    piece1 = p;
                } if (vertical == 4) {
                    return new Winner(piece1, i, row1, i, j);
                }

            }
            piece1 = Piece.EMPTY;

        }

        vertical = 1;
        piece1 = Piece.EMPTY;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j < NUM_OF_COLS; j++) {
                Piece p = pieces[j][i];
                if (p == Piece.EMPTY || piece1 != p) {
                    piece1 = p;
                    vertical = 1;
                    col1 = j;
                    continue;

                }
                if (p.equals(piece1)) {
                    vertical += 1;
                    piece1 = p;

                }
                if (vertical == 4) return new Winner(piece1, col1, i, j, i);

            }
            piece1 = Piece.EMPTY;
        }
        return null;
    }
}
