package fr.inria.sniffer.metrics.calculator;

/**
 * Created by sarra on 18/07/17.
 */
public interface DevelopersHandler {
    /**
     * Add the developer ID to developer set.
     *
     * @param developer The developer to notify
     */
    void notify(String developer);

    /**
     * Add the smell definitions to smells introduced by the developer.
     *
     * @param developer The developer ID.
     * @param smellId   The smell ID.
     */
    void notifyIntroduced(String developer, String smellId);

    /**
     * Add the smell definitions to smells refactored by the developer.
     *
     * @param developer The developer ID.
     * @param smellId   The smell ID.
     */
    void notifyRefactored(String developer, String smellId);

    /**
     * Add the smell definitions to smells deleted by the developer.
     * This means that the developer did not actually fixed the smell
     * but removed the associated source code.
     *
     * @param developer The developer ID.
     * @param smellId   The smell ID.
     */
    void notifyDeleted(String developer, String smellId);

    /**
     * Count the total number of introduced smells by the developer.
     *
     * @param developer The developer ID.
     * @return The count of introduced smells.
     */
    long countIntroduced(String developer);

    /**
     * Count the total number of refactored smells by the developer.
     *
     * @param developer The developer ID.
     * @return The count of refactored smells.
     */
    long countRefactored(String developer);

    /**
     * Count the total number of refactored smells introduced by the same developer.
     *
     * @param developer The developer ID.
     * @return The count of refactored smells.
     */
    long countSelfRefactored(String developer);

    /**
     * Count the total number of refactored smells introduced by other developers.
     *
     * @param developer The developer ID.
     * @return The count of refactored smells.
     */
    long countOtherRefactored(String developer);

    /**
     * Count the total number of deleted smells by the developer.
     *
     * @param developer The developer ID.
     * @return The count of deleted smells.
     */
    long countDeleted(String developer);

    /**
     * Count the total number of deleted smells introduced by the same developer.
     *
     * @param developer The developer ID.
     * @return The count of deleted smells.
     */
    long countSelfDeleted(String developer);

    /**
     * Count the total number of deleted smells introduced by other developers.
     *
     * @param developer The developer ID.
     * @return The count of deleted smells.
     */
    long countOtherDeleted(String developer);

    /**
     * Returns the number of synchronized developers.
     *
     * @return The number of developers
     */
    int size();

    /**
     * Returns the developer offset.
     *
     * @return Offset for the given developer.
     */
    int getOffset(String devName);

    /**
     * Returns an array containing the developers in the right offset order.
     *
     * @return An array of developers
     */
    String[] sortedDevelopers();
}
