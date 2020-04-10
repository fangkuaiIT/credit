
import Vue from 'vue'
import App from './App'
import router from './router'
import  store  from './store/index.js'

// import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import {postRequest} from "../utils/api";
import {postKeyValueRequest} from "../utils/api";
import {putRequest} from "../utils/api";
import {deleteRequest} from "../utils/api";
import {getRequest} from "../utils/api";


Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;

Vue.use(ElementUI,{size:'small'});

router.beforeEach((to, from, next) => {
  if (to.path == '/') {
    next();
  }
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
