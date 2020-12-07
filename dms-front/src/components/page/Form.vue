<template>
    <div class="main">
        <ul class="kuangKuan">
            <li class="li01">
                <!--采集发票-->
                <img src="https://sems.sinosoft.com.cn/ecs/images/fenzuOnesizes.png">
                <div class="kuangBor">
                    <span>采集数据</span>
                    <span>办公效率更加方便快捷</span>
                    <span onclick="invoiceQuick()" style="cursor:pointer">查看详情</span>
                </div>
            </li>
            <li style="background:url(https://sems.sinosoft.com.cn/ecs/images/fenzuTwos.png) no-repeat;background-size:100% 100%;">
                <!--发起报销-->
                <img src="https://sems.sinosoft.com.cn/ecs/images/fenzuTwosizes.png">
                <div class="kuangBor">
                    <span>数据处理</span>
                    <span>办公效率更加方便快捷</span>
                    <span onclick="reimburseQuick()" style="cursor:pointer">查看详情</span>
                </div>
            </li>
            <li><!--最新公告-->
                <div class="gonggao">
                    <span>最新公告</span>
                    <span><a target="_blank" href="view?view=system/notice.html">查看更多&nbsp;&nbsp;&gt;&gt;</a></span>
                    <!--<span onclick="noticeMore()" style="cursor:pointer">查看更多&nbsp;&nbsp;>></span>-->
                </div>
                <div class="gonggaoMain">
                    <div>
                        <span><a target="_blank" href="view?view=system/noticeDetail.html?id=6">核对个人信息（重要！！！）</a></span>
                        <span>2019-10-13 21:27:45</span>
                    </div>
                    <div>
                        <span><a target="_blank" href="view?view=system/noticeDetail.html?id=15">问题反馈途径</a></span>
                        <span>2019-12-01 16:44:43</span>
                    </div>
                    <div>
                        <span><a target="_blank" href="view?view=system/noticeDetail.html?id=18">发票采集报销单创建操作快捷参考</a></span>
                        <span>2020-01-07 14:11:54</span> </div>
                    <div> <span><a target="_blank" href="view?view=system/noticeDetail.html?id=10">系统外币发票报销注意事项</a></span>
                        <span>2019-10-29 10:00:02</span>
                    </div>
                </div>
            </li>
        </ul>
        <div class="form">
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="用户管理" name="first">
                    <el-calendar>
                        <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
                        <template
                                slot="dateCell"
                                slot-scope="{date, data}">
                            <p :class="data.isSelected ? 'is-selected' : ''">
                                {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
                            </p>
                        </template>
                    </el-calendar>
                </el-tab-pane>
                <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>
                <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
                <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Form",
        data () {
            return {
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                activeName: 'first'
            }
        },
        methods: {
            onSubmit() {
                console.log('submit!');
            }
        }
    }
</script>

<style scoped>
    .main {
        position: absolute;
        left: 0px;
        right: 0px;
        top: 0px;
        bottom: 0px;
        text-align: left;
    }

    .form {
        background-color: white;
        position: absolute;
        left: 10px;
        right: 10px;
        top: 135px;
        bottom: 10px;
        border-radius: 10px;
        height: auto;
        text-align: left;
        padding: 50px;
        overflow-y: auto;
    }

    .is-selected {
        color: #1989FA;
    }

    /*滚动条整体样式，高宽分别对应横竖滚动条的尺寸，只在chrome下有效！*/
    .form::-webkit-scrollbar {
        width: 6px;
        height: 1px;
    }

    /*滚动条里面小方块*/
    .form::-webkit-scrollbar-thumb {
        border-radius: 10px;
        background-color: rgba(144,147,153,.3);
        cursor: pointer;
        border-radius: inherit;
        background-color: rgba(144,147,153,.3);
    }

    /*滚动条里面轨道*/
    .form::-webkit-scrollbar-track {
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        border-radius: 10px;
        background: #EDEDED;
    }

    .kuangKuan {
        /*background-color: yellow;*/
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-left: 10px;
        padding-right: 10px;

        font-size: 12px;
        font-family: Tahoma, Verdana, 微软雅黑, 新宋体;
    }

    .kuangKuan li {
        /*background-color: #409EFF;*/
        width: 25%;
        height: 125px;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: -0.4%;
        margin-top: -0.3%;
    }

    .li01 {
        background-image: url("../../assets/image/sucai01.png");
        background-repeat: no-repeat;
        background-size: 100% 100%;
    }

    .kuangKuan img {
        width: 30%;
        height: auto;
        margin-right: 8%;
    }

    .kuangBor {
        display: flex;
        flex-direction: column;
    }

    .kuangBor span:nth-of-type(1) {
        font-size: 12px;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
        margin-bottom: 2px;
    }

    .kuangBor span:nth-of-type(2) {
        font-size: 10px;
        color: #666666;
        margin-bottom: 12px;
        line-height: 16px;
    }

    .kuangBor span:nth-last-of-type(1) {
        background: #FFFFFF;
        border: 1px solid #479DFE;
        border-radius: 15px;
        color: #479DFE;
        width: 70%;
        text-align: center;
        font-size: 10px;
        line-height: 18px;
    }

    .kuangKuan li:nth-last-of-type(1) {
        width: 49%;
        background: #FFFFFF;
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.04);
        border-radius: 10px;
        border-radius: 10px;
        height: 92px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 10px;
    }

    .kuangKuan li:nth-of-type(2) {
        margin-left: -1%;
    }

    .gonggao {
        display: flex;
        justify-content: space-between;
        width: 100%;
        align-items: center;
        line-height: 18px;
        margin-bottom: 5px;
    }

    .gonggao span:nth-of-type(1) {
        background-image: linear-gradient(-180deg, #57B4FE 0%, #2B80FF 100%);
        border-radius: 3px;
        color: #fff;
        padding: 0 10px;
        font-size: 10px;
    }

    .gonggao span:nth-of-type(2) {
        border: 1px solid #479DFE;
        border-radius: 12px;
        color: #479DFE;
        padding: 0 10px;
        font-size: 8px;
        line-height: 16px;
        height: 16px;
    }

    .gonggaoMain {
        width: 100%;
    }

    .gonggaoMain div {
        position: relative;
        display: flex;
        justify-content: space-between;
        width: 98%;
        padding-left: 1%;
        padding-right: 1%;
    }
</style>
