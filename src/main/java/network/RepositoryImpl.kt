package network

class RepositoryImpl(private val repositoryList: List<Repository>) : RepositoryManager {

    private var exceptionList: List<String> = emptyList()

    override fun getExceptionList() = exceptionList

    override suspend fun getLatestVersion(dependency: Dependency): String {
        val exceptionList = mutableListOf<String>()
        for (repo in repositoryList) {
            try {
                val version = repo.getLatestVersion(dependency)
                if (version != NetworkConstants.NOT_FOUND) return version
            } catch (exception: Exception) {
                exceptionList.add("${dependency.group}:${dependency.version} Exception for ${repo.javaClass} : ${exception.message}")
            }
        }
        this.exceptionList = exceptionList
        return NetworkConstants.NOT_FOUND
    }
}