package repo;

import model.Card;
import model.Card.Celebration;
import model.Card.Favor;
import model.Card.Greeting;
import model.Message;
import model.Status;
import model.celebration.Type;
import model.favor.Deadline;

import java.time.LocalDateTime;
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
                new Celebration(new Message("よっ部長！"), Status.READ, Type.PROMOTION),
                new Favor(new Message("あんぱん買ってこい！"), Status.SENT, new Deadline(LocalDateTime.of(2021, 9, 23, 15, 0)))
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
        // TODO 2
        return myCards.stream().filter(c -> c instanceof Greeting)
                .collect(Collectors.toList());
    }

    /**
     * 「既読」の「お祝い」カードをすべて取得する
     *
     * @return 「既読」の「挨拶」カード一覧
     */
    public List<Card> findReadCelebrations() {
        // TODO 3
        return myCards.stream().filter(c -> c instanceof Celebration greeting && greeting.status().equals(Status.READ))
                .collect(Collectors.toList());
    }

    /**
     * 「未読」のカードを1件取得する
     *
     * @return 取得した「未読」のカード
     */
    public Optional<Card> findSentCard() {
        // TODO 4
        return myCards.stream().filter(c -> c.status().equals(Status.SENT))
                .findFirst();
    }

    /**
     * カードプレビュー画面に表示する文を取得する
     *
     * @param card プレビュー表示したいカード
     * @return プレビュー画面用文
     */
    public String getDisplayText(Card card) {
        // TODO 5
        return switch (card) {
            case Greeting g -> """
                    誰かからメッセージが来たよ
                    %s"
                    """.formatted(g.message().withEllipsis());
            case Celebration c -> """
                    おめでとう！お祝いが届いたよ
                    中身は開けてからのお楽しみ！
                    """;
            case Favor f -> "お願い事を頼まれたみたいだよ";
        };
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
        // TODO: 7
        switch (card) {
            case Greeting g -> callWhenGreeting(g);
            case Celebration c -> callWhenCelebration(c);
            case Favor f -> callWhenFavor(f);
        }
    }

    /**
     * 「未読」のカードを「既読」にして返す
     * (「未読」以外の場合は何もしない)
     * @param card 更新したいカード
     * @return 「既読」のカード
     */
    private Card read(Card card) {
        // TODO: 8
        if (card.status().equals(Status.SENT)) {
            return switch (card) {
                case Greeting g -> new Greeting(g.message(), Status.READ);
                case Celebration c -> new Celebration(c.message(), Status.READ, c.type());
                case Favor f -> new Favor(f.message(), Status.READ, f.deadline());
            };
        } else {
            return card;
        }
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

    private void callWhenFavor(Favor favor) {
        System.out.printf("""
                お願いだ！
                %s%n""", favor.toString());
    }
}