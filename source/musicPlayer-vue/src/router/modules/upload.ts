import type { RouteRecordRaw } from 'vue-router';
import { Layout } from '@/router/constant';
const routeName = 'obj_Upload';

/**
 * @param name 路由名称, 必须设置,且不能重名
 * @param meta 路由元信息（路由附带扩展信息）
 * @param redirect 重定向地址, 访问这个路由时,自定进行重定向
 * */
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: routeName,
        component: Layout,
        redirect: '/upload',
        meta: {
            title: '测试',
        },
        children: [
            {
              path: 'upload',
              name: `upload`,
              meta: {
                title: '上传',
              },
              component: () => import('@/layout/Main/upload/upload.vue'),
            },
        ],
    },
];

export default routes;