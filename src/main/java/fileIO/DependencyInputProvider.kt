package fileIO

interface DependencyInputProvider {
    fun provideDependencies(): List<Dependency>
}