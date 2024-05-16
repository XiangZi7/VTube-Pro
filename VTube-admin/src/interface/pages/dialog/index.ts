export interface DialogState<T = any> {
  enumMap?: Map<string, { [key: string]: any }[]>;
  title: string
  model: T
  disabled: boolean
}
