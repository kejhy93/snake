package configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class ConfigurationReader {

    fun parseConfigurationFile(path: String) : ConfigurationData {
        val mapper = createObjectMapper()

        val configurationFile: File = File(path)
        if ( !configurationFile.exists() ) {
            return ConfigurationData(1, false)
        }

        val configurationData: ConfigurationData = mapper.readValue(configurationFile)

        return configurationData
    }


    private fun createObjectMapper(): ObjectMapper {
        return jacksonObjectMapper().registerModule(KotlinModule())
    }
}