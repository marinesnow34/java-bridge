package bridge.model;

import bridge.view.InputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    List<String> answerMove;
    List<String> playerMove;
    boolean life;

    public BridgeGame(Bridge gameBridge) {
        this.answerMove = gameBridge.getBridge();
        this.playerMove = new ArrayList<>();
        this.life = true;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move() {
        String step = InputView.readMoving();
        playerMove.add(step);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.playerMove = new ArrayList<>();
    }

    public List<List<String>> getGameMap() {
        List<String> upMap = new ArrayList<>();
        List<String> downMap = new ArrayList<>();
        for (int index = 0; index < playerMove.size(); index++) {
            if (answerMove.get(index).equals("U")) {
                downMap.add(" ");
                if (answerMove.get(index).equals(playerMove.get(index))) {
                    upMap.add("O");
                } else {
                    upMap.add("X");
                    this.life = false;
                }
            } else {
                upMap.add(" ");
                if (answerMove.get(index).equals(playerMove.get(index))) {
                    downMap.add("O");
                } else {
                    downMap.add("X");
                    this.life = false;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(upMap, downMap));
    }

    public boolean isLife() {
        return this.life;
    }

    public boolean isEnd() {
        return answerMove.equals(playerMove);
    }
}
