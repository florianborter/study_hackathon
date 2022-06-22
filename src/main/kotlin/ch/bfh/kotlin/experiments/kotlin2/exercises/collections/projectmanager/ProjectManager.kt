/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.collections.projectmanager

import java.time.LocalDate
import java.util.*

class ProjectManager<T : Project> {

    private val projectQueue = PriorityQueue(10)
    { t1: T, t2: T -> t1.deadline.compareTo(t2.deadline) }

    val numberOfPendingProjects: Int
        get() = projectQueue.size

    val earliestDeadline: Optional<LocalDate>
        get() = if (projectQueue.isEmpty()) Optional.empty() else Optional.of(projectQueue.peek().deadline)

    fun addProject(project: T) {
        projectQueue.add(project)
    }

    fun nextProject(): Optional<T> {
        return if (projectQueue.isEmpty()) Optional.empty() else Optional.of(projectQueue.poll())
    }

    val hasPendingProjects: Boolean
        get() = !projectQueue.isEmpty()

}