package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public abstract class AbstractJsonDeserializer<T> extends JsonDeserializer<T> {

    protected JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
