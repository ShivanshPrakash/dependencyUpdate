package network.google

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json
import network.NetworkConstants
import network.Repository
import org.json.XML

class GoogleRepository(private val httpClient: HttpClient) : Repository {

    private var lastResponse: Pair<String, Map<String, Versions>>? = null

    override suspend fun getLatestVersion(dependency: Dependency): String {
        val artifacts: Map<String, Versions>? = if (lastResponse == null || lastResponse?.first != dependency.group) {
            val url = GoogleConstants.BASE_URL + dependency.group.replace('.', '/') + GoogleConstants.PAGE
            val response: HttpResponse = httpClient.get(url)
            val apiResponse: String = response.body()
            val rootJsonObj = XML.toJSONObject(apiResponse)
            val googleBlob = Json.decodeFromString<Map<String, Map<String, Versions>>>(rootJsonObj.toString())
            val key: String = dependency.group
            val networkArtifacts: Map<String, Versions>? = googleBlob[key]
            if (networkArtifacts != null) {
                lastResponse = Pair<String, Map<String, Versions>>(key, networkArtifacts)
            }
            networkArtifacts
        } else {
            lastResponse?.second
        }
        if (artifacts != null) {
            val artifactVersions = artifacts[dependency.artifactId]?.versions?.split(',')
                ?.filter { !(it.contains("alpha") || it.contains("rc") || it.contains("beta")) }?.sorted()
            val latestVersion = artifactVersions?.last()
            if (latestVersion != null) return latestVersion
        }
        return NetworkConstants.NOT_FOUND
    }
}