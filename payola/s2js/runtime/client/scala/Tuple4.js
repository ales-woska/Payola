s2js.runtime.client.core.get().classLoader.provide('scala.Tuple4');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product4');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Tuple4 = function(_1, _2, _3, _4) {
var self = this;
self._1 = _1;
self._2 = _2;
self._3 = _3;
self._4 = _4;
scala.Product4.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Tuple4, scala.Product4);
s2js.runtime.client.core.get().mixInFunctions(scala.Tuple4.prototype, scala.Product.prototype);
scala.Tuple4.prototype.toString = function() {
var self = this;
return (((((((('(' + self._1) + ',') + self._2) + ',') + self._3) + ',') + self._4) + ')');
};
scala.Tuple4.prototype.copy = function(_1, _2, _3, _4) {
var self = this;
if (typeof(_1) === 'undefined') { _1 = self._1; }
if (typeof(_2) === 'undefined') { _2 = self._2; }
if (typeof(_3) === 'undefined') { _3 = self._3; }
if (typeof(_4) === 'undefined') { _4 = self._4; }
return new scala.Tuple4(_1, _2, _3, _4);
};
scala.Tuple4.prototype.productPrefix = function() {
var self = this;
return 'Tuple4';
};
scala.Tuple4.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Tuple4', [scala.Product4, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Tuple4, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Tuple4';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(new scala.Tuple4(x$0._1, x$0._2, x$0._3, x$0._4));
}
})();
};
obj.$apply = function(_1, _2, _3, _4) {
var self = this;
return new scala.Tuple4(_1, _2, _3, _4);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Tuple4', []);
return obj;
}), true);
