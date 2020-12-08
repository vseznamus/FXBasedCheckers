package service;

import model.*;
import model.move.MoveResult;
import model.move.MoveType;

import java.util.ArrayList;
import java.util.List;

public class BrainService {

    public Tile[][] board = new Tile[Size.WIDTH.getSize()][Size.HEIGHT.getSize()];
    public ArrayList<Checker> checkerRemover = new ArrayList<>();
    public ColorType turn = ColorType.WHITE;
    public int valueOfWhiteAlive = 0;
    public int valueOfOrangeAlive = 12;

    public int toBoard(double displaced) {
        return (int) (displaced + Size.TILE.getSize() / 2) / Size.TILE.getSize();
    }

    public MoveResult tryMove(Checker checker, int newX, int newY) {
        if (board[newX][newY].hasChecker() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int x0 = toBoard(checker.getOldX());
        int y0 = toBoard(checker.getOldY());

        if (Math.abs(newX - x0) == 1 && newY - y0 == checker.getColor().moveDirection) {
            return new MoveResult(MoveType.DEFAULT);
        } else if (Math.abs(newX - x0) == 2 && Math.abs(newY - y0) == Math.abs(checker.getColor().moveDirection * 2)) {
            int x1 = x0 + (newX - x0) / 2;
            int y1 = y0 + (newY - y0) / 2;

            if (board[x1][y1].hasChecker() && board[x1][y1].getChecker().getColor() != checker.getColor()) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getChecker());
            }
        }

        return new MoveResult(MoveType.NONE);

    }

    public ArrayList<Checker> initObjects() {
        ArrayList<Checker> checkerArrayList = new ArrayList<>();
        for (int y = 0; y < Size.HEIGHT.getSize(); y++) {
            for (int x = 0; x < Size.WIDTH.getSize(); x++) {

                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                Checker checker = null;


                if (y <= 2 && (x + y) % 2 != 0) {
                    checker = createChecker(ColorType.ORANGE, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    checker = createChecker(ColorType.WHITE, x, y);
                }

                if (checker != null) {
                    tile.setChecker(checker);
                    checkerArrayList.add(checker);
                }
            }
        }
        return checkerArrayList;
    }

    public Checker createChecker(ColorType type, int x, int y) {
        return new Checker(type, x, y);
    }

    public void moveSwitcher(Checker newChecker) {
        int newX = toBoard(newChecker.getLayoutX());
        int newY = toBoard(newChecker.getLayoutY());

        MoveResult result;

        if ((newX < 0 || newY < 0 || newX >= Size.WIDTH.getSize() || newY >= Size.HEIGHT.getSize())
                || (turn == ColorType.WHITE && newChecker.getColor() == ColorType.ORANGE) || (turn == ColorType.ORANGE
                && newChecker.getColor() == ColorType.WHITE)) {
            result = new MoveResult(MoveType.NONE);
        } else {
            result = tryMove(newChecker, newX, newY);
        }

        int x0 = toBoard(newChecker.getOldX());
        int y0 = toBoard(newChecker.getOldY());
        switch (result.getType()) {
            case NONE -> newChecker.abortMove();
            case DEFAULT -> {
                newChecker.move(newX, newY);
                board[x0][y0].setChecker(null);
                board[newX][newY].setChecker(newChecker);
                if (turn == ColorType.ORANGE)
                    turn = ColorType.WHITE;
                else turn = ColorType.ORANGE;
            }
            case KILL -> {
                newChecker.move(newX, newY);
                board[x0][y0].setChecker(null);
                board[newX][newY].setChecker(newChecker);
                Checker otherChecker = result.getChecker();
                board[toBoard(otherChecker.getOldX())][toBoard(otherChecker.getOldY())].setChecker(null);
                checkerRemover.add(otherChecker);
                if (otherChecker.getColor() == ColorType.ORANGE) {
                    valueOfOrangeAlive -= 1;
                } else valueOfWhiteAlive -= 1;
                //TODO: Multikill

                if (turn == ColorType.ORANGE)
                    turn = ColorType.WHITE;
                else turn = ColorType.ORANGE;
            }
        }
    }

    public ArrayList<Tile> initTiles() {
        ArrayList<Tile> tileArrayList = new ArrayList<>();
        for (int y = 0; y < Size.HEIGHT.getSize(); y++) {
            for (int x = 0; x < Size.WIDTH.getSize(); x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileArrayList.add(tile);
            }
        }
        return tileArrayList;
    }
}
