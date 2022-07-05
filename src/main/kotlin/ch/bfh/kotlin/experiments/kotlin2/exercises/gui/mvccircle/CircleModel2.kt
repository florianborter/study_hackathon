package ch.bfh.kotlin.experiments.kotlin2.exercises.gui.mvccircle

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.util.*

class CircleModel2(x: Double, y: Double, r: Double) {

    private val support = PropertyChangeSupport(this)

    var centerX = x
        set(centerX) {
            val oldX = field
            field = centerX
            support.firePropertyChange("X changed", oldX, field)

        }

    var centerY = y
        set(centerY) {
            val oldY = field
            field = centerY
            support.firePropertyChange("Y changed", oldY, field)
        }

    var radius = r
        set(radius) {
            val oldRadius = field
            field = radius
            support.firePropertyChange("Radius changed", oldRadius, field)
        }

    fun addObserver(l: PropertyChangeListener) {
        support.addPropertyChangeListener(l)
    }

    fun removeObserver(l: PropertyChangeListener) {
        support.removePropertyChangeListener(l)
    }
}