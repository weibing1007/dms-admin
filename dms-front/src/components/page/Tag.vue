<template>
    <div class="tags">
        <el-row>
            <el-col class="collapse-icon" :span="1" v-if="isCollapse">
                <i class="el-icon-s-unfold" @click="handleCollapse"></i>
            </el-col>
            <el-col class="collapse-icon" :span="1" v-else>
                <i class="el-icon-s-fold" @click="handleCollapse"></i>
            </el-col>
            <el-col :span="23">
                <ul>
                    <li class="tags-li" v-for="(item, index) in tagList" :key="index" :class="{active: isActive(item.path)}">
                        <router-link :to="item.path" class="tags-li-title">
                            {{item.title}}
                        </router-link>
                        <span class="tags-li-icon" @click="closeTag(index)"><i class="el-icon-close"></i></span>
                    </li>
                </ul>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Tags",
        data() {
            return {
                isCollapse: false,
                tagList: []
            }
        },
        methods: {
            // 侧栏折叠
            handleCollapse() {
                this.isCollapse = !this.isCollapse
                this.$emit("getFromHeader", this.isCollapse);
            },
            // 当前标签
            isActive(path) {
                return path === this.$route.fullPath;
            },
            // 设置标签
            setTags(route) {
                const isExists = this.tagList.some((item) => {
                    console.log(item.path + " || " + route.fullPath)
                    return item.path === route.fullPath;
                })
                if(!isExists) {
                    this.tagList.push({path: route.fullPath, title: route.meta.title, name: route.meta.home});
                }
            },
            // 关闭单个标签
            closeTag(index) {
                const delItem = this.tagList.splice(index, 1)[0];
                const item = this.tagList[index] ? this.tagList[index] : this.tagList[index - 1];
                if (item) {
                    delItem.path === this.$route.fullPath && this.$router.push(item.path);
                }else{
                    this.$router.push('/');
                }
            }
        },
        watch: {
            $route(newVal) {
                this.setTags(newVal)
            }
        },
        created() {
            this.setTags(this.$route)
        }
    }
</script>

<style scoped>
    .tags {
        position: absolute;
        left: 0px;
        right: 0px;
        top: 0px;
        height: 30px;
        background: #fff;
        padding-right: 120px;
        /*border: 1px solid #f0f0f0;*/
    }

    .collapse-icon {
        font-size: 25px;
        /*border: 1px solid #f0f0f0;*/
    }

    .tags ul {
        box-sizing: border-box;
        width: 100%;
        height: 100%;
    }

    .tags-li {
        float: left;
        margin: -12px 5px 2px 3px;
        /*border-radius: 5px;*/
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        font-size: 12px;
        overflow: hidden;
        cursor: pointer;
        height: 25px;
        line-height: 25px;
        border: 1px solid #e9eaec;
        background: #fff;
        padding: 0 5px 0 12px;
        vertical-align: middle;
        color: #666;
        -webkit-transition: all .3s ease-in;
        -moz-transition: all .3s ease-in;
        transition: all .3s ease-in;
    }

    .tags-li:not(.active):hover {
        background: #f8f8f8;
    }

    .tags-li.active {
        color: #fff;
        /*color: #333;*/
    }

    .active {
        background-color: #409EFF;
    }

    .tags-li-title {
        float: left;
        max-width: 80px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin-right: 5px;
        color: #666;
    }

    .tags-li.active .tags-li-title {
        color: #fff;
    }

    .tags-close-box {
        position: absolute;
        right: 0;
        top: 0;
        box-sizing: border-box;
        padding-top: 1px;
        text-align: center;
        width: 110px;
        height: 30px;
        background: #fff;
        box-shadow: -3px 0 15px 3px rgba(0, 0, 0, .1);
        z-index: 10;
    }

    a {
        text-decoration: none;
    }

    .router-link-active {
        text-decoration: none;
    }
</style>
