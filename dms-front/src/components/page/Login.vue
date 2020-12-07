
<template>
    <el-row class="login-main">
        <el-col class="el-headline" :span="12">
            <div class="el-title">数据管理系统</div><br/>
            <div class="el-subhead">办公更加高效、方便、快捷。</div>
        </el-col>

        <el-col class="el-login" :span="12">
            <el-form ref="form" :model="loginInfo" :rules="rules" label-width="0px" size="mini" class="demo-ruleForm">
                <el-form-item>
                    <span class="title">系统登录</span>
                </el-form-item>
                <el-form-item prop="userid">
                    <el-input placeholder="请输入账号"
                              v-model="loginInfo.userid"
                              prefix-icon="el-icon-user-solid"
                              clearable></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="请输入密码"
                              v-model="loginInfo.password"
                              show-password
                              prefix-icon="el-icon-s-goods"
                              clearable></el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <el-input class="code-input"
                              placeholder="请输入验证码"
                              v-model="loginInfo.code"
                              @keyup.enter.native="onSubmit('form')"></el-input>
                    <div class="code" @click="refreshCode">
                        <SIdentify :identifyCode="identifyCode"></SIdentify>
                    </div>
                </el-form-item>
                <el-form-item size="large">
                <el-button type="primary" @click="onSubmit('form')">登录</el-button><br/>
<!--                <el-link type="primary">忘记密码</el-link>-->
<!--                <el-link type="primary" href="/Register.vue">账号注册</el-link>-->
                <router-link to="">忘记密码</router-link>&nbsp;&nbsp;&nbsp;
                <router-link to="/register">账号注册</router-link>
            </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<script>
    //**引入验证码组件**
    import SIdentify from '../common/Sidentify.vue'
    import jwt from 'jwt-decode';

    export default {
        name: "Login",
        data: function(){
            return {
                loginInfo: {
                    userid: '',
                    password: '',
                    code: ''
                },

                // 验证码
                identifyCodes: "1234567890",
                identifyCode: "",
                // 表单验证规则
                rules: {
                    userid: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入验证码', trigger: 'blur' }
                    ]
                }
            }
        },
        components: {
            SIdentify
        },
        mounted() {
            this.identifyCode = "";
            this.makeCode(this.identifyCodes, 4);
        },
        methods: {
            // 验证码 生成随机数
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            },
            // 验证码 生成
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[
                        this.randomNum(0, this.identifyCodes.length)
                        ];
                }
                console.log(this.identifyCode);
            },
            // 验证码 刷新
            refreshCode() {
                this.identifyCode = "";
                this.makeCode(this.identifyCodes, 4);
            },
            // 登录
            onSubmit(formName) {
                const self = this;
                // 数据校验
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log('数据校验通过!');
                    } else {
                        console.log('数据校验未通过!');
                        return false;
                    }
                });

                // 验证码校验
                if(this.identifyCode != this.loginInfo.code) {
                    this.$alert("验证码输入错误！", '提示', {
                        confirmButtonText: '确定',
                        type: 'warning'
                    });
                    this.loginInfo.code = "";
                    return false;
                }

                // 提交数据
                this.$axios.post('/api/user/login', this.loginInfo).then(function (response) {
                    if(response.data.code == "1") {
                        const token = response.data.data.token;
                        localStorage.setItem('eleToken',token);
                        // 解析Token
                        const decoded = jwt(token);
                        console.log("打印Token信息：")
                        console.log(decoded)
                        // 存储到vuex
                        self.$store.dispatch("setAuthenticated",!self.isEmpty(decoded))
                        self.$store.dispatch("setUser",decoded)
                        self.$store.commit("setUserInfo", response.data.data.username)

                        self.$router.push("/")
                    } else {
                        const errorMsg = response.data.msg;
                        self.$alert(errorMsg, '提示', {
                            confirmButtonText: '确定',
                            type: 'warning'
                        });
                        return false;
                    }
                }).catch(function (error) {
                    self.$alert("登录失败，提交数据遇到错误！", '提示', {
                        confirmButtonText: '确定',
                        type: 'warning'
                    });
                    console.log(error);
                });
            },
            isEmpty(value){
                return(
                    value ===undefined || value ===null ||
                    (typeof  value === "object" && Object.keys(value).length ===0) ||
                    (typeof value ==="string" && value.trim().length ===0)
                );
            }
        }
    }
</script>

<style scoped>
    .login-main {
        /*background-color: darkcyan;*/
        background-color: black;
        background-image: url("../login.jpg");
        background-size:100% 50%;
        background-repeat:no-repeat;
        background-position: center;
        position: fixed;
        left: 0px;
        right: 0px;
        top: 0px;
        bottom: 0px;
        padding: 30px;
    }

    .el-login {
        /*border: 1px solid #FFFFFF;*/
        /*background-color: #FFFFFF;*/
        background: rgba(255, 255, 255, 0.3);
        position: absolute;
        top: 0px;
        bottom: 0px;
        right: 200px;
        margin: auto;
        border-radius: 5px;
        width: 350px;
        height: 280px;
        /*line-height: 50px;*/
        padding: 5px 40px 5px 40px;
        text-align: center;
    }

    .el-input {
        width: 100%;
    }

    .el-headline {
        /*border: 1px solid #FFFFFF;*/
        width: 350px;
        height: 280px;
        position: absolute;
        left: 170px;
        top: 0px;
        bottom: 0px;
        margin: auto;
        color: lightskyblue;
        text-shadow:5px 2px 6px #000;
        font-size: 35px;
        letter-spacing: 5px;
        font-weight: bold;
    }

    .el-button {
        width: 100%;
    }

    .el-title {
        position: relative;
        top: 60px;
        text-align: left;
    }

    .el-subhead {
        /*background-color: yellow;*/
        position: relative;
        top: 50px;
        color: #FFFFFF;
        text-align: left;
        font-size: 15px;
    }

    .title {
        /*text-align: left;*/
        color: #FFFFFF;
        font-weight: bold;
    }

    .code {
        width: 110px;
        height: 35px;
        border: 1px solid #ccc;
        float: right;
        border-radius: 2px;
    }

    .code-input {
        width: 155px;
    }

    .el-loading-parent--relative {
        position: unset;
    }
</style>
