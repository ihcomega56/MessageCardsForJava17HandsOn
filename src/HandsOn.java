import model.Card;
import model.Message;
import model.Status;
import model.celebration.Type;
import model.favor.Deadline;
import repo.CardRepository;

import java.time.LocalDateTime;
import java.util.List;

public class HandsOn {
    public static void main(String... args) {
        // はじめに6枚カードを登録しておく
        CardRepository repo = new CardRepository();
        repo.registerDefaultCards();

        // TODO: 1 好きなカードを複数枚登録しよう(ウォーミングアップ)
        repo.registerAdditionalCards(List.of(
                new Card.Celebration(new Message("Congrats!"), Status.EDITING, Type.PROMOTION),
                new Card.Favor(new Message("ご飯よろしく"), Status.SENT, new Deadline(LocalDateTime.of(2021, 9, 23, 20, 0)))));

        System.out.println("===「挨拶」カードをすべて取得する===");
        System.out.println(repo.findGreetings()); // TODO: 2 メソッドの中身を実装しよう

        System.out.println("===「既読」の「お祝い」カードをすべて取得する===");
        System.out.println(repo.findReadCelebrations()); // TODO: 3 メソッドの中身を実装しよう

        System.out.println("===「未読」のカードを1件取得し、表示用の文を取得する===");
        // TODO: 4 メソッドの中身を実装しよう
        // TODO: 5 メソッドの中身を実装しよう
        // TODO: 6 1文にしてみよう
        String randomSentNotification = repo.findSentCard().map(repo::getDisplayText)
                .orElse("メッセージがないよ…");
        System.out.println(randomSentNotification);

        repo.outputAllMyCards(); // TODO: 7 privateメソッドの中身を実装しよう

        repo.readAllMyCards(); // TODO: 8 privateメソッドの中身を実装しよう

        // TODO: 7 新しいカード種別「お願い」を追加しよう
    }
}
