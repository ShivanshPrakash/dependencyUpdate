package network.mavenCentral

import kotlinx.serialization.Serializable

@Serializable
data class SonarResponse(val response: Response)

@Serializable
data class Response(val docs: List<Docs>)

@Serializable
data class Docs(val latestVersion: String)