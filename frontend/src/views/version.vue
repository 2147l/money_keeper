<template>
    <div class="version">
        <div class="title">
            <div class="exit"><img src="../assets/image/左箭头.png" alt="" @click="goBack"></div>
            关于记账本
        </div>
        <!-- 记帐本logo -->
        <div class="logo1">
            <img src="../assets/image/logo1.png" alt="">
            <br>
            <br>
            <label for="">财务自由从记账本开始</label>
        </div>
        <!-- 记帐本版本号 -->
        <div class="item">
            <label for="">当前版本</label>
            <label for="" class="curVer">version 3.0.0</label>
        </div>
        <!-- 记账本隐私协议 -->
        <div class="item">
            <label for="">隐私协议</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
        </div>
        <!-- 记账本收集个人信息 -->
        <div class="item">
            <label for="">已收集个人信息明示清单</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
        </div>
        <!-- 记账本第三方信息 -->
        <div class="item">
            <label for="">第三方信息共享清单</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
        </div>
        <!-- 联系记账本客服 -->
        <div class="item">
            <label for="">联系客服</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
        </div>


    </div>
</template>
<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
        }
    },
    methods: {
        // 返回上一个页面
        goBack() {
            this.$router.go(-1)
        },
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

.logo1 label {
    color: #7E7E7E;
    font-size: 18px;
    margin-left: 0;
}

label {
    margin-left: 25px;
    font-size: 16px;
    color: #7E7E7E;
}

.curVer {
    float: right;
    margin-right: 25px;
}

.item {
    width: 390px;
    height: 50px;
    line-height: 50px;

}

.rightArrow {
    float: right;
    margin-top: 12px;
    margin-right: 20px;
    width: 30px;
    height: 30px;

}
</style>