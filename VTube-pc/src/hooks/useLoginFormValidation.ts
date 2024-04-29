interface Model {
  [key: string]: string
}

interface ValidatorRules {
  [key: string]: { required: boolean; message: string }[]
}

/**
 * 表单校验钩子，用于管理表单数据校验逻辑
 * @param initialModel 初始表单数据
 * @param validatorRules 表单校验规则
 * @returns 返回包含表单数据、错误信息和提交方法的对象
 */
export const useFormValidation = (
  initialModel: Model,
  validatorRules: ValidatorRules
) => {
  const model = ref<Model>(initialModel)

  const errors = ref<{ [key: string]: string }>(
    Object.keys(initialModel).reduce(
      (acc, key) => {
        acc[key] = ''
        return acc
      },
      {} as Record<string, string>
    )
  )

  /**
   * 校验表单数据并设置错误信息
   */
  const validateForm = () => {
    // eslint-disable-next-line guard-for-in,no-restricted-syntax
    for (const key in model.value) {
      errors.value[key] = ''
      const rules = validatorRules[key]
      // eslint-disable-next-line no-restricted-syntax
      for (const rule of rules) {
        if (rule.required && !model.value[key]) {
          errors.value[key] = rule.message
          break
        }
      }
    }
  }

  return {
    model,
    errors,
    validateForm,
  }
}
