import fileIO.FileIOConstants
import io.ktor.client.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import network.RepositoryImpl
import network.RepositoryManager
import network.google.GoogleRepository
import network.mavenCentral.MavenRepository

suspend fun main() {
    val dependencyList = FileIOConstants.getDefaultDependencyListProvider().provideDependencies()
    val client = HttpClient(Java) {
        expectSuccess = true
//        install(Logging)
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    val repository: RepositoryManager = RepositoryImpl(listOf(MavenRepository(client), GoogleRepository(client)))
    val responseList = mutableListOf<String>()
    for (dependency in dependencyList) {
        try {
            val version = repository.getLatestVersion(dependency)
            val printVersion = if (version == dependency.version) "UP TO DATE" else version
            responseList.add("${dependency.group}:${dependency.artifactId}:${dependency.version} -> $printVersion")
        } catch (exception: Exception) {
            responseList.add("${dependency.group}:${dependency.artifactId}:${dependency.version} -> ${exception.message}")
        }
    }
    println(repository.getExceptionList())
    FileIOConstants.getDefaultOutputHandler().output(responseList)
    client.close()
}