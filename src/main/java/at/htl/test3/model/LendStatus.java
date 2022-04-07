package at.htl.test3.model;

public enum LendStatus {
    AVAILABLE('A'), LENT('L');

    public final char letter;
    LendStatus(char l) {
        this.letter = l;
    }

    public LendStatus fromLetter(char l) {
        for (LendStatus ls : LendStatus.values()) {
            if (ls.letter == l)
                return ls;
        }
        return null;
    }

}
