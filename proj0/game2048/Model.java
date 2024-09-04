package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        /*
         TODO: Modify this.board (and perhaps this.score) to account
         for the tilt to the Side SIDE. If the board changed, set the
         changed local variable to true.
        */
        boolean changed;
        changed = false;

        // 重置视角
        board.setViewingPerspective(side);
        // 保存当前列的初始值
        int[] backup = new int[4];
        for (int c = board.size()-1; c >= 0; c--) {
            // 记录当前列的元素
            int[] x = new int[4];
            // 记录移动次数
            int len = 0;
            for (int i = 3; i >= 0; i--) {
                if (this.tile(c, i) != null) {
                    // 更新这个值到backup[]
                    backup[i] = this.tile(c, i).value();
                }
            }

            // 将顶行数据存放到辅助数组中
            if (board.tile(c, 3) != null) {
                x[3] = board.tile(c, 3).value();
            }
            for (int r = board.size() - 2; r >= 0; r--) {
                // 方块不为空，取出当前的值
                if (board.tile(c, r) != null) {
                    int currentValue = board.tile(c, r).value();
                    Tile t = board.tile(c, r);
                    // 添加到辅助数组中
                    x[r] = currentValue;
                    // 调用getMoveStep函数，判断移动位置
                    int moveStep = getMoveStep(x, c, r, currentValue, len, backup);
                    if (board.tile(c, moveStep) != null && moveStep != r) {
                        // 计算两个方块的合并值
                        score += 2 * board.tile(c, moveStep).value();
                        len++;
                        // 写一个数组

                    }
                    board.move(c, moveStep, t);
                    // 更新辅助数组
                    update(x, c);
                    changed = true;
                }
            }
        }
        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    // 更新数组状态
    private void update(int[] x, int col) {
        for (int r = 0; r < board.size(); r++) {
            if (board.tile(col, r) != null) {
                x[r] = board.tile(col, r).value();
            }
            else{
                x[r] = 0;
            }
        }
    }

    // 步数移动
    private int getMoveStep(int[] x, int col, int row, int currentValue, int len, int[] backup) {
        int targetRow = row;

        if (isBottomRow(row, backup, currentValue, x, len)) {
            targetRow = row - 1;
        } else {
            for (int i = row; i < x.length - 1 - len; i++) {
                targetRow++;
                if (currentValue != x[i + 1] && x[i + 1] != 0) {
                    return targetRow - 1;
                } else if (currentValue == x[i + 1]) {
                    return targetRow;
                }
            }
        }

        return targetRow;
}
    private boolean isBottomRow(int row, int[] backup, int currentValue, int[] x, int len) {
        if (row != 0) {
            return false;
        }

        int backupIndex = 0;
        for (int value : backup) {
            if (value != 0) {
                backupIndex++;
            }
        }

        return backupIndex == 4 && currentValue == x[x.length - 1 - len] && backup[1] != currentValue;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        // 获取棋盘大小
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(j, i) == null) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(j, i) != null && b.tile(j, i).value() == MAX_PIECE) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b)) return true;

        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {

                int currentValue = b.tile(j, i).value();

                // 循环到最后的元素直接退出
                if (i == b.size() - 1 && j == b.size() - 1) break;

                // 遍历到最后一列，只需要判断下面的元素是否相等
                if (j == b.size() - 1) {
                    if (b.tile(j, i + 1).value() == currentValue) {
                        return true;
                    }
                    continue;
                }

                // 遍历到最后一行，只需要判断左边的元素是否相等
                if (i == b.size() - 1) {
                    if (b.tile(j + 1, i).value() == currentValue) {
                        return true;
                    }
                    continue;
                }

                // 判断左边和下面的元素是否相等
                if ((b.tile(j, i + 1).value() == currentValue) ||
                        (b.tile(j + 1, i).value() == currentValue)
                ) return true;
            }
        }

        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}

