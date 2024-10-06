package fileIO

object FileIOConstants {
    const val INPUT_FILE = "input_dependency.txt"
    const val DEFAULT_OUTPUT_FILE = "output.txt"
    const val EXPERIMENTAL_OUTPUT_FILE = "experiments.txt"

    private fun getOutputFile() = EXPERIMENTAL_OUTPUT_FILE

    fun getDefaultDependencyListProvider(): DependencyInputProvider = PlainTextAndroidDependencyProvider()
    fun getDefaultOutputHandler(): OutputHandler = DefaultOutputHandler(getOutputFile())
}