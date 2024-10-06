package fileIO

import Dependency
import java.io.File

class PlainTextAndroidDependencyProvider : DependencyInputProvider {
    override fun provideDependencies(): List<Dependency> {
        val inputFile = File(FileIOConstants.INPUT_FILE)
        if (inputFile.exists()) {
            val dependencyList = mutableListOf<Dependency>()
            inputFile.bufferedReader().apply {
                lines().forEach { dependencyLine ->
                    val splits = dependencyLine.split(':')
                    if (splits.size >= 3) {
                        dependencyList.add(Dependency(group = splits[0], artifactId = splits[1], version = splits[2]))
                    }
                }
                close()
            }
            return dependencyList
        }
        println("Input File does not exist")
        return emptyList()
    }
}