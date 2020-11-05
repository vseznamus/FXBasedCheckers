package service;

import model.*;
import model.move.MoveResult;
import model.move.MoveType;

import java.util.ArrayList;
import java.util.List;

public class BrainService {

    public Tile[][] board = new Tile[Size.WIDTH.getSize()][Size.HEIGHT.getSize()];

    private int toBoard(double displaced) {
        return (int) (displaced + Size.TILE.getSize() / 2) / Size.TILE.getSize();
    }

    /*private MoveResult tryToMove(Checker checker, int newX, int newY) {
        if (board[newX][newY].hasChecker() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(checker.getOldX());
        int y0 = toBoard(checker.getOldY());
    }

     */

    public Checker makeChecker(ColorType type, int x, int y) {
        Checker checker = new Checker(type, x, y);

        checker.setOnMouseReleased(e -> {

        });

        return checker;
    }

    public ArrayList<Checker[]> initObjects() {
        ArrayList<Checker[]> checkerArrayList = new ArrayList<>();
        for (int y = 0; y < Size.HEIGHT.getSize(); y++) {
            for (int x = 0; x < Size.WIDTH.getSize(); x++) {

                Checker checker = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    checker = makeChecker(ColorType.ORANGE, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    checker = makeChecker(ColorType.WHITE, x, y);
                }

                if (checker != null) {
                    board[x][y].setChecker(checker);
                    checkerArrayList.add(checker);
                }
            }
        }
        return checkerArrayList;
    }

}
