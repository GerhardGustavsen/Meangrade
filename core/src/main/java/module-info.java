@SuppressWarnings("module") module vault.core {
    requires transitive json.simple;
    requires jasypt;

    exports core;
    exports json;
}
