package controller.management;

import modules.sports.Sport;
import modules.sports.SportEnum;

public class SportFactory {

    private static final String NATATION = "Natation";
    private static final String TENNIS = "Tennis";
    private static final String MUSCULATION = "Musculation";
    private static final String JOGGING = "Jogging";
    private static final String ESCALADE = "Escalade";
    private static final String CYCLISME = "Cyclisme";

    public static Sport createSport(String sportChoisis) throws IllegalArgumentException {
        return switch (sportChoisis) {
            case NATATION -> new Sport(SportEnum.NATATION);
            case TENNIS -> new Sport(SportEnum.TENNIS);
            case MUSCULATION -> new Sport(SportEnum.MUSCULATION);
            case JOGGING -> new Sport(SportEnum.JOGGING);
            case ESCALADE -> new Sport(SportEnum.ESCALADE);
            case CYCLISME -> new Sport(SportEnum.CYCLISME);
            default -> throw new IllegalArgumentException("Unknown operation type : " + sportChoisis);
        };
    }
}
