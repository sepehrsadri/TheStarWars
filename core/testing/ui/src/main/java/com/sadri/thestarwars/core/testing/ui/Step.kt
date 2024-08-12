package com.sadri.thestarwars.core.testing.ui

/**
 * Defines a step in a UI test scenario with a descriptive message.
 *
 * This function helps organize UI tests into smaller, more manageable steps,
 * especially when dealing with long and complex scenarios. It prints the
 * provided description to the console before executing the given action.
 *
 * This approach complements the use of "given", "when", and "then" blocks
 * by providing an additional level of granularity and readability within
 * each section of the test.
 *
 * @param description A descriptive message explaining the purpose of this step.
 * @param action The code to be executed as part of this step.
 */
internal fun step(description: String, action: () -> Unit) {
    println(description)
    action()
}