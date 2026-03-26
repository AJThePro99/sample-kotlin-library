package org.example.kaifuzzer

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Public API of our compiler fuzzer library.
 */
class KaiFuzzer(private val name: String = "Default Fuzzer") {
    
    suspend fun startFuzzing(runs: Int) {
        println("[$name] Starting fuzzing session for $runs runs...")
        
        // We can use coroutines safely here!
        coroutineScope {
            for (i in 1..runs) {
                launch {
                    internalWorkerFunction(i)
                }
            }
        }
        
        println("[$name] Fuzzing session completed.")
    }

    /**
     * This function is marked 'internal'. 
     * It is used by our library but cannot be seen or called by the user's project!
     * This prevents API breakage if we change it later.
     */
    internal suspend fun internalWorkerFunction(runId: Int) {
        delay(100L) // Simulate some work
        println("[$name]   -> Worker finished run #$runId")
    }
}
