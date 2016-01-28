s2js.runtime.client.core.get().classLoader.provide('scala.Tuple8');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product8');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Tuple8 = function(_1, _2, _3, _4, _5, _6, _7, _8) {
var self = this;
self._1 = _1;
self._2 = _2;
self._3 = _3;
self._4 = _4;
self._5 = _5;
self._6 = _6;
self._7 = _7;
self._8 = _8;
scala.Product8.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Tuple8, scala.Product8);
s2js.runtime.client.core.get().mixInFunctions(scala.Tuple8.prototype, scala.Product.prototype);
scala.Tuple8.prototype.toString = function() {
var self = this;
return ((((((((((((((((('(' + self._1) + ',') + self._2) + ',') + self._3) + ',') + self._4) + ',') + self._5) + ',') + self._6) + ',') + self._7) + ',') + '') + self._8) + ')');
};
scala.Tuple8.prototype.copy = function(_1, _2, _3, _4, _5, _6, _7, _8) {
var self = this;
if (typeof(_1) === 'undefined') { _1 = self._1; }
if (typeof(_2) === 'undefined') { _2 = self._2; }
if (typeof(_3) === 'undefined') { _3 = self._3; }
if (typeof(_4) === 'undefined') { _4 = self._4; }
if (typeof(_5) === 'undefined') { _5 = self._5; }
if (typeof(_6) === 'undefined') { _6 = self._6; }
if (typeof(_7) === 'undefined') { _7 = self._7; }
if (typeof(_8) === 'undefined') { _8 = self._8; }
return new scala.Tuple8(_1, _2, _3, _4, _5, _6, _7, _8);
};
scala.Tuple8.prototype.productPrefix = function() {
var self = this;
return 'Tuple8';
};
scala.Tuple8.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Tuple8', [scala.Product8, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Tuple8, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Tuple8';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(new scala.Tuple8(x$0._1, x$0._2, x$0._3, x$0._4, x$0._5, x$0._6, x$0._7, x$0._8));
}
})();
};
obj.$apply = function(_1, _2, _3, _4, _5, _6, _7, _8) {
var self = this;
return new scala.Tuple8(_1, _2, _3, _4, _5, _6, _7, _8);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Tuple8', []);
return obj;
}), true);
