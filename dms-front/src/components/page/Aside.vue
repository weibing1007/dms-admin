<template>
    <div class="aside">
        <el-menu router :default-active="$route.path"
                 class="el-menu-vertical-demo"
                 @open="handleOpen"
                 @close="handleClose"
                 :collapse="isCollapse">
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :key="item" :index="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{item.title}}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu :key="subItem" :index="subItem.index" v-if="subItem.subs">
                                <template slot="title">
                                    <span slot="title">{{subItem.title}}</span>
                                </template>
                                <el-menu-item :key="threeItem" :index="threeItem.index" v-for="threeItem in subItem.subs">
                                    <span slot="title">{{item.title}}</span>
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item :key="subItem" :index="subItem.index" v-else>
                                <span slot="title">{{subItem.title}}</span>
                            </el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :key="item" :index="item.index">
                        <i :class="item.icon"></i>
                        <span slot="title">{{item.title}}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        data() {
            return {
                items: [
                    {
                        title: "系统首页",
                        index: "/home/dashboard",
                        icon: "el-icon-s-home"
                    },
                    {
                        title: "表单数据",
                        index: "/home/form",
                        icon: "el-icon-menu"
                    },
                    {
                        title: "表格数据",
                        index: "/home/table",
                        icon: "el-icon-s-grid"
                    },
                    {
                        title: "一级菜单",
                        index: "1",
                        icon: "el-icon-s-order",
                        subs: [
                            {
                                title: "二级菜单",
                                index: "/home/tabs",
                                icon: "el-icon-s-grid"
                            },
                            {
                                title: "三级菜单",
                                index: "1-4",
                                icon: "el-icon-s-grid",
                                subs: [
                                    {
                                        title: "选项1",
                                        index: "/home/tabs2",
                                        icon: "el-icon-s-grid"
                                    }
                                ]
                            }
                        ]
                    }
                ],
            };
        },
        props: {
            // 侧栏参数（父组件传递一个数组）
            isCollapse: Boolean
        },
        methods: {
            // 侧栏
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>
    .aside {
        /*background-color: #D3DCE6;*/
        /*overflow-y: scroll;*/
        position: absolute;
        left: 0px;
        top: 70px;
        bottom: 0px;
        text-align: left;
    }

    .el-menu {
        background-color: #FFFFFF;
    }

    /*菜单折叠*/
    .el-menu-vertical-demo {
        width: 200px;
        min-height: 100%;
    }
</style>
