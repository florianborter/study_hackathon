/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.collections.projectmanager

import java.time.LocalDate

/** Every project needs to have a deadline.  */
interface Project {
    val deadline: LocalDate
}

/** A simple project contains an ID, a description, and a deadline.  */
data class SimpleProject(val projectId: Int, override val deadline: LocalDate, val description: String) : Project,
    Comparable<SimpleProject> {

    override fun compareTo(other: SimpleProject): Int {
        return deadline.compareTo(other.deadline)
    }
}