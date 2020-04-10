import Vue from 'vue'
import Vuex from 'vuex'
import { Notification } from 'element-ui';
import {getRequest} from "../../utils/api";


Vue.use(Vuex)

const now = new Date();

const store = new Vuex.Store({

  mutations: {

  },
  actions: {


  }
})

store.watch(function (state) {

}, function (val) {

}, {
  deep: true/*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})


export default store;
