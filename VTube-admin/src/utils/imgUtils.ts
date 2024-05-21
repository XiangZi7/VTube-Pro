
/** 查询图片大小 */
export const imgSize = (file: any) => {
    return new Promise((resolve) => {
        const reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = () => {
            // 让页面中的img标签的src指向读取的路径
            const img = new Image()
            img.src = reader.result as string
            if (img.complete) {
                // 如果存在浏览器缓存中
                if (img.width / img.height !== 1) {
                    resolve(false)
                } else {
                    resolve(true)
                }
            } else {
                img.onload = () => {
                    if (img.width / img.height !== 1) {
                        resolve(false)
                    } else {
                        resolve(true)
                    }
                }
            }
        }
    })
}

/**
 * 文件格式转换
 * @description blobToFile
 * @param {Blob} blob
 * @param {String} fileName
 */
export const blobToFile = (blob: any, fileName: string) => {
    return new window.File([blob], fileName, {
        type: blob.type,
    })
}