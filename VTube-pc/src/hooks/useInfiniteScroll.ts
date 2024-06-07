// 定义 hooks 的类型
interface UseInfiniteScrollOptions {
    target: Ref<HTMLElement | null>;  // 目标元素的引用
    callback: () => void;             // 当目标元素进入视口时调用的回调函数
}

export function useInfiniteScroll({ target, callback }: UseInfiniteScrollOptions): void {
    let observer: IntersectionObserver | null = null;

    // 定义 IntersectionObserver 的回调函数
    const intersectionCallback = (entries: IntersectionObserverEntry[]) => {
        if (entries[0].isIntersecting) {
            callback();
        }
    };

    // 在组件挂载时初始化 IntersectionObserver
    onMounted(() => {
        observer = new IntersectionObserver(intersectionCallback, {
            root: null,
            threshold: 1.0
        });

        if (target.value) {
            observer.observe(target.value);
        }
    });

    // 在组件卸载时停止观察目标元素
    onUnmounted(() => {
        if (observer && target.value) {
            observer.unobserve(target.value);
        }
    });
}