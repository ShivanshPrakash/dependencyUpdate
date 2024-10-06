package fileIO

import java.io.File

class DefaultOutputHandler(private val outputFilePath: String) : OutputHandler {
    override fun output(dependencies: List<String>) {
        val output = File(outputFilePath)
        output.bufferedWriter().apply {
            dependencies.forEach { appendLine(it) }
            close()
        }
    }
}