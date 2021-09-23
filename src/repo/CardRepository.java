package repo;

import model.Message;
import model.Status;
import model.celebration.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * カードの操作をするためのクラス
 */
public class CardRepository {
    /**
     * 手持ちのカード一覧
     * (今日はこのリストを更新していくよ！)
     */
    List<Card> myCards = new ArrayList<>();

    /**
     * 6枚のデフォルトカードを用意する
     * (ハンズオンのために何枚か適当にカードを登録しておくよ！)
     */
    public void registerDefaultCards() {
        myCards.addAll(List.of(
                new Greeting(new Message("おはようございまーす"), Status.EDITING),
                new Greeting(new Message("お元気ですか？"), Status.SENT),
                new Celebration(new Message("はぴば"), Status.EDITING, Type.BIRTHDAY),
                new Celebration(new Message("結婚おめでとう"), Status.SENT, Type.WEDDING),
                new Celebration(new Message("よっ部長！"), Status.READ, Type.PROMOTION)
        ));
    }

    /**
     * カードを複数枚登録する
     *
     * @param additionalCards 登録したいカード
     */
    public void registerAdditionalCards(List<Card> additionalCards) {
        myCards.addAll(additionalCards);
    }

    /**
     * 手持ちの「挨拶」カードをすべて取得する
     *
     * @return 「挨拶」カード一覧
     */
    public List<Card> findGreetings() {
        // TODO 2 仕様を満たした実装をしよう
        return List.of();
    }

    /**
     * 「既読」の「お祝い」カードをすべて取得する
     *
     * @return 「既読」の「挨拶」カード一覧
     */
    public List<Card> findReadCelebrations() {
        // TODO 3 もっと簡潔に書けるよ
        return myCards.stream().filter(c -> c instanceof Celebration)
                .filter(c -> c.status().equals(Status.READ))
                .collect(Collectors.toList());
    }

    /**
     * 「未読」のカードを1件取得する
     *
     * @return 取得した「未読」のカード
     */
    public Optional<Card> findSentCard() {
        // TODO 4 仕様を満たした実装をしよう
        return null;
    }

    /**
     * カードプレビュー画面に表示する文を取得する
     *
     * @param card プレビュー表示したいカード
     * @return プレビュー画面用文
     */
    public String getDisplayText(Card card) {
        // TODO 5 Switch式で書き換えてみよう
        if (card instanceof Greeting) {
            Greeting greeting = (Greeting) card;
            return """
                    誰かからメッセージが来たよ
                    %s"
                    """.formatted(greeting.message().withEllipsis());
        } else if (card instanceof Celebration) {
            return """
                    おめでとう！お祝いが届いたよ
                    中身は開けてからのお楽しみ！
                    """;
        } else {
            return "何かがおかしい！";
        }
    }

    /**
     * 手持ちのカードをすべてコンソール出力する
     */
    public void outputAllMyCards() {
        myCards.forEach(this::outputCardContent);
    }

    /**
     * 手持ちのカードのうち「未読」なものをすべて「既読」にする
     */
    public void readAllMyCards() {
        myCards = myCards.stream().map(this::read).collect(Collectors.toList());
    }

    /**
     * どのカード種別だったかとカードの中身をコンソール出力する
     *
     * @param card 出力対象のカード
     */
    public void outputCardContent(Card card) {
        // TODO: 7 仕様を満たした実装をしよう
    }

    /**
     * 「未読」のカードを「既読」にして返す
     * (「未読」以外の場合は何もしない)
     *
     * @param card 更新したいカード
     * @return 「既読」のカード
     */
    private Card read(Card card) {
        // TODO: 8 仕様を満たした実装をしよう
        return null;
    }

    private void callWhenGreeting(Greeting greeting) {
        System.out.printf("""
                挨拶だ！
                %s%n""", greeting.toString());
    }

    private void callWhenCelebration(Celebration celebration) {
        System.out.printf("""
                お祝いだ！
                %s%n""", celebration.toString());
    }
}