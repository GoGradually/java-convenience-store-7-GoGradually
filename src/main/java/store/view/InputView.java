package store.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public boolean askUserTF(String message) {
        while (true) {
            System.out.println(message);
            String result = readLine();
            if (result.equals("Y")) {
                return true;
            } else if (result.equals("N")) {
                return false;
            }
            System.out.println("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
        }
    }
}
