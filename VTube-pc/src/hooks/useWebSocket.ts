
// 定义配置选项的接口
interface WebSocketOptions {
    reconnectInterval?: number; // 重连间隔，单位：毫秒
    heartbeatInterval?: number; // 心跳间隔，单位：毫秒
}
interface message {
    content: string;
    position: 'left' | 'right'
}

export function useWebSocket(url: string, options: WebSocketOptions = {}) {
    const { reconnectInterval = 3000, heartbeatInterval = 5000 } = options;

    // 响应式引用
    const ws = ref<WebSocket | null>(null); // WebSocket 实例
    const isConnected = ref(false); // 连接状态
    const messages = ref<message[]>([]); // 接收到的消息
    const reconnectTimer = ref<NodeJS.Timeout | null>(null); // 重连定时器
    const heartbeatTimer = ref<NodeJS.Timeout | null>(null); // 心跳定时器
    const shouldReconnect = ref(true); // 是否自动重连的标志

    // 建立 WebSocket 连接的方法
    const connect = () => {
        ws.value = new WebSocket(url);

        ws.value.onopen = () => {
            isConnected.value = true;
            console.log('WebSocket 已连接');
            startHeartbeat(); // 开始心跳检测
        };

        ws.value.onmessage = (event) => {
            messages.value.push({ position: "left", content: event.data });
            console.log('收到 WebSocket 消息:', event.data);
        };

        ws.value.onclose = () => {
            isConnected.value = false;
            console.log('WebSocket 已断开');
            clearTimeout(heartbeatTimer.value!); // 清除心跳定时器
            if (!shouldReconnect.value) return
            attemptReconnect(); // 尝试重连
        };

        ws.value.onerror = (error) => {
            console.error('WebSocket 错误:', error);
        };
    };

    // 尝试重连的方法
    const attemptReconnect = () => {
        if (!isConnected.value) {
            reconnectTimer.value = setTimeout(() => {
                console.log('尝试重连...');
                connect();
            }, reconnectInterval);
        }
    };

    // 开始心跳检测的方法
    const startHeartbeat = () => {
        heartbeatTimer.value = setInterval(() => {
            if (ws.value && ws.value.readyState === WebSocket.OPEN) {
                ws.value.send('ping');
                console.log('发送心跳');
            }
        }, heartbeatInterval);
    };

    // 发送消息的方法
    const sendMessage = (message: string) => {
        if (ws.value && ws.value.readyState === WebSocket.OPEN) {
            ws.value.send(message);
        } else {
            console.error('WebSocket 未开启');
        }
    };

    // 添加文字数据
    const addMessage = (message: string) => {
        messages.value.push({ position: "right", content: message })
    }

    // 断开连接的方法
    const disconnect = () => {
        if (ws.value) {
            ws.value.close();
        }
        shouldReconnect.value = false; // 设置标志以停止重连
        clearTimeout(reconnectTimer.value!);
        clearInterval(heartbeatTimer.value!);
        isConnected.value = false;
    };

    // 生命周期钩子，在组件挂载时连接 WebSocket，卸载时关闭连接
    onMounted(connect);

    onUnmounted(() => {
        disconnect();
    });

    return {
        ws,
        isConnected,
        disconnect,
        messages,
        sendMessage,
        addMessage
    };
}