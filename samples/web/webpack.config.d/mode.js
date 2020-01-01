//config.mode = "development"; // [production|development|none]
config.mode = "production";

if (config.mode === "production") {
    config.optimization = {
       minimize: true,
       usedExports: true,
       sideEffects: false
    };
}