import type { App } from 'vue';
import { createRouter, createWebHashHistory  } from 'vue-router';
import type { RouteRecordRaw  } from 'vue-router';
import { RedirectRoute } from '@/router/base';
import { createRouterGuards } from './router-guards';

const modules = import.meta.globEager('./modules/**/*.ts');

const routeModuleList: RouteRecordRaw[] = [];

Object.keys(modules).forEach((key) => {
  const mod = modules[key].default || {};
  const modList = Array.isArray(mod) ? [...mod] : [mod];
  routeModuleList.push(...modList);
});

function sortRoute(a:any, b:any) {
  return (a.meta?.sort || 0) - (b.meta?.sort || 0);
}

routeModuleList.sort(sortRoute);

export const LoginRoute: RouteRecordRaw = {
  path: '/login',
  name: 'Login',
  component: () => import('@/views/login/login.vue'),
  meta: {
    title: '登录',
  },
};

//普通路由 无需验证权限
export const constantRouter: any[] = [LoginRoute, RedirectRoute, ...routeModuleList];

console.log(constantRouter);

const router = createRouter({
  history: createWebHashHistory(''),
  routes: constantRouter,
  strict: true,
  scrollBehavior: () => ({ left: 0, top: 0 }),
});


export function setupRouter(app: App) {
  app.use(router);
  // 创建路由守卫
  createRouterGuards(router);
}

export default router;
