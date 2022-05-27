import type { RouteRecordRaw } from 'vue-router';
import { Layout } from '@/router/constant';
const routeName = 'myMusic';

/**
 * @param name 路由名称, 必须设置,且不能重名
 * @param meta 路由元信息（路由附带扩展信息）
 * @param redirect 重定向地址, 访问这个路由时,自定进行重定向
 * */
const routes: Array<RouteRecordRaw> = [
  {
    path: '/myMusic',
    name: routeName, 
    component: Layout,
    redirect:'/Home',
    meta: {
      title: '测试',
    },
    children: [
      {
        path: 'list',
        name: `${routeName}_login`,
        meta: {
          title: '登录',
        },
        component: () => import('@/views/login/login.vue'),
      },
      {
        path: 'Home',
        name: `Home`,
        meta: {
          title: '首页',
        },
        component: () => import('@/layout/Main/Main.vue'),
      },
      {
        path: 'music',
        name: `music`,
        meta: {
          title: '音乐',
        },
        component: () => import('@/layout/Main/Music/music.vue'),
      },
      {
        path: 'singer',
        name: `singer`,
        meta: {
          title: '歌手',
        },
        component: () => import('@/layout/Main/singer/singer.vue'),
      },
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
