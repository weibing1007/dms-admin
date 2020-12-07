<template>
    <div>
        <section ref="print">
            打印内容
            <div class="no-print">不要打印我</div>
        </section>
        <button onclick="print();">打印</button>
    </div>

</template>

<script>
    import Vue from 'vue'
    import Print from '@/plugs/print';
    Vue.use(Print) // 注册
    export default {
        name: "printTest",
        methods: {
            print() {
                this.$print(this.$refs.print)
            }
        }
    }
</script>

<style scoped>

</style>
