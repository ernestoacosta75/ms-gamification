package microservices.book.gamification.domain.model;

public enum BadgeType {
    // Badges depending on the score
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),

    // Other badges won for different conditions
    FIRST_WON("First time"),
    LUCKY_NUMBER("Lucky number");

    private final String description;

    BadgeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
