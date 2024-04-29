module.exports = {
  printWidth: 80, // 一行最多100字符
  tabWidth: 2, // 使用2个空格缩进
  useTabs: false, // 不适用缩进符，使用空格
  semi: false, // 行尾是否要有分号
  singleQuote: true, // 使用单引号
  quoteProps: 'as-needed', // 对象的key，仅仅在必要时使用引号
  jsxSingleQuote: false, // jsx是否使用双引号
  trailingComma: 'es5', // 尾随逗号
  bracketSpacing: true, // 大括号内的首尾需要有空格
  arrowParens: 'always', // 箭头函数，是有一个参数的时候，也需要小括号
  rangeStart: 0, // 文件格式化的范围是全部内容
  rangeEnd: Infinity,
  requirePragma: false, // 不需要写文件开头的 @prettier
  insertPragma: false, // 不需要在文件开头插入 @prettier
  proseWrap: 'always', // 使用默认执行标准
  htmlWhitespaceSensitivity: 'css', // 根据显示样式决定html是否执行
  endOfLine: 'lf', // 换行符是有lf
}
