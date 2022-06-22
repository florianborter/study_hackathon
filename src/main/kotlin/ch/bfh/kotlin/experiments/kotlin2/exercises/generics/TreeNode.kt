package ch.bfh.kotlin.experiments.kotlin2.exercises.generics

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

sealed class TreeNode {
    data class Node<T>(val data: T, var left: TreeNode = Terminal, var right: TreeNode = Terminal) : TreeNode() {

        override fun toString() = "(Data: $data, Left: $left, Right:$right)"

        fun isLeaf() =
            left == TreeNode.Terminal && right == TreeNode.Terminal

        fun addLeft(node: TreeNode) {
            left = node
        }

        fun addRight(node: TreeNode) {
            this.right = node
        }
    }

    object Terminal : TreeNode() {
        override fun toString() = "null"
    }
}

val emptyTree = TreeNode.Terminal


