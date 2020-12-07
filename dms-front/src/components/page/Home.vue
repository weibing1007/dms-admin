<template>
    <el-container>
        <!-- 顶栏 -->
        <Header></Header>
        <el-container>
            <!-- 侧栏 -->
            <Aside :isCollapse="isCollapse"></Aside>
                <el-container class="wb_main" :class="{'collapse-style': isCollapse}">
                <!-- 标签页 -->
                <Tag @getFromHeader="handleCollapse"></Tag>
                <!-- 主窗口 -->
                <div class="wb_content">
<!--                    <el-page-header @back="goBack" content="详情页面">-->
<!--                    </el-page-header>-->
<!--                    <el-breadcrumb separator-class="el-icon-arrow-right">-->
<!--                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
<!--                        <el-breadcrumb-item>活动管理</el-breadcrumb-item>-->
<!--                        <el-breadcrumb-item>活动列表</el-breadcrumb-item>-->
<!--                        <el-breadcrumb-item>活动详情</el-breadcrumb-item>-->
<!--                    </el-breadcrumb>-->
                    <router-view></router-view>
                </div>
            </el-container>
        </el-container>
    </el-container>

</template>

<script>
    import Header from "./Header.vue";
    import Aside from "./Aside.vue";
    import Tag from "./Tag.vue";

    export default {
        name: "Home",
        data() {
            return {
                // 折叠
                isCollapse: false
            };
        },
        components: {
            Header,
            Aside,
            Tag
        },
        methods: {
            // 顶栏
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            // 侧栏
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            // 侧栏折叠
            handleCollapse(value) {
                this.isCollapse = value;
            }
        }
    }
</script>

<style scoped>
    /** 容器样式 */
    .wb_main {
        /*background-color: red;*/
        position: absolute;
        left: 200px;
        right: 0px;
        top: 70px;
        bottom: 0;
        padding: 0px;
    }

    .collapse-style {
        left: 80px;
    }

    .wb_content {
        /*background-color: #E9EEF3;*/
        /*background-color: #f0f0f0;*/
        background-color: #F4F5F8;
        position: absolute;
        left: 0px;
        right: 0px;
        top: 30px;
        bottom: 0;
        padding: 0px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }
</style>
