package Tetris.global.config.constant;

public enum WindowSize {
    W800_H600(800, 600),
    ;

    private int width;
    private int height;

    WindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
