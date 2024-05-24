export class LinkedList<TElement> {
  private elements: TElement[] = []

  public push(element: TElement): void {
    this.elements.push(element)
  }

  public pop(): TElement | undefined {
    return this.elements.pop()
  }

  public shift(): TElement | undefined {
    return this.elements.shift()
  }

  public unshift(element: TElement): number {
    return this.elements.unshift(element)
  }

  public delete(element: TElement): void {
    const elementIndex: number = this.elements.indexOf(element)
    if (elementIndex >= 0)
      this.elements.splice(elementIndex, 1)
  }

  public count(): number {
    return this.elements.length
  }
}
