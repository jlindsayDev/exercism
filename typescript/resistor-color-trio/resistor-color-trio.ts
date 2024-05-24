export const COLORS: string[] = [
  'black',
  'brown',
  'red',
  'orange',
  'yellow',
  'green',
  'blue',
  'violet',
  'grey',
  'white',
]

export const decodedResistorValue = (colors: string[]) : string => {
  let reduceFn = (value: number, color: string) => value * 10 + COLORS.indexOf(color)
  let sum: number = colors.slice(0, 2).reduce(reduceFn, 0)
  sum *= 10 ** COLORS.indexOf(colors[2])

  if (sum > 10**9) return `${sum / 10**9} gigaohms`
  if (sum > 10**6) return `${sum / 10**6} megaohms`
  if (sum > 10**3) return `${sum / 10**3} kiloohms`
  return `${sum} ohms`
}
