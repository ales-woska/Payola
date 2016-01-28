s2js.runtime.client.core.get().classLoader.provide('scala.Product6');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.require('scala.IndexOutOfBoundsException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Product6 = function() {
var self = this;
scala.Product.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Product6, scala.Product);
scala.Product6.prototype.productArity = function() {
var self = this;
return 6;
};
scala.Product6.prototype.productElement = function(n) {
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
if ($selector$1 === 4) {
return self._5;
}
if ($selector$1 === 5) {
return self._6;
}
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException(n.toString(), undefined);
})();
}
})(n);
};
scala.Product6.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Product6', [scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Product6, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.unapply = function(x) {
var self = this;
return new scala.Some(x);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Product6', []);
return obj;
}), true);
