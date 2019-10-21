package seedu.algobase.model.searchrule.problemsearchrule;

import java.util.function.Predicate;

import seedu.algobase.model.problem.Difficulty;
import seedu.algobase.model.problem.Problem;

/**
 * Tests that a {@code Problem}'s {@code Difficulty} is in the range [{@code lowerBound}, {@code upperBound}].
 */
public class DifficultyIsInRangePredicate implements Predicate<Problem> {

    public static final DifficultyIsInRangePredicate DEFAULT_DIFFICULTY_PREDICATE =
        new DifficultyIsInRangePredicate() {
            @Override
            public boolean test(Problem problem) {
                return true;
            }
        };
    private static final double DEFAULT_BOUND = -1.0;
    private final double lowerBound;
    private final double upperBound;

    public DifficultyIsInRangePredicate(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    private DifficultyIsInRangePredicate() {
        this.lowerBound = DEFAULT_BOUND;
        this.upperBound = DEFAULT_BOUND;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    @Override
    public boolean test(Problem problem) {
        Difficulty difficulty = problem.getDifficulty();
        return difficulty.value >= lowerBound && difficulty.value <= upperBound;
    }
}
