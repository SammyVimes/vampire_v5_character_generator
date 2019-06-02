fun <T> List<T>.fillWith(filler: T, fillSize: Int): List<T> {
    return this + (this.size..fillSize).map { filler }
}

