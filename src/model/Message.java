package model;

/**
 * 本文
 */
public record Message(String value) {
    /**
     * 6文字以上のメッセージについて
     * 5文字までカットして残りを'…'に置き換えた値を生成する
     * @return カット済みのメッセージ
     */
    public String withEllipsis() {
        if (value.length() >= 6) {
            return value.substring(0, 5) + "…";
        } else {
            return value;
        }
    }
}
