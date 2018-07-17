/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.example.spring.boot.mybatis;

import io.shardingsphere.example.spring.boot.mybatis.service.DemoService;
import io.shardingsphere.opentracing.ShardingJDBCTracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 1 在idea启动项目增加
 * vmoptions -javaagent:E:\hs\software\apache-skywalking-apm-incubating\agent\skywalking-agent.jar
 *
 * 2 http://localhost:8080/#/trace 即可查看跟踪情况
 */
@SpringBootApplication
public class SpringBootDataMain {
    
    public static void main(final String[] args) {
        /*try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootDataMain.class, args)) {
            applicationContext.getBean(DemoService.class).demo();
        }*/
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootDataMain.class, args);
        applicationContext.getBean(DemoService.class).demo();
    }

    @Bean
    public CommandLineRunner init() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                ShardingJDBCTracer.init(new SkywalkingTracer());
            }
        };
    }
}
