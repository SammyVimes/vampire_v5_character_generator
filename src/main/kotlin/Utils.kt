fun <T> List<T>.fillWith(filler: T, fillSize: Int): List<T> {
    return this + (this.size until fillSize).map { filler }
}

