package Tetris.global.color;

/**
 * 도메인 및 컴포넌트에 사용되는 모든 색을 16진수로 나타낸 int형으로 보관
 */
public enum BaseColor {
    WHITE(0x000000),
    ;


    private int color;

    BaseColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
