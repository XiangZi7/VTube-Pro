import { ElNotification } from "element-plus";

/**
 * @description 获取当前时间
 * @return string
 */
export function getTimeState() {
  // 获取当前时间
  const timeNow = new Date();
  // 获取当前小时
  const hours = timeNow.getHours();
  // 判断当前时间段
  if (hours >= 6 && hours <= 10) return `早上好 ⛅`;
  if (hours >= 10 && hours <= 14) return `中午好 🌞`;
  if (hours >= 14 && hours <= 18) return `下午好 🌞`;
  if (hours >= 18 && hours <= 24) return `晚上好 🌛`;
  if (hours >= 0 && hours <= 6) return `凌晨好 🌛`;
  return undefined; // 处理未匹配到任何条件的情况
}


/**
 * @description 消息提醒的封装
 * @param code 请求码
 * @param message 信息
 */
export const meassgePro = (code: number, data: string, message: string) => {
  ElNotification({
    type: code === 200 ? "success" : "error",
    message: data || message,
    title: getTimeState(),
  });
};

