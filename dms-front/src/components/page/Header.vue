<template>
    <div class="header">
        <el-row>
            <el-col class="logo" span="4">
                DMS<el-divider direction="vertical"></el-divider>数据管理系统
            </el-col>
            <el-col class="wb-avatar" span="2" offset="15">
            <!--<el-avatar :size="45" :src="circleUrl"></el-avatar>-->
                <img :src="circleUrl">
            </el-col>
            <el-col span="2" class="user-select">
                <el-dropdown @command="handleCommand" trigger="click">
                    <span class="el-dropdown-link">
                        {{username}}
                        <i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="a" icon="el-icon-edit">编辑用户</el-dropdown-item>
                        <el-dropdown-item command="b" icon="el-icon-circle-close" divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <!-- 用户编辑-对话框 -->
                <UserEditDialog :dialogFormVisible.sync="dialogFormVisible"></UserEditDialog>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import UserEditDialog from "./UserEdit";

    export default {
        name: "Header",
        data() {
            return {
                // 用户名
                username: localStorage.getItem("username"),
                // 顶栏
                activeIndex: '1',
                // 折叠
                // isCollapse: false,
                // 当前时间
                currentDate: '2019年12月25日 10时05分01秒',
                circleUrl: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
                dialogFormVisible: false
            }
        },
        components: {
            UserEditDialog
        },
        methods: {
            handleCommand(command) {
                // this.$message('click on item ' + command);
                if(command === 'a') {
                    this.dialogFormVisible = true
                } else if(command === 'b') {
                    this.$confirm('您确定要退出系统吗?', '系统提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        localStorage.removeItem('eleToken');
                        this.$router.push('/login')
                    }).catch(() => {

                    });
                }

            },
            closeDialog() {
                this.dialogFormVisible = false;
            }

        }
    }
</script>

<style scoped>
    .header {
        /*background-color: #545c64;*/
        background-image: url("../../assets/image/header.jpg");
        position: absolute;
        left: 0px;
        right: 0px;
        top: 0px;
        magin: auto;
        height: 70px;
    }

    .el-menu {
        background-color: #D3DCE6;
    }

    .logo {
        /*background-color: red;*/
        color: #FFFFFF;
        text-align: left;
        padding-left: 20px;
        font-weight: bold;
        text-shadow:5px 2px 6px #000;
        letter-spacing: 2px;
        line-height: 70px;
    }

    .wb-avatar {
        /*background-color: yellow;*/
        text-align: right;
        height: 70px;
        line-height: 70px;
        position: relative;
    }

    .wb-avatar img {
        height: 40px;
        line-height: 40px;
        border-radius: 50%;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -20px;
        margin-left: 10px;
    }

    .el-avatar {
        text-align: right;
    }

    .user-select {
        /*background-color: red;*/
        display: flex;
        height: 70px;
        align-items: center;
    }

    .el-dropdown {
        margin: auto;
        width:114px;
        text-align: left;
        padding-left: 5px;
    }

    .el-dropdown-link {
        cursor: pointer;
        color: #ffffff;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .logoff {
        font-size: 30px;
    }
</style>
