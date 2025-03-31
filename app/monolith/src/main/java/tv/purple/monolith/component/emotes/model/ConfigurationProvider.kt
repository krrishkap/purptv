package tv.purple.monolith.component.emotes.model

interface ConfigurationProvider {
    fun getConfiguration(): Configuration

    fun setConfiguration(configuration: Configuration)

    interface ConfigurationListener {
        fun onConfigurationChanged(configuration: Configuration)
    }
}