package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2022/12/23.
 */
public enum OperationType {
    INSERT("Add"),
    UPDATE("Modify"),
    DELETE("Delete"),
    QUERY("Query"),
    LIST("List"),
    GRANT("Grant"),
    REVOKE("Revoke"),
    EXPORT("Export"),
    IMPORT("Import"),
    UPLOAD("Upload"),
    DOWNLOAD("Download"),
    VIEW("View"),
    EXECUTE("Execute"),
    GENCODE("Generate Code");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
