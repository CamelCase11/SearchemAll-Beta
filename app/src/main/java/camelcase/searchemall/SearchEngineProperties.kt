package camelcase.searchemall

/**
 * A simple dataclass to hold some parameters of a webpage
 * @name : Name of a website to show in Tablayout
 * @url : self explanatory
 * @enableJs: enable javascript or not.
 */
data class SearchEngineProperties(
        val name: String?,
        val url: String?,
        val enableJs: Boolean = false
)