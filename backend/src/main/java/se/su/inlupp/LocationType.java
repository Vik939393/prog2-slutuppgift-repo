// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

public enum LocationType {
    SMALL_AMOUNT_BERRIES, MEDIUM_AMOUNT_BERRIES, BIG_AMOUNT_BERRIES;

    public static LocationType fromString(String s) {
        return switch (s) {
            case "Small amount of berries" -> LocationType.SMALL_AMOUNT_BERRIES;
            case "Medium amount of berries" -> LocationType.MEDIUM_AMOUNT_BERRIES;
            case "Large amount of berries" -> LocationType.BIG_AMOUNT_BERRIES;
            default -> throw new IllegalStateException("Unexpected amount of berries.");
        };
    }
}
