package com.dormstd.langchain4j.sandbox;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = { TableSample.class })
public interface DescriptionGeneratorService {

    @SystemMessage("You are the responsible of the data governance in a big company.")
    @UserMessage("""
                Can you generate the list of columns of a table named {name}. Return the columns as a list, with no other prompt.
            """)
    String getColumns(String name);

    @UserMessage("""
                Given this list of columns {list}.
                Given the name of the table {name}.
                Can you generate a business description of the content of the table. Do not mention specific columns. Output as json in a field called description.
            """)
    String getDescription(String name, String list);

    @UserMessage("""
                That's it. You can sum up the article and add key takeaways to the end of the sum up.
            """)
    String sumUp();
}