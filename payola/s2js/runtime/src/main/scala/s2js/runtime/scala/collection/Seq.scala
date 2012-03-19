package s2js.runtime.scala.collection

import s2js.runtime.scala.util.control.Breaks._
import s2js.compiler.javascript

trait Seq extends Iterable
{
    @javascript("[]")
    var internalJsArray = null

    @javascript("""
        for (var i in self.internalJsArray) {
            f(self.internalJsArray[i]);
        }
    """)
    def foreach[U](f: Double => U) {}

    @javascript("self.internalJsArray.push(x);")
    def +=(x: Any) {}

    // From TraversableLike
    def reversed: Iterable = {
        val elems: Iterable = newInstance
        for (x <- this) {
            elems.prepend(x)
        }
        elems
    }

    @javascript("return self.internalJsArray.length;")
    override def size: Int = 0

    @javascript("""
        if (s2js.isUndefined(self.internalJsArray[n])) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        return self.internalJsArray[n];
    """)
    def apply(n: Int): Any = null

    @javascript("""
        if (self.size() <= n) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        self.internalJsArray[n] = newelem;
    """)
    def update(n: Int, newelem: Any) {}

    def length: Int = size

    @javascript("""
        if (index < 0 || self.size() <= index) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        var removed = self.internalJsArray[index];
        self.internalJsArray.splice(index, 1);
        return removed;
    """)
    def remove(index: Int) {}

    @javascript("""self.internalJsArray.splice(0, 0, x);""")
    def prepend(x: Any) {}

    @javascript("""
        var index = self.internalJsArray.indexOf(x);
        if (index != -1) {
            self.internalJsArray.splice(index, 1);
        }
    """)
    def -=(x: Double) {}

    // From SeqLike
    def indexWhere(p: Double => Boolean, from: Int = 0): Int = {
        var i = from
        breakable {() =>
            drop(from).foreach {x =>
                if (p(x)) {
                    break
                } else {
                    i += 1
                }
            }
            i = -1
        }
        i
    }
}


