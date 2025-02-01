package com.dormstd.langchain4j.sandbox;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TableSample {

    //ToDo: Add the posibility to extract a sample of each column (or all of them somehow) to provide context for the description.
    @Tool("Get the list of columns of a table, given the name of the table")
    public List<String> getTableColumns(String name) {
        //ToDo: Change for a jdbc connection to the real table to extract the column names
        List<String> columnsList = new ArrayList<>();
        columnsList.add("runDate");
        if(name.contains("audit")){
             columnsList.add("job");
            columnsList.add("country");
            columnsList.add("businessUnit");
            columnsList.add("rowsCopied");
        }
        if(name.contains("tomador_seguro")){
            columnsList.add("nombre");
            columnsList.add("apellido");
            columnsList.add("direccion");
            columnsList.add("numeroPoliza");
            columnsList.add("nombreAgente");
            columnsList.add("maximoPoliza");
            columnsList.add("compañiaAseguradora");
        }
        return columnsList;
    }
}