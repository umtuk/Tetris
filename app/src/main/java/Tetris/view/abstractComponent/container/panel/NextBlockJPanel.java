package Tetris.view.abstractComponent.container.panel;

import javax.swing.JPanel;

import java.awt.*;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.service.BoardService;
import Tetris.global.matrix.IntMatrixUtil;

public class NextBlockJPanel extends JPanel {

    private BoardService boardService = BoardService.getInstance();

    public NextBlockJPanel() {
        setBackground(new Color(BoardColorMap.getColor(BoardComponent.EMPTY)));
        setPreferredSize(new Dimension(160, 160));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNextBlock(g);
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / boardService.getPrevBlock().getShape()[0].length;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / boardService.getPrevBlock().getShape().length;
    }

    private void drawSquare(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
    }

    public void drawNextBlock(Graphics g) {
        int[][] nextBlockPos = IntMatrixUtil.findAllNotZeroValuePos(boardService.getPrevBlock().getShape(), 4);
        int[] center = IntMatrixUtil.findNearestCenter(boardService.getPrevBlock().getShape());

        for (int i = 0; i < 4; i++) {
            nextBlockPos[i][0] = 1 + (nextBlockPos[i][0] - center[0]);
            nextBlockPos[i][1] = 1 + (nextBlockPos[i][1] - center[1]);
        }

        for (int i = 0; i < 4; i++) {
            int x = nextBlockPos[i][1];
            int y = nextBlockPos[i][0];

            Color color = new Color(boardService.getPrevBlock().getColor());

            drawSquare(g, x * squareWidth(), y * squareHeight(), color);
        }
    }
}
