<script setup lang="ts">
import 'vue-cropper/dist/index.css'
import { VueCropper } from 'vue-cropper'
import type { UploadProps } from 'element-plus'
import { realTimeProps } from '@/interface/components/vt-upload'
import { Icon } from '@iconify/vue'
// 资源链接
const fileUrl = import.meta.env.VITE_API_MINIO
const VModel = defineModel<string>()
/** 事件通知 */
const emit = defineEmits(['img-upload'])
defineProps({
  disabled: {
    type: Boolean,
    default: false,
  },
})
/** 图片上传 */
const imgUpload = reactive({
  // 是否展示裁剪
  dialogCropping: false,
  isCropping: false, // 判断是否已经截图
  // 图片
  imageUrl: '',
  // 图片格式
  imgBmp: 'image/*',
  // 图片名称
  imgName: '',
})
// 图片上传前校验
const beforUpload: UploadProps['beforeUpload'] = async (rawFile) => {
  // 判断是否需要进行裁剪
  imgUpload.isCropping = (await imgSize(rawFile)) as boolean
  // 图片名称
  imgUpload.imgName = rawFile.name
  // 进入裁剪
  imgCropping.imageUrl = URL.createObjectURL(rawFile)
  imgUpload.dialogCropping = true
  return false
}
// 上传
const updataImg = async (data: any) => {
  // 如果未截图,打开裁剪
  if (imgUpload.isCropping) {
    // 图片预览
    imgUpload.imageUrl = URL.createObjectURL(data.file)
    imgUpload.dialogCropping = false
    imgUpload.isCropping = false
    // 图片信息
    emit('img-upload', data.file)
  }
}

/** 图片裁剪 */
const imgCropping = reactive({
  imageUrl: '',
  // 裁剪生成图片的格式
  outputType: 'png',
  // 是否默认生成截图框
  autoCrop: true,
  // 上传图片按照原始比例渲染
  //   original: true,
  // 是否输出原图比例的截图
  full: false,
  // 默认生成截图框宽度
  autoCropWidth: 298,
  // 默认生成截图框高度
  autoCropHeight: 223.5,
  // 是否开启截图框宽高固定比例
  // fixed: true,
  // 截图框的宽高比例
  // fixedNumber: [1, 1],
  // 截图框是否被限制在图片里面
  centerBox: true,
})

// 生成裁剪图片
const handleCropping = async (roleRefs: any, type: boolean) => {
  if (type) {
    roleRefs.getCropBlob((data: any) => {
      // 判断裁剪图片大小
      // 图片预览
      let contentType = data.type // 获取blob的类型
      let file = new File([data], imgUpload.imgName, { type: contentType }) // 创建File对象
      let formData = new FormData()
      formData.append('file', file)
      httpPost('/uploadFile', formData, {
        'Content-Type': 'multipart/form-data',
      }).then(({ data, code }) => {
        if (code !== 200) return
        // imgUpload.imageUrl = data as string
        VModel.value = data as string
        imgUpload.dialogCropping = false
        // 图片信息
        emit('img-upload', data)
      })
    })
  } else {
    imgUpload.imageUrl = ''
    imgUpload.dialogCropping = false
  }
}

// 清除图片
const clearImg = () => {
  imgUpload.imageUrl = ''
  emit('img-upload')
}
// 实时预览
const previews = ref<realTimeProps>({})
function realTime(data: realTimeProps) {
  previews.value = data
}

onMounted(() => {
  // 图片地址传递
  imgUpload.imageUrl = VModel.value as string
})
</script>
<template>
  <div class="w-full h-full">
    <!-- 上传 -->
    <el-upload
      action=""
      :show-file-list="false"
      :accept="imgUpload.imgBmp"
      :http-request="updataImg"
      :before-upload="beforUpload"
      :disabled="disabled"
    >
      <div v-if="VModel" class="w-full h-full relative">
        <el-image class="w-full h-full" :src="fileUrl + VModel" />
        <!-- 删除 -->
        <div
          @click="clearImg"
          class="absolute top-0 right-0 w-8 h-8 rounded flex items-center justify-center bg-black bg-opacity-30 text-white text-base z-20 cursor-pointer"
        >
          <i class="iconfont icon-shanchu"></i>
        </div>
      </div>

      <div v-else class="uploader_cutsom_default">
        <div
          class="flex flex-col items-center justify-center p-4 bg-gray-100 rounded-lg border-2 border-dashed border-gray-300 dark:bg-gray-800 dark:border-gray-600"
        >
          <div class="flex flex-col items-center justify-center space-y-4">
            <Icon icon="clarity:upload-cloud-line" class="text-5xl" />
            <h3 class="font-semibold text-gray-700 dark:text-gray-300">
              Drag and drop files here
            </h3>
          </div>
        </div>
      </div>
    </el-upload>
    <!-- 裁剪 -->
    <el-dialog
      v-model="imgUpload.dialogCropping"
      custom-class="dialog_custom"
      title="裁剪图片"
      append-to-body
      :close-on-press-escape="false"
      :show-close="false"
      :close-on-click-modal="false"
    >
      <div class="flex justify-around w-full overflow-hidden">
        <div class="h-[298px] w-[298px]">
          <vueCropper
            ref="cropper"
            :img="imgCropping.imageUrl"
            :outputType="imgCropping.outputType"
            :autoCrop="imgCropping.autoCrop"
            :autoCropWidth="imgCropping.autoCropWidth"
            :autoCropHeight="imgCropping.autoCropHeight"
            :centerBox="imgCropping.centerBox"
            :full="imgCropping.full"
            @realTime="realTime"
          />
        </div>
        <div class="flex flex-col items-center justify-center gap-5">
          <h2>图片预览</h2>
          <div class="w-[298px] h-[223.5px] overflow-hidden">
            <el-image :style="previews.img" :src="previews.url" />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="handleCropping($refs.cropper, false)">
          取消
        </el-button>
        <el-button type="primary" @click="handleCropping($refs.cropper, true)">
          确定
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>
<style lang="scss" scoped>
img {
  max-width: none;
}
</style>
