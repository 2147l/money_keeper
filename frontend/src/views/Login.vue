<template>
    <div class="banner">
        <div class="logo">
            <img src="../assets/image/logo.png" alt="">
            <div class="logoText">记账本</div>
        </div>
    </div>
    <div class="formBody">
        <div class="loginOpxion">使用手机号码登录</div>
        <div>
            <form action="#">
                <input type="text" placeholder="请输入手机号码" v-model.lazy="phone">
                <br>
                <br>
                <div id="password">
                    <input type="password" placeholder="请输入密码" id="password1" ref="password" v-model.lazy="password">
                    <span id="changeVisbility" @click="changeVisbilityState"></span>
                </div>
                <br>
                <br>
                <br>
                <div class="item">
                    <input class="checkBox" type="checkbox">
                    已阅读并同意 <a href="#">《用户协议》</a>和<a href="#">《隐私协议》</a>
                </div>
                <br>
                <br>
                <br>
                <!-- 尝试登录 -->
                <button type="submit" @click.prevent="login">登录</button>
                <div class="tip" v-show="typeInStatus == 1">请输入账号密码</div>
                <div class="tip" v-show="typeInStatus == 2">账号或密码错误</div>
            </form>
        </div>

    </div>
</template>
<script>
export default {
    data() {
        return {
            passwordVisible: 1, // 1表示密码不可见，0表示密码可见
            typeInStatus: 0, // 0表示初始状态，1表示等待输入账号或密码，2表示账号或密码错误
            phone: "",
            password: "",
        }
    },
    methods: {
        changeVisbilityState() {
            if (this.passwordVisible == 1) {
                this.$refs.password.type = 'text';
                this.passwordVisible = 0;
            } else {
                this.$refs.password.type = 'password';
                this.passwordVisible = 1;
            }
        },
        login() {
            if (!this.phone || !this.password) {
                this.typeInStatus = 1
                return
            }
            let param = new URLSearchParams();
            param.append("phone", this.phone)
            param.append("password", this.password)
            this.$axios.post('http://localhost:8080/user/login', param)
                .then(res => {
                    console.log(res) // 控制台打印返回结果观察
                    res = res.data  
                    this.$router.push({ path: '/main', query: { id: res.id } })
                })
                .catch(err => {
                    console.log(err)
                    this.typeInStatus = 2;
                })
        }
    }
}

</script>
<style>
.banner {
    position: relative;
    width: 390px;
    height: 132px;
    /* background-color: pink; */
}

.logo {
    position: absolute;
    margin-top: 40px;
    margin-left: 15px;
    line-height: 30px;
    font-size: 20px;
    /* background-color: gray; */
}

.logoText {
    width: 100px;
    height: 74px;
    float: right;
    font-size: 25px;
    line-height: 74px;
    font-weight: bolder;

}

.formBody {
    padding: 25px;
    width: 390px;
    height: 400px;
    /* background-color: skyblue; */
}

.loginOpxion {
    width: 350px;
    height: 80px;
    line-height: 80px;
    color: #A1A1A1;
    font-size: large;
    /* background-color: yellow; */
}

.formBody input {
    width: 336px;
    height: 52px;
    border-radius: 10px;
    border: none;
    background-color: #F6F7F9;
    padding-left: 10px;
}

#password {
    width: 336px;
}

#password1 {
    width: 297px;
    border-radius: 10px 0 0 10px;
}

/* 去掉默认的小眼睛 */
input[type="password"]::-ms-reveal {
    display: none
}

#changeVisbility {
    display: block;
    float: right;
    width: 39px;
    height: 52px;
    background: url("../assets/image/眼.png");
    background-repeat: no-repeat;
    border: 0;
    border-left: 0;
    border-radius: 0 10px 10px 0;
    cursor: pointer;
}

input::placeholder {
    color: #BFC0C2;
}

.formBody .checkBox {
    width: 14px;
    height: 14px;
}

button {
    width: 336px;
    height: 52px;
    border-radius: 10px;
    background-color: #FFD946;
    font-size: 18px;
}

.item {
    height: 14px;
    line-height: 14px;
    font-size: 14px;
}

.tip {
    text-align: center;
    color: #ff5a5a;
}
</style>