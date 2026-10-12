package com.platzi.play.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

@Configuration
public class LangChainConfig {
	@Bean
	public ChatLanguageModel chatLanguageModel(
			@Value("${langchain4j.ollama.chat-model.base-url}") String baseUrl,
			@Value("${langchain4j.ollama.chat-model.model-name}") String modelName,
			@Value("${langchain4j.ollama.chat-model.temperature}") Double temperature,
			@Value("${langchain4j.ollama.chat-model.timeout}") Duration timeout) {
		return OllamaChatModel.builder()
				.baseUrl(baseUrl)
				.modelName(modelName)
				.temperature(temperature)
				.timeout(timeout)
				.logRequests(true)
				.logResponses(true)
				.build();
	}
}
