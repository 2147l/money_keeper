<template>
    <div class="version">
        <div class="title">
            <div class="exit"><img src="../assets/image/左箭头.png" alt=""></div>
            账户安全中心
        </div>
        <div class="logo1">
            <img src="../assets/image/报警.png" alt="">
            <br>
            <label for="" class="label1">高风险</label>
            <br>
            <br>
            <label for="" class="label2">绑定的登录方式过少</label>

        </div>
        <div class="item">
            <img src="../assets/image/打叉.png" alt="">
            <label for="">手机号</label>
            <button>去绑定</button>
        </div>
        <div class="item">
            <img src="../assets/image/打勾.png" alt="">
            <label for="">微信号</label>
            <button class="button1">已绑定</button>
        </div>
        <div class="item">
            <img src="../assets/image/打叉.png" alt="">
            <label for="">应急联系方式</label>
            <button>去填写</button>
        </div>
        <div class="item">
            <img src="../assets/image/打勾.png" alt="">
            <label for="">登录状态</label>
            <button class="button1">无异常</button>
        </div>




    </div>
</template>
<script>
export default {
    data() {
        return {
        }
    }
}
</script>
<style scoped>
.version {
    width: 390px;
    height: 844px;
    background-color: #fff;
}

.title {
    position: relative;
    width: 390px;
    height: 60px;
    background-color: #FFD946;
    text-align: center;
    font-size: 20px;
    color: #101010;
    line-height: 60px;
}


.exit {
    position: absolute;
    margin-left: 15px;
    float: left;
}

.logo1 {
    width: 390px;
    height: 271px;
    text-align: center;
}

.logo1 img {
    margin-top: 66px;
}

.label1 {
    margin-left: 0;
    font-size: 16px;
    font-weight: 700;
    color: #E18286;

}

.label2 {
    width: 144px;
    height: 25px;
    margin-left: 0;
    font-size: 16px;
    background-color: #FEF3F5;
    color: #EE989A;
}


label {
    margin-left: 10px;
    font-size: 14px;
    color: #7E7E7E;
}

.curVer {
    float: right;
    margin-right: 25px;
}

.item {
    margin-left: 15px;
    margin-top: 10px;
    padding-left: 20px;
    width: 360px;
    height: 60px;
    line-height: 50px;
    background-color: #FAFBFD;
    border-radius: 12px;

}

.rightArrow {
    float: right;
    margin-top: 12px;
    margin-right: 20px;
    width: 30px;
    height: 30px;

}

button {
    margin-top: 13px;
    margin-right: 10px;
    float: right;
    width: 70px;
    height: 30px;
}

.button1 {
    background-color: #F0F1F3;
    color: #A5A6A8;
}
</style>