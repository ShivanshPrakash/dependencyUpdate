package network

interface RepositoryManager {
    fun getExceptionList(): List<String>
    suspend fun getLatestVersion(dependency: Dependency): String
}

interface Repository {
    suspend fun getLatestVersion(dependency: Dependency): String
}