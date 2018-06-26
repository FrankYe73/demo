package com.example.demo;

import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SchemaTests {

    @Test
    public void testJsonSchema() throws Exception {
        JsonSchema schema = JsonUtil.getJsonSchemaFromClasspath("schema.json");

        JsonNode json = JsonUtil.getJsonNodeFromClasspath("test.json");
        Set<ValidationMessage> result = schema.validate(json);
        System.out.println(result);
        Assert.assertTrue(result.isEmpty());
    }


}
