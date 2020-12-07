import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from './api/http'
import Blob from './excel/Blob'
import Export2Excel from './excel/Export2Excel'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import './assets/css/theme-blue/index.css'
import VueI18n from 'vue-i18n'
import enLocale from 'element-ui/lib/locale/lang/en'
import zhLocale from 'element-ui/lib/locale/lang/zh-CN'

Vue.config.productionTip = false
Vue.use(ElementUI, { size: 'small', zIndex: 3000 });
// Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.prototype.$store = store
Vue.use(VueI18n)
Vue.use(Blob)
Vue.prototype.$Export2Excel = Export2Excel


router.beforeEach((to, from, next) => {
  const token = localStorage.eleToken;
  console.log("token: " + token);
  if(to.path ==="/login" || to.path ==="/register") {
    next();
  } else {
    token ? next() : next("/login")
  }
});

const messages = {
  en: {
    message: 'hello',
    ...enLocale
  },
  zh: {
    message: '你好',
    ...zhLocale
  }
}

const i18n = new VueI18n({
  locale: 'zh',
  messages,
})

new Vue({
  router,
  i18n,
  store,
  render: h => h(App)
}).$mount('#app')
