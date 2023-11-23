import Login from '../views/Login.vue'
import Main from '../views/Main.vue'

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
    }
]

export default routes