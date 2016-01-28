s2js.runtime.client.core.get().classLoader.provide('scala.Product2');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.require('scala.IndexOutOfBoundsException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Product2 = function() {
var self = this;
scala.Product.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Product2, scala.Product);
scala.Product2.prototype.productArity = function() {
var self = this;
return 2;
};
scala.Product2.prototype.productElement = function(n) {
var self = this;
return (function($selector$1) {
if ($selector$1 === 0) {
return self._1;
}
if ($selector$1 === 1) {
return self._2;
}
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException(n.toString(), undefined);
})();
}
})(n);
};
scala.Product2.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Product2', [scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Product2, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.unapply = function(x) {
var self = this;
return new scala.Some(x);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Product2', []);
return obj;
}), true);
