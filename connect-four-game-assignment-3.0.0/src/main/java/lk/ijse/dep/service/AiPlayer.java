package lk.ijse.dep.service;

public class AiPlayer extends Player {

    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        int random;

        do {
            random = (int) (Math.random() * (Board.NUM_OF_COLS));
        } while (!board.isLegalMove(random));

        System.out.println(random);

        board.updateMove(random, Piece.GREEN);

        board.getBoardUI().update(random, false);

        Winner winner = board.findWinner();

        if (winner != null) {
            board.getBoardUI().notifyWinner(winner);

        } else {
            if (!board.existLegalMoves()) {
                Winner winner1 = new Winner(Piece.EMPTY);
                board.getBoardUI().notifyWinner(winner1);
            }


        }

    }
}
