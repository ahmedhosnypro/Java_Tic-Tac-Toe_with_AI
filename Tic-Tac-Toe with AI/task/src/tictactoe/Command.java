package tictactoe;

public enum Command {
    STARTEASYEASY("START EASY  EASY", "EASY", "EASY"),
    STARTEASYUSER("START EASY USER", "EASY", "USER"),
    STARTUSEREASY("START USER EASY", "USER", "EASY"),
    STARTUSERUSER("START USER USER", "USER", "USER"),
    EXIT("EXIT", "", ""),
    NOTCOMMAND("NOTCOMMAND", "", "");

    private String value;
    private String fstPlayer;
    private String sndPlayer;

    Command(String value, String fstPlayer, String sndPlayer){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getFstPlayer() {
        return fstPlayer;
    }
    public void setFstPlayer(String fstPlayer) {
        this.fstPlayer = fstPlayer;
    }

    public String getSndPlayer() {
        return sndPlayer;
    }
    public void setSndPlayer(String sndPlayer) {
        this.sndPlayer = sndPlayer;
    }


}
