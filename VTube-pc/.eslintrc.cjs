module.exports = {
  root: true,
  // 环境 浏览器，最新ES语法，node环境
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  /**
   * 扩展的eslint规范语法，可以被继承的规则，字符串数组，每个配置继承它之前的配置
   * 分别是eslint-config-vue 提供的
   * eslint-config-airbnb-base 提供的
   * eslint-config-prettier 提供的
   * eslint-config- 前缀可以简写
   */
  extends: [
    'eslint:recommended',
    './src/auto-import/eslintrc-auto-import.json',
    'plugin:@typescript-eslint/recommended',
    'plugin:vue/vue3-essential',
    'eslint-config-prettier',
    'prettier',
  ],
  // eslint 会对代码进行校验，parser是将代码转换为ESTree(AST),ESlint会对ESTree校验
  parser: 'vue-eslint-parser',
  // 解析器的配置项
  parserOptions: {
    // eslint的版本号，或者年份都可以
    ecmaVersion: 'latest',
    parser: '@typescript-eslint/parser',
    sourceType: 'module',
    // 额外的语言类型
    ecmaFeatures: {
      jsx: true,
      tsx: true,
    },
  },
  // 全局自定义宏，这样在源文件中使用全局变量不会报错或警告
  globals: {
    defineProps: 'readonly',
    defineEmits: 'readonly',
    defineExpose: 'readonly',
    withDefaults: 'readonly',
  },
  /**
   * 插件
   * eslint-plugin- 前缀可以简写
   * vue官方提供了一个eslint插件eslint-plugin-vue，它提供了parser和rules。
   * parser为vue-eslint-parser，放在前面的parser字段里，rules放在extends字段里
   */
  plugins: ['@typescript-eslint', 'vue', 'import', 'prettier'],
  settings: {
    // 设置项目内的别名
    'import/resolver': {
      alias: {
        map: [['@', './src']],
      },
    },
    'import/extensions': ['.js', '.jsx', '.tsx', '.ts', '.mjs', '.cjs'],
  },
  /**
   * rules: 自定义规则，覆盖extends继承的规则，对规则进行灵活配置
   *
   * "off" 或 0    ==>  关闭规则
   * "warn" 或 1   ==>  打开的规则作为警告（不影响代码执行）
   * "error" 或 2  ==>  规则作为一个错误（代码不能执行，界面报错）
   */
  rules: {
    // eslint（https://eslint.bootcss.com/docs/rules/）
    'no-var': 'error', // 要求使用 let 或 const 而不是 var
    'no-multiple-empty-lines': ['warn', { max: 2 }], // 不允许多个空行
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-unexpected-multiline': 'error', // 禁止空余的多行
    'no-useless-escape': 'off', // 禁止不必要的转义字符
    'import/no-unresolved': 'off',
    'import/extensions': 'off',
    'import/no-absolute-path': 'off',
    'import/no-extraneous-dependencies': 'off',
    'import/prefer-default-export': 'off',

    // typeScript (https://typescript-eslint.io/rules)
    '@typescript-eslint/no-unused-vars': 'error', // 禁止定义未使用的变量
    '@typescript-eslint/prefer-ts-expect-error': 'error', // 禁止使用 @ts-ignore
    '@typescript-eslint/no-explicit-any': 'off', // 禁止使用 any 类型
    '@typescript-eslint/no-non-null-assertion': 'off',
    '@typescript-eslint/no-namespace': 'off', // 禁止使用自定义 TypeScript 模块和命名空间。
    '@typescript-eslint/semi': 'off',

    // eslint-plugin-vue (https://eslint.vuejs.org/rules/)
    'vue/multi-word-component-names': 'off', // 要求组件名称始终为 “-” 链接的单词
    'vue/script-setup-uses-vars': 'error', // 防止<script setup>使用的变量<template>被标记为未使用
    'vue/no-mutating-props': 'off', // 不允许组件 prop的改变
    'vue/attribute-hyphenation': 'off', // 对模板中的自定义组件强制执行属性命名样式
  },
}
