export function timeAgo(dateStr: string): string {
    const now = new Date();
    const inputDate = new Date(dateStr.replace(/-/g, '/')); // IE 兼容性处理

    const diffInMs = now.getTime() - inputDate.getTime();
    const diffInSec = Math.floor(diffInMs / 1000);
    const diffInMin = Math.floor(diffInSec / 60);
    const diffInHours = Math.floor(diffInMin / 60);
    const diffInDays = Math.floor(diffInHours / 24);

    if (diffInSec < 60) {
        return '刚刚';
    } else if (diffInMin < 60) {
        return `${diffInMin}分钟前`;
    } else if (diffInHours < 24) {
        return `${diffInHours}小时前`;
    } else if (diffInDays < 7) {
        return `${diffInDays}天前`;
    } else {
        return inputDate.toLocaleDateString(); // 返回具体日期
    }
}