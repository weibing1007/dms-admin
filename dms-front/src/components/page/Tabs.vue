<template>
    <div>
        <el-button
                type="primary"
                @click="openFullScreen1"
                v-loading.fullscreen.lock="fullscreenLoading">
            指令方式
        </el-button>
        <el-button
                type="primary"
                @click="openFullScreen2">
            服务方式
        </el-button>

        <section ref="print">
            打印内容
            <div class="no-print">不要打印我</div>
        </section>
        <el-button @click="print">打印</el-button>
        <!--导出Excel-->
        <el-button @click="exportExcel">导出Excel</el-button>
    </div>
</template>

<script>
    import Vue from 'vue'
    // import Print from '@/plugs/print';
    import Print from '../../plugs/print';
    Vue.use(Print) // 注册
    export default {
        name: "Tabs",
        data() {
            return {
                fullscreenLoading: false,
                tableData: [{'index':'001', 'nickName':'张三', 'name':'张三'}]
            }
        },
        methods: {
            // 加载动画
            openFullScreen1() {
                this.fullscreenLoading = true;
                setTimeout(() => {
                    this.fullscreenLoading = false;
                }, 2000);
            },
            openFullScreen2() {
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                setTimeout(() => {
                    loading.close();
                }, 2000);
            },
            // 打印
            print() {
                this.$print(this.$refs.print)
            },
            //导出Excel
            exportExcel() {
                // const self = this;
                require.ensure([], () => {
                    const { export_json_to_excel } = require('../../excel/Export2Excel');//注意这个Export2Excel路径
                    const tHeader = ['序号', '昵称', '姓名'];   // 上面设置Excel的表格第一行的标题
                    const filterVal = ['index', 'nickName', 'name']; // 上面的index、nickName、name是tableData里对象的属性key值
                    const list = this.tableData;  //把要导出的数据tableData存到list
                    const data = this.formatJson(filterVal, list);
                    export_json_to_excel(tHeader, data, '列表excel');//最后一个是表名字
                })
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]));
            }
        }
    }
</script>

<style scoped>

</style>
