<template>
  <div>
    <h1>登陆成功</h1>
  </div>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
        // user: JSON.parse(window.sessionStorage.getItem("user"))
      }
    },
    computed: {

    },
    methods: {
      // goChat() {
      //   this.$router.push("/chat");
      // },

    }
  }
</script>

<style>
  .homeRouterView {
    margin-top: 10px;
  }

  .homeWelcome {
    text-align: center;
    font-size: 30px;
    font-family: 华文行楷;
    color: #409eff;
    padding-top: 50px;
  }

  .homeHeader {
    background-color: #409eff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0px 15px;
    box-sizing: border-box;
  }

  .homeHeader .title {
    font-size: 30px;
    font-family: 华文行楷;
    color: #ffffff
  }

  .homeHeader .userInfo {
    cursor: pointer;
  }

  .el-dropdown-link img {
    width: 48px;
    height: 48px;
    border-radius: 24px;
    margin-left: 8px;
  }

  .el-dropdown-link {
    display: flex;
    align-items: center;
  }
</style>
