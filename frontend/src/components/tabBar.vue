<template>
    <div class="bar">
        <div v-for="(value, index) in item" :key="index" class="item" @click="goto(value.url)">
            <img :src="value.img" alt="">
            <br>
            {{ value.name }}
        </div>
    </div>
</template>

<script>
export default {
    props: ['id'],
    data() {
        return {
            item: [
                {
                    img: "src/assets/image/明细.png",
                    name: "明细",
                    url: "/main",
                },
                {
                    img: "src/assets/image/图表.png",
                    name: "图表",
                    url: "/lineChartOutput",
                },
                {
                    img: "src/assets/image/记账.png",
                    name: "记账",
                    url: "/output",
                },
                {
                    img: "src/assets/image/账单.png",
                    name: "账单",
                    url: "/bill",
                },
                {
                    img: "src/assets/image/我的.png",
                    name: "我的",
                    url: "/myInfor",
                },
            ]
        }
    },
    methods: {
        goto(url) {
            // console.log(this.id)
            this.$router.push({path: url, query: {id: this.id}})
        }
    }

}
</script>

<style scoped>
.bar {
    display: flex;
    position: fixed;
    bottom: 0;
    height: 70px;
    width: 390px;
    align-items: flex-end;
    border-top: 1.5px solid #cccccc;
    background-color: white;
}
.item {
    width: 80px;
    height: auto;
    text-align: center;
    padding-bottom: 10px;
}
img {
    width: 40px;
    height: 40px;
}
.item:nth-child(3) {
    width: 80px;
    height: auto;
}
.item:nth-child(3) img {
    width: 80px;
    height: 80px;
}

</style>
