package com.sadri.thestarwars.core.testing.ui

/**
 * The {@code AndroidTestCase} and {@code AndroidSuspendTestCase} classes provide a structured way to define Android test cases using the Given-When-Then pattern.
 * The {@code given()} method sets up the initial state of the test.
 * The {@code whenever()} method performs the action being tested.
 * The {@code then()} method verifies the expected results.
 * The {@code AndroidSuspendTestCase} class allows suspend functions to be used in the different phases of the test case, making it suitable for testing asynchronous code utilizing Coroutines.
 */
class AndroidTestCase {

    var given: (() -> Unit)? = null
    var whenever: (() -> Unit)? = null
    var then: (() -> Unit)? = null

    fun given(block: () -> Unit) {
        given = block
    }

    fun whenever(block: () -> Unit) {
        whenever = block
    }

    fun <T> whenever(input: T, block: T.() -> Unit) {
        whenever = {
            block(input)
        }
    }

    fun then(block: () -> Unit) {
        then = block
    }
}

class AndroidSuspendTestCase {

    var given: (suspend () -> Unit)? = null
    var whenever: (suspend () -> Unit)? = null
    var then: (suspend () -> Unit)? = null

    fun given(block: suspend () -> Unit) {
        given = block
    }

    fun whenever(block: suspend () -> Unit) {
        whenever = block
    }

    fun <T> whenever(input: T, block: suspend T.() -> Unit) {
        whenever = {
            block(input)
        }
    }

    fun then(block: suspend () -> Unit) {
        then = block
    }
}