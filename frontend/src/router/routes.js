import Login from '../views/Login.vue'
import Main from '../views/Main.vue'
import inputView from '../views/inputView.vue'
import outputView from '../views/outputView.vue'

const routes = [
    {
        name: 'login',
        path: '/',
        component: Login
    },
    {
        name: 'main',
        path: '/main',
        component: Main
    },
    {
        name: 'output',
        path: '/output',
        component: outputView
    },
    {
        name: 'input',
        path: '/input',
        component: inputView
    }
]

export default routes