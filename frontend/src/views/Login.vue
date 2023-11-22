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
            <form action="">
                <input type="text" placeholder="请输入手机号码">
                <br>
                <br>
                <div id="password">
                    <input type="password" placeholder="请输入密码" id="password1" ref="password">
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
                <button type="submit">登录</button>

            </form>
        </div>

    </div>
</template>
<script>
export default {
    data() {
        return {
            flag: 1
        }
    },
    methods: {
        changeVisbilityState() {
            if (this.flag == 1) {
                this.$refs.password.type = 'text';
                this.flag = 0;
            } else {
                this.$refs.password.type = 'password';
                this.flag = 1;
            }
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
    /* background-color: yellow; */
}

.formBody input {
    width: 336px;
    height: 52px;
    border-radius: 10px;
    border: none;
    background-color: #F6F7F9;
}

#password {
    width: 336px;
}

#password1 {
    width: 297px;
    border-radius: 10px 0 0 10px;
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
</style>