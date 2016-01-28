s2js.runtime.client.core.get().classLoader.provide('scala.Product3');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.require('scala.IndexOutOfBoundsException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Product3 = function() {
var self = this;
scala.Product.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Product3, scala.Product);
scala.Product3.prototype.productArity = function() {
var self = this;
return 3;
};
scala.Product3.prototype.productElement = function(n) {
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
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException(n.toString(), undefined);
})();
}
})(n);
};
scala.Product3.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Product3', [scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Product3, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.unapply = function(x) {
var self = this;
return new scala.Some(x);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Product3', []);
return obj;
}), true);
