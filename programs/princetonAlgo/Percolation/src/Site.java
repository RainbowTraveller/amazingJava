import java.util.Objects;

public class Site {
   private int row;
   private int col;
   private int id;
   private int size;
   private Site connection;

    public Site(int row, int col) {
        this.row = row;
        this.col = col;
        this.id = 0;
        connection = null;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Site && this.getRow()
                == ((Site) obj).getRow() && this.getCol() == ((Site) obj).getCol() && this.getId() == ((Site) obj).getId());
    }
    public int getRow() {
        return row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setId(int n) {
        id = (row - 1) * n + col;
    }

    public int getId() {
        return id;
    }

    public Site getConnection() {
        return connection;
    }

    public void setConnection(Site connection) {
        this.connection = connection;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Site{" +
                "row=" + row +
                ", col=" + col +
                ", id=" + id +
                '}';
    }
}
