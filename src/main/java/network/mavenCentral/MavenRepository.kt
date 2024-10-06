package network.mavenCentral

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import network.NetworkConstants
import network.Repository

class MavenRepository(private val httpClient: HttpClient) : Repository {
    override suspend fun getLatestVersion(dependency: Dependency): String {
        val response: HttpResponse = httpClient.get(MavenCentralConstants.MAVEN_URL) {
            url {
                encodedParameters.append(
                    MavenCentralConstants.QUERY_PARAM,
                    MavenCentralConstants.getQueryParams(dependency.group, dependency.artifactId)
                )
            }
        }
        val apiResponse: SonarResponse = response.body()
        val apiDocs = apiResponse.response.docs
        return if (apiDocs.isEmpty()) NetworkConstants.NOT_FOUND else apiDocs[0].latestVersion
    }
}