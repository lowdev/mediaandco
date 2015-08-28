package eu.entropy.mediapedia.company;

public enum MediaType {
    TV("tv"),
    RADIO("radio"),
    PAPER("paper");

    private final String folderName;

    MediaType(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return this.folderName;
    }
}
