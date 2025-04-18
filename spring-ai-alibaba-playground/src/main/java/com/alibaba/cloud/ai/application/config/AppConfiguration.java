/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.application.config;

import com.alibaba.cloud.ai.application.advisor.TraceContentAdvisor;
import com.alibaba.cloud.ai.memory.jdbc.SQLiteChatMemory;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 *
 * 全局统一管理 ChatMemory Bean 和 SimpleLoggerAdvisor
 */

@Configuration
public class AppConfiguration {

	@Bean
	public ChatMemory SQLiteChatMemory() {

		return new SQLiteChatMemory(
				null,
				null,
				"jdbc:sqlite:src/main/resources/db/saa.db"
		);
	}

	@Bean
	public SimpleLoggerAdvisor simpleLoggerAdvisor() {

		return new SimpleLoggerAdvisor(100);
	}

	@Bean
	public MessageChatMemoryAdvisor messageChatMemoryAdvisor(
			ChatMemory sqLiteChatMemory
	) {

		return new MessageChatMemoryAdvisor(sqLiteChatMemory);
	}

	@Bean
	public TraceContentAdvisor traceContentAdvisor() {

		return new TraceContentAdvisor(1);
	}

}
