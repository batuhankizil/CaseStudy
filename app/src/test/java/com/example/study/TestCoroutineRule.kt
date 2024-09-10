package com.example.study

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
/**
 * @see <a href="https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md#replace-testcoroutinescopecleanuptestcoroutines-with-runtest">Clean Up</a>
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun runTestAndCleanUp(
    coroutineDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    block: suspend TestScope.() -> Unit,
) = runTest(coroutineDispatcher) {
    try {
        block()
    } finally {
        val timeAfterTest = currentTime
        advanceUntilIdle() // run the remaining tasks
        assertEquals(timeAfterTest, currentTime) // will fail if there were tasks scheduled at a later moment
    }
}