<template>
    <div>
        <el-dialog custom-class="dialog-style"
                   title="用户编辑"
                   :visible.sync="dialogFormVisible"
                   :before-close="handleClose">
            <el-form class="form-style" :model="form" label-width="100px" size="mini">
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="userid" label="账号：">
                            <el-input placeholder="请输入账号" v-model="form.userid" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                        <el-form-item prop="username" label="姓名：">
                            <el-input placeholder="请输入姓名" v-model="form.username" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="password" label="密码：">
                            <el-input placeholder="请输入初始密码" v-model="form.password" show-password :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                        <el-form-item prop="confirmpwd" label="确认密码：">
                            <el-input placeholder="请输入确认密码" v-model="form.confirmpwd" show-password :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="sex" label="性别：">
                            <el-select v-model="form.sex" placeholder="请选择性别">
                                <el-option label="男" value="1"></el-option>
                                <el-option label="女" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                        <el-form-item prop="department" label="部门：">
                            <el-input placeholder="请输入部门" v-model="form.department"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="birthday" label="出生日期：">
                            <el-date-picker
                                    v-model="form.birthday"
                                    type="date"
                                    placeholder="请输入出生日期"
                                    value-format="yyyy-MM-dd"
                                    style="width: 100%;">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                        <el-form-item prop="idtype" label="证件类型：">
<!--                            <el-input placeholder="请输入证件类型" v-model="form.idtype"></el-input>-->
                            <el-select v-model="form.idtype" placeholder="请选择证件类型">
                                <el-option label="身份证" value="1"></el-option>
                                <el-option label="军官证" value="2"></el-option>
                                <el-option label="学生证" value="3"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="idno" label="证件号码：">
                            <el-input placeholder="请输入证件号码" v-model="form.idno"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                        <el-form-item prop="mobile" label="手机号：">
                            <el-input placeholder="请输入手机号" v-model="form.mobile"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col span="11">
                        <el-form-item prop="email" label="邮箱：">
                            <el-input placeholder="请输入邮箱" v-model="form.email"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="11" offset="2">
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="confirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import dateUtil from "../common/dateUtil"

    export default {
        name: "UserEdit",
        data() {
            return {
                form: {
                    userid: "",
                    username: "",
                    password: "",
                    confirmpwd: "",
                    sex: "",
                    department: "",
                    birthday: "",
                    idtype: "",
                    idno: "",
                    mobile: "",
                    email: "",
                    makedate: "",
                    maketime: "",
                    modifydate: "",
                    modifytime: ""
                },
                formLabelWidth: '120px',
            }
        },
        props: {
            dialogFormVisible: Boolean
        },
        methods: {
            // 对话框--取消按钮
            cancel() {
                this.dialogFormVisible = false;
                this.$emit("update:dialogFormVisible", false);
            },
            // 对话框--确认按钮
            confirm() {
                const self = this;
                const date = new Date();
                this.form.modifydate = dateUtil.dateFormat(date, "yyyy-MM-dd");
                this.form.modifytime = dateUtil.dateFormat(date, "HH:mm:ss");
                // 提交数据
                this.$axios.post('/api/user/modifyUser', this.form).then(function (response) {
                    console.log(response);
                    self.$alert('用户修改成功', '保存结果', {
                        confirmButtonText: '确定',
                        callback: () => {
                            self.dialogFormVisible = false;
                            self.$emit("update:dialogFormVisible", false);
                        }
                    });
                }).catch(function (error) {
                    self.$alert("用户修改失败，提交数据遇到错误！", '提示', {
                        confirmButtonText: '确定',
                        type: 'warning'
                    });
                    console.log(error);
                });
            },
            handleClose() {
                this.dialogFormVisible = false;
                this.$emit("update:dialogFormVisible", false);
            },
            getUserInfo() {
                // 获取用户信息
                const self = this;
                const userid = self.$store.getters.user.aud;
                this.$axios.get('/api/user/getUserInfo', {params: {"userid": userid}}).then(function (response) {
                    console.log(response);
                    if(response.status == 200) {
                       const user = response.data;
                       self.form = user;
                    }
                }).catch(function (error) {
                    self.$message('获取用户信息失败！');
                    console.log(error);
                });
            }
        },
        mounted() {
            this.getUserInfo();
        }
    }
</script>

<style>
    .dialog-style {
        background-color: #f0f0f0;
    }

    .el-dialog__header {
        background-color: #545c64;
        background-image: url("../../assets/image/header.jpg");
        background-position: center;
        padding: 5px 20px 5px;
        line-height: 30px;
        text-align: left;
    }

    .el-dialog__header button {
        top: 15px
    }

    .el-dialog__title {
        color: #ffffff;
        font-size: 16px;
        /*line-height: 30px;*/
    }

    .el-dialog__body {
        background-color: #f0f0f0;
        padding: 10px 10px 0px
    }

    .el-dialog__footer {
        background-color: #f0f0f0;
        padding: 10px 10px 10px;
    }

    .form-style {
        background-color: #ffffff;
        height: 280px;
        overflow-y: auto;
        overflow-style: marquee-line;
        padding: 20px;
        border-radius: 10px;
    }

    /*滚动条整体样式，高宽分别对应横竖滚动条的尺寸，只在chrome下有效！*/
    .form-style::-webkit-scrollbar {
        width: 6px;
        height: 1px;
        transition: opacity .12s ease-out;
        transition-property: opacity;
        transition-duration: 0.12s;
        transition-timing-function: ease-out;
        transition-delay: 0s;
    }

    /*滚动条里面小方块*/
    .form-style::-webkit-scrollbar-thumb {
        border-radius: 10px;
        /*-webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);*/
        background-color: rgba(144,147,153,.3);
        cursor: pointer;
        border-radius: inherit;
        background-color: rgba(144,147,153,.3);
        transition: background-color .3s;
        transition-property: background-color;
        transition-duration: 0.3s;
        transition-timing-function: ease;
        transition-delay: 0s;
    }

    /*滚动条里面轨道*/
    .form-style::-webkit-scrollbar-track {
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        border-radius: 10px;
        background: #EDEDED;
    }

    .dialog-footer {
        background-color: #fff;
        height: 30px;
        line-height: 30px;
        padding: 10px 30px 10px;
        border-radius: 10px;
        /*text-align: center;*/
    }

    .el-input {
        width: auto;
    }

    .el-date-picker {
        /*width: 20px;*/
    }

</style>
