<template>
  <div>


    <el-form
      ref="loginForm"
      v-loading="loading"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :model="loginForm"
      class="loginContainer">
      <h3 class="loginTitle"></h3>
      <el-form-item prop="username">
        <el-row  :gutter="20">
          <el-col :span="24"> <el-input size="normal" type="text" v-model="loginForm.phone" auto-complete="off"
                                       placeholder="请输入手机号"></el-input>
          </el-col>

        </el-row>
      </el-form-item>
      <el-form-item prop="password">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                      placeholder="请输入验证码" @keydown.enter.native="submitLogin"></el-input>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" size="middle" :disabled="disabled" @click="sendcode">
              {{btntxt}}
            </el-button>
          </el-col>
        </el-row>


      </el-form-item>
      <!--<el-checkbox size="normal" class="loginRemember" v-model="checked"></el-checkbox>-->
      <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>

  export default {
    name: "Login",
    data() {
      return {
        btntxt:"获取验证码",
        disabled:false,
        time:0,
        loading: false,
        phone:'',
        loginForm: {
          phone: '' ,
          code: ''
        },

        checked: true,

      }
    },

    methods: {

      //验证手机号码部分
      sendcode:function(){

        if(this.loginForm.phone==''){
          alert("请输入手机号码");
          return;
        }
        this.time=60;
        this.disabled=true;
        this.timer();
        this.getRequest('/api/user/sendmsg/?telephone='+this.loginForm.phone).then(resp => {
          this.loading = false;


          if (resp) {
            console.log(resp);
            this.$message.success('发送成功');
          }
        })

      },
      timer:function () {
        if (this.time > 0) {
          this.time--;
          this.btntxt=this.time+"s,后重新获取验证码";
          setTimeout(this.timer, 1000);
        } else{
          this.time=0;
          this.btntxt="获取验证码";
          this.disabled=false;
        }
      },


      submitLogin() {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.loading = true;
            this.postRequest('/api/user/login/', this.loginForm).then(resp => {
              this.loading = false;

              if (resp.code == '200') {
                console.log(resp.code);
                console.log(resp);
                this.$router.push("/home");
              }
            })
          } else {
            this.$message.error('请输入所有字段');
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  .loginContainer {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 15px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .loginTitle {
    margin: 15px auto 20px auto;
    text-align: center;
    color: #505458;
  }

  .loginRemember {
    text-align: left;
    margin: 0px 0px 15px 0px;
  }
</style>
