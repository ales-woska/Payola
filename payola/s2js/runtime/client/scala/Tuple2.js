s2js.runtime.client.core.get().classLoader.provide('scala.Tuple2');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product2');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
scala.Tuple2 = function(_1, _2) {
var self = this;
self._1 = _1;
self._2 = _2;
scala.Product2.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Tuple2, scala.Product2);
s2js.runtime.client.core.get().mixInFunctions(scala.Tuple2.prototype, scala.Product.prototype);
scala.Tuple2.prototype.toString = function() {
var self = this;
return (((('(' + self._1) + ',') + self._2) + ')');
};
scala.Tuple2.prototype.copy = function(_1, _2) {
var self = this;
if (typeof(_1) === 'undefined') { _1 = self._1; }
if (typeof(_2) === 'undefined') { _2 = self._2; }
return new scala.Tuple2(_1, _2);
};
scala.Tuple2.prototype.productPrefix = function() {
var self = this;
return 'Tuple2';
};
scala.Tuple2.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Tuple2', [scala.Product2, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.Tuple2, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Tuple2';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(new scala.Tuple2(x$0._1, x$0._2));
}
})();
};
obj.$apply = function(_1, _2) {
var self = this;
return new scala.Tuple2(_1, _2);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Tuple2', []);
return obj;
}), true);
