package com.dt2js.dgs

import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import reactor.netty.http.server.HttpServer

@Configuration
open class VertxConfig {
    @Bean
    suspend fun vertx() : Vertx {
        return Vertx.vertx()
    }
}

@Component
open class EventLoopNettyCustomizer : NettyServerCustomizer {

    @Autowired
    private lateinit var vertx: Vertx

    override fun apply(t: HttpServer): HttpServer {
        return t.runOn(vertx.nettyEventLoopGroup())
    }

}
