package com.fabiocarlesso.forum.integration

import com.fabiocarlesso.forum.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {
    @Autowired
    private lateinit var topicoRepository: TopicoRepository
    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("123456")
        }
    }
    @DynamicPropertySource
    fun properties (registry: DynamicPropertyRegistry){
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
        registry.add("spring.datasource.password", mysqlContainer::getPassword)
        registry.add("spring.datasource.username", mysqlContainer::getUsername)
    }

}