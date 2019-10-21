package seedu.algobase.storage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.algobase.commons.exceptions.IllegalValueException;
import seedu.algobase.logic.parser.ParserUtil;
import seedu.algobase.model.AlgoBase;
import seedu.algobase.model.plan.Plan;
import seedu.algobase.model.plan.PlanDescription;
import seedu.algobase.model.plan.PlanName;
import seedu.algobase.model.task.Task;

/**
 * Jackson-friendly version of {@link Plan}.
 */
class JsonAdaptedPlan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Plan's %s field is missing!";

    private final String id;
    private final String name;
    private final String description;
    private final String startDate;
    private final String endDate;
<<<<<<< HEAD
    private final List<JsonAdaptedTask> taskList = new ArrayList<>();
=======
    private final List<JsonAdaptedTask> tasked = new ArrayList<>();
>>>>>>> Hard-code json task for fields in json plan

    /**
     * Constructs a {@code JsonAdaptedPlan} with the given Plan details.
     */
    @JsonCreator
<<<<<<< HEAD
    public JsonAdaptedPlan(@JsonProperty("id") String id,
                           @JsonProperty("name") String name,
                           @JsonProperty("description") String description,
                           @JsonProperty("startDate") String startDate,
                           @JsonProperty("endDate") String endDate,
                           @JsonProperty("taskList") List<JsonAdaptedTask> taskList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        if (taskList != null) {
            this.taskList.addAll(taskList);
=======
    public JsonAdaptedPlan(@JsonProperty("planName") String planName,
                           @JsonProperty("planDescription") String planDescription,
                           @JsonProperty("startDate") String startDate,
                           @JsonProperty("endDate") String endDate,
                           @JsonProperty("tasked") List<JsonAdaptedTask> tasked) {
        this.planName = planName;
        this.planDescription = planDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        if (tasked != null) {
            this.tasked.addAll(tasked);
>>>>>>> Hard-code json task for fields in json plan
        }
    }

    /**
     * Converts a given {@code Plan} into this class for Jackson use.
     */
    public JsonAdaptedPlan(Plan plan) {
<<<<<<< HEAD
        id = Long.toString(plan.getId());
        name = plan.getPlanName().fullName;
        description = plan.getPlanDescription().value;
        startDate = plan.getStartDate().format(ParserUtil.FORMATTER);
        endDate = plan.getEndDate().format(ParserUtil.FORMATTER);
        taskList.addAll(plan.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
=======
        planName = plan.getPlanName().fullName;
        planDescription = plan.getPlanDescription().value;
        startDate = plan.getStartDate().format(Plan.FORMATTER);
        endDate = plan.getEndDate().format(Plan.FORMATTER);
        tasked.addAll(plan.getTasks().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
>>>>>>> Hard-code json task for fields in json plan
    }

    /**
     * Converts this Jackson-friendly adapted Plan object into the model's {@code Plan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Plan.
     */
<<<<<<< HEAD
    public Plan toModelType(AlgoBase algoBase) throws IllegalValueException {
        final List<Task> tasks = new ArrayList<>();
        for (JsonAdaptedTask task : taskList) {
            tasks.add(task.toModelType(algoBase));
=======
    public Plan toModelType() throws IllegalValueException {
        final List<Task> planTasks = new ArrayList<>();
        for (JsonAdaptedTask task : tasked) {
            planTasks.add(task.toModelType());
>>>>>>> Hard-code json task for fields in json plan
        }

        final long modelId = retrieveId(id);
        final PlanName modelName = retrieveName(name);
        final PlanDescription modelDescription = retrieveDescription(description);
        final LocalDate modelStartDate = retrieveDate(startDate);
        final LocalDate modelEndDate = retrieveDate(endDate);
        final Set<Task> modelTasks = new HashSet<>(tasks);

        return new Plan(modelId, modelName, modelDescription, modelStartDate, modelEndDate,
                modelTasks);
    }

    /**
     * Converts an id in string format to a long number.
     *
     * @param id id in String format.
     * @return id in long format.
     * @throws IllegalValueException if string format is invalid.
     */
    public long retrieveId(String id) throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }

        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }
    }

    /**
     * Converts a name in string format to a PlanName Object.
     *
     * @param name name in string format.
     * @return the corresponding PlanName Object.
     * @throws IllegalValueException if string format is invalid.
     */
    public PlanName retrieveName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            PlanName.class.getSimpleName()));
        }
        if (!PlanName.isValidName(name)) {
            throw new IllegalValueException(PlanName.MESSAGE_CONSTRAINTS);
        }
        return new PlanName(name);
    }

    /**
     * Converts a description in string format to a PlanDescription Object.
     *
     * @param description description in string format.
     * @return the corresponding PlanDescription Object.
     * @throws IllegalValueException if string format is invalid.
     */
    public PlanDescription retrieveDescription(String description) throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            PlanDescription.class.getSimpleName()));
        }

        if (PlanDescription.isDefaultDescription(description)) {
            return PlanDescription.DEFAULT_PLAN_DESCRIPTION;
        }

        if (!PlanDescription.isValidDescription(description)) {
            throw new IllegalValueException(PlanDescription.MESSAGE_CONSTRAINTS);
        }

        return new PlanDescription(description);
    }

    /**
     * Converts a date in string format to a LocalDateTime Object.
     *
     * @param date date in string format.
     * @return the corresponding LocalDateTime Object.
     * @throws IllegalValueException if {@code date} is invalid.
     */
    private LocalDate retrieveDate(String date) throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        try {
            return LocalDate.parse(date, ParserUtil.FORMATTER);
        } catch (DateTimeException e) {
            throw new IllegalValueException(ParserUtil.DATE_CONSTRAINTS);
        }
    }
}
