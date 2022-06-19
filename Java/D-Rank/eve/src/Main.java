import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try(Scanner sc = new Scanner(System.in);) {
            String[] basedData = sc.nextLine().split(" ");

            // 予約されている席数
            int reservedSheetsCount = Integer.parseInt(basedData[0]);
            // 縦席数
            int verticalSheetsCount = Integer.parseInt(basedData[1]);
            // 横席数
            int horizontalSheetsCount = Integer.parseInt(basedData[2]);
            // 見やすい席p
            int visibilitySheetPositionP = Integer.parseInt(basedData[3]);
            // 見やすい席q
            int visibilitySheetPositionQ = Integer.parseInt(basedData[4]);

            // 座席配列 予約済みシートには1を、空きシートには0を設定
            boolean[][] sheets = new boolean[verticalSheetsCount][horizontalSheetsCount];
            // sheets初期化処理 予約済みシートはfalse, 空きシートはtrue
            for (int verticalIndex = 0; verticalIndex < verticalSheetsCount; verticalIndex++) {
                for (int horizontalIndex = 0; horizontalIndex < horizontalSheetsCount; horizontalIndex++) {
                    sheets[verticalIndex][horizontalIndex] = true;
                }
            }

            // 予約情報を取得
            for (int i = 0; i < reservedSheetsCount; i++) {
                // 予約情報をもとにsheetsへの情報設定
                String reservedInfo = sc.nextLine();
                String[] reservedInfoArray = reservedInfo.split(" ");
                sheets[Integer.parseInt(reservedInfoArray[0])][Integer.parseInt(reservedInfoArray[1])] = false;
            }

            // マンハッタン距離マップ<座標, マンハッタン距離>
            Map<String, Integer> manhattanMap = new HashMap<String, Integer>();

            // 最小マンハッタン距離
            int minManhattanDistance = 100000;

            // マンハッタン距離算出
            for (int verticalIndex = 0; verticalIndex < verticalSheetsCount; verticalIndex++) {
                for (int horizontalIndex = 0; horizontalIndex < horizontalSheetsCount; horizontalIndex++) {
                    if(sheets[verticalIndex][horizontalIndex]){
                        // マンハッタン距離算出
                        int manhattanDistance = Math.abs(visibilitySheetPositionP - verticalIndex) + Math.abs(visibilitySheetPositionQ - horizontalIndex);

                        // HashMap用キー
                        String key = String.valueOf(verticalIndex) + " " + String.valueOf(horizontalIndex);
                        manhattanMap.put(key, manhattanDistance);

                        // 最小マンハッタン距離がよりも算出マンハッタン距離が小さい場合、置き換える。
                        if (minManhattanDistance > manhattanDistance) {
                            minManhattanDistance = manhattanDistance;
                        }
                    }
                }
            }

            // 最小マンハッタン距離座標リスト
            List<String> minManhattanDisList = new ArrayList<String>();

            // 最小マンハッタン距離絞り込み
            for (String key : manhattanMap.keySet()) {
                if (manhattanMap.get(key) == minManhattanDistance) {
                    minManhattanDisList.add(key);
                }
            }

            minManhattanDisList.stream().forEach((data) -> System.out.println(data));

        }
    }
}
