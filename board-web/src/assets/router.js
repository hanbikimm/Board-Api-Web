import { createRouter, createWebHistory } from "vue-router";


export default createRouter({
    history: createWebHistory(),
    routes: [
        { 
            path: '/', 
            redirect: {name: 'currentStatus'} 
        },
        {
            path: '/status', name: 'currentStatus',
            component: () => import('@/components/statistic/CurrentStatus.vue')
        },
        {
            path: '/board', name: 'boardList', 
            component: () => import('@/components/board/BoardList.vue'),
        },
        {
            path: '/question/:id', name: 'questionDetail', 
            component: () => import('@/components/question/QuestionDetail.vue'),
        },
        {
            path: '/answer', name: 'answerDetail', 
            component: () => import('@/components/answer/AnswerDetail.vue'),
        },
        {
            path: '/:catchAll(.*)', name: 'notFound',
            component: () => import('@/components/NotFound.vue')
        },
    ],
});