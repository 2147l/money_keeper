import Login from '../views/Login.vue'
import Main from '../views/Main.vue'
import inputView from '../views/inputView.vue'
import outputView from '../views/outputView.vue'
import myInfor from "../views/myInfo.vue"
import accountSettings from "../views/accountSettings.vue"
import feedback from "../views/feedback.vue"
import lineChartInput from "../views/lineChartInput.vue"
import lineChartOutput from "../views/lineChartOutput.vue"
import secure from "../views/secure.vue"
import version from "../views/version.vue"
import bill from "../views/bill.vue"

const routes = [
    { path: '', redirect: '/login'},
    { path: '/account', component: accountSettings},
    { path: '/bill', component: bill},
    { path: '/feedback', component: feedback},
    { path: '/input', component: inputView },
    { path: '/lineChartInput', component: lineChartInput},
    { path: '/lineChartOutput', component: lineChartOutput},
    { path: '/login', component: Login },
    { path: '/main', component: Main },
    { path: '/myInfor', component: myInfor },
    { path: '/output', component: outputView },
    { path: '/secure', component: secure},
    { path: '/version', component: version},
]

export default routes