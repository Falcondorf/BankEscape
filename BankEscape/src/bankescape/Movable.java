package bankescape;

/**
 *
 * @author jackd
 */
public class Movable {

    private Position pos;

    public Movable(Position position) {
        this.pos = position;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                this.pos.setRow(this.pos.getRow() - 1);
                break;
            case DOWN:
                this.pos.setRow(this.pos.getRow() + 1);
                break;
            case LEFT:
                this.pos.setColumn(this.pos.getColumn() - 1);
                break;
            case RIGHT:
                this.pos.setColumn(this.pos.getColumn() + 1);
                break;
        }
    }

    public int getRow() {
        return pos.getRow();
    }

    public int getColumn() {
        return pos.getColumn();
    }

}
