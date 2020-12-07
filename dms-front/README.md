# element-demo

## 目录结构介绍 ##

	|-- public                            // 入口html文件
	|-- src                               // 源码目录
    |   |-- api                           // 接口
    |       │-- http.js                   // Axios配置
    |   |-- assets                        // 系统资源（图片、样式等）
    |       |-- css                       // 样式文件
    |       |-- image                     // 图片文件
	|   |-- components                    // 组件
	|       |-- common                    // 公共组件
	|           |-- dateUtil.js           // 日期处理工具
	|           |-- Sidentify.vue         // 验证码
	|       |-- page                      // 主要路由页面
	|           |-- Aside.vue             // 系统侧边栏
	|           |-- Dashboard.vue         // 系统首页
	|           |-- Form.vue              // 表单页
	|           |-- Header.vue            // 系统顶栏
	|           |-- Home.vue              // 系统主界面
	|           |-- Login.vue             // 登录页面
	|           |-- Register.vue          // 注册页面
    |           |-- Table.vue             // 表格页面
    |       |-- HelloWorld.vue            // 默认登录页
    |       |-- login.jpg                 // 默认登录图标
    |   |-- excel                         // Excel导出
    |   |-- plugs                         // 打印
    |   |-- router                        // 路由
    |   |-- sample                        // 测试案例
    |   |-- views                         // 组件
	|   |-- App.vue                       // 页面入口文件
	|   |-- main.js                       // 程序入口文件，加载各种公共组件
	|-- .babelrc                          // ES6语法编译配置
	|-- .editorconfig                     // 代码编写规格
	|-- .gitignore                        // 忽略的文件
	|-- index.html                        // 入口html文件
	|-- package.json                      // 项目及工具的依赖配置文件
	|-- README.md                         // 说明

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
