import axios from 'axios'
import { Message, Loading } from 'element-ui';
import router from '../router/index'

let loading;
function startLoading () {
    loading = Loading.service({
        lock: true,
        text: '拼命加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0,0,0,0,0.7)'
    });
}

function endLoading () {
    // loading.close();
    setTimeout(() => {
        loading.close();
    }, 3000);
}

// 请求拦截
axios.interceptors.request.use(config => {
    // 加载动画
    startLoading();
    // 判断token存在   登录拦截
    if(localStorage.eleToken){
        /*设置统一的header*/
        config.headers.Authorization  = localStorage.eleToken;
    }
    return config;
},error => {
    return Promise.reject(error);
});

// 响应拦截
axios.interceptors.response.use(Response => {
    // 结束加载动画
    endLoading();
    // Token过期及无效处理
    console.log(Response);
    if(Response.data.code === '0') {
        Message.error(Response.data.msg);
        router.push('/login')
    } else if(Response.data.code === '2') {
        Message.error(Response.data.msg);
        localStorage.removeItem('eleToken');
        router.push('/login')
    }
    return Response;
},error => {
    // 结束加载动画
    endLoading();
    // 错误提醒
    Message.error(error.response.data);

    return Promise.reject(error);
})

export default axios;
