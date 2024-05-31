import { ElMessage } from "element-plus";

/**
 * @description 切换主题
 * */
export const useTheme = () => {
    const themeStore = useThemeStore()
    const { isDark, primary } = toRefs(useThemeStore())

    // 切换暗黑模式
    const switchDark = () => {
        const body = document.documentElement;
        isDark.value
            ? body.classList.add("dark")
            : body.classList.remove("dark");
    };

    // // 修改主题颜色
    const changePrimary = (val: string) => {
        if (!val) {
            ElMessage({
                type: "success",
                message: `主题颜色已重置为：${val}`,
            });
        }

        themeStore.setPrimary(val);
        // 颜色加深
        document.documentElement.style.setProperty(
            "--el-color-primary-dark-2",
            `${getDarkColor(primary.value, 0.1)}`,
        );
        document.documentElement.style.setProperty(
            "--el-color-primary",
            primary.value,
        );
        // 颜色变浅
        for (let i = 1; i <= 9; i++) {
            document.documentElement.style.setProperty(
                `--el-color-primary-light-${i}`,
                `${getLightColor(primary.value, i / 10)}`,
            );
        }
    };

    onBeforeMount(() => {
        switchDark();
        changePrimary(primary.value)
    });

    return {
        switchDark,
        changePrimary,
    };
};