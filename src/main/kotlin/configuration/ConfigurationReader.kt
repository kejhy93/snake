package configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class ConfigurationReader {

    fun parseConfigurationFile(path: String) {
        val mapper = createObjectMapper()

        val configurationData: ConfigurationData = mapper.readValue(path)
    }


    private fun createObjectMapper(): ObjectMapper {
        return jacksonObjectMapper().registerModule(KotlinModule())
    }
}