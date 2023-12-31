/**
 *   Sniffer - Analyze the history of Android code smells at scale.
 *   Copyright (C) 2019 Sarra Habchi
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package fr.inria.sniffer.tracker.analysis.persistence.queries;

import fr.inria.sniffer.tracker.analysis.model.Commit;
import fr.inria.sniffer.tracker.analysis.model.GitChangedFile;
import fr.inria.sniffer.tracker.analysis.model.GitDiff;
import fr.inria.sniffer.tracker.analysis.model.GitRename;

public interface CommitQueries {
    /**
     * Generate a statement inserting the commit into the persistence.
     *
     * @param projectId The project identifier.
     * @param commit    The commit to insert.
     * @param diff      {@link GitDiff} for this commit.
     * @return The generated insertion statement.
     */
    String commitInsertionStatement(int projectId, Commit commit, GitDiff diff);

    /**
     * Generate a statement inserting a {@link GitRename} into the persistence.
     *
     * @param projectId The project identifier.
     * @param commitSha Sha1 of the commit to link.
     * @param rename    {@link GitRename} instance to persist.
     * @return The generated insertion statement.
     */
    String fileRenameInsertionStatement(int projectId, String commitSha, GitRename rename);

    /**
     * Query the identifier of a commit.
     *
     * @param projectId Project to look into.
     * @param sha       Commit sha.
     * @return The generated query statement.
     */
    String idFromShaQuery(int projectId, String sha);

    /**
     * Query the sha1 of a commit.
     *
     * @param projectId Project to look into.
     * @param ordinal   Commit ordinal in the project.
     * @return The generated query statement.
     */
    String shaFromOrdinalQuery(int projectId, int ordinal);

    /**
     * Query the identifier of a commit.
     *
     * @param projectId   Project to look into.
     * @param sha         Commit sha.
     * @param paprikaOnly Only return a commit analyzed by paprika.
     * @return The generated query statement.
     */
    String idFromShaQuery(int projectId, String sha, boolean paprikaOnly);

    /**
     * Query the sha1 of a commit.
     *
     * @param projectId   Project to look into.
     * @param ordinal     Commit ordinal in the project.
     * @param paprikaOnly Only return a commit analyzed by paprika.
     * @return The generated query statement.
     */
    String shaFromOrdinalQuery(int projectId, int ordinal, boolean paprikaOnly);

    /**
     * Returns the sha1 of the last project's commit.
     *
     * @param projectId Project to look into.
     * @return The generated query statement.
     */
    String lastProjectCommitShaQuery(int projectId);

    /**
     * Returns the sha1 of the last project's commit.
     *
     * @param projectId   Project to look into.
     * @param paprikaOnly Only return a commit analyzed by paprika.
     * @return The generated query statement.
     */
    String lastProjectCommitShaQuery(int projectId, boolean paprikaOnly);

    /**
     * Returns the id of the commit merged into this one, if exists.
     *
     * @param projectId Project to look into.
     * @param commit    The commit to look on.
     * @return The generated query statement.
     */
    String mergedCommitIdQuery(int projectId, Commit commit);

    /**
     * Return the id of the project holding the given commit sha.
     *
     * @param sha Commit sha.
     * @return The generated query statement.
     */
    String projectIdFromShaQuery(String sha);

    /**
     * Update all the commits of the given project with the elements size
     * from the given table.
     *
     * @param projectId          the project to update.
     * @param paprikaResultsPath The table to use.
     * @return The generated query statement.
     */
    String updateCommitSizeQuery(int projectId, String paprikaResultsPath);

    /**
     * Generate a statement inserting a {@link GitChangedFile} into the persistence.
     *
     * @param projectId   The project identifier.
     * @param commitSha   Sha1 of the commit to link.
     * @param changedFile {@link GitRename} instance to persist.
     * @return The generated insertion statement.
     */
    String fileChangedInsertionStatement(int projectId, String commitSha, GitChangedFile changedFile);

    String updateCommitSize(int projectId, String paprikaResultsPath);
}
