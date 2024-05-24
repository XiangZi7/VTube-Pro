/* eslint-disable no-duplicate-case */
import { StatusCode } from "@/enums/httpEnum";
import router from "@/routers";

/**
 * 校验 data.code 并执行相应的操作
 * @param {number} code - code
 * @param {string} message - 展示消息
 */
export function checkStatusCode(code: number, message: string) {
    const userStore = useUserStore()
    switch (code) {
        case StatusCode.NOT_LOGIN:
            userStore.setUserInfo({});
            router.replace("/login");
            messagePro(code, message)
            break;
        case StatusCode.NOT_PERMISSION:
            messagePro(code, message)
            break;
        default:
            break;
    }

}