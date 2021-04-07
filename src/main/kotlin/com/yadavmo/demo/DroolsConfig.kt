package com.yadavmo.demo

import org.drools.decisiontable.DecisionTableProviderImpl
import org.kie.api.KieServices
import org.kie.api.builder.KieFileSystem
import org.kie.api.io.Resource
import org.kie.api.runtime.KieContainer
import org.kie.api.runtime.KieSession
import org.kie.internal.builder.DecisionTableInputType
import org.kie.internal.builder.KnowledgeBuilderFactory
import org.kie.internal.io.ResourceFactory
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.util.*


@Configuration
class DroolsConfig {
    private val RULES_PATH = "com/yadavmo/rules/"
    private val kieServices = KieServices.Factory.get()

    @Throws(IOException::class)
    private fun getKieFileSystem(): KieFileSystem? {
        val kieFileSystem = kieServices.newKieFileSystem()
        val rules: List<String> = Arrays.asList("BackwardChaining.drl", "SuggestApplicant.drl", "Product_rules.xls")
        for (rule in rules) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rule))
        }
        return kieFileSystem
    }

    @Throws(IOException::class)
    fun getKieContainer(): KieContainer? {
        getKieRepository()
        val kb = kieServices.newKieBuilder(getKieFileSystem())
        kb.buildAll()
        val kieModule = kb.kieModule
        return kieServices.newKieContainer(kieModule.releaseId)
    }

    private fun getKieRepository() {
        val kieRepository = kieServices.repository
        kieRepository.addKieModule { kieRepository.defaultReleaseId }
    }

    fun getKieSession(): KieSession? {
        getKieRepository()
        val kieFileSystem = kieServices.newKieFileSystem()
//        kieFileSystem.write(ResourceFactory.newClassPathResource("com/yadavmo/rules/BackwardChaining.drl"))
//        kieFileSystem.write(ResourceFactory.newClassPathResource("com/yadavmo/rules/SuggestApplicant.drl"))
//        kieFileSystem.write(ResourceFactory.newClassPathResource("com/yadavmo/rules/Product_rules.xls"))
        val kb = kieServices.newKieBuilder(kieFileSystem)
        kb.buildAll()
        val kieModule = kb.kieModule
        val kContainer = kieServices.newKieContainer(kieModule.releaseId)
        return kContainer.newKieSession()
    }

    fun getKieSession(dt: List<Resource>): KieSession? {
        val kieFileSystem = kieServices.newKieFileSystem()
        dt.forEach { kieFileSystem.write(it) }
        val kieBuilder = kieServices.newKieBuilder(kieFileSystem)
            .buildAll()
        val kieRepository = kieServices.repository
        val krDefaultReleaseId = kieRepository.defaultReleaseId
        val kieContainer = kieServices.newKieContainer(krDefaultReleaseId)
        return kieContainer.newKieSession()
    }

    /*
     * Can be used for debugging
     * Input excelFile example: com/yadavmo/rules/Discount.xls
     */
    fun getDrlFromExcel(excelFile: String?): String? {
        val configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration()
        configuration.inputType = DecisionTableInputType.XLS
        val dt: Resource = ResourceFactory.newClassPathResource(excelFile, javaClass)
        val decisionTableProvider = DecisionTableProviderImpl()
        return decisionTableProvider.loadFromResource(dt, null)
    }
}