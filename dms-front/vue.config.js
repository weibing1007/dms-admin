// vue.config.js
// const proxy = require('http-proxy-middleware');
const path = require("path");
function resolve(dir) {
    return path.join(__dirname, dir);
}
module.exports = {
    // 路径别名
    chainWebpack: config => {
        config.resolve.alias
            .set("@", resolve("src"))
            .set("assets", resolve("src/assets"))
            .set("components", resolve("src/components"))
            .set("public", resolve("public"));
    },
    // 服务代理
    devServer: {
        // proxy: 'http://localhost:4000'
        proxy: {
            '/api': {
                target: 'http://localhost:8081/api',
                ws: true,
                changeOrigin: true,
                pathRewrite:{
                    '^/api':''
                }
            }
        },
        disableHostCheck: true,
        // assetsSubDirectory: 'static',
        // assetsPublicPath: '/',
        // proxyTable: {},
        // host: 'localhost',
        port: 8082
        // autoOpenBrowser: false,
        // errorOverlay: true,
        // notifyOnErrors: true,
        // poll: false,
        // useEslint: true,
        // showEslintErrorsInOverlay: false,
        // devtool: 'cheap-module-eval-source-map',
        // cacheBusting: true,
        // cssSourceMap: true
    }
}
