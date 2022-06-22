package ch.bfh.kotlin.experiments.kotlin2.exercises.collections.projectmanager


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class ProjectManagerTest {
    @Test
    fun testEmptyProjectManager() {
        val pm = ProjectManager<SimpleProject>()
        assertFalse(pm.hasPendingProjects)
        assertEquals(0, pm.numberOfPendingProjects)
        assertFalse(pm.nextProject().isPresent)
    }

    private fun createProjectManagerWithProjects(): ProjectManager<SimpleProject> {
        val pm = ProjectManager<SimpleProject>()
        pm.addProject(
            SimpleProject(
                1,
                LocalDate.of(2021, 8, 23),
                "Implement Website for Customer X"
            )
        )
        pm.addProject(
            SimpleProject(
                2,
                LocalDate.of(2021, 9, 16),
                "Pick project topic for fall semester"
            )
        )
        pm.addProject(
            SimpleProject(
                3,
                LocalDate.of(2021, 7, 3),
                "Prepare for OOP2 exam"
            )
        )
        return pm
    }

    @Test
    fun testProjectManagerCanAddProjects() {
        val pm = createProjectManagerWithProjects()
        assertTrue(pm.hasPendingProjects)
        assertEquals(3, pm.numberOfPendingProjects)
        assertTrue(pm.nextProject().isPresent)
    }

    @Test
    fun testProjectManagerDoesEarliestDeadlineFirst() {
        val pm = createProjectManagerWithProjects()
        var optProj = pm.nextProject()
        assertTrue(optProj.isPresent)
        assertEquals(3, optProj.get().projectId) // project 3
        optProj = pm.nextProject()
        assertTrue(optProj.isPresent)
        assertEquals(1, optProj.get().projectId) // project 1
        optProj = pm.nextProject()
        assertTrue(optProj.isPresent)
        assertEquals(2, optProj.get().projectId) // project 2
        optProj = pm.nextProject()
        assertFalse(optProj.isPresent)
        assertEquals(0, pm.numberOfPendingProjects)
        assertFalse(pm.hasPendingProjects)
    }

    @Test
    fun testProjectManagerCheckEarliestDeadline() {
        val pm = createProjectManagerWithProjects()
        var optLocalDate: Optional<LocalDate> = pm.earliestDeadline
        assertTrue(optLocalDate.isPresent)
        assertEquals(LocalDate.of(2021, 7, 3), optLocalDate.get())
        pm.nextProject()
        optLocalDate = pm.earliestDeadline
        assertTrue(optLocalDate.isPresent)
        assertEquals(LocalDate.of(2021, 8, 23), optLocalDate.get())
        pm.nextProject()
        optLocalDate = pm.earliestDeadline
        assertTrue(optLocalDate.isPresent)
        assertEquals(LocalDate.of(2021, 9, 16), optLocalDate.get())
        pm.nextProject()
        optLocalDate = pm.earliestDeadline
        assertFalse(optLocalDate.isPresent)
    }
}