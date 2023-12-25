<template>
    <div class="myInfo">
        <div class="banner" v-if="isShow">
            <img src="src/assets/image/左箭头.png" alt="" class="cancel" @click="goBack">
            <!-- 用户信息展示内容：这里主要是头像和昵称  -->
            <div class="info" @click="gotoAccount">
                <img :src=result.avatar alt="" class="avatorImg">
                <label for="" class="infoText">{{ result.username }}</label>
            </div>
            <!-- 打卡信息 -->
            <button>{{ message }}</button>
            <!-- 我的模块一些记账总览 -->
            <div>
                <ul>
                    <li class="first">
                        <label for="" class="text1">{{ clockInNum }}</label>
                        <br>
                        <label for="" class="text2">已连续打卡</label>
                    </li>
                    <li>
                        <label for="" class="text1">{{ accountDayNum }}</label>
                        <br>
                        <label for="" class="text2">记账总天数</label>
                    </li>
                    <li>
                        <label for="" class="text1">{{ accountNum }}</label>
                        <br>
                        <label for="" class="text2">记账总笔数</label>
                    </li>

                </ul>
            </div>
        </div>
        <!-- 主体内容 -->
        <div class="content">
            <!-- 每月预算栏目 -->
            <div class="item" @click="goto('/budget')">
                <img src="../assets/image/预算.png" alt="">
                <label for="">&nbsp;&nbsp;每月预算</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>
            <!-- 账户安全中心栏目 -->
            <div class="item" @click="goto('/secure')">
                <img src="../assets/image/安全.png" alt="">
                <label for="">&nbsp;&nbsp;账户安全中心</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>
            <!-- 意见反馈栏目 -->
            <div class="item" @click="goto('/feedback')">
                <img src="../assets/image/反馈.png" alt="">
                <label for="">&nbsp;&nbsp;意见反馈</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>
            <!-- 记账本版本栏目 -->
            <div class="item" @click="goto('version')">
                <img src="../assets/image/版本.png" alt="">
                <label for="">&nbsp;&nbsp;关于记账本</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            isShow: false,
            id: this.$route.query.id,
            message: "已打卡",
            clockInNum: 1,
            accountDayNum: 1,
            accountNum: 1,
            result: {
                id: null,
                phone: null,
                password: null,
                username: null,
                avatar: null,
                sex: null,
                wechat: null,
                email: null
            }
        }
    },
    methods: {
        // 返回上一个页面
        goBack() {
            this.$router.go(-1)
        },
        // 向后台获取用户数据
        getInfor() {
            this.$axios.get("http://localhost:8080/user/getById", { params: { userId: this.id} })
                .then(res => {
                    this.result = res.data
                    if (this.result.avatar == null)
                        this.result.avatar = "src/assets/image/我的.png"
                    this.isShow = true
                    // console.log(res.data)
                })
                .catch(err => {
                    console.log(err)
                })
        },
        // 跳转到其他页面
        goto(url) {
            this.$router.push({ path: url, query: { id: this.id } })
        },
        gotoAccount() {
            this.$router.push({ path: "/account", query: { id: this.id } })
        }
    },
    created() {
        this.getInfor()
    }
}
</script>
<style scoped>
.cancel {
    padding-top: 18px;
    padding-left: 15px;
}

.myInfo {
    width: 390px;
    height: 844px;
    background-color: #F6F6F6;
}

.banner {
    position: relative;
    width: 390px;
    height: 203px;
    background-color: #FFD946;
    border-radius: 0 0 15px 15px;
}

.info {
    position: absolute;
    margin-top: 10px;
    width: 390px;
    height: 70px;
    line-height: 70px;
}

.avatorImg {
    margin-left: 20px;
    width: 68px;
    height: 68px;
    border-radius: 34px;
}

.infoText {
    margin-left: 10px;
    font-size: 18px;
    font-weight: 700;
    color: #101010;
}

button {
    float: right;
    margin-top: 70px;
    margin-right: 20px;
    width: 66px;
    height: 30px;
    line-height: 26px;
    background-color: #fff;
    border-radius: 5px;
}

ul {
    position: absolute;
    margin-top: 85px;
    width: 390px;
    text-align: center;
}

ul li {
    float: left;
    text-align: center;
    margin-right: 30px;
}

ul .first {
    margin-left: 40px;
}

.text1 {
    font-size: 20px;
    font-weight: 700;
    color: #101010;
}

.text2 {
    font-size: 16px;
    color: #7E7E7E;
    font-weight: 600;
}

.content {
    padding-top: 10px;
}

.item {
    margin-left: 15px;
    padding-left: 12px;
    margin-top: 8px;
    width: 360px;
    height: 55px;
    background-color: #fff;
    line-height: 55px;
    border-radius: 5px;
}


.exit{
    left: 167px;
    position: absolute;
    font-size: 14px;
}

.rightArrow {
    float: right;
    margin-top: 15px;
    margin-right: 10px;
    width: 25px;
    height: 25px;

}
</style>