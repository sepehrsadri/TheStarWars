package com.sadri.thestarwars.core.testing.ui

import kotlinx.coroutines.runBlocking

/**
 * This framework provides a set of inline functions that enforce the Given-When-Then structure for Android test cases, making them more readable and maintainable.
 * The {@code androidTestCase()} function takes a lambda expression that configures the {@code given()}, {@code whenever()}, and {@code then()} methods of the {@code AndroidTestCase} instance.
 * The {@code androidCoroutineTestCase()} function takes an additional {@code context} argument, which specifies the coroutine context in which the {@code AndroidSuspendTestCase} should be executed.
 * Both functions invoke the {@code given()}, {@code whenever()}, and {@code then()} methods of the test case instance, and throw a {@code NotImplementedError} if the {@code then()} or {@code whenever()} methods are not implemented.
 */
inline fun androidTestCase(testCase: AndroidTestCase.() -> Unit) {
    AndroidTestCase().apply(testCase).apply {
        given?.invoke()
        whenever?.invoke() ?: throw NotImplementedError("You need to implement whenever")
        then?.invoke() ?: throw NotImplementedError("You need to implement then")
    }
}

inline fun androidCoroutineTestCase(
    crossinline testCase: AndroidSuspendTestCase.() -> Unit,
) {
    runBlocking {
        AndroidSuspendTestCase().apply(testCase).apply {
            given?.invoke()
            whenever?.invoke() ?: throw NotImplementedError("You need to implement whenever")
            then?.invoke() ?: throw NotImplementedError("You need to implement then")
        }
    }
}