<template>
    <div class="main">
        <div class="content-top">
            <el-form ref="form" :model="sizeForm" label-width="80px" size="mini" inline="true">
            <el-row>
                <el-col :span="8">
                    <el-form-item label="活动名称">
                        <el-input v-model="sizeForm.name"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="活动区域">
                        <el-select v-model="sizeForm.region" placeholder="请选择活动区域">
                            <el-option label="区域一" value="shanghai"></el-option>
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="活动时间">
                        <el-col :span="24">
                            <el-date-picker type="date" placeholder="选择日期" v-model="sizeForm.date1" style="width: 100%;"></el-date-picker>
                        </el-col>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="活动性质">
                        <el-checkbox-group v-model="sizeForm.type">
                            <el-checkbox-button label="美食" name="type"></el-checkbox-button>
                            <el-checkbox-button label="轰趴" name="type"></el-checkbox-button>
                            <el-checkbox-button label="主题活动" name="type"></el-checkbox-button>
                        </el-checkbox-group>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="特殊资源">
                        <el-radio-group v-model="sizeForm.resource">
                            <el-radio border label="线上品牌商赞助"></el-radio>
                            <el-radio border label="线下场地免费"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6" offset="18">
                    <el-form-item class="btn">
                        <el-button type="primary" @click="onSubmit">查询</el-button>
                        <el-button>重置</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
            </el-form>
        </div>
        <el-row class="content-bottom">
            <el-col :span="24">
                <el-table
                        :data="tableData"
                        style="width: 100%">
                    <el-table-column
                            label="日期"
                            width="180">
                        <template slot-scope="scope">
                            <i class="el-icon-time"></i>
                            <span style="margin-left: 10px">{{ scope.row.date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="姓名"
                            width="180">
                        <template slot-scope="scope">
                            <el-popover trigger="hover" placement="top">
                                <p>姓名: {{ scope.row.name }}</p>
                                <p>住址: {{ scope.row.address }}</p>
                                <div slot="reference" class="name-wrapper">
                                    <el-tag size="medium">{{ scope.row.name }}</el-tag>
                                </div>
                            </el-popover>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                            <el-button
                                    size="mini"
                                    type="danger"
                                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    export default {
        name: "Table",
        data() {
            return {
                // 表单数据
                sizeForm: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                // 表格数据
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄'
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            }
        },
        methods: {
            // 表单事件
            onSubmit() {
                console.log('submit!');
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            // 表格事件
            handleDelete(index, row) {
                console.log(index, row);
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
    }

    .content-top {
        background-color: white;
        position: absolute;
        left: 10px;
        right: 10px;
        top: 10px;
        border-radius: 10px;
        height: 120px;
        text-align: left;
        padding: 10px 20px;
    }

    .content-bottom {
        background-color: white;
        position: absolute;
        left: 10px;
        right: 10px;
        top: 160px;
        bottom: 10px;
        border-radius: 10px;
        text-align: left;
        padding: 10px 20px;
    }

    .content-top .el-row {
        height: 40px;
    }

    .el-input {
        width: auto;
    }

    .btn {
        position: absolute;
        top: 0px;
        bottom: 0px;
        margin: auto
    }
</style>
