s2js.runtime.client.core.get().classLoader.provide('scala.Tuple1');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product1');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Tuple1 = function(_1) {
var self = this;
self._1 = _1;
scala.Product1.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Tuple1, scala.Product1);
s2js.runtime.client.core.get().mixInFunctions(scala.Tuple1.prototype, scala.Product.prototype);
scala.Tuple1.prototype.toString = function() {
var self = this;
return (('(' + self._1) + ')');
};
scala.Tuple1.prototype.copy = function(_1) {
var self = this;
if (typeof(_1) === 'undefined') { _1 = self._1; }
return new scala.Tuple1(_1);
};
scala.Tuple1.prototype.productPrefix = function() {
var self = this;
return 'Tuple1';
};
scala.Tuple1.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Tuple1', [scala.Product1, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Tuple1, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Tuple1';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(x$0._1);
}
})();
};
obj.$apply = function(_1) {
var self = this;
return new scala.Tuple1(_1);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Tuple1', []);
return obj;
}), true);
