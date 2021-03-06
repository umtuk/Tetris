package Tetris.view.adapter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import Tetris.domain.board.service.BoardService;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.config.entity.branch.KeyMap;
import Tetris.view.frame.game.GameFrame;
import Tetris.view.frame.game.PauseFrame;


public class GameAdapter extends KeyAdapter {

    private MainConfig mainConfig = MainConfig.getInstance();
    private BoardService boardService = BoardService.getInstance();
    private ScoreService scoreService = ScoreService.getInstance();
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        KeyMap keyMap = mainConfig.getKeyMap();

        if (boardService.isPause()) {
            return;
        }

        if (keyCode == keyMap.get(KeyType.UP)) {

            if (GameFrame.toDelete != null) {
                boardService.deleteFullLine(GameFrame.toDelete);
                GameFrame.toDelete = null;
            }

            if (boardService.moveBlockAtOnce()) {
                GameFrame.setPeriodInterval();
            }
            GameFrame.toDelete = boardService.getArrayFullLines();
            if (GameFrame.toDelete != null && !GameFrame.toDelete.isEmpty()) {
                int deletedLines = GameFrame.toDelete.size();
                int clock = GameFrame.periodInterval;
    
                scoreService.updateScore(deletedLines, clock);
                // 줄 사라질 때 쓸 이펙트
            }
        }
        else if (keyCode == keyMap.get(KeyType.LEFT)) {
            boardService.moveBlockLeft();
        }
        else if (keyCode == keyMap.get(KeyType.RIGHT)) {
            boardService.moveBlockRight();
        }
        else if (keyCode == keyMap.get(KeyType.DOWN)) {
            if (GameFrame.toDelete != null) {
                boardService.deleteFullLine(GameFrame.toDelete);
                GameFrame.toDelete = null;
            }

            if (boardService.moveBlockDown()) {
                GameFrame.setPeriodInterval();
            }
            GameFrame.toDelete = boardService.getArrayFullLines();
            if (GameFrame.toDelete != null && !GameFrame.toDelete.isEmpty()) {
                int deletedLines = GameFrame.toDelete.size();
                int clock = GameFrame.periodInterval;
    
                scoreService.updateScore(deletedLines, clock);
                // 줄 사라질 때 쓸 이펙트
            }
        }
        else if (keyCode == keyMap.get(KeyType.ROTATE)) {
            boardService.rotate();
        }
        else if (keyCode == keyMap.get(KeyType.PAUSE)) {
            boardService.switchPause();
            new PauseFrame();
        }
    }
}
