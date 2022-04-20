package Tetris.global.constant.color;

/**
 * 도메인 및 컴포넌트에 사용되는 모든 색을 16진수로 나타낸 int형으로 보관
 */
public enum BaseColor {
    WHITE(0xffffff),

    CYAN(0x00ffff),
    YELLOW(0xffff00),
    PURPLE(0x800080),
    GREEN(0x00ff00),
    RED(0xff0000),
    BLUE(0x0000ff),
    ORANGE(0xff7f00),
    GREY(0x7f7f7f),

    DW_ORANGE(0xe59f01),
    SKY_BLUE(0x54b5ec),
    BLUISH_GREEN(0x009f73),
    DW_YELLOW(0xf1e344),
    DW_BLUE(0x0072b1),
    VERMILION(0xd65e00),
    REDDISH_PURPLE(0xcc79a7),

    LIGHT_GREY(0xd3d3d3),
    BLACK(0x000000),
    ;


    private int color;

    BaseColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }
}
