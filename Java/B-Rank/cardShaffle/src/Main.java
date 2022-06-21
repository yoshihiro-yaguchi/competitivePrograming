import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        try(Scanner sc = new Scanner(System.in);) {
            String[] basedData = sc.nextLine().split(" ");

            // カードの枚数
            int cardCount = Integer.parseInt(basedData[0]);
            // 1セットあたり個数
            int cardCountPerSet = Integer.parseInt(basedData[1]);
            // シャッフル回数
            int shaffleCount = Integer.parseInt(basedData[2]);

            // カード
            List<String> cards = new ArrayList<String>();
            for (int i = 1; i <= cardCount; i++) {
                cards.add(String.valueOf(i));
            }

            // シャッフル
            for (int i = 0; i < shaffleCount; i++) {
                // カード分割
                List<List<String>> shaffleCards = chunk(cards, cardCountPerSet);
                shaffleCards = shaffler(shaffleCards);
                cards = new ArrayList<String>();
                for (List<String> list : shaffleCards) {
                    for (String list2 : list) {
                        cards.add(list2);
                    }
                }
            }

            // 出力
            cards.stream().forEach(parentData -> {
                System.out.println(parentData);
            });
        }
    }

    // シャッフルする
    private static List<List<String>> shaffler(List<List<String>> list) {
        int listIndex = list.size() - 1;

        List<List<String>> returnList = new ArrayList<List<String>>();
        for (int i = listIndex; i >= 0; i--) {
            returnList.add(list.get(i));
        }

        return returnList;
    }

    // 指定サイズごとにリストを分割
    private static List<List<String>> chunk(List<String> origin, int size) {
        if (origin == null || origin.isEmpty() || size <= 0) {
            return Collections.emptyList();
        }

        int block = origin.size() / size + (origin.size() % size > 0 ? 1 : 0);

        return IntStream.range(0, block)
                .boxed()
                .map(i -> {
                    int start = i * size;
                    int end = Math.min(start + size, origin.size());
                    return origin.subList(start, end);
                })
                .collect(Collectors.toList());
    }
}
