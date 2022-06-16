import type { RouteRecordRaw } from 'vue-router';
import { Layout } from '@/router/constant';
const routeName = 'Obj_Home';

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
    redirect: '/Home',
    meta: {
      title: '测试',
    },
    children: [
      {
        path: 'Home',
        name: `Home`,
        meta: {
          title: '首页',
        },
        component: () => import('@/layout/Main/Main.vue'),
      },
    ],
  },
];

export default routes;