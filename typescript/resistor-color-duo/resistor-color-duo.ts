export const COLORS = [
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

export const decodedValue = (colors: string[]) : number => (
  colors.slice(0, 2).reduce((value, color) => value * 10 + COLORS.indexOf(color), 0)
)
