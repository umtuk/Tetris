package Tetris.domain.board.constant.map;

import java.util.EnumMap;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.global.constant.color.BaseColor;

public class BoardColorMap {
    private static EnumMap<BoardComponent, BaseColor> bEnumMap;

    static {
        bEnumMap = new EnumMap<>(BoardComponent.class);

        bEnumMap.put(BoardComponent.EMPTY, BaseColor.BLACK);
        bEnumMap.put(BoardComponent.WALL, BaseColor.LIGHT_GREY);
    }

    public static int getColor(BoardComponent boardComponent) {
        return bEnumMap.get(boardComponent)
                        .getColor();
    }
}
