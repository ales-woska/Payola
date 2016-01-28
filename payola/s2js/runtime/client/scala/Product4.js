s2js.runtime.client.core.get().classLoader.provide('scala.Product4');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.require('scala.IndexOutOfBoundsException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Product4 = function() {
var self = this;
scala.Product.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Product4, scala.Product);
scala.Product4.prototype.productArity = function() {
var self = this;
return 4;
};
scala.Product4.prototype.productElement = function(n) {
var self = this;
return (function($selector$1) {
if ($selector$1 === 0) {
return self._1;
}
if ($selector$1 === 1) {
return self._2;
}
if ($selector$1 === 2) {
return self._3;
}
if ($selector$1 === 3) {
return self._4;
}
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException(n.toString(), undefined);
})();
}
})(n);
};
scala.Product4.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Product4', [scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Product4, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.unapply = function(x) {
var self = this;
return new scala.Some(x);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Product4', []);
return obj;
}), true);
