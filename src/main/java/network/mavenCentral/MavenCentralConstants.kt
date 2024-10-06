package network.mavenCentral

object MavenCentralConstants {
    const val MAVEN_URL = "https://search.maven.org/solrsearch/select"
    const val QUERY_PARAM = "Q"

    private const val GROUP_PRE_STRING = "g%3A%22"
    private const val GROUP_POST_STRING = "%22"
    private const val CONNECTOR_STRING = "+AND+"
    private const val ARTIFACT_PRE_STRING = "a%3A%22"
    private const val ARTIFACT_POST_STRING = "%22"

    fun getQueryParams(group: String, artifact: String): String {
        return "$GROUP_PRE_STRING$group$GROUP_POST_STRING$CONNECTOR_STRING$ARTIFACT_PRE_STRING$artifact$ARTIFACT_POST_STRING"
    }
}