<template>
    <div class="wb_main">
        <el-row class="wb-head">
            <el-col >
                <span>已有账号，<router-link class="wb-title" to="/login">马上登陆</router-link></span>
            </el-col>
        </el-row>
        <el-row class="wb-divider">
            <el-divider><span class="wb-font">用户注册</span></el-divider>
        </el-row>
        <el-row class="wb-body">
            <el-col span="8" offset="8">
                <el-form ref="regInfo" :model="regInfo" :rules="rules" size="small" class="demo-ruleForm">
                    <el-form-item prop="userid">
                        <el-input placeholder="请输入账号" v-model="regInfo.userid"></el-input>
                    </el-form-item>
                    <el-form-item prop="username">
                        <el-input placeholder="请输入姓名" v-model="regInfo.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input placeholder="请输入初始密码" v-model="regInfo.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="confirmpwd">
                        <el-input placeholder="请输入确认密码" v-model="regInfo.confirmpwd"></el-input>
                    </el-form-item>
                    <el-form-item prop="idno">
                        <el-input placeholder="请输入身份证号" v-model="regInfo.idno"></el-input>
                    </el-form-item>
                    <el-form-item prop="mobile">
                        <el-input placeholder="请输入手机号" v-model="regInfo.mobile"></el-input>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-input class="code-input" placeholder="请输入验证码" v-model="code"></el-input>
                        <div class="code" @click="refreshCode">
                            <SIdentify :identifyCode="identifyCode"></SIdentify>
                        </div>
                    </el-form-item>
                    <el-button type="primary" @click="register">注册</el-button>
                    <el-button type="primary" @click="cancel">取消</el-button>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    //**引入验证码组件**
    import SIdentify from '../common/Sidentify.vue'
    import dateUtil from "../common/dateUtil"

    export default {
        name: "Register",
        data() {
            // 验证两次密码输入是否一致
            const validateConfirmpwd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.regInfo.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                regInfo: {
                    userid: "",
                    username: "",
                    password: "",
                    confirmpwd: "",
                    idno: "",
                    mobile: ""
                },
                code: "",
                // 验证码
                identifyCodes: "1234567890",
                identifyCode: "",
                // 表单验证规则
                rules: {
                    userid: [
                        { required: true, message: '账号不能为空', trigger: 'blur'},
                        { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
                    ],
                    username: [
                        { required: true, message: '姓名不能为空', trigger: 'blur'},
                        { min: 2, max: 12, message: '长度在 2 到 12 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur'},
                        { min: 3, message: '长度大于等于 3 个字符', trigger: 'blur' }
                    ],
                    confirmpwd: [
                        {validator: validateConfirmpwd, trigger: 'blur'}
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
            register() {
                const self = this;
                const date = new Date();
                this.regInfo.makedate = dateUtil.dateFormat(date, "yyyy-MM-dd");
                this.regInfo.maketime = dateUtil.dateFormat(date, "HH:mm:ss");
                this.$axios.post('/api/user/register', this.regInfo).then(function (response) {
                    console.log(response);
                    this.$alert('注册成功', '保存结果', {
                        confirmButtonText: '确定',
                        callback: () => {
                            self.$router.push("/login")
                        }
                    });
                }).catch(function (error) {
                    console.log(error);
                });
            },
            cancel() {
                this.$router.push("/login");
            }
        }
    }
</script>

<style scoped>
    .wb_main {
        position: absolute;
        left: 0px;
        right: 0px;
        top: 0px;
        bottom: 0px;
        background-color: #f0f0f0;
    }

    .wb-head {
        background-color: black;
        height: 50px;
        line-height: 50px;
        text-align: right;
        padding-right: 30px;

    }

    .wb-title {
        color: #409EFF;
    }

    .wb-head span {
        color: #FFFFFF;
        font-size: small;
        font-weight: bold;

    }

    .el-divider {
        width: 95%;
        position: relative;
        left: 0px;
        right: 0px;
        top: 20px;
        margin: auto;
        font-weight: bold;
    }

    .el-divider span {
        font-size: larger;
        font-weight: bold;
    }

    .wb-divider {
        background-color: #FFFFFF;
        position: absolute;
        left:200px;
        right: 200px;
        top:80px;
        height: 50px;
    }

    .wb-body {
        background-color: #FFFFFF;
        position: absolute;
        left:200px;
        right: 200px;
        top:130px;
        bottom: 30px;
    }

    .code-input {
        float: left;
        width: auto;
    }

    .code {
        width: 110px;
        height: 35px;
        border: 1px solid #ccc;
        float: right;
        border-radius: 2px;
    }
</style>
