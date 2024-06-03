<script setup>
import { ElMessage } from 'element-plus'

const state = reactive({
  list: [],
  isCurrent: 'login',
  showPopup: false,
  loginForm: {},
})
const { isCurrent, loginForm } = toRefs(state)

const modelValue = defineModel()
const userstore = useUserStore()

function ckBtn(params) {
  state.isCurrent = params
}

function isEmptyObject(obj) {
  return Object.keys(obj).length === 0
}

function handleSubmit(event, tpye) {
  event.preventDefault() // 阻止默认提交行为

  if (isEmptyObject(state.loginForm)) return ElMessage('请填写信息')
  httpPost(tpye == 'sign-up' ? '/SignIn' : '/login', state.loginForm).then(
    ({ data, code, message }) => {
      if (code == 500) return ElMessage.error(message)
      // 如果是注册直接提示，不添加数据
      if (tpye == 'sign-up') {
        state.isCurrent = 'login'
        return ElMessage.success(data)
      }
      // 添加数据
      userstore.setUserInfo(data)
      // 关闭登录窗口
      modelValue.value = false
    }
  )
}
</script>
<template>
  <div
    class="bg-black/5 fixed top-0 bottom-0 left-0 right-0 z-2000 w-full h-full flex items-center justify-center"
  >
    <div
      class="fixed left-0 top-0 z-[1998] bg-black/15 w-full h-full"
      :class="{ visible: modelValue }"
      @click="modelValue = false"
    ></div>
    <div class="relative z-[10000]">
      <div
        class="container"
        :class="{ 'right-panel-active': isCurrent !== 'login' }"
      >
        <div class="form-container sign-up-container">
          <form @submit="handleSubmit($event, 'sign-up')" class="z-99999">
            <h1 class="text-black">注册账号</h1>
            <input
              v-model="loginForm.userName"
              type="text"
              placeholder="userName"
            />
            <input v-model="loginForm.phone" placeholder="Phone" />
            <input
              v-model="loginForm.password"
              type="password"
              placeholder="Password"
            />
            <button type="submit">注册</button>
          </form>
        </div>
        <div class="form-container sign-in-container">
          <form @submit="handleSubmit($event, 'login')">
            <h1 class="font-bold text-black text-[40px]">登录</h1>
            <input v-model="loginForm.userName" placeholder="UserName" />
            <input
              v-model="loginForm.password"
              type="password"
              placeholder="Password"
            />
            <a class="text-[#333] text-sm my-[15px] no-underline" href="#"
              >忘记密码?</a
            >
            <button type="submit">登录</button>
          </form>
        </div>
        <div class="overlay-container">
          <div class="overlay">
            <div class="overlay-panel overlay-left space-y-4">
              <h1 class="font-bold">欢迎回来!</h1>
              <p class="text-sm">为了与我们保持联系，请使用您的个人信息登录</p>
              <button class="ghost" id="signIn" @click="ckBtn('login')">
                登录
              </button>
            </div>
            <div class="overlay-panel overlay-right space-y-4">
              <h1 class="font-bold">欢迎!</h1>
              <p class="text-sm">输入您的个人资料，与我们一起开始旅程</p>
              <button class="ghost" id="signUp" @click="ckBtn('Sign')">
                注册
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.popup-content {
  position: relative;
  z-index: 10000;
}

button {
  border-radius: 20px;
  border: 1px solid #ff4b2b;
  background-color: #ff4b2b;
  color: #ffffff;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  letter-spacing: 1px;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #ffffff;
}

form {
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
  text-align: center;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
  border-radius: 5px;
  font-size: 14px;
  color: #000;
}

.container {
  margin: 0 auto;
  background-color: #fff;
  border-radius: 10px;
  box-shadow:
    0 14px 28px rgba(0, 0, 0, 0.25),
    0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 768px;
  max-width: 100%;
  min-height: 600px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
  z-index: 999999999;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
  z-index: 100;
}

.container.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

.overlay {
  background: #ff416c;
  background: -webkit-linear-gradient(to right, #ff4b2b, #ff416c);
  background: linear-gradient(to right, #ff4b2b, #ff416c);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: #ffffff;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  text-align: center;
  top: 0;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.overlay-left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}

.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}

.social-container a {
  border: 1px solid #dddddd;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}
</style>
