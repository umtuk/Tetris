package Tetris.view.abstractComponent.container.panel;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.*;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.service.BoardService;
import Tetris.global.matrix.IntMatrixUtil;

public class NextBlockJPanel extends JPanel {

    private BoardService boardService = BoardService.getInstance();
    private LineBorder lineBorder = new LineBorder(Color.WHITE);

    public NextBlockJPanel() {
        setBackground(new Color(BoardColorMap.getColor(BoardComponent.EMPTY)));
        setPreferredSize(new Dimension(300, 300));
        setBorder(lineBorder);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNextBlock(g);
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / 4;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / 4;
    }

    private void drawSquare(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
    }

    private void drawText(Graphics g, int x, int y, Color color, String shape) {
        int fontSize = (int)(squareWidth());
        g.setColor(color);
        g.setFont(new Font("Serif", Font.BOLD, fontSize));
        g.drawString(shape, x * squareWidth(), (int)((y + 1) * squareWidth() * .74));
    }

    public void drawNextBlock(Graphics g) {
        int[][] nextBlockPos = IntMatrixUtil.findAllNotZeroValuePos(boardService.getPrevBlock().getShape(), IntMatrixUtil.countNotZeroValue(boardService.getPrevBlock().getShape()));
        int[] center = IntMatrixUtil.findNearestCenter(boardService.getPrevBlock().getShape());

        for (int i = 0; i < IntMatrixUtil.countNotZeroValue(boardService.getPrevBlock().getShape()); i++) {
            nextBlockPos[i][0] = 1 + (nextBlockPos[i][0] - center[0]);
            nextBlockPos[i][1] = 1 + (nextBlockPos[i][1] - center[1]);
        }

        for (int i = 0; i < IntMatrixUtil.countNotZeroValue(boardService.getPrevBlock().getShape()); i++) {
            int x = nextBlockPos[i][1];
            int y = nextBlockPos[i][0];

            Color color = new Color(boardService.getPrevBlock().getColor());
            String shape;
            Block nextBlock = boardService.getPrevBlock();
            int lineRemoverIdx = boardService.findLineRemover(nextBlock);
            if (boardService.isBomb(nextBlock)) {
                shape = "B";
            }
            else if (boardService.isDrill(nextBlock)) {
                shape = "D";
            }
            else if (i == lineRemoverIdx) {
                shape = "L";
            }
            else {
                shape = "O";
            }

            //drawSquare(g, x * squareWidth(), y * squareHeight(), color);
            //drawText(g, x, y, color, "O");
            drawText(g, x, y, color, shape);
        }
    }
}