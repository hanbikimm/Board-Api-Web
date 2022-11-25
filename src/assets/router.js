import { createRouter, createWebHistory } from "vue-router";


export default createRouter({
    history: createWebHistory(),
    routes: [
        { 
            path: '/', 
            redirect: {name: 'boardList'} 
        },
        {
            path: '/board', name: 'boardList', 
            component: () => import('@/components/board/BoardList.vue') 
        },
        {
            path: '/status', name: 'currentStatus',
            component: () => import('@/components/statistic/CurrentStatus.vue')
        },
        {
            path: '/:catchAll(.*)', name: 'notFound',
            component: () => import('@/components/NotFound.vue')
        },
    ],
});