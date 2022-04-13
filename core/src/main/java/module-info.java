@SuppressWarnings("module") module MeanGrade.core {
    requires transitive json.simple;
    requires jasypt;

    exports core;
    exports json;
}
