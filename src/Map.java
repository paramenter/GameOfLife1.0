import java.util.List;

public class Map {
    private boolean[][] map;
    private int width;
    private int height;
    public Map(int width, int height) {
        map = new boolean[width][height];
        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = false;
            }
        }
    }
    public void setPixel(int x, int y, boolean status) {
        map[x][y] = status;
    }
    public void setNextPixel(int x, int y) {
        map[x][y] = !map[x][y];
    }
    public boolean getPixel(int x, int y) {
        return map[x][y];
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArround(int x, int y) {
        int around = 0;
        if ((x - 1) >= 0 &&  (y - 1) >= 0 && map[x - 1][y - 1]) around++; // left / top
        if ((y - 1) >= 0 && map[x][y - 1]) around++; // mid / top
        if ((x + 1) < width &&  (y - 1) >= 0 && map[x + 1][y - 1]) around++; // right / top
        if ((x - 1) >= 0 && map[x - 1][y]) around++; // left / mid
        if ((x + 1) < width && map[x + 1][y]) around++; // right / mid
        if ((x - 1) >= 0 &&  (y + 1) < height && map[x - 1][y + 1]) around++; // left / bottom
        if ((y + 1) < height && map[x][y + 1]) around++; // mid / bottom
        if ((x + 1) < width &&  (y + 1) < height && map[x + 1][y + 1]) around++; // right / bottom
        return around;
    }
    public void nextGeneration() {
        boolean[][] newMap = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int neighbors = getArround(x, y);

                // Apply Conway's Game of Life rules:
                if (map[x][y]) {
                    // Any live cell with 2 or 3 neighbors stays alive
                    if (neighbors == 2){
                        newMap[x][y] = getPixel(x, y);
                    } else if (neighbors == 3) {
                        newMap[x][y] = true;
                    } else {
                        newMap[x][y] = false; // Otherwise it dies
                    }
                } else {
                    // Any dead cell with exactly 3 neighbors becomes alive
                    if (neighbors == 3) {
                        newMap[x][y] = true;
                    } else {
                        newMap[x][y] = false;
                    }
                }
            }
        }
        map = newMap;
    }

}
