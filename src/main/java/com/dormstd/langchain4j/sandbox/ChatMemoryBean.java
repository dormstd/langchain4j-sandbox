package com.dormstd.langchain4j.sandbox;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestScoped
public class ChatMemoryBean implements ChatMemoryProvider {

    Map<Object, ChatMemory> memories = new ConcurrentHashMap<>();

    @Override
    public ChatMemory get(Object memoryId) {
        return memories.computeIfAbsent(memoryId,
                id -> MessageWindowChatMemory.builder()
                        .maxMessages(20)
                        .id(memoryId)
                        .build()
        );
    }

    @PreDestroy
    public void close() {
        memories.clear();
    }
}