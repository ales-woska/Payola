s2js.runtime.client.core.get().classLoader.provide('scala.Tuple3');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product3');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Tuple3 = function(_1, _2, _3) {
var self = this;
self._1 = _1;
self._2 = _2;
self._3 = _3;
scala.Product3.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Tuple3, scala.Product3);
s2js.runtime.client.core.get().mixInFunctions(scala.Tuple3.prototype, scala.Product.prototype);
scala.Tuple3.prototype.toString = function() {
var self = this;
return (((((('(' + self._1) + ',') + self._2) + ',') + self._3) + ')');
};
scala.Tuple3.prototype.copy = function(_1, _2, _3) {
var self = this;
if (typeof(_1) === 'undefined') { _1 = self._1; }
if (typeof(_2) === 'undefined') { _2 = self._2; }
if (typeof(_3) === 'undefined') { _3 = self._3; }
return new scala.Tuple3(_1, _2, _3);
};
scala.Tuple3.prototype.productPrefix = function() {
var self = this;
return 'Tuple3';
};
scala.Tuple3.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Tuple3', [scala.Product3, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Tuple3, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Tuple3';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(new scala.Tuple3(x$0._1, x$0._2, x$0._3));
}
})();
};
obj.$apply = function(_1, _2, _3) {
var self = this;
return new scala.Tuple3(_1, _2, _3);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Tuple3', []);
return obj;
}), true);
